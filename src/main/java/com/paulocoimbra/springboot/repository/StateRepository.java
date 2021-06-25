package com.paulocoimbra.springboot.repository;

import com.paulocoimbra.springboot.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    

}
