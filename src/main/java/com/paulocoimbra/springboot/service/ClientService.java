package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.Client;
import com.paulocoimbra.springboot.exception.ObjectNotFoundException;
import com.paulocoimbra.springboot.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    public Client findById(Integer id) {
        Optional<Client> client = repo.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Client.class.getName()));
    }
}
