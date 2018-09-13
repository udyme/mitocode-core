package com.mitocode.service;

import java.util.List;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.dto.ConsultaResumenDTO;
import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.model.Consulta;

public interface IConsultaService {

	Consulta registrar(ConsultaListaExamenDTO consultaDTO);

	void modificar(Consulta consulta);

	void eliminar(int idConsulta);

	Consulta listarId(int idConsulta);

	List<Consulta> listar();
	
	List<Consulta> buscar(FiltroConsultaDTO filtro);

	List<Consulta> buscarfecha(FiltroConsultaDTO filtro);
	
	List<ConsultaResumenDTO> listarResumen();

	byte[] generarReporte();
}
