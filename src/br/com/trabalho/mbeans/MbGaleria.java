package br.com.trabalho.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MbGaleria implements Serializable {
	
	private List<String> imagensChales;
	private List<String> imagensPraia;
	
	public MbGaleria(){
		
	}
	
	@PostConstruct
	public void carregarImagens(){
		
		imagensPraia = new ArrayList<String>();
		
		for(int i = 1; i <= 15; i++){
			imagensPraia.add("praia" + i + ".jpg");
		}
		
		imagensChales = new ArrayList<String>();
		
		for(int i = 1; i <= 15; i++){
			imagensChales.add("Chales" + i + ".jpg");
		}
	}

	public List<String> getImagensChales() {
		return imagensChales;
	}

	public void setImagensChales(List<String> imagensChales) {
		this.imagensChales = imagensChales;
	}

	public List<String> getImagensPraia() {
		return imagensPraia;
	}

	public void setImagensPraia(List<String> imagensPraia) {
		this.imagensPraia = imagensPraia;
	}
	
}