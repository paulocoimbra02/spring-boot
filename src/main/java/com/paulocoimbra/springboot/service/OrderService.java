package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.Order1;
import com.paulocoimbra.springboot.exception.ObjectNotFoundException;
import com.paulocoimbra.springboot.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public Order1 findById(Integer id) {
        Optional<Order1> order = repo.findById(id);
        return order.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Order1.class.getName()));
    }
}
