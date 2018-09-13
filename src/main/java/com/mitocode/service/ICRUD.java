package com.mitocode.service;

import java.util.List;

public interface ICRUD<T> {

	T registrar(T t);

	T modificar(T t);

	void eliminar(int id);

	T listarId(int id);

	List<T> listar();
}
