package br.com.trabalho.servicos.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.trabalho.domain.Diaria;
import br.com.trabalho.servicos.ServicoCarregarDiarias;

public class MysqlDiariaDao implements DiariaDao {
	
	@Override
	public boolean salvarDiaria(Diaria diaria){
		throw new UnsupportedOperationException("Not supported yet.");
	}
	@Override
	public boolean atualizarDiaria(Diaria diaria){
		throw new UnsupportedOperationException("Not supported yet.");
	}
	@Override
	public boolean excluirDiaria(Diaria diaria){
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	@Override
	public List<Diaria> consultarDiaria(String...criterios){
		List<Diaria> diarias = new ArrayList<Diaria>();
		DaoFactory fabrica = DaoFactory.getFactory(DaoFactory.MYSQL); 
		Connection conexao = fabrica.criarConexao();
		
		if(criterios != null && criterios.length == 1 &&
				ServicoCarregarDiarias.TODOS.equals(criterios[0])){
			Statement consulta;
			try{
				consulta = conexao.createStatement();
				ResultSet itens = consulta.executeQuery(
						MySqlDaoFactory.lerPropriedade("DIARIA_CONSULTAR_TODOS"));
				
				while(itens.next()){
					diarias.add(new Diaria(itens.getString(DiariaDao.CAMPO_MES_REFERENCIA),
							itens.getInt(DiariaDao.CAMPO_ANO_REFERENCIA),
							itens.getInt(DiariaDao.CAMPO_VALOR)));
				}
			}catch(SQLException ex){
				Logger.getLogger(MysqlPacoteDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return diarias;
	}

}
