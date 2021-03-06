package com.metamagic.order.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.metamagic.order.dto.ResponseBean;
import com.metamagic.order.entities.Order;
import com.metamagic.order.service.OrderService;

import reactor.core.publisher.Mono;

@CrossOrigin()
@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService service;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> findall() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String tokenId = request.getHeader("tokenid");
		if(tokenId == null){
			tokenId = "";
		}
		
		Mono<ResponseBean> response = service.findByUserId(tokenId)
					.collectList()
					.map(details -> new ResponseBean(true, "Record retrieved successfully", HttpStatus.OK + "", details));

		return new ResponseEntity<Mono<ResponseBean>>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> findById(@PathVariable(value ="id") String id) {
		
		Mono<ResponseBean> response = service.findById(id)
					.map(details -> new ResponseBean(true, "Record retrieved successfully", HttpStatus.OK + "", details));

		return new ResponseEntity<Mono<ResponseBean>>(response, HttpStatus.OK);
	}

	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mono<ResponseBean>> save(@RequestBody Order order) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		ResponseBean response = new ResponseBean(false, "Record not saved successfully", HttpStatus.OK + "", null);
		
		String tokenId = request.getHeader("tokenid");
		if(tokenId != null){
			order.setUserId(tokenId);
			service.save(order);
			response.setData(order);
			response.setMessage("Record Saved successfully");
			response.setSucess(true);
		}
		
		
		return new ResponseEntity<Mono<ResponseBean>>(Mono.justOrEmpty(response), HttpStatus.OK);
		
	}
}
