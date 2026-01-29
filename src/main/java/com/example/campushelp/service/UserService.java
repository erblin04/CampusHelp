package com.example.campushelp.service;

import com.example.campushelp.domain.User;
import com.example.campushelp.repository.UserRepository;
import com.example.campushelp.web.dto.CreateUserRequest;
import com.example.campushelp.web.dto.UserResponse;
import com.example.campushelp.web.exception.BadRequestException;
import com.example.campushelp.web.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(CreateUserRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
        user.setPasswordHash("{noop}" + req.getPassword());

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
