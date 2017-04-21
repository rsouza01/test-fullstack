package com.test.api.application;


/**
 * 
 */
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

import com.test.api.resources.UsuarioController;

public class TestApplication extends Application
{
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public TestApplication(){
		singletons.add(new UsuarioController());
	}

	public Set<Object> getSingletons() {
		return singletons;
	}

	public Set<Class<?>> getEmpty() {
		return empty;
	}
}