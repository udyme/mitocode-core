package com.mitocode.service.impl;

import com.mitocode.dao.IRolDAO;
import com.mitocode.model.Rol;
import com.mitocode.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolDAO dao;

    @Override
    public Page<Rol> listarPageable(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public Rol getLast() {
        return dao.getLast();
    }

    @Override
    public Rol registrar(Rol rol) {
        return dao.save(rol);
    }

    @Override
    public Rol modificar(Rol rol) {
        return dao.save(rol);
    }

    @Override
    public void eliminar(int id) {
        dao.delete(id);
    }

    @Override
    public Rol listarId(int id) {
        return dao.getOne(id);
    }

    @Override
    public List<Rol> listar() {
        return dao.findAll();
    }
}
