package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.Category;
import com.paulocoimbra.springboot.domain.Product;
import com.paulocoimbra.springboot.repository.CategoryRepository;
import com.paulocoimbra.springboot.repository.ProductRepository;
import com.paulocoimbra.springboot.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product findById(Integer id) {
        Optional<Product> product = repo.findById(id);
        return product.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Product.class.getName()));
    }

    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Category> categories = categoryRepository.findAllById(ids);
        return repo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
    }
}
