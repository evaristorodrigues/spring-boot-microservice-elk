package br.com.ecommerce.notificationservice;

import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}
	
	@Bean
	//Usando Message devido ao trace distribuido
	public Consumer<Message<String>> notificationEventSupplier(){
		return message -> new EmailSender().sendEmail(message.getPayload());
	}
}
