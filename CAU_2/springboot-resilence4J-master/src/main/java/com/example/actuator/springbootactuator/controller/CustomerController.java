package com.example.actuator.springbootactuator.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class CustomerController {

	private static final String ORDER_SERVICE = "orderService";
	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
//	retry
	private int attempts = 1;
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@GetMapping("/customer")
//    @CircuitBreaker(name=ORDER_SERVICE, fallbackMethod = "orderFallback")
//    public ResponseEntity<String> createOrder(){
//        String response = restTemplate.getForObject("http://localhost:8083/customer", String.class);
//        return new ResponseEntity<String>(response, HttpStatus.OK);
//    }
//    public ResponseEntity<String> orderFallback(Exception e){
//        return new ResponseEntity<String>("Item service is down", HttpStatus.OK);
//    }

//    Retry

	@Retry(name = ORDER_SERVICE, fallbackMethod = "fallback_retry")
	public ResponseEntity<String> createOrder() {
		logger.info("item service call attempted:::" + attempts++);
		String response = restTemplate.getForObject("http://localhost:8083/customer", String.class);
		logger.info("item service called");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	public ResponseEntity<String> fallback_retry(Exception e) {
		attempts = 1;
		return new ResponseEntity<String>("Item service is down", HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
//	ratelimit
//	@RateLimiter(name=ORDER_SERVICE, fallbackMethod = "rateLimiterFallback")
//    public ResponseEntity<String> createOrder()
//    {
//        String response = restTemplate.getForObject("http://localhost:8083/customer", String.class);
//        logger.info(LocalTime.now() + " Call processing finished = " + Thread.currentThread().getName());
//        return new ResponseEntity<String>(response, HttpStatus.OK);
//    }
//
//    public ResponseEntity<String> rateLimiterFallback(Exception e){
//        return new ResponseEntity<String>("order service does not permit further calls", HttpStatus.TOO_MANY_REQUESTS);
//
//    }
}
