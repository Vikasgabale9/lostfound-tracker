package com.vikas.lostfound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vikas.lostfound.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{

	boolean existsByUsername(String username);
	Optional<AppUser> findByUsername(String username);
}
