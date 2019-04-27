package br.com.trabalho.servicos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DaoFactory {
	
	public static final String MYSQL = "mysql";
	
	
	protected static Properties propriedades;
	
	public static DaoFactory getFactory(String tipoFabrica){
		
		DaoFactory fabrica = null;
		
		if(MYSQL.equals(tipoFabrica)){
			fabrica = new MySqlDaoFactory();
		}
		 return fabrica;
	}
	public abstract UsuarioDao getUsuarioDao();
	public abstract DiariaDao getDiariaDao();
	public abstract PacoteDao getPacoteDao();
	
	abstract Connection criarConexao();
	
	void fecharConexao(Connection conexao){
		try{
			if(conexao != null){
				conexao.close();
			}
		}catch(SQLException sqle){
			Logger.getLogger("DaoFactory").log(Level.SEVERE, "Problemas ao fechar conexão!");
			Logger.getLogger("DaoFactory").log(Level.SEVERE, "Erro: {0}", sqle.getMessage());
		}
	}
	
	static String lerPropriedade(String chave){
		return propriedades.getProperty(chave);
	}

}