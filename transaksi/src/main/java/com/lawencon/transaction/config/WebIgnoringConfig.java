package com.lawencon.transaction.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class WebIgnoringConfig {
	
	@Bean(name = "webIgnoring")
	public List<RequestMatcher> antMatchers() {
		return new ArrayList<RequestMatcher>();
	}
}
