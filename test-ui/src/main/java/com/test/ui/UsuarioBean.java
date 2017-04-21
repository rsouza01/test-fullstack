/**
 * 
 */
package com.test.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.test.model.entities.Usuario;
import com.test.model.pojo.UsuarioTO;
import com.test.ws.WebServiceAPIConsumer;

/**
 * @author rsouza
 *
 */
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean {

	private String tituloOperacao = "";
	private int operacao = 0;
	
	public static final int OPERACAO_INCLUSAO = 1;
	public static final int OPERACAO_ALTERACAO = 2;
	
	private UsuarioTO usuarioSelecionado = null;
	
	private WebServiceAPIConsumer wsApi = new WebServiceAPIConsumer();

	/**
	 * @return the tituloOperacao
	 */
	public String getTituloOperacao() {
		return tituloOperacao;
	}

	/**
	 * @param tituloOperacao the tituloOperacao to set
	 */
	public void setTituloOperacao(String tituloOperacao) {
		this.tituloOperacao = tituloOperacao;
	}

	/**
	 * @return the operacao
	 */
	public int getOperacao() {
		return operacao;
	}

	/**
	 * @param operacao the operacao to set
	 */
	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}

	/**
	 * @return the usuarioSelecionado
	 */
	public UsuarioTO getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	/**
	 * @param usuarioSelecionado the usuarioSelecionado to set
	 */
	public void setUsuarioSelecionado(UsuarioTO usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
    

	public List<UsuarioTO> getListaUsuarios() {
		
		return wsApi.listarTodosUsuarios();

	}

	public UsuarioTO getUsuario() {
		return new UsuarioTO("nome", "email", "telefone", "sexo");
	}

    public void excluirAction(ActionEvent actionEvent) {
    	
    	FacesContext context = FacesContext.getCurrentInstance();

        Map<String,String> params = context.getExternalContext().getRequestParameterMap();

        System.out.println("excluirAction - id = "  + params.get("id"));

        if(params.get("id") != null) {
        	
        	Long id = Long.parseLong(params.get("id"));

        	wsApi.removerUsuario(id);
        	
        	context.addMessage(null, new FacesMessage("Sucesso",  "Usuário excluído com sucesso!") );

        } else {
        	context.addMessage(null, new FacesMessage("Erro",  "Erro na exclusão do usuário!") );
        }

    }
    
    public void alterarAction(ActionEvent actionEvent) {

        FacesContext fc = FacesContext.getCurrentInstance();

        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();

        System.out.println("alterarAction - id = "  + params.get("id"));

        this.tituloOperacao = "Alteração";
        this.operacao = UsuarioBean.OPERACAO_ALTERACAO;
        
        this.usuarioSelecionado = new UsuarioTO(2L, "Nome 2", "Email 2", "Telefone 2", "Sexo 2"); 
        
    }

    public void incluirAction(ActionEvent actionEvent) {

        System.out.println("incluirAction");

        this.tituloOperacao = "Inclusão";
        this.operacao = UsuarioBean.OPERACAO_INCLUSAO;
        
        this.usuarioSelecionado = new UsuarioTO(); 
        
    }

    public void salvarUsuarioAction(ActionEvent actionEvent) {
    	
    	FacesContext context = FacesContext.getCurrentInstance();

        context.getExternalContext().getFlash().setKeepMessages(true);
        
        try {

        	UsuarioTO user = this.usuarioSelecionado;

            if(UsuarioBean.OPERACAO_INCLUSAO == this.operacao) {
            	wsApi.cadastrarUsuario(user);
            } else if(UsuarioBean.OPERACAO_ALTERACAO == this.operacao) {
            	wsApi.salvarUsuario(user);
            }

            context.addMessage(null, new FacesMessage("Sucesso", "Usuário salvo com sucesso!") );
			
		} catch (Exception e) {
            context.addMessage(null, new FacesMessage("Erro", "Erro na gravação do Usuário!") );
		}
    }

}
