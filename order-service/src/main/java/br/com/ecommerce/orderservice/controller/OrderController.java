/**
 * 
 */
package br.com.ecommerce.orderservice.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.orderservice.client.InventoryClient;
import br.com.ecommerce.orderservice.dto.OrderDTO;
import br.com.ecommerce.orderservice.model.Order;
import br.com.ecommerce.orderservice.model.OrderLineItems;
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
	
	@PostMapping
	public String placeOrder(@RequestBody OrderDTO orderDTO) {
		boolean allProductsInStock = orderDTO.getOrderLineItems().stream()
				.allMatch(orderLineItems -> inventoryClient.checkStock(orderLineItems.getSkuCode()));
		
		if(allProductsInStock) {
			Order order = new Order();
			order.setOrderLineItems(orderDTO.getOrderLineItems());
			order.setOrderNumber(UUID.randomUUID().toString());
			
			orderRepository.save(order);			
			return "Order Place Successfully" + order.getOrderNumber();
		}else {
			return "Order failes, one of the products in the order is not in stock"; 
		}

		
		
		
	}

}
