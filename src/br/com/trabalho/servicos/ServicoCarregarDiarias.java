package br.com.trabalho.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.trabalho.domain.Diaria;
import br.com.trabalho.servicos.dao.DaoFactory;

public class ServicoCarregarDiarias extends ServicoCarga<Diaria> {
	
	public static final String TODOS ="TODOS";
	
	@Override
	public List<Diaria> carregarDados(String ... criterios){
		if(criterios != null && criterios.length == 1 && TODOS.equals(criterios[0])){
			return DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).
					getDiariaDao().consultarDiaria(TODOS);
		}
		return new ArrayList<Diaria>();
	}

}
