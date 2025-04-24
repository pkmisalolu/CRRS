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
import com.abcbs.crrs.service.IP09125Service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/crrs")
@CrossOrigin(origins = "http://localhost:4200")
public class CRRSController {
	
	@Autowired
	private IP09125Service service;
	
	@PostMapping(value = "/query", consumes = "application/json")
	public ResponseEntity<?> registerUser(@Valid @RequestBody P09125Binding bind, BindingResult result) {
		if (result.hasErrors()) {
			// Send back validation errors
			List<String> errors = result.getAllErrors()
					.stream()
					.map(ObjectError::getDefaultMessage)
					.toList();

			return ResponseEntity.badRequest().body(errors);

		}else {
			return new ResponseEntity<P09125Output>(service.generateP09125Output(bind), HttpStatus.OK);
		}
	}
}
