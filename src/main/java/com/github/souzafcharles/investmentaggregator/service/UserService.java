package com.github.souzafcharles.investmentaggregator.service;

import com.github.souzafcharles.investmentaggregator.model.entity.User;
import com.github.souzafcharles.investmentaggregator.model.entity.dto.CreateUserDTO;
import com.github.souzafcharles.investmentaggregator.model.entity.dto.UpdateUserDTO;
import com.github.souzafcharles.investmentaggregator.model.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UUID createUser(CreateUserDTO dto) {
        var entity = new User(null, dto.username(), dto.email(), dto.password(), Instant.now(), null);
        var userSaved = repository.save(entity);
        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {
        return repository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers() {
        return repository.findAll();
    }

    public void updateUserById(String userId, UpdateUserDTO dto) {
        var id = UUID.fromString(userId);
        var userEntity = repository.findById(id);
        if (userEntity.isPresent()) {
            var user = userEntity.get();
            if (dto.username() != null) {
                user.setUsername(dto.username());
            }
            if (dto.password() != null) {
                user.setPassword(dto.password());
            }
            repository.save(user);
        }
    }

    public void deleteById(String userId) {
        var id = UUID.fromString(userId);
        var userExists = repository.existsById(id);
        if (userExists) {
            repository.deleteById(id);
        }
    }
}