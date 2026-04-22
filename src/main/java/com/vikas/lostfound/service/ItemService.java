package com.vikas.lostfound.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikas.lostfound.dto.ItemDTO;
import com.vikas.lostfound.entity.Item;
import com.vikas.lostfound.enums.Status;
import com.vikas.lostfound.exception.ResourceNotFoundException;
import com.vikas.lostfound.repository.ItemRepository;



@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    // CREATE
    public ItemDTO addItem(ItemDTO dto) {
        Item item = mapToEntity(dto);
        item.setStatus(Status.OPEN);
        item.setCreatedAt(LocalDateTime.now());
        return mapToDTO(repository.save(item));
    }

    // READ
    public List<ItemDTO> getAllItems() {
        List<Item> items = repository.findAll();

        // Sorting (DSA)
        items.sort((a, b) -> b.getId().compareTo(a.getId()));

        return items.stream().map(this::mapToDTO).toList();
    }

    // UPDATE
    public ItemDTO updateItem(Long id, ItemDTO dto) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setLocation(dto.getLocation());
        item.setType(dto.getType());
        item.setStatus(dto.getStatus());

        return mapToDTO(repository.save(item));
    }

    // DELETE
    public void deleteItem(Long id) {
        repository.deleteById(id);
    }

    // SEARCH (DSA - HashMap)
    public List<ItemDTO> searchByLocation(String location) {
        Map<String, List<Item>> map = new HashMap<>();

        for (Item item : repository.findAll()) {
            map.computeIfAbsent(item.getLocation(), k -> new ArrayList<>()).add(item);
        }

        return map.getOrDefault(location, new ArrayList<>())
                .stream().map(this::mapToDTO).toList();
    }

    // CLAIM API
    public ItemDTO markAsClaimed(Long id) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        item.setStatus(Status.CLAIMED);
        return mapToDTO(repository.save(item));
    }

    // Mapping
    private Item mapToEntity(ItemDTO dto) {
        Item item = new Item();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setLocation(dto.getLocation());
        item.setType(dto.getType());
        item.setContactInfo(dto.getContactInfo());
        return item;
    }

    private ItemDTO mapToDTO(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setLocation(item.getLocation());
        dto.setType(item.getType());
        dto.setStatus(item.getStatus());
        dto.setContactInfo(item.getContactInfo());
        return dto;
    }
}