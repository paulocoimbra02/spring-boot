package com.paulocoimbra.springboot.dto;

import com.paulocoimbra.springboot.domain.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = -8375035684904223618L;
    private Integer id;
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category){
        id = category.getId();
        name = category.getName();
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
