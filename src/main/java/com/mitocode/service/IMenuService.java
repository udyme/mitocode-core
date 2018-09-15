package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMenuService {

	Menu registrar(Menu menu);

	void modificar(Menu menu);

	void eliminar(int idMenu);

	Menu listarId(int idMenu);

	List<Menu> listar();
	
	List<Menu> listarMenuPorUsuario(String nombre);

	Page<Menu> listarPageable(Pageable pageable);

	Menu getLast();
}
