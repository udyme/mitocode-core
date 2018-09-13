package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.model.Examen;

@Repository
public interface IExamenDAO extends JpaRepository<Examen, Integer> {

}
