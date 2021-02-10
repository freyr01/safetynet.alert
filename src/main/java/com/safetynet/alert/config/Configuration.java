package com.safetynet.alert.config;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
	
	@Bean
	public HttpTraceRepository httpTraceRepository()
	{
	  return new InMemoryHttpTraceRepository();
	}

}
