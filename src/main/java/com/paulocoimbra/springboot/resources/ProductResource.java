package com.paulocoimbra.springboot.resources;

import com.paulocoimbra.springboot.domain.Product;
import com.paulocoimbra.springboot.dto.ProductDTO;
import com.paulocoimbra.springboot.resources.utils.URL;
import com.paulocoimbra.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        return ResponseEntity.ok().body(service.findById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> find(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        String nameDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = service.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> listDto = list.map(ProductDTO::new);
        return ResponseEntity.ok().body(listDto);
    }

}
