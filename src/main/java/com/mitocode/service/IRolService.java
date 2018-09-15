package com.mitocode.service;

import com.mitocode.model.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRolService  extends ICRUD<Rol>  {
    Page<Rol> listarPageable(Pageable pageable);

    Rol getLast();
}
