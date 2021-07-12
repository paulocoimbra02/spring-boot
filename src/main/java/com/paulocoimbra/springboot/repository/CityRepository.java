package com.paulocoimbra.springboot.repository;

import com.paulocoimbra.springboot.domain.City;
import com.paulocoimbra.springboot.domain.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Transactional(readOnly = true)
    Page<City> findByState(State state, Pageable pageRequest);
}
