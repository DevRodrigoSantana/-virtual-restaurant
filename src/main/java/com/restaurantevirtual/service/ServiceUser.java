package com.restaurantevirtual.service;

import com.restaurantevirtual.model.entity.User;
import com.restaurantevirtual.repository.RepositoryUser;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceUser {

    private final RepositoryUser repositoryUser;


    public ServiceUser(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    public User create(User usuario) {

            return repositoryUser.save(usuario);
    }

    public User findUserId(UUID id) {
        return  repositoryUser.findById(id).orElseThrow(() ->new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id)));
    }

    public List<User> searchAllUsers() {
        return repositoryUser.findAll();
    }

    public void deleteUser(UUID id) {

        User user = findUserId(id);

        repositoryUser.delete(user);

    }
    @Transactional
    public void UpdatePassword(String passedPassword, String currentPassword, String confirmPassword, UUID id) {

        if(!currentPassword.equals(confirmPassword)){
            throw  new IllegalArgumentException("Senha nova não confere com a senha de confirmação ");

        }
        User user = findUserId(id);

        if(!passedPassword.equals(user.getPassword())){
            throw  new IllegalArgumentException("Senha incorreta");
        }
        user.setPassword(currentPassword);

    }
}