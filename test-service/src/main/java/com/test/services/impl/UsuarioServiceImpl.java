package com.test.services.impl;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import javax.inject.Named;

import com.test.model.entities.UsuarioRepository;
import com.test.model.pojo.UsuarioTO;
import com.test.services.UsuarioService;

import javax.annotation.Resource;
import javax.ejb.Stateless;

/**
 */
@Named("usuarioService")
@Stateless
public class UsuarioServiceImpl extends AbstractService implements UsuarioService {

	@Resource
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void delete(UsuarioTO deleted) {
		System.out.println("UsuarioServiceImpl.delete");
	}

	@Override
	public List<UsuarioTO> findAll() {

		System.out.println("UsuarioServiceImpl.list");
		
		List<UsuarioTO> listUsuario = new ArrayList<UsuarioTO>(); 		

		listUsuario.add(new UsuarioTO(1L, "Nome 1 API", "Email 1", "Telefone 1", "F"));
		listUsuario.add(new UsuarioTO(2L, "Nome 2 API", "Email 2", "Telefone 2", "M"));
		listUsuario.add(new UsuarioTO(3L, "Nome 3 API", "Email 3", "Telefone 3", "F"));

		return listUsuario;
	}

	@Override
	public Optional<UsuarioTO> findOne(Long id) {

		System.out.println("UsuarioServiceImpl.findOne");
		
		Optional<UsuarioTO> user = 
				Optional.of(new UsuarioTO(3L, "Nome FindOne API", "Email", "11 1111 1111", "M"));
		
		return user;
	}

	@Override
	public UsuarioTO save(UsuarioTO persisted) {

		System.out.println("UsuarioServiceImpl.save");

		UsuarioTO user = 
				new UsuarioTO(3L, "Nome save API", "Email", "11 1111 1111", "M");
		
		return user;
	}

	/**
	 * @return the repository
	 */
	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	/**
	 * @param repository the repository to set
	 */
	public void setUsuarioRepository(UsuarioRepository repository) {
		this.usuarioRepository= repository;
	}

}
