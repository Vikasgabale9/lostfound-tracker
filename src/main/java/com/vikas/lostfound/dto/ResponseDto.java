package com.vikas.lostfound.dto;

import com.vikas.lostfound.enums.Role;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ResponseDto {
	
	       private Long id;

		 @Column(nullable = false)
		    private String username;
		 
		 @Column(nullable = false)
		    private String email;
		 
		 @Column(nullable = false)
		    private Role role;
		 
}
