package br.com.trabalho.servicos.dao;

import java.util.List;

import br.com.trabalho.domain.Diaria;

public interface DiariaDao {
	
	static final String CAMPO_MES_REFERENCIA = "mes_referencia";
	static final String CAMPO_ANO_REFERENCIA = "ano_referencia";
	static final String CAMPO_VALOR = "valor";
	
	boolean salvarDiaria(Diaria diaria);
	boolean atualizarDiaria(Diaria diaria);
	boolean excluirDiaria(Diaria diaria);
	List<Diaria> consultarDiaria(String...criterios);

}