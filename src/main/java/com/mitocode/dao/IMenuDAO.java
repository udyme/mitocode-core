package com.mitocode.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mitocode.model.Menu;

@Repository
public interface IMenuDAO extends JpaRepository<Menu, Integer> {

    @Query(value = "select distinct m.* from menu_rol mr inner join usuario_rol ur on ur.id_rol = mr.id_rol inner join menu m on m.id_menu = mr.id_menu inner join usuario u on u.id_usuario = ur.id_usuario where u.nombre = :nombre order by m.id_menu asc", nativeQuery = true)
    List<Object[]> listarMenuPorUsuario(@Param("nombre") String nombre);

    @Query(value = "select * from Menu m  ORDER BY r.id_menu DESC LIMIT 1", nativeQuery= true)
    Menu getLast();

    //0 | [ 1, 'serach', 'buscar', '/buscar']
    //1 | [ 2, 'register', 'registrar', '/consulta']
}
