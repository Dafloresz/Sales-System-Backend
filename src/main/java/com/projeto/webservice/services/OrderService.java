package com.projeto.webservice.services;

import com.projeto.webservice.entities.Order;
import com.projeto.webservice.repositories.OrderRepository;
import com.projeto.webservice.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findByiId(Long id) {
        Optional<Order> orderById = orderRepository.findById(id);
        return orderById.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
