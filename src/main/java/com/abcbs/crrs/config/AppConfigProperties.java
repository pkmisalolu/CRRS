package com.abcbs.crrs.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties("service.p09125")
public class AppConfigProperties {
		
	Map<String, String> messages;

}

