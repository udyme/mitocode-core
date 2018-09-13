package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.model.Medico;

@Repository
public interface IMedicoDAO extends JpaRepository<Medico, Integer> {

}
