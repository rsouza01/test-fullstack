package com.test.api.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.test.api.util.SpringApplicationContext;
import com.test.model.pojo.UsuarioTO;
import com.test.services.UsuarioService;

import javax.ws.rs.core.MediaType;



/**
 * @author rsouza
 *
 */
@Path("/api")
public class UsuarioController {

	
	public static final int RETORNO_200 = 200;
	public static final int RETORNO_500 = 500;
	
	private UsuarioService usuarioService;
	
	private void logErro(Exception e) {
		//TODO: Colocar logs se der tempo.
		e.printStackTrace();
	}
	
	/**
	 * @return the usuarioService
	 */
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	/**
	 * @param usuarioService the usuarioService to set
	 */
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	
	public UsuarioController() {
		
	}

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user")    
	public Response cadastrarUsuario(@RequestBody UsuarioTO user) {

    	String result = "Registro cadastrado com sucesso!";
    	int status = UsuarioController.RETORNO_200;
    	
    	try {
    		
    		usuarioService.save(user);
    		
    	} catch (Exception e) {
    		result = "Erro no cadastro do registro.";
    		status = UsuarioController.RETORNO_500;
    		logErro(e);
    	}

    	return Response.status(status).entity(result).build();

	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user")    
	public Response listarTodosUsuarios() {
    	
		usuarioService = (UsuarioService)SpringApplicationContext.getBean("usuarioService");

		List<UsuarioTO> listTemp = null;

    	int status = UsuarioController.RETORNO_200;
    	
    	try {
    		
    		listTemp = usuarioService.findAll();
    		
    	} catch (Exception e) {
    		listTemp = new ArrayList<UsuarioTO>();
    		status = UsuarioController.RETORNO_500;
    		
    		logErro(e);
    	}

    	UsuarioTO[] result = new UsuarioTO[listTemp.size()];
    	result = listTemp.toArray(result);

    	return Response.status(status).entity(result).build();
    
    }
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}")    
	public Response obterUsuario(@PathVariable Long id) {

		usuarioService = (UsuarioService)SpringApplicationContext.getBean("usuarioService");

		UsuarioTO result = null;

    	int status = UsuarioController.RETORNO_200;
    	
    	try {
    		
    		Optional<UsuarioTO> resultTemp = usuarioService.findOne(id);
    		
    		if(resultTemp.isPresent()) { 
    			result = resultTemp.get();
    		}

    	} catch (Exception e) {

    		result = new UsuarioTO();
    		status = UsuarioController.RETORNO_500;
    		
    		logErro(e);
    	}

    	return Response.status(status).entity(result).build();

    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}")    
	public Response removerUsuario(@PathVariable Long id) {

		usuarioService = (UsuarioService)SpringApplicationContext.getBean("usuarioService");

    	int status = UsuarioController.RETORNO_200;
    	String result = "OK";
    	
    	try {
    		
    		Optional<UsuarioTO> resultTemp = usuarioService.findOne(id);
    		
    		if(resultTemp.isPresent()) { 
    			usuarioService.delete(resultTemp.get());
    		}

    	} catch (Exception e) {

    		result = "ERRO";
    		status = UsuarioController.RETORNO_500;

    		logErro(e);
    	}

    	return Response.status(status).entity(result).build();

	}

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/user/{id}")    
	public Response salvarUsuario(@RequestBody UsuarioTO user) {

    	String result = "Registro alterado com sucesso!";
    	int status = UsuarioController.RETORNO_200;
    	
    	try {
    		
    		usuarioService.save(user);
    		
    	} catch (Exception e) {

    		result = "Erro na alteração do registro.";
    		status = UsuarioController.RETORNO_500;

    		logErro(e);
    	}

    	return Response.status(status).entity(result).build();

	}
}
