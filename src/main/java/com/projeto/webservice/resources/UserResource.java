package com.projeto.webservice.resources;

import com.projeto.webservice.dto.UserResponseDTO;
import com.projeto.webservice.entities.User;
import com.projeto.webservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> ListDTOs = service.findAll();
        return ResponseEntity.ok().body(ListDTOs);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<UserResponseDTO> findById (@PathVariable Long id) {
        UserResponseDTO dto = service.findByID(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<User> insert (@RequestBody User user){
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update (@PathVariable Long id, @RequestBody User user){
       service.update(id, user);
        return ResponseEntity.noContent().build();
    }
}