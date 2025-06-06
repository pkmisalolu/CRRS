package com.abcbs.crrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcbs.crrs.bindings.P09125Binding;
import com.abcbs.crrs.bindings.P09125Output;
import com.abcbs.crrs.bindings.P09165Binding;
import com.abcbs.crrs.projections.IControlFileView;
import com.abcbs.crrs.service.IP09125Service;
import com.abcbs.crrs.service.IP09165Service;

import com.abcbs.crrs.bindings.P09160Binding;
import com.abcbs.crrs.service.IP09160Service;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/crrs")
@CrossOrigin(origins = "http://localhost:4200")
public class CRRSController {
	
	@Autowired
	private IP09125Service service;

	@Autowired
	private IP09165Service p09165service;
	

	@Autowired
	private IP09160Service p09160service;

	@PostMapping(value = "/query", consumes = "application/json")
	public ResponseEntity<?> registerUser(@Valid @RequestBody P09125Binding bind, BindingResult result) {
		if (result.hasErrors()) {
			//Send back validation errors
			List<String> errors = result.getAllErrors()
					.stream()
					.map(ObjectError::getDefaultMessage)
					.toList();

			return ResponseEntity.badRequest().body(errors);

		}else {
			return new ResponseEntity<P09125Output>(service.generateP09125Output(bind), HttpStatus.OK);
		}
	}

	@PostMapping(value = "/P09165", consumes = "application/json")
	public ResponseEntity<?> registerUser(@Valid @RequestBody P09165Binding bind, BindingResult result) {
		if (result.hasErrors()) {
			// Send back validation errors
			
			List<String> errors = result.getAllErrors()
					.stream()
					.map(ObjectError::getDefaultMessage)
					.toList();

			return ResponseEntity.badRequest().body(errors);

		}else {
			
			if(bind.getRefundAction().equals("Q")) {
				return new ResponseEntity<IControlFileView>(p09165service.fetch(bind), HttpStatus.OK);
			}else{
				return new ResponseEntity<Integer>(p09165service.updateWithRefundType(bind), HttpStatus.OK);
			}
		}
	}

	
	@PostMapping(value = "/HelpScreen", consumes = "application/json")
	public ResponseEntity<?> registerUser(@Valid @RequestBody P09160Binding bind, BindingResult result) {
		if (result.hasErrors()) {
			// Send back validation errors
			List<String> errors = result.getAllErrors()
					.stream()
					.map(ObjectError::getDefaultMessage)
					.toList();

			return ResponseEntity.badRequest().body(errors);

		}else {
			return new ResponseEntity<List>(p09160service.help(bind), HttpStatus.OK);
		}
	}

}
