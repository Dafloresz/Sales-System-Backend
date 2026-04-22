package com.projeto.webservice.services;

import com.projeto.webservice.entities.Product;
import com.projeto.webservice.repositories.ProductRepository;
import com.projeto.webservice.services.exceptions.DatabaseException;
import com.projeto.webservice.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findById(Long id) {
        Optional<Product> productById = productRepository.findById(id);
        return productById.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product insert(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
       if (!productRepository.existsById(id)){
           throw new ResourceNotFoundException("Produto não encontrado!");

       } else productRepository.deleteById(id);
    }

    public Product update(Product data, Long id){
        try {
            Product entity = productRepository.getReferenceById(id);
            updateData(entity, data);
            return productRepository.save(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Product entity, Product data){
        entity.setName(data.getName());
        entity.setDescription(data.getDescription());
        entity.setPrice(data.getPrice());
        entity.setImgUrl(data.getImgUrl());
    }
}
