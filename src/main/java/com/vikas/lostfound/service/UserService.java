package com.vikas.lostfound.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vikas.lostfound.dto.RegisterDto;
import com.vikas.lostfound.dto.ResponseDto;
import com.vikas.lostfound.entity.AppUser;
import com.vikas.lostfound.repository.AppUserRepository;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository ur;

    public UserService(
            PasswordEncoder passwordEncoder,
            AppUserRepository ur) {

        this.passwordEncoder = passwordEncoder;
        this.ur = ur;
    }

    // GET ALL USERS
    public List<ResponseDto> getAllUsers() {

        List<AppUser> users = ur.findAll();

        return users.stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    // GET USER BY ID
    public ResponseDto getUserById(Long id)
            throws UsernameNotFoundException {

        AppUser au = ur.findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User Id Not Found : " + id
                        )
                );

        return mapToResponseDto(au);
    }

    // UPDATE USER
    public String updateUser(Long id, RegisterDto dto)
            throws UsernameNotFoundException {

        AppUser au = ur.findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User Id Not Found : " + id
                        )
                );

        au.setEmail(dto.getEmail());

        au.setUsername(dto.getUsername());

        au.setPassword(
                passwordEncoder.encode(
                        dto.getPassword()
                )
        );

        ur.save(au);

        return "User Updated Successfully";
    }

    // DELETE USER
    public String deleteUser(Long id)
            throws UsernameNotFoundException {

        AppUser au = ur.findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User Id Not Found : " + id
                        )
                );

        ur.delete(au);

        return "User Deleted Successfully";
    }

    // ENTITY TO DTO MAPPING
    private ResponseDto mapToResponseDto(AppUser user) {

        ResponseDto rd = new ResponseDto();

        rd.setId(user.getId());

        rd.setUsername(user.getUsername());

        rd.setEmail(user.getEmail());

        rd.setRole(user.getRole());

        return rd;
    }
}