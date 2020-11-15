package com.cosa.ascosa.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class cosaController {

	@GetMapping("/start")
	public String startTest() {
		return "<h1>!!!!!!!!!!!!!!!!!Hello Mundo!!!!!!!!!!!!</h1>\r\n"
				+"<br>"+
				"<h2> !!!!!!!!!!!Estoy funcionando!!!!!!!!! </h2>";
	}
	
}
