package com.vikas.lostfound.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<ItemDTO> getAll() {
        return service.getAllItems();
    }

    @PutMapping("/{id}")
    public ItemDTO update(@PathVariable Long id, @RequestBody ItemDTO dto) {
        return service.updateItem(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteItem(id);
        return "Deleted successfully";
    }

    @GetMapping("/search")
    public List<ItemDTO> search(@RequestParam String location) {
        return service.searchByLocation(location);
    }

    @PutMapping("/{id}/claim")
    public ItemDTO claim(@PathVariable Long id) {
        return service.markAsClaimed(id);
    }
}
