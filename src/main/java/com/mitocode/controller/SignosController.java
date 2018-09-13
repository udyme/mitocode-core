package com.mitocode.controller;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Signos;
import com.mitocode.service.ISignosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/signos")
public class SignosController {

    @Autowired
    private ISignosService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Signos>> listar() {
        List<Signos> signos = new ArrayList<>();

        signos = service.listar();

        return new ResponseEntity<List<Signos>>(signos, HttpStatus.OK);
    }

    @GetMapping(value="/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Signos>> listarPageable(Pageable pageable) {
        Page<Signos> signos = null;

        signos = service.listarPageable(pageable);

        return new ResponseEntity<Page<Signos>>(signos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Signos> listarId(@PathVariable("id") Integer id) {
        Signos signos = new Signos();
        signos = service.listarId(id);
        if (signos == null) {
            throw new ModeloNotFoundException("ID: " + id);
        }

        Resource<Signos> resource = new Resource<Signos>(signos);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listar());
        resource.add(linkTo.withRel("all-signos"));
        return resource;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registrar(@Valid @RequestBody Signos signos) {
        Signos pac = new Signos();
        pac = service.registrar(signos);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pac.getIdSignos()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizar(@RequestBody Signos signos) {
        service.modificar(signos);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void eliminar(@PathVariable Integer id) {
        Signos pac = service.listarId(id);
        if (pac == null) {
            throw new ModeloNotFoundException("ID: " + id);
        } else {
            service.eliminar(id);
        }
    }
}
