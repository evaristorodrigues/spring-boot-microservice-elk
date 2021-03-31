package br.com.ecommerce.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.orderservice.model.Order;

/**
 * 
 */

/**
 * @author evaristosrodrigues
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}
