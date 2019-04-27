package br.com.trabalho.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.trabalho.domain.Usuario;
import br.com.trabalho.mbeans.MbLogin;

public class ControladorAcesso {
	
	private boolean permissaoAdministrador;
	private boolean permissaoFuncionario;
	private boolean permissaoComum;
	
	public boolean isPermissaoAdministrador(){
		HttpSession sessao = (HttpSession)
				FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuarioSessao = (Usuario) sessao.getAttribute(MbLogin.USUARIO_SESSAO);
		
		if(usuarioSessao != null){
			permissaoAdministrador = (usuarioSessao.getTipo() == Usuario.ADMINISTRADOR);
		}else {
			permissaoAdministrador = false;
		}
		return permissaoAdministrador;
	}
	
	public boolean isPermissaoFuncionario(){
		HttpSession sessao = (HttpSession)
				FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuarioSessao = (Usuario) sessao.getAttribute(MbLogin.USUARIO_SESSAO);
		
		if(usuarioSessao != null){
			permissaoAdministrador = (usuarioSessao.getTipo() == Usuario.ADMINISTRADOR);
			
			if(permissaoAdministrador){
				permissaoFuncionario = true;
			}else {
				permissaoFuncionario = (usuarioSessao.getTipo() == Usuario.FUNCIONARIO);
			}
		}else{
			permissaoFuncionario = false;
		}
		return permissaoFuncionario;
	}
	public boolean isPermissaoComun(){
		HttpSession sessao = (HttpSession)
				FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuarioSessao = (Usuario) sessao.getAttribute(MbLogin.USUARIO_SESSAO);
		
		if(usuarioSessao != null){
			permissaoAdministrador = (usuarioSessao.getTipo() == Usuario.ADMINISTRADOR);
			permissaoFuncionario = (usuarioSessao.getTipo() == Usuario.FUNCIONARIO);
			
			if(permissaoAdministrador || permissaoFuncionario){
				permissaoComum = true;
			}else{
				permissaoComum = (usuarioSessao.getTipo() == Usuario.CONVIDADO);
			}
		}else{
			permissaoComum = false;
		}
		return permissaoComum;
	}
	public void configurarAcesso(){
		HttpSession sessao = (HttpSession)
				FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuarioSessao = (Usuario) sessao.getAttribute(MbLogin.USUARIO_SESSAO);
		
		if(usuarioSessao != null){
			Logger.getLogger("ControladorAcesso").log(Level.INFO, "Usuario da sessão tem tipo {0}", usuarioSessao.getTipo());
			
			permissaoAdministrador = (usuarioSessao.getTipo() == Usuario.ADMINISTRADOR);
			
			if(permissaoAdministrador){
				permissaoFuncionario = true;
			}else{
				permissaoFuncionario = (usuarioSessao.getTipo() == Usuario.FUNCIONARIO);
				permissaoComum = (usuarioSessao.getTipo() == Usuario.CONVIDADO);
			}
		}
	}
}