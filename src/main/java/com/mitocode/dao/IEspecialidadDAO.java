package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.model.Especialidad;

@Repository
public interface IEspecialidadDAO extends JpaRepository<Especialidad, Integer> {

}
