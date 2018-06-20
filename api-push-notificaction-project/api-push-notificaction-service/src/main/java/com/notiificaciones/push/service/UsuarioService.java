package com.notiificaciones.push.service;

import com.notiificaciones.push.domain.mysql.Usuario;

public interface UsuarioService {

	public Usuario autenticar(String username , String password ) throws Exception;
	
}
