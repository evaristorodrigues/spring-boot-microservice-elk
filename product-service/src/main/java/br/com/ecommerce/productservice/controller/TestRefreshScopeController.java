/**
 * 
 */
package br.com.ecommerce.productservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author evaristosrodrigues
 *
 */
@RestController
@RequestMapping("/api/test")
@RefreshScope
public class TestRefreshScopeController {
	
	@Value("${test.name}")
	private String name;
	
	@GetMapping
	public String test() {
		return name;
	}

}
