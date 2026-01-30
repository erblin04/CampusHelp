package com.example.campushelp.api;

import com.example.campushelp.api.dto.UpdateUserRequest;
import com.example.campushelp.service.UserService;
import com.example.campushelp.api.dto.CreateUserRequest;
import com.example.campushelp.api.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody CreateUserRequest req) {
        return userService.create(req);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return userService.getById(id);}

        @GetMapping
        public List<UserResponse> getAll() {
            return userService.getAll();
        }

        @PutMapping("/{id}")
        public UserResponse update(@PathVariable Long id,
                @Valid @RequestBody UpdateUserRequest req) {
            return userService.update(id, req);
        }

//        @DeleteMapping("/{id}")
//        @ResponseStatus(HttpStatus.NO_CONTENT)
//        public void delete(@PathVariable Long id) {
//            userService.delete(id);
//        }
@PatchMapping("/{id}/deactivate")
public UserResponse deactivate(@PathVariable Long id) {
    return userService.deactivate(id);
}


}
