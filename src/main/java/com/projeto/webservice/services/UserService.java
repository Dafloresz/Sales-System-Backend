package com.projeto.webservice.services;

import com.projeto.webservice.dto.UserResponseDTO;
import com.projeto.webservice.entities.User;
import com.projeto.webservice.repositories.UserRepository;
import com.projeto.webservice.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserResponseDTO> findAll() {
       List<User> users = repository.findAll();
       return listUserToListUserDTO(users);
    }

    public UserResponseDTO findByID(Long id) {
        User userById = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
        return userToUserDTO(userById);
    }

    public User insert(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Usuário não encontrado!");
        } else repository.deleteById(id);
    }


    public void update(Long id, User data) {
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, data);
            repository.save(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User data) {
        entity.setName(data.getName());
        entity.setEmail(data.getEmail());
        entity.setPhone(data.getPhone());
    }

    private UserResponseDTO userToUserDTO(User user){
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone());
    }

    private List<UserResponseDTO> listUserToListUserDTO(List<User> usersList) {
        return usersList.stream().map(this::userToUserDTO).collect(Collectors.toList());
    }
}
