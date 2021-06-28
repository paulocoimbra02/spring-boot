package com.paulocoimbra.springboot.repository;

import com.paulocoimbra.springboot.domain.Category;
import com.paulocoimbra.springboot.domain.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer> {

}
