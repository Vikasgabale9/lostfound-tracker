package com.vikas.lostfound.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vikas.lostfound.dto.ItemDTO;
import com.vikas.lostfound.exception.ResourceNotFoundException;
import com.vikas.lostfound.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService service;

    @PostMapping
    public ItemDTO create(@Valid @RequestBody ItemDTO dto) {
        return service.addItem(dto);
    }

    
    @PostMapping("/bulk")
    public List<ItemDTO> createAll( @RequestBody List<@Valid ItemDTO> dto) {
        return service.addItem(dto);
    }


    @GetMapping
    public Page<ItemDTO> getAll(
    		@RequestParam(defaultValue="0") int page
    	   ,@RequestParam(defaultValue="5") int size
    ) {
        return service.getAllItems(page,size);
    }

    
    @PutMapping("/{id}")
    public ItemDTO update(@PathVariable Long id, @RequestBody ItemDTO dto) throws ResourceNotFoundException {
        return service.updateItem(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws ResourceNotFoundException {
        service.deleteItem(id);
        return "Deleted successfully";
    }

    @GetMapping("/search")
    public List<ItemDTO> search(@RequestParam String location) throws ResourceNotFoundException {
        return service.searchByLocation(location);
    }

    @PutMapping("/{id}/claim")
    public ItemDTO claim(@PathVariable Long id) throws ResourceNotFoundException {
        return service.markAsClaimed(id);
    }
}
