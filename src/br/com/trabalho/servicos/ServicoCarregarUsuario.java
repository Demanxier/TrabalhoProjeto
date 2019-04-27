package br.com.trabalho.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.trabalho.domain.Usuario;
import br.com.trabalho.servicos.dao.DaoFactory;

public class ServicoCarregarUsuario extends ServicoCarga<Usuario> {
	
	@Override
	public List<Usuario> carregarDados(String...criterios){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		if(criterios != null && criterios.length == 2){
			Usuario usuario = new Usuario();
			usuario.setUsername(criterios[0]);
			usuario.setSenha(criterios[1]);
			
			usuarios = DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).
					getUsuarioDao().consultarUsuario(true, usuario.getUsername(), usuario.getSenha());
		}
		return usuarios;
	}

}
