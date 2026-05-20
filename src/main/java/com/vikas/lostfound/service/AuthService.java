package com.vikas.lostfound.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vikas.lostfound.dto.LoginDto;
import com.vikas.lostfound.dto.RegisterDto;
import com.vikas.lostfound.dto.ResponseDto;
import com.vikas.lostfound.entity.AppUser;
import com.vikas.lostfound.enums.Role;
import com.vikas.lostfound.exception.UsernameAlreadyExistsException;
import com.vikas.lostfound.repository.AppUserRepository;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserRepository userRepository;

    public ResponseDto register(RegisterDto req)
            throws UsernameAlreadyExistsException {

        if (userRepository.existsByUsername(req.getUsername())) {
            throw new UsernameAlreadyExistsException(
                    "Username already exists"
            );
        }

        AppUser user = userRepository.save(
                mapToAppUserEntity(req)
        );

        return mapToResponseDto(user);
    }

    public boolean login(LoginDto ldto)
            throws UsernameNotFoundException {

        AppUser user = userRepository
                .findByUsername(ldto.getUsername())
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Username not found"
                        )
                );

        boolean isPasswordMatched =
                passwordEncoder.matches(
                        ldto.getPassword(),
                        user.getPassword()
                );

        if (!isPasswordMatched) {
            throw new RuntimeException("Invalid password");
        }

        return true;
    }

    private AppUser mapToAppUserEntity(RegisterDto req) {

        AppUser user = new AppUser();

        user.setEmail(req.getEmail());

        user.setPassword(
                passwordEncoder.encode(req.getPassword())
        );

        user.setUsername(req.getUsername());

        user.setRole(Role.ROLE_USER);

        return user;
    }

    private ResponseDto mapToResponseDto(AppUser user) {

        ResponseDto rd = new ResponseDto();

        rd.setEmail(user.getEmail());
        rd.setId(user.getId());
        rd.setUsername(user.getUsername());
        rd.setRole(user.getRole());

        return rd;
    }
}