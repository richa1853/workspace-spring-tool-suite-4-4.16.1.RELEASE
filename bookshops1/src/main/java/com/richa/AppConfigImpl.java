package com.richa;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class AppConfigImpl implements AppConfig {

	private long addbook;

	@Override
	public long getAddbook() {
		return addbook;
	}

	@Override
	public void setAddbook(long addbook) {
		this.addbook = addbook;
	}
	
}
