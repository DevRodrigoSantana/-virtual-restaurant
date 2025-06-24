package com.restaurantevirtual.service;

import com.restaurantevirtual.exception.EmailUniqueViolationException;
import com.restaurantevirtual.exception.EntityNotFoundException;
import com.restaurantevirtual.exception.PasswordInvalidException;
import com.restaurantevirtual.model.entity.User;
import com.restaurantevirtual.repository.RepositoryUser;

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


    @Transactional
    public User create(User usuario) {

        if(repositoryUser.findByEmail(usuario.getEmail()).isPresent()) {
            throw  new EmailUniqueViolationException(String.format("O e-mail '%s' já está cadastrado.",usuario.getEmail()));
        }

        return repositoryUser.save(usuario);

    }

    public User findUserId(UUID id) {
        return  repositoryUser.findById(id).orElseThrow(
                () ->new EntityNotFoundException(String.format("Usuário id= %s não encontrado", id)));
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

        User user = findUserId(id);

        if (!passedPassword.equals(user.getPassword())) {
            throw new PasswordInvalidException("Senha atual incorreta");
        }

        if (!currentPassword.equals(confirmPassword)) {
            throw new PasswordInvalidException("Nova senha não confere com a confirmação");
        }

        user.setPassword(currentPassword);
    }
}