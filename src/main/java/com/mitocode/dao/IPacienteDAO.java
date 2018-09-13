package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.model.Paciente;

@Repository
public interface IPacienteDAO extends JpaRepository<Paciente, Integer>{

}
