package com.test.services;

import java.util.List;
import java.util.Optional;
import com.test.model.pojo.UsuarioTO;

/**
 */
public interface UsuarioService {

	void delete(UsuarioTO deleted);
	
    List<UsuarioTO> findAll();

    Optional<UsuarioTO> findOne(Long id);

    UsuarioTO save(UsuarioTO persisted);


}
