package br.com.trabalho.servicos;

import br.com.trabalho.domain.Usuario;
import br.com.trabalho.servicos.dao.DaoFactory;

public class ServicoLogin extends ServicoVerificador {
	
	private Usuario usuario;
	
	public ServicoLogin(Usuario usuario){
		this.usuario = usuario;
	}
	
	@Override
	public boolean executar(){
		return (DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).getUsuarioDao().
				consultarUsuario(true, usuario.getUsername(), usuario.getSenha()).size() > 0);
	}
}