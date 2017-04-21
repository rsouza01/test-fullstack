package com.test.api.ws;

import java.util.ArrayList;
import java.util.List;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.test.model.entities.Usuario;


/**
 * @author rsouza
 *
 */
@Path("/api")
public class UsuarioController {

	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/user")	
	public String cadastrarUsuario(Usuario user) {
		
		return "Registro cadastrado com sucesso!";

	}

	@GET	
	@Produces("application/json; charset=UTF-8")
	@Path("/user")	
	public void listarTodosUsuarios() {
		
	}
	
	@GET	
	@Produces("application/json; charset=UTF-8")
	@Path("/user/{id}")	
	public void obterUsuario(@PathParam("id") Long id) {
		
	}

	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/user/{id}")	
	public String removerUsuario(@PathParam("id") Long id) {

		return "Registro excluido com sucesso!";
		
	}

	@PUT
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/user")	
	public String salvarUsuario(Usuario user) {

		return "Registro alterado com sucesso!";
	}
	
	
}
