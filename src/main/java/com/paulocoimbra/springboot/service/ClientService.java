package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.Client;
import com.paulocoimbra.springboot.dto.ClientDTO;
import com.paulocoimbra.springboot.service.exception.DataIntegrityException;
import com.paulocoimbra.springboot.service.exception.ObjectNotFoundException;
import com.paulocoimbra.springboot.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Client update(Client obj) {
        Client newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Not possible to delete a client with nested entities");
        }
    }

    public List<Client> findAll() {
        return repo.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO obj){
        return new Client(obj.getId(), obj.getName(), obj.getEmail(), null, null);
    }

    private void updateData(Client newObj, Client obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
