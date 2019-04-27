package br.com.trabalho.servicos;

public abstract class ServicoCadastro<T> extends Servico {
	
	protected T entidade;
	
	public ServicoCadastro(T entidade) {
		this.entidade = entidade;
	}
	public abstract void cadastrar();

}
