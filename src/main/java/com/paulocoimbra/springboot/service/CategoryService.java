package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.Category;
import com.paulocoimbra.springboot.exception.ObjectNotFoundException;
import com.paulocoimbra.springboot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category findById(Integer id) {
        Optional<Category> category = repo.findById(id);
        return category.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Category.class.getName()));
    }

    public Category insert(Category obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Category update(Category category) {
        findById(category.getId());
        return repo.save(category);
    }
}
