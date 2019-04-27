package br.com.trabalho.domain;

import java.io.Serializable;

public class Diaria implements Serializable {
	
	public static final String JANEIRO = "Janeiro";
	public static final String FEVEREIRO = "Fevereiro";
	public static final String MARÇO = "Março";
	public static final String ABRIL = "Abril";	
	public static final String MAIO = "Maio";
	public static final String JUNHO = "Junho";
	public static final String JULHO = "Julho";
	public static final String AGOSTO = "Agosto";
	public static final String SETEMBRO ="Setembro";
	public static final String OUTUBRO = "Outobro";
	public static final String NOVEMBRO = "Novembro";
	public static final String DEZEMBRO = "Dezembro";
	
	private String mesReferecia;
	private int anoReferencia;
	private long valor;
	
	public Diaria(){
		
	}

	public Diaria(String mesReferecia, int anoReferencia, long valor) {
		super();
		this.mesReferecia = mesReferecia;
		this.anoReferencia = anoReferencia;
		this.valor = valor;
	}

	public String getMesReferecia() {
		return mesReferecia;
	}

	public void setMesReferecia(String mesReferecia) {
		this.mesReferecia = mesReferecia;
	}

	public int getAnoReferencia() {
		return anoReferencia;
	}

	public void setAnoReferencia(int anoReferencia) {
		this.anoReferencia = anoReferencia;
	}

	public long getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}
	
}