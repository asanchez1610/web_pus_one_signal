package com.notiificaciones.push.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notificaciones.push.util.UtilMD5;
import com.notiificaciones.push.dao.mysql.UsuarioMapper;
import com.notiificaciones.push.domain.mysql.Usuario;
import com.notiificaciones.push.domain.mysql.UsuarioCriteria;
import com.notiificaciones.push.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioMapper usuarioMapper;
	
	public Usuario autenticar(String username, String password) throws Exception {
	
		UsuarioCriteria uc = new UsuarioCriteria();
		uc.createCriteria().andUsernameEqualToInCaseSensitive(username.trim());
		List<Usuario> list = usuarioMapper.selectByExample(uc);
		
		Usuario usuario = null;
		
		if(list != null && list.size() > 0) {
			usuario = list.get(0);
			try {
				if(!usuario.getClave().equals(UtilMD5.MD5(password))) {
					throw new Exception("La contraseña es incorrecta.");
				}
			} catch (NoSuchAlgorithmException e) {
				
				return null;
			} catch (UnsupportedEncodingException e) {
				
				return null;
			}
			
			return usuario;
		}else {
			throw new Exception("El usuario ingresado no existe.");
		}
		
	
		
	}

}
