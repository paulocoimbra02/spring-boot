package com.paulocoimbra.springboot.dto;

import com.paulocoimbra.springboot.domain.State;

import java.io.Serializable;

public class StateDTO implements Serializable {

    private static final long serialVersionUID = 603775349504009178L;

    private Integer id;
    private String name;

    public StateDTO() {
    }

    public StateDTO(State obj) {
        id = obj.getId();
        name = obj.getName();
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

}
