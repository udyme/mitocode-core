package com.mitocode.service;


import com.mitocode.model.Signos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISignosService extends ICRUD<Signos> {

    Page<Signos> listarPageable(Pageable pageable);
}
