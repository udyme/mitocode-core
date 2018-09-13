package com.mitocode.service.impl;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RestAuthService {

	public boolean hasAccess(String path) {

		boolean rpta = false;
		
		//INI Reemplazar por llamada a base de datos para devovelver un arrayList<MetodoRol>
		String metodoRol = "";

		switch (path) {
		case "listar":
			metodoRol = "ADMIN";
			break;

		case "listarId":
			metodoRol = "ADMIN,USER,DBA";
			break;
		}

		String metodoRoles[] = metodoRol.split(",");
		//MetodoRol.java
		
		//private int id;
		//private Metodo metodo; (@ManyToOne)
		//private Rol rol; (@ManyToOne)
		
		//FIN Reemplazar por llamada a base de datos para devovelver un arrayList<MetodoRol>

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			System.out.println(authentication.getName());
			for (GrantedAuthority auth : authentication.getAuthorities()) {
				String rolUser = auth.getAuthority();
				//System.out.println("rolUser " + rolUser);
				for (String rolMet : metodoRoles) {  //---------> metodoRoles seria reemplazado por el arrayList <MetodoRol>   MetodoRol mr : list
					//System.out.println("rolMet " + rolMet);
					if (rolUser.equalsIgnoreCase(rolMet)) {
						rpta = true;
					}
				}
			}
		}

		return rpta;
	}
}
