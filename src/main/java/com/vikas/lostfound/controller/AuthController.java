package com.vikas.lostfound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikas.lostfound.dto.LoginDto;
import com.vikas.lostfound.dto.RegisterDto;
import com.vikas.lostfound.dto.ResponseDto;
import com.vikas.lostfound.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	  private AuthService as; 
	
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@Valid @RequestBody RegisterDto req)throws Exception{ 
   ResponseDto rdt= 	as.register(req);
    	return ResponseEntity.status(HttpStatus.CREATED).body(rdt);
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto ldto) throws Exception{
			as.login(ldto);
			return ResponseEntity.status(HttpStatus.OK).body("Welcome "+ldto.getUsername()+" successfully logged in Smart Lost & Found System.");
    }
}
