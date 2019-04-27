package br.com.trabalho.servicos;

import java.util.List;

import br.com.trabalho.domain.Usuario;
import br.com.trabalho.servicos.dao.DaoFactory;

public class ServicoControleSessao extends ServicoVerificador {
	
	private Usuario usuario;
	
	public ServicoControleSessao(Usuario usuario){
		this.usuario = usuario;
	}
	
	@Override
	public boolean executar(){
		List<Usuario> usuarios =
				DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).
					getUsuarioDao().consultarUsuario(true, usuario.getUsername(), usuario.getSenha());
		
		return(usuarios.size() > 0 && usuarios.get(0).getStatus() == Usuario.ATIVO);
	}
}