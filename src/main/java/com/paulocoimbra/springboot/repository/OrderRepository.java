package com.paulocoimbra.springboot.repository;

import com.paulocoimbra.springboot.domain.Client;
import com.paulocoimbra.springboot.domain.Order1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order1, Integer> {

    @Transactional(readOnly = true)
    Page<Order1> findByClient(Client client, Pageable pageRequest);

}
