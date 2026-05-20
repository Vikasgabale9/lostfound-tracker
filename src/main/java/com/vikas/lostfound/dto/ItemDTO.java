package com.vikas.lostfound.dto;

import com.vikas.lostfound.entity.ContactInfo;
import com.vikas.lostfound.enums.ItemType;
import com.vikas.lostfound.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ItemDTO {

    private Long id;

    @NotBlank(message = "Item name is required")
    private String itemName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Item type is required")
    private ItemType type;

    @NotNull(message = "Status is required")
    private Status status;

    @NotBlank(message = "Contact info is required")
    private ContactInfo contactInfo;
}