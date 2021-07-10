package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.Address;
import com.paulocoimbra.springboot.domain.City;
import com.paulocoimbra.springboot.domain.Client;
import com.paulocoimbra.springboot.domain.enums.ClientType;
import com.paulocoimbra.springboot.domain.enums.Profile;
import com.paulocoimbra.springboot.dto.ClientDTO;
import com.paulocoimbra.springboot.dto.ClientNewDTO;
import com.paulocoimbra.springboot.repository.AddressRepository;
import com.paulocoimbra.springboot.repository.ClientRepository;
import com.paulocoimbra.springboot.security.UserSS;
import com.paulocoimbra.springboot.service.exception.AuthorizationException;
import com.paulocoimbra.springboot.service.exception.DataIntegrityException;
import com.paulocoimbra.springboot.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private S3Service s3Service;

    @Transactional
    public Client insert(Client obj) {
        obj.setId(null);
        obj = repo.save(obj);
        addressRepository.saveAll(obj.getAddresses());
        return obj;
    }

    public Client findById(Integer id) {

        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Access Denied");
        }

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
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Not possible to delete a client with orders");
        }
    }

    public List<Client> findAll() {
        return repo.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO obj) {
        return new Client(obj.getId(), obj.getName(), obj.getEmail(), null, null, null);
    }

    public Client fromDTO(ClientNewDTO obj) {
        Client cli = new Client(null, obj.getName(), obj.getEmail(), obj.getCpfOrCnpj(),
                ClientType.toEnum(obj.getClientType()), pe.encode(obj.getPassword()));
        City city = new City(obj.getCityId(), null, null);
        Address address = new Address(null, obj.getStreet(), obj.getNumber(), obj.getZipCode(), cli, city);
        cli.getAddresses().add(address);
        cli.getPhoneNumbers().add(obj.getPhone1());
        if (obj.getPhone2() != null) {
            cli.getPhoneNumbers().add(obj.getPhone2());
        }
        if (obj.getPhone3() != null) {
            cli.getPhoneNumbers().add(obj.getPhone3());
        }
        return cli;
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public URI uploadProfilePicture(MultipartFile multipartFile) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Access denied");
        }
        URI uri = s3Service.uploadFile(multipartFile);

        Optional<Client> client = repo.findById(user.getId());
        if (client.isPresent()) {
            client.get().setImageUrl(uri.toString());
            repo.save(client.get());
        }
        return uri;
    }
}
