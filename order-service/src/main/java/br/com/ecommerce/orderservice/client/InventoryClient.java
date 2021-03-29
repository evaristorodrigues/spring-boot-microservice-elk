package br.com.ecommerce.orderservice.client;
/**
 * 
 */

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author evaristosrodrigues
 *
 */

@FeignClient(name="inventory-service")
public interface InventoryClient {

	@GetMapping("/api/invetory/{skuCode}")
	Boolean checkStock(@PathVariable String skuCode);
}
