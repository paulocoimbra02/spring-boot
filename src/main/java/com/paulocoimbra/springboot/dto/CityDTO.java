package com.paulocoimbra.springboot.dto;

import com.paulocoimbra.springboot.domain.City;
import com.paulocoimbra.springboot.domain.State;

import java.io.Serializable;

public class CityDTO implements Serializable {

    private static final long serialVersionUID = 603775349504009178L;

    private Integer id;
    private String name;
    private State state;

    public CityDTO() {
    }

    public CityDTO(City obj) {
        id = obj.getId();
        name = obj.getName();
        state = obj.getState();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
