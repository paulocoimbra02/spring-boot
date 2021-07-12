package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.City;
import com.paulocoimbra.springboot.domain.State;
import com.paulocoimbra.springboot.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository repo;

    @Autowired
    private StateService stateService;

    public Page<City> findPageByState(Integer stateId, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        State state = stateService.findById(stateId);

        return repo.findByState(state, pageRequest);
    }

}
