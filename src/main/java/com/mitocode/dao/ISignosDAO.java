package com.mitocode.dao;

import com.mitocode.model.Signos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISignosDAO extends JpaRepository<Signos, Integer> {

}
