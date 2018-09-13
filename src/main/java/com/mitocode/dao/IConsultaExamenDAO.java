package com.mitocode.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mitocode.model.ConsultaExamen;

@Repository
public interface IConsultaExamenDAO extends JpaRepository<ConsultaExamen, Integer> {

	//@Transactional
	@Modifying
	@Query(value = "INSERT INTO consulta_examen(id_consulta, id_examen) VALUES (:idConsulta, :idExamen)", nativeQuery = true)
	int registrar(@Param("idConsulta") Integer idConsulta,@Param("idExamen") Integer idExamen);
	
	@Query("from ConsultaExamen ce where ce.consulta.idConsulta=:idConsulta")
	List<ConsultaExamen> listarExamenesPorConsulta(@Param("idConsulta") Integer idconsulta);
}
