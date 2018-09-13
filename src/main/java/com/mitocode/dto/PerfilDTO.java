package com.mitocode.dto;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class PerfilDTO extends ResourceSupport {
    private int idUsuario;
    private String usuario;
    private List<RolDTO> roles;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<RolDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RolDTO> roles) {
        this.roles = roles;
    }
}
