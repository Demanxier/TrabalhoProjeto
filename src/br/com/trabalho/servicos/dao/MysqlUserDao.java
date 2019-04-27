package br.com.trabalho.servicos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.trabalho.domain.Usuario;

public class MysqlUserDao implements UsuarioDao {
	
	@Override
	public boolean salvarUsuario(Usuario usuario){
		
		boolean adicionado = false;
		
		DaoFactory fabrica = DaoFactory.getFactory(DaoFactory.MYSQL);
		Connection conn = fabrica.criarConexao();
		
		try{
			PreparedStatement ps = conn.prepareStatement(MySqlDaoFactory.lerPropriedade("SALVAR_USUARIO"));
			ps.setString(1, usuario.getUsername());
			ps.setString(2, usuario.getSenha());
			
			if(ps.executeUpdate() > 0){
				adicionado = true;
			}
		}catch(SQLException ex){
			Logger.getLogger(MysqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		fabrica.fecharConexao(conn);
		return adicionado;
	}
	
	@Override
	public boolean atualizarUsuario(Usuario usuario){
		boolean atualizado = false;
		
		DaoFactory fabrica = DaoFactory.getFactory(DaoFactory.MYSQL);
		Connection conn = fabrica.criarConexao();
		
		try{
			PreparedStatement ps = conn.prepareStatement(MySqlDaoFactory.lerPropriedade("ATUALIZAR_STATUS_USUARIO"));
			ps.setByte(1, usuario.getStatus());
			ps.setString(2, usuario.getUsername());
			
			if(ps.executeUpdate() > 0){
				atualizado = true;
			}
		}catch (SQLException ex){
			Logger.getLogger(MysqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		fabrica.fecharConexao(conn);
		return atualizado;
	}
	@Override
	public boolean excluirUsuario(Usuario usuario){
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	@Override
	public List<Usuario> consultarUsuario(boolean login, String... criterios){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		if(login && criterios.length == 2){
			DaoFactory fabrica = DaoFactory.getFactory(DaoFactory.MYSQL);
			Connection conn = fabrica.criarConexao();
			
			try{
				PreparedStatement ps = conn.prepareStatement(MySqlDaoFactory.lerPropriedade("VERIFICAR_USUARIO_LOGIN"));
				ps.setString(1, criterios[0]);
				ps.setString(2, criterios[1]);
				
				ResultSet resultados = ps.executeQuery();
				
				if(resultados.next()){
					String tipo = resultados.getString("tipo");
					if(Usuario.TIPO_ADMINISTRADOR.equals(tipo)){
						usuarios.add(new Usuario(resultados.getString("username"),
								resultados.getString("email"), resultados.getString("senha"),
								Usuario.ADMINISTRADOR, resultados.getByte("status")));
					}else if(Usuario.TIPO_FUNCIONARIO.equals(tipo)){
						usuarios.add(new Usuario(resultados.getString("username"),
								resultados.getString("email"), resultados.getString("senha"),
								Usuario.FUNCIONARIO, resultados.getByte("status")));
					}else if(Usuario.TIPO_CONVIDADO.equals(tipo)){
						usuarios.add(new Usuario(resultados.getString("username"),
								resultados.getString("email"), resultados.getString("senha"),
										Usuario.CONVIDADO, resultados.getByte("status")));
					}
						
				}
			}catch(SQLException ex){
				Logger.getLogger(MysqlUserDao.class.getName()).log(Level.SEVERE, null, ex);
			}
			fabrica.fecharConexao(conn);
		}
		return usuarios;
	}

}