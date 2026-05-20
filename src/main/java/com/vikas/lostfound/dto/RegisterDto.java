package com.vikas.lostfound.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterDto {
	
	@Column(nullable = false)
    private String username;
 
    @Column(nullable = false)
    private String email;

 @Column(nullable = false)
    private String password;
}
