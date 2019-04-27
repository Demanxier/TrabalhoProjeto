package br.com.trabalho.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import br.com.trabalho.domain.Diaria;
import br.com.trabalho.domain.Pacote;
import br.com.trabalho.servicos.ServicoCadastroPacote;
import br.com.trabalho.servicos.ServicoCarregarDiarias;
import br.com.trabalho.servicos.ServicoCarregarPacotes;

public class MbAluguel implements Serializable {
	
	private List<Pacote> pacotes;
	private List<Diaria> diariasReferencia;
	private Pacote pacoteCadastro;
	private String valorPacoteAvaliacao;
	
	public MbAluguel(){
		
	}
	
	@PostConstruct
	public void carregarDados(){
		pacotes = new ServicoCarregarPacotes().carregarDados(ServicoCarregarPacotes.TODOS);
		diariasReferencia = new ServicoCarregarDiarias().carregarDados(ServicoCarregarDiarias.TODOS);
		pacoteCadastro = new Pacote();
	}

	public List<Pacote> getPacotes() {
		return pacotes;
	}

	public void setPacotes(List<Pacote> pacotes) {
		this.pacotes = pacotes;
	}

	public List<Diaria> getDiariasReferencia() {
		return diariasReferencia;
	}

	public void setDiariasReferencia(List<Diaria> diariasReferencia) {
		this.diariasReferencia = diariasReferencia;
	}
	
	public void salvarPacote(){
		if(validarPreco()){
			new ServicoCadastroPacote(pacoteCadastro).cadastrar();
			pacotes = new ServicoCarregarPacotes().carregarDados(ServicoCarregarPacotes.TODOS);
		}
	}
	private boolean validarPreco(){
		boolean valido = true;
		try{
			if(valorPacoteAvaliacao != null){
				Long valor = new Long(valorPacoteAvaliacao);
				System.out.println("O valor fornecido pelo usuario é de "
						+ valor.longValue());
			}
		}catch(NumberFormatException nfe){
			valido = false;
		}
		return valido;
	}

	public Pacote getPacoteCadastro() {
		return pacoteCadastro;
	}

	public void setPacoteCadastro(Pacote pacoteCadastro) {
		this.pacoteCadastro = pacoteCadastro;
	}

	public String getValorPacoteAvaliacao() {
		return valorPacoteAvaliacao;
	}

	public void setValorPacoteAvaliacao(String valorPacoteAvaliacao) {
		this.valorPacoteAvaliacao = valorPacoteAvaliacao;
	}
	
}