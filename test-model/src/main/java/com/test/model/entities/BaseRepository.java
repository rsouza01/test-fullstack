package com.test.model.entities;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

	void delete(Usuario deleted);
	
    @Query("SELECT * FROM USUARIOS")
    List<Usuario> findAll();

    @Query("SELECT * FROM USUARIOS u where u.id = :id")
    Optional<Usuario> findOne(@Param("id") Long id);

    Usuario save(Usuario persisted);
}
