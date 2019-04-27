package br.com.trabalho.servicos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.trabalho.domain.Pacote;
import br.com.trabalho.servicos.ServicoCarregarPacotes;

public class MysqlPacoteDao implements PacoteDao {
	
	@Override
	public boolean salvarPacote(Pacote pacote){
		
		boolean salvo = false;
		
		DaoFactory fabrica = DaoFactory.getFactory(DaoFactory.MYSQL);
		Connection conexao = fabrica.criarConexao();
		
		try{
			PreparedStatement sentenca = conexao.prepareStatement(
					MySqlDaoFactory.lerPropriedade("PACOTE_SALVAR_COMPLETO"));
			
			sentenca.setString(1, pacote.getNome());
			
			String descricao = pacote.getDescricao();
			
			if(descricao != null && !"".equals(descricao)){
				sentenca.setString(2, descricao);
			}else{
				sentenca.setString(2, "");
			}
			
			sentenca.setLong(3, pacote.getDataInicio().getTime());
			sentenca.setLong(4, pacote.getDataFim().getTime());
			sentenca.setLong(5, pacote.getValorFechado());
			salvo = (sentenca.executeUpdate() > 0);
		}catch (SQLException ex){
			Logger.getLogger(MysqlPacoteDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return salvo;
	}
	
	@Override
	public boolean atualizarPacote(Pacote pacote){
		throw new UnsupportedOperationException("Not supported yet");
	}
	public boolean excluirPacote(Pacote pacote){
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	@Override
	public List<Pacote> consultarPacotes(String... criterios){
		
		List<Pacote> pacotes = new ArrayList<Pacote>();
		DaoFactory fabrica = DaoFactory.getFactory(DaoFactory.MYSQL);
		Connection conexao = fabrica.criarConexao();
		
		if(criterios != null && criterios.length == 1 &&
				ServicoCarregarPacotes.TODOS.equals(criterios[0])){
			
			Statement consulta;
			
			try{
				consulta= conexao.createStatement();
				ResultSet itens = consulta.executeQuery(
						MySqlDaoFactory.lerPropriedade("PACOTE_CONSULTAR_TODOS"));
				
				while(itens.next()){
					pacotes.add(new Pacote(itens.getString(PacoteDao.CAMPO_NOME),
							itens.getString(PacoteDao.CAMPO_DESCRICAO),
							new Date(itens.getLong(PacoteDao.CAMPO_DATA_INICIO)),
							new Date(itens.getLong(PacoteDao.CAMPO_DATA_FIM)),
							itens.getLong(PacoteDao.CAMPO_VALOR)));
				}
			}catch(SQLException ex){
				Logger.getLogger(MysqlPacoteDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return pacotes;
	}
	

}
