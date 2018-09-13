package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IEspecialidadDAO;
import com.mitocode.model.Especialidad;
import com.mitocode.service.IEspecialidadService;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

	@Autowired
	private IEspecialidadDAO dao;

	@Override
	public void registrar(Especialidad especialidad) {
		dao.save(especialidad);
	}

	@Override
	public void modificar(Especialidad especialidad) {
		dao.save(especialidad);
	}

	@Override
	public void eliminar(int idEspecialidad) {
		dao.delete(idEspecialidad);
	}

	@Override
	public Especialidad listarId(int idEspecialidad) {
		return dao.findOne(idEspecialidad);
	}

	@Override
	public List<Especialidad> listar() {
		return dao.findAll();
	}
}
