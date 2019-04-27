package br.com.trabalho.servicos;

import br.com.trabalho.domain.Usuario;
import br.com.trabalho.servicos.dao.DaoFactory;

public class ServicoAtivarUsuario extends ServicoCadastro<Usuario> {
	
	public ServicoAtivarUsuario(Usuario usuario){
		super(usuario);
	}
	
	@Override
	public void cadastrar(){
		DaoFactory.getFactory(propriedades.
				getProperty(TIPO_EIS)).getUsuarioDao().atualizarUsuario(this.entidade);
	}

}
