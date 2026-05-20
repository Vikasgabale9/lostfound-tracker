package com.vikas.lostfound.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vikas.lostfound.dto.ItemDTO;
import com.vikas.lostfound.entity.Item;
import com.vikas.lostfound.enums.Status;
import com.vikas.lostfound.exception.ResourceNotFoundException;
import com.vikas.lostfound.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // CREATE
    public ItemDTO addItem(ItemDTO dto) {

        Item item = mapToItemEntity(dto);

        item.setStatus(Status.OPEN);
        item.setCreatedAt(LocalDateTime.now());

        return mapToItemDTO(
                itemRepository.save(item)
        );
    }

    // BULK CREATE
    public List<ItemDTO> addItem(List<ItemDTO> dtos) {

        List<Item> items = dtos.stream()
                .map(this::mapToItemEntity)
                .toList();

        items.forEach(i -> i.setStatus(Status.OPEN));

        items = itemRepository.saveAll(items);

        return items.stream()
                .map(this::mapToItemDTO)
                .toList();
    }

    // READ
    public List<ItemDTO> getAllItems() {

        List<Item> items = itemRepository.findAll();

        // Sorting
        items.sort((a, b) ->
                b.getId().compareTo(a.getId())
        );

        return items.stream()
                .map(this::mapToItemDTO)
                .toList();
    }

    // PAGINATION
    public Page<ItemDTO> getAllItems(
            int page,
            int size
    ) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by("id").descending()
                );

        Page<Item> items =
                itemRepository.findAll(pageable);

        return items.map(this::mapToItemDTO);
    }

    // UPDATE
    public ItemDTO updateItem(
            Long id,
            ItemDTO dto
    ) throws ResourceNotFoundException {

        Item item = itemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Item not found"
                        )
                );

        item.setItemName(dto.getItemName());
        item.setDescription(dto.getDescription());
        item.setLocation(dto.getLocation());
        item.setType(dto.getType());
        item.setStatus(dto.getStatus());
        item.setContactInfo(dto.getContactInfo());

        return mapToItemDTO(
                itemRepository.save(item)
        );
    }

    // DELETE
    public void deleteItem(Long id) throws ResourceNotFoundException {

        if (!itemRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Item not found"
            );
        }

        itemRepository.deleteById(id);
    }

    // SEARCH
    public List<ItemDTO> searchByLocation(
            String location
    ) throws ResourceNotFoundException {

        List<Item> items =
                itemRepository.findByLocation(location);

        if (items.isEmpty()) {

            throw new ResourceNotFoundException(
                    "Item not found on " + location
            );
        }

        return items.stream()
                .map(this::mapToItemDTO)
                .toList();
    }

    // CLAIM ITEM
    public ItemDTO markAsClaimed(Long id) throws ResourceNotFoundException {

        Item item = itemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Item not found"
                        )
                );

        item.setStatus(Status.CLAIMED);

        return mapToItemDTO(
                itemRepository.save(item)
        );
    }

    // ENTITY MAPPING
    private Item mapToItemEntity(ItemDTO dto) {

        Item item = new Item();

        item.setItemName(dto.getItemName());
        item.setDescription(dto.getDescription());
        item.setLocation(dto.getLocation());
        item.setType(dto.getType());
        item.setContactInfo(dto.getContactInfo());

        return item;
    }

    // DTO MAPPING
    private ItemDTO mapToItemDTO(Item item) {

        ItemDTO dto = new ItemDTO();

        dto.setId(item.getId());
        dto.setItemName(item.getItemName());
        dto.setDescription(item.getDescription());
        dto.setLocation(item.getLocation());
        dto.setType(item.getType());
        dto.setStatus(item.getStatus());
        dto.setContactInfo(item.getContactInfo());

        return dto;
    }
}