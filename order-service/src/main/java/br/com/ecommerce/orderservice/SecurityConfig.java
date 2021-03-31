/**
 * 
 */
package br.com.ecommerce.orderservice;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author evaristosrodrigues
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	//Compartilhar o Token entre as threads
	@PostConstruct
	public void enableAuthenticationContextOnSpawnedThreads() {
     SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	}

}
