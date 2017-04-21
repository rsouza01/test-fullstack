/**
 * 
 */
package com.test.ws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.test.model.pojo.UsuarioTO;

/**
 * @author rsouza
 *
 */
public class WebServiceAPIConsumer {
	
	/**
	 * Deve ir pra um properties.
	 */
	public static final String API_URL = "http://127.0.0.1:8080/test-api-0.0.1-SNAPSHOT/api";
	
	private static final String SERVICE_URL_ADD = WebServiceAPIConsumer.API_URL + "/user";  
	private static final String SERVICE_URL_UPDATE = WebServiceAPIConsumer.API_URL + "/user";  
	private static final String SERVICE_URL_GET = WebServiceAPIConsumer.API_URL + "/user/{id}";
	private static final String SERVICE_URL_GET_ALL = WebServiceAPIConsumer.API_URL + "/user";
	//private static final String SERVICE_URL_DELETE = WebServiceAPIConsumer.API_URL + "/user/{id}";
	private static final String SERVICE_URL_DELETE = WebServiceAPIConsumer.API_URL + "/user/";

	/**
	 * Loga a mensagem de erro. Deve ser passado para um log.	
	 * @param e
	 */
	public void logErro(Exception e) {
		e.printStackTrace();
	}

	/**
	 * Cadastra um usuário no serviço
	 * @param user
	 * @return
	 */
	public Long cadastrarUsuario(UsuarioTO user) {

		Long id = -1L;
		
		try {

			ResteasyClient client = new ResteasyClientBuilder().build();

			Response response = client.target(SERVICE_URL_ADD).request()
					.post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
			
			int status = response.getStatus();
			
			System.out.println("Status code: " + status);
			
			id = (Long) response.readEntity(Long.class);

	    }
	    catch (Exception e)
	    {
	    	logErro(e);
	    }
		
		return id;
	}

	/**
	 * Lista todos os usuários cadastrados
	 * @return
	 */
	public List<UsuarioTO> listarTodosUsuarios() {
		

		List<UsuarioTO> listUsuario = new ArrayList<UsuarioTO>();

		try {
			ResteasyClient client = new ResteasyClientBuilder().build();
			
			Response response = client.target(SERVICE_URL_GET_ALL).request().get();
		    
			int status = response.getStatus();
		    
			System.out.println("Status code: " + status);
		    
			UsuarioTO[] usuarios = response.readEntity(UsuarioTO[].class);
		    
			listUsuario = new ArrayList<UsuarioTO>(Arrays.asList(usuarios));

		} catch (Exception e) {

			/*
			 * Mock
			 */
			listUsuario.add(new UsuarioTO(1L, "Nome 1", "Email 1", "Telefone 1", "Sexo 1"));
			listUsuario.add(new UsuarioTO(2L, "Nome 2", "Email 2", "Telefone 2", "Sexo 2"));
			listUsuario.add(new UsuarioTO(3L, "Nome 3", "Email 3", "Telefone 3", "Sexo 3"));
			
			
	    	logErro(e);
		} 
		
		return listUsuario;
    }
	
	/**
	 * Obtém um usuário específico.
	 * @param id
	 * @return
	 */
	public UsuarioTO obterUsuario(Long id) {
		
		UsuarioTO usuario = null;

		try {

			ResteasyClient client = new ResteasyClientBuilder().build();
			
			ResteasyWebTarget target = client.target(SERVICE_URL_GET).path(String.valueOf(id));

			Response response = target.request().get();
		    
			int status = response.getStatus();
		    
			System.out.println("Status code: " + status);
		    
			usuario = response.readEntity(UsuarioTO.class);

		} catch (Exception e) {

	    	logErro(e);
		} 
		
		return usuario;
	     
    }

	/**
	 * Remove um usuário do serviço
	 * @param id
	 */
	public void removerUsuario(Long id) {

		ResteasyClient client = new ResteasyClientBuilder().build();
	
		ResteasyWebTarget target = client.target(SERVICE_URL_DELETE).path(String.valueOf(id));

		System.out.println("URI:" + target.getUri());

		Response response = target.request().delete();
		
		System.out.println("Status code:" + response.getStatus());

	}


	/**
	 * Atualiza um usuário no serviço
	 * @param user
	 */
	public void salvarUsuario(UsuarioTO user) {
		
		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target = client.target(SERVICE_URL_UPDATE).path(String.valueOf(user.getId()));
	    
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
	        .put(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
	    
		int status = response.getStatus();
	    
		System.out.println("Status code: " + status);

	}

}