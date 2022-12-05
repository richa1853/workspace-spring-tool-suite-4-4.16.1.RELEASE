package com.richa;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class AppConfig {

	private long addbook;

	
	public long getAddbook() {
		return addbook;
	}

	public void setAddbook(long addbook) {
		this.addbook = addbook;
	}
	
}
