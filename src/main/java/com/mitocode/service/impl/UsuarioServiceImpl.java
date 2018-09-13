package com.mitocode.service.impl;

import com.mitocode.dao.IUsuarioDAO;
import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioDAO dao;

    @Override
    public Page<Usuario> listarPageable(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        return dao.save(usuario);
    }

    @Override
    public Usuario modificar(Usuario usuario) {
        return dao.save(usuario);
    }

    @Override
    public void eliminar(int id) {
        dao.delete(id);
    }

    @Override
    public Usuario listarId(int id) {
        return dao.getOne(id);
    }

    @Override
    public List<Usuario> listar() {
        return dao.findAll();
    }
}
