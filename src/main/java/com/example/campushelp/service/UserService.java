package com.example.campushelp.service;

import com.example.campushelp.domain.User;
import com.example.campushelp.repository.UserRepository;
import com.example.campushelp.api.dto.CreateUserRequest;
import com.example.campushelp.api.dto.UserResponse;
import com.example.campushelp.api.exception.BadRequestException;
import com.example.campushelp.api.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserResponse create(CreateUserRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
//        user.setPasswordHash("{noop}" + req.getPassword());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));

        User saved = userRepository.save(user);

        return new UserResponse(
                saved.getId(),
                saved.getEmail(),
                saved.getFullName(),
                saved.isActive(),
                saved.getCreatedAt()
        );
    }

    public User getEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
