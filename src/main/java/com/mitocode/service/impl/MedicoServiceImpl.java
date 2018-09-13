package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IMedicoDAO;
import com.mitocode.model.Medico;
import com.mitocode.service.IMedicoService;

@Service
public class MedicoServiceImpl implements IMedicoService {

	@Autowired
	private IMedicoDAO dao;

	@Override
	public Medico registrar(Medico medico) {
		return dao.save(medico);
	}

	@Override
	public Medico modificar(Medico medico) {
		return dao.save(medico);
	}

	@Override
	public void eliminar(int idMedico) {
		dao.delete(idMedico);
	}

	@Override
	public Medico listarId(int idMedico) {
		return dao.findOne(idMedico);
	}

	@Override
	public List<Medico> listar() {
		return dao.findAll();
	}

}
