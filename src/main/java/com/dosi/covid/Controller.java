package com.dosi.covid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/")
	public String index() {
		return "Hello MOuad";
	}
	
	@GetMapping("/mouad")
	public String sayHello() {
		return "MOuadALoulou";
	}

}