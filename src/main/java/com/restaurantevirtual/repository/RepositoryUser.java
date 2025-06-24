package com.restaurantevirtual.repository;

import com.restaurantevirtual.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositoryUser extends JpaRepository<User,UUID> {

    Optional<User> findByEmail(String email);
}
