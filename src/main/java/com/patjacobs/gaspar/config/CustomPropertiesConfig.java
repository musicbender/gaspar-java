package com.patjacobs.gaspar.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "gaspar")
public class CustomPropertiesConfig {

	@Getter
	@Setter
	private Integer apiVersion;
}
