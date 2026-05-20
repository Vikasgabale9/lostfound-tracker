package com.vikas.lostfound.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LoginDto {

	@Column(nullable = false)
    private String username;
 
    
 @Column(nullable = false)
    private String password;

}
