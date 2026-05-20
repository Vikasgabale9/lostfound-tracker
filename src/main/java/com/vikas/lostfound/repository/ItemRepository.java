package com.vikas.lostfound.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vikas.lostfound.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	public List<Item> findByLocation(String location);
	public  Page<Item> findByLocation(String location, Pageable pageable);


}
