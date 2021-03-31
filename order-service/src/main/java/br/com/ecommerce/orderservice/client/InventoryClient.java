package br.com.ecommerce.orderservice.client;
/**
 * 
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author evaristosrodrigues
 *
 */

@FeignClient(name="inventory-service")
public interface InventoryClient {

	@GetMapping("/api/inventory/{skuCode}")
	Boolean checkStock(@PathVariable String skuCode);
}
