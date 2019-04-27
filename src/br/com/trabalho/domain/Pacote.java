package br.com.trabalho.domain;

import java.io.Serializable;
import java.util.Date;

public class Pacote implements Serializable {
	
	private String nome;
	private String descricao;
	private Date dataInicio;
	private Date dataFim;
	private long valorFechado;
	
	public Pacote(){
		
	}

	public Pacote(String nome, String descricao, Date dataInicio, Date dataFim, long valorFechado) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.valorFechado = valorFechado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public long getValorFechado() {
		return valorFechado;
	}

	public void setValorFechado(long valorFechado) {
		this.valorFechado = valorFechado;
	}
	
	
}