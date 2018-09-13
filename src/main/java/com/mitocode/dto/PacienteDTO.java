package com.mitocode.dto;

import org.springframework.hateoas.ResourceSupport;

//https://blog.zenika.com/2012/06/13/hateoas-with-spring-mvc-rest/
public class PacienteDTO extends ResourceSupport {

	private int idPaciente;
	private String nombresCompletos;

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNombresCompletos() {
		return nombresCompletos;
	}

	public void setNombresCompletos(String nombresCompletos) {
		this.nombresCompletos = nombresCompletos;
	}

}
