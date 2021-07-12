package com.paulocoimbra.springboot.resources;

import com.paulocoimbra.springboot.domain.City;
import com.paulocoimbra.springboot.dto.CityDTO;
import com.paulocoimbra.springboot.dto.StateDTO;
import com.paulocoimbra.springboot.service.CityService;
import com.paulocoimbra.springboot.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/states")
public class StateResource {

    @Autowired
    private StateService service;

    @Autowired
    private CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StateDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll().stream().map(StateDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/{id}/cities", method = RequestMethod.GET)
    public ResponseEntity<Page<CityDTO>> find(
            @PathVariable(value = "id") Integer id,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        Page<City> list = cityService.findPageByState(id, page, linesPerPage, orderBy, direction);
        Page<CityDTO> listDto = list.map(CityDTO::new);
        return ResponseEntity.ok().body(listDto);
    }

}
