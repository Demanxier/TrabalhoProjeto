package br.com.trabalho.servicos;

public abstract class ServicoVerificador extends Servico {
	
	/**
	 * Verificar existencia de um registro no sistema.
	 * @return<code>true</code>
	 * 		  <code>false</code>
	 */
	public abstract boolean executar();

}
