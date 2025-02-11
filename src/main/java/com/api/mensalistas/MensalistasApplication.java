package com.api.mensalistas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MensalistasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MensalistasApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "Mensalistas!";
	}
}
