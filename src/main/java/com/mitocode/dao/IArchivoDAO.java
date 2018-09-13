package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Archivo;
import org.springframework.stereotype.Repository;

@Repository
public interface IArchivoDAO extends JpaRepository<Archivo, Integer>{

}
