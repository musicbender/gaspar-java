package com.patjacobs.gaspar;

import com.patjacobs.gaspar.config.CustomPropertiesConfig;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.patjacobs.gaspar.config",
		"com.patjacobs.gaspar.handler",
		"com.patjacobs.gaspar.service",
		"com.patjacobs.gaspar.repository",
		"com.patjacobs.gaspar.router"
})
@Import({
		CustomPropertiesConfig.class
})
public class GasparApplication {

	@Bean()
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		return initializer;
	}

	public static void main(String[] args) {
		SpringApplication.run(GasparApplication.class, args);
	}
}
