/**
 * 
 */
package br.com.ecommerce.orderservice.controller;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.orderservice.client.InventoryClient;
import br.com.ecommerce.orderservice.dto.OrderDTO;
import br.com.ecommerce.orderservice.model.Order;
import br.com.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author evaristosrodrigues
 *
 */

@RestController
@Slf4j
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderRepository orderRepository;
	private final InventoryClient  inventoryClient;
	private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
	private final StreamBridge streamBridge;
	private final ExecutorService traceableExecutorService;
	
	@PostMapping
	public String placeOrder(@RequestBody OrderDTO orderDTO) {
		
		//Para propagar trace distribuido
		circuitBreakerFactory.configureExecutorService(traceableExecutorService);
		Resilience4JCircuitBreaker circuitBreaker = circuitBreakerFactory.create("inventory");
		
		Supplier<Boolean>  booleanSupplier= () -> orderDTO.getOrderLineItems().stream()
				.allMatch(orderLineItems -> {
					log.info("Making call to inventory service for skuCode {}",orderLineItems.getSkuCode());
					return inventoryClient.checkStock(orderLineItems.getSkuCode());
					}
					);
		
		Boolean allProductsInStock = circuitBreaker.run(booleanSupplier, throwable -> handleErrorCase());
		
		if(allProductsInStock) {
			Order order = new Order();
			order.setOrderLineItems(orderDTO.getOrderLineItems());
			order.setOrderNumber(UUID.randomUUID().toString());
			
			orderRepository.save(order);	
			
			log.info("Sending Order Details to Notification Service");
			streamBridge.send("notificationEventSupplier-out-0", MessageBuilder.withPayload(order.getId()).build());
			return "Order Place Successfully" + order.getId();
		}else {
			return "Order failes, one of the products in the order is not in stock"; 
		}		
		
		
	}

	private Boolean handleErrorCase() {
		return false;
	}

}
