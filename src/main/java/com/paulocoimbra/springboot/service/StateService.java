package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.State;
import com.paulocoimbra.springboot.repository.StateRepository;
import com.paulocoimbra.springboot.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository repo;

    public List<State> findAll() {
        List<State> states = repo.findAll();
        return states;
    }

    public State findById(Integer id) {
        Optional<State> state = repo.findById(id);
        return state.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + State.class.getName()));
    }

}
