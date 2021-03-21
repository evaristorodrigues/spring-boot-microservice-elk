/**
 * 
 */
package br.com.ecommerce.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.ecommerce.productservice.model.Product;

/**
 * @author evaristosrodrigues
 *
 */
public interface ProductRepository extends MongoRepository<Product, String> {

}
