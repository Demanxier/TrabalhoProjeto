package br.com.trabalho.servicos;

import java.util.List;

public abstract class ServicoCarga<T> extends Servico {
	
	public abstract List<T> carregarDados(String ... criterios);

}
