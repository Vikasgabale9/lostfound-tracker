
package com.vikas.lostfound.dto;

import com.vikas.lostfound.enums.ItemType;

import com.vikas.lostfound.enums.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemDTO {

    private Long id;

    @NotBlank
    private String name;

    private String description;
    private String location;
    private ItemType type;
    private Status status;
    private String contactInfo;
    
    
}