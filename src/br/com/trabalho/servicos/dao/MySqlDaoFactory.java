package br.com.trabalho.servicos.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

class MySqlDaoFactory extends DaoFactory {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	static{
		try{
			Class.forName(DRIVER).newInstance();		
		}catch(InstantiationException ex){
			Logger.getLogger(MySqlDaoFactory.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IllegalAccessException ex){
			Logger.getLogger(MySqlDaoFactory.class.getName()).log(Level.SEVERE, null, ex);
		}catch(ClassNotFoundException ex){
			Logger.getLogger(MySqlDaoFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public MySqlDaoFactory(){
		propriedades = new Properties();
		try{
			propriedades.load(MySqlDaoFactory.class.getResourceAsStream("db_mysql.properties"));
		}catch(IOException ex){
			Logger.getLogger(MySqlDaoFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@Override
	Connection criarConexao(){
		Connection conexao = null;
		try{
			conexao = DriverManager.getConnection(propriedades.getProperty("DB_URL"),
					propriedades.getProperty("DB_USUARIO"), propriedades.getProperty("DB_SENHA"));
		}catch(SQLException ex){
			Logger.getLogger(MySqlDaoFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
		return conexao;
	}
	
	@Override
	public UsuarioDao getUsuarioDao(){
		return new MysqlUserDao();
	}
	
	@Override
	public DiariaDao getDiariaDao(){
		return new MysqlDiariaDao();
	}

	@Override
	public PacoteDao getPacoteDao(){
		return new MysqlPacoteDao();
	}
}