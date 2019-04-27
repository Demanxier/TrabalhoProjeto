package br.com.trabalho.servicos;


import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Servico {
	
	static final String TIPO_EIS = "TIPO_EIS";
	
	/**Encapsulamento de mapa das propriedades derais do sistema **/
	protected static Properties propriedades;
	
	public Servico() {
		
		if(propriedades == null){
			propriedades = new Properties();
			try{
				propriedades.load(Servico.class.getResourceAsStream("settings.properties"));
			}catch(IOException ex){
				Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
