package com.darkenergy.EncryptIt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EncryptItApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptItApplication.class, args);
	}
	@GetMapping("/home")
	public ResponseEntity<String> temp(){
		return new ResponseEntity<>("Test Success", HttpStatus.OK);
	}

}

