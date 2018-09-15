package com.mitocode.dao;

import com.mitocode.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolDAO extends JpaRepository<Rol, Integer> {
    @Query(value = "select * from Rol r  ORDER BY r.id_rol DESC LIMIT 1", nativeQuery= true)
    Rol getLast();
}
