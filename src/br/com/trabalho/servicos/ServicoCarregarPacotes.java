package br.com.trabalho.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.trabalho.domain.Pacote;
import br.com.trabalho.servicos.dao.DaoFactory;

public class ServicoCarregarPacotes extends ServicoCarga<Pacote> {
	
	public static final String TODOS = "TODOS";
	
	@Override
	public List<Pacote> carregarDados(String...criterios){
		if(criterios != null &&
				criterios.length == 1 && TODOS.equals(criterios[0])){
			return DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).
					getPacoteDao().consultarPacotes(TODOS);
		}
		return new ArrayList<Pacote>();
	}

}
