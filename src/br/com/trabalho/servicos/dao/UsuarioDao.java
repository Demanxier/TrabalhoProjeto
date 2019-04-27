package br.com.trabalho.servicos.dao;

import java.util.List;

import br.com.trabalho.domain.Usuario;

public interface UsuarioDao {
	
	static final String CAMPO_USERNAME ="username";
	static final String CAMPO_EMAIL = "email";
	static final String CAMPO_SENHA = "senha";
	
	boolean salvarUsuario(Usuario usuario);
	boolean atualizarUsuario(Usuario usuario);
	boolean excluirUsuario(Usuario usuario);
	List<Usuario> consultarUsuario(boolean login, String ...criterios);

}
