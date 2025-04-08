package com.github.souzafcharles.investmentaggregator.controller;

import com.github.souzafcharles.investmentaggregator.model.entity.User;
import com.github.souzafcharles.investmentaggregator.model.entity.dto.CreateUserDTO;
import com.github.souzafcharles.investmentaggregator.model.entity.dto.UpdateUserDTO;
import com.github.souzafcharles.investmentaggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO dto) {
        var userId = service.createUser(dto);
        return ResponseEntity.created(URI.create("/v1/user/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        var user = service.getUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        var users = service.listUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId,
                                               @RequestBody UpdateUserDTO dto) {
        service.updateUserById(userId, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) {
        service.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}