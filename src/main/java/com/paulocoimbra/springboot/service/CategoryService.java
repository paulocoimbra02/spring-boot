package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.Category;
import com.paulocoimbra.springboot.dto.CategoryDTO;
import com.paulocoimbra.springboot.repository.CategoryRepository;
import com.paulocoimbra.springboot.service.exception.DataIntegrityException;
import com.paulocoimbra.springboot.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Category update(Category obj) {
        Category newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Not possible to delete a category with products");
        }
    }

    public List<Category> findAll() {
        return repo.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Category fromDTO(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }

    private void updateData(Category newObj, Category obj) {
        newObj.setName(obj.getName());
    }
}
