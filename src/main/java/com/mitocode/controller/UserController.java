package com.mitocode.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.mitocode.dto.PerfilDTO;
import com.mitocode.dto.RolDTO;
import com.mitocode.dto.UsuarioDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Rol;
import com.mitocode.model.Usuario;
import com.mitocode.service.IRolService;
import com.mitocode.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Resource(name = "tokenServices")
    private ConsumerTokenServices tokenServices;
    @Autowired
    private IUsuarioService service;
    @Autowired
    private IRolService rolService;

    @GetMapping(value = "/anular/{tokenId:.*}")
    public void revokeToken(@PathVariable("tokenId") String token) {
        tokenServices.revokeToken(token);
    }

    @RequestMapping("/user")
    public PerfilDTO user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PerfilDTO dto = new PerfilDTO();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            dto.setUsuario(currentUserName);
        }
        List<RolDTO> roles = new ArrayList<>();
        authentication.getAuthorities().forEach(t -> {
            RolDTO rol = new RolDTO();
            rol.setNombre(t.getAuthority());
            roles.add(rol);
        });
        dto.setRoles(roles);
        return dto;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = service.listar();

        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @PostMapping(value = "/rol", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> usuarioRol(@Valid @RequestBody UsuarioDTO dto) {
        Usuario usuario = service.listarId(dto.getIdUsuario());
        List<Rol> roles = usuario.getRoles().isEmpty()?new ArrayList<>():usuario.getRoles();
        dto.getRoles().forEach(t -> {
            Rol rol = rolService.listarId(t.getIdRol());
            roles.add(rol);
        });
        usuario.setRoles(roles);
        Usuario response = service.modificar(usuario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getIdUsuario()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Usuario>> listarPageable(Pageable pageable) {
        Page<Usuario> usuarios = service.listarPageable(pageable);

        return new ResponseEntity<Page<Usuario>>(usuarios, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public org.springframework.hateoas.Resource<Usuario> listarId(@PathVariable("id") Integer id) {
        Usuario usuario = service.listarId(id);
        if (usuario == null) {
            throw new ModeloNotFoundException("ID: " + id);
        }

        org.springframework.hateoas.Resource<Usuario> resource = new org.springframework.hateoas.Resource<Usuario>(usuario);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listar());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }
}
