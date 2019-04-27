package br.com.trabalho.servicos.dao;

import java.util.List;

import br.com.trabalho.domain.Pacote;

public interface PacoteDao {
	
	static final String CAMPO_NOME = "nome";
	static final String CAMPO_DESCRICAO = "descricao";
	static final String CAMPO_DATA_INICIO = "data_inicio";
	static final String CAMPO_DATA_FIM = "data_fim";
	static final String CAMPO_VALOR = "valor_fechado";
	
	boolean salvarPacote(Pacote pacote);
	boolean atualizarPacote(Pacote pacpte);
	boolean excluirPacote(Pacote pacote);
	List<Pacote> consultarPacotes(String...criterios);

}
