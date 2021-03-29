/**
 * 
 */
package br.com.ecommerce.inventoryservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.inventoryservice.model.Inventory;

/**
 * @author evaristosrodrigues
 *
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	Optional<Inventory> findBySkuCode(String skuCode);

}
