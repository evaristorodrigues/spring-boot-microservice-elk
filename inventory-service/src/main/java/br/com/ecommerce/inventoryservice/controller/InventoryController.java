/**
 * 
 */
package br.com.ecommerce.inventoryservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.inventoryservice.model.Inventory;
import br.com.ecommerce.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;

/**
 * @author evaristosrodrigues
 *
 */
@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
	
	private final InventoryRepository InventoryRepository;
	
	@GetMapping("/{skuCode}")
	Boolean isInStock(@PathVariable String skuCode) {
		Inventory inventory = InventoryRepository.findBySkuCode(skuCode)
				.orElseThrow(() -> new RuntimeException("Cannot Find Product by sku code "  + skuCode));
		return inventory.getStock() > 0;
		
	}

}
