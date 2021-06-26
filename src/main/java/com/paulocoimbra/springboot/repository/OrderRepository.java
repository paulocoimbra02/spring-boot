package com.paulocoimbra.springboot.repository;

import com.paulocoimbra.springboot.domain.Order1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order1, Integer> {

}
