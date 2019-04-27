package br.com.trabalho.servicos;

import br.com.trabalho.domain.Pacote;
import br.com.trabalho.servicos.dao.DaoFactory;

public class ServicoCadastroPacote extends ServicoCadastro<Pacote> {
	
	public ServicoCadastroPacote(Pacote pacote){
		super(pacote);
	}
	
	@Override
	public void cadastrar(){
		DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).
			getPacoteDao().salvarPacote(this.entidade);
	}

}
