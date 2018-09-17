package com.mitocode.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.mitocode.dto.MenuDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Rol;
import com.mitocode.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mitocode.model.Menu;
import com.mitocode.service.IMenuService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private IMenuService service;
    @Autowired
    private IRolService rolService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menu>> listar() {
        List<Menu> menues = new ArrayList<>();
        menues = service.listar();
        return new ResponseEntity<List<Menu>>(menues, HttpStatus.OK);
    }

    @PostMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Menu>> listar(@RequestBody String nombre) {
        List<Menu> menues = new ArrayList<>();
        menues = service.listarMenuPorUsuario(nombre);
        return new ResponseEntity<List<Menu>>(menues, HttpStatus.OK);
    }

    @GetMapping(value = "/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Menu>> listarPageable(Pageable pageable) {
        Page<Menu> signos = null;

        signos = service.listarPageable(pageable);

        return new ResponseEntity<Page<Menu>>(signos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Menu> listarId(@PathVariable("id") Integer id) {
        Menu menu = new Menu();
        menu = service.listarId(id);
        if (menu == null) {
            throw new ModeloNotFoundException("ID: " + id);
        }

        Resource<Menu> resource = new Resource<Menu>(menu);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listar());
        resource.add(linkTo.withRel("all-menu"));
        return resource;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registrar(@Valid @RequestBody Menu menu) {
        Menu last = service.getLast();
        menu.setIdMenu(last.getIdMenu() + 1);
        menu = service.registrar(menu);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(menu.getIdMenu()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/rol", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> menuRol(@Valid @RequestBody MenuDTO dto) {
        Menu menu = service.listarId(dto.getIdMenu());
        List<Rol> roles = menu.getRoles().isEmpty()?new ArrayList<>():menu.getRoles();
        dto.getRoles().forEach(t -> {
            Rol rol = rolService.listarId(t.getIdRol());
            roles.add(rol);
        });
        menu.setRoles(roles);
         service.modificar(menu);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(menu.getIdMenu()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizar(@RequestBody Menu menu) {
        service.modificar(menu);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void eliminar(@PathVariable Integer id) {
        Menu pac = service.listarId(id);
        if (pac == null) {
            throw new ModeloNotFoundException("ID: " + id);
        } else {
            service.eliminar(id);
        }
    }


}
