package com.projeto.webservice.services;

import com.projeto.webservice.dto.OrderStatusDTO;
import com.projeto.webservice.entities.Order;
import com.projeto.webservice.entities.OrderItem;
import com.projeto.webservice.entities.Product;
import com.projeto.webservice.entities.User;
import com.projeto.webservice.entities.enums.OrderStatus;
import com.projeto.webservice.repositories.OrderItemRepository;
import com.projeto.webservice.repositories.OrderRepository;
import com.projeto.webservice.repositories.ProductRepository;
import com.projeto.webservice.repositories.UserRepository;
import com.projeto.webservice.services.exceptions.DatabaseException;
import com.projeto.webservice.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findByiId(Long id) {
        Optional<Order> orderById = orderRepository.findById(id);
        return orderById.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Order insert(Order order) {
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
        order.setMoment(Instant.now());

        User user = userRepository.findById(order.getClient().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado!"));
        order.setClient(user);

        order = orderRepository.save(order);

        for (OrderItem itens : order.getItem()) {
            Product product = productRepository.findById(itens.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não econtrado!"));

            itens.setPrice(product.getPrice());
            orderItemRepository.save(itens);
        }

        return order;
    }

    public void delete(Long id) {
        if (!orderRepository.existsById(id)){
            throw new ResourceNotFoundException("Pedido não encontrado!");
        } else orderRepository.deleteById(id);
    }

    public Order update(Long id, OrderStatusDTO statusDTO){
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado!"));
        order.setOrderStatus(statusDTO.getOrderStatus());
        return orderRepository.save(order);
    }
}
