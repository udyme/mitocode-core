package com.mitocode.service.impl;

import com.mitocode.dao.ISignosDAO;
import com.mitocode.model.Signos;
import com.mitocode.service.ISignosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignosServiceImpl implements ISignosService {

    @Autowired
    private ISignosDAO dao;

    @Override
    public Page<Signos> listarPageable(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public Signos registrar(Signos signos) {
        return dao.save(signos);
    }

    @Override
    public Signos modificar(Signos signos) {
        return dao.save(signos);
    }

    @Override
    public void eliminar(int id) {
        dao.delete(id);
    }

    @Override
    public Signos listarId(int id) {
        return dao.findOne(id);
    }

    @Override
    public List<Signos> listar() {
        return dao.findAll();
    }
}
