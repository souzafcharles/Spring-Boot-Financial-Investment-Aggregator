package com.github.souzafcharles.investmentaggregator.model.entity.repository;

import com.github.souzafcharles.investmentaggregator.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}