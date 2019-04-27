package br.com.trabalho.mbeans;

import java.io.Serializable;
import java.util.logging.Level;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import com.sun.istack.internal.logging.Logger;

import br.com.trabalho.controller.ControladorAcesso;
import br.com.trabalho.domain.Usuario;
import br.com.trabalho.servicos.ServicoAtivarUsuario;
import br.com.trabalho.servicos.ServicoCarregarUsuario;
import br.com.trabalho.servicos.ServicoControleSessao;
import br.com.trabalho.servicos.ServicoDesativarUsuario;
import br.com.trabalho.servicos.ServicoLogin;

@ManagedBean
@SessionScoped
public class MbLogin implements Serializable {
	
	private static final String LOGIN_SUCESSO = "login_sucesso";
	public static final String LOGIN_FALHA = "login_falha";
	public static final String SESSAO_INEXISTENTE = "sessao_invalida";
	public static final String OUTCOME_LOGOUT = "logout";
	public static final String USUARIO_SESSAO = "usuario";
	
	private Usuario usuario;
	private ControladorAcesso controladorAcesso;
	
	public MbLogin(){
		
	}
	
	@PostConstruct
	public void inicializar(){
		usuario = new Usuario();
		controladorAcesso = new ControladorAcesso();
		Logger.getLogger(MbLogin.class).log(Level.INFO, "Inicializando um bean de login.");
	}
	
	public String doLogin(){
		if(camposPreenchidos() && !isUsuarioLogado()){
			if(new ServicoLogin(usuario).executar()){
				// Descobrindo o tipo de usuário que está tentando acessar o sistema
				Usuario usuarioLogado = new ServicoCarregarUsuario().
						carregarDados(usuario.getUsername(), usuario.getSenha()).get(0);
				usuarioLogado.setStatus(Usuario.ATIVO);
				
				HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().
						getExternalContext().getSession(true);
				sessao.setAttribute(USUARIO_SESSAO, usuarioLogado);
				controladorAcesso.configurarAcesso();
				
				// Atualizando sistema de informações para informar que o usuario está logado.
				new ServicoAtivarUsuario(usuarioLogado).cadastrar();
				return LOGIN_SUCESSO;
			}
		}
		return LOGIN_FALHA;
	}
	
	public String doLogout(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpSession sessao = (HttpSession) FacesContext.
				getCurrentInstance().getExternalContext().getSession(false);
		Usuario usuarioSessao = (Usuario) sessao.getAttribute(USUARIO_SESSAO);
		
		if(usuarioSessao != null){
			usuarioSessao.setStatus(Usuario.INATIVO);
			new ServicoDesativarUsuario(usuarioSessao).cadastrar();
		}
		
		context.getExternalContext().invalidateSession();
		return OUTCOME_LOGOUT;
	}
	
	private boolean camposPreenchidos(){
		return (usuario !=null && usuario.getUsername() != null
				&& !"".equals(usuario.getUsername()) && usuario.getSenha() !=null
				&& !"".equals(usuario.getSenha()));
	}
	
	private boolean isUsuarioLogado(){
		return new ServicoControleSessao(usuario).executar();
	}
	
	//Limpar os dados da tela de login
	public void LimparTela(){
		this.usuario = new Usuario();
	}
	
	public Usuario getUsuario(){
		return usuario;
	}
	
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	
	public ControladorAcesso getControladorAcesso(){
		return controladorAcesso;
	}
	
}