/**
 * 
 */
package br.com.ecommerce.orderservice.dto;

import java.util.List;

import br.com.ecommerce.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author evaristosrodrigues
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
	
	private List<OrderLineItems> orderLineItems;

}
