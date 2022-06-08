package com.lawencon.master.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.lawencon.master.resttemplate.RestTemplateExceptionHandler;
import com.lawencon.master.resttemplate.RestTemplateInterceptor;

@Configuration
public class ObjectConfig {

	@Bean(name = "restTemplate")
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new RestTemplateInterceptor());		
		restTemplate.setErrorHandler(new RestTemplateExceptionHandler());
		return restTemplate;
	}
	
	@Bean(name = "restTemplateAuthorization")
	@LoadBalanced
	public RestTemplate restTemplateAuthorization() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateExceptionHandler());
		return restTemplate;
	}
	
}
