package br.com.evd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.com.evd.domain.Usuario;
import br.com.evd.factory.ConexaoFactory;

public class UsuarioDAO {

	public void salvar(Usuario u) throws SQLException, ClassNotFoundException {

		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO usuario ");
		sql.append("(matricula, nome, email, senha, cpf, telefone, escola_id, tipo_id) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");
		Connection conexao = ConexaoFactory.getInstance().Conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, u.getMatricula());
		comando.setString(2, u.getNome());
		comando.setString(3, u.getEmail());
		comando.setString(4, u.getSenha());
		comando.setString(5, u.getCpf());
		comando.setString(6, u.getTelefone());
		comando.setLong(7, u.getEscola().getId());
		comando.setLong(8, u.getTipo().getId());
		comando.executeUpdate();

	}

	public void Editar(Usuario u) throws SQLException, ClassNotFoundException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE usuario ");
		sql.append("SET matricula = ?, nome = ?, email = ?, senha = ?, cpf = ?, telefone = ?, escola_id = ?, tipo_id = ? ");
		sql.append("WHERE id = ? ");

		Connection conexao = ConexaoFactory.getInstance().Conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, u.getMatricula());
		comando.setString(2, u.getNome());
		comando.setString(3, u.getEmail());
		comando.setString(4, u.getSenha());
		comando.setString(5, u.getCpf());
		comando.setString(6, u.getTelefone());
		comando.setLong(7, u.getEscola().getId());
		comando.setLong(8, u.getTipo().getId());
		comando.setLong(9, u.getId());
		comando.executeUpdate();

	}

	public void Excluir(Usuario u) throws SQLException, ClassNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM usuario ");
		sql.append("WHERE id = ? ");
		Connection conexao = ConexaoFactory.getInstance().Conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, u.getId());
		comando.executeUpdate();

		/* retorna apenas um único objeto */
	}

	public Usuario buscarPorID(Usuario u) throws SQLException, ClassNotFoundException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT matricula, nome, email ");
		sql.append("FROM usuario ");
		sql.append("WHERE id = ? ");

		Connection conexao = ConexaoFactory.getInstance().Conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, u.getId());
		ResultSet resultado = comando.executeQuery();
		Usuario retorno = null;

		if (resultado.next()) {
			retorno = new Usuario();
			retorno.setMatricula(resultado.getLong("matricula"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
		}
		
		return retorno;

	}

	public ArrayList<Usuario> listarUsuarios() throws SQLException, ClassNotFoundException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT matricula, nome ");
		sql.append("FROM usuario ");
		sql.append("ORDER BY matricula ASC ");

		Connection conexao = ConexaoFactory.getInstance().Conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet result = comando.executeQuery();
		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (result.next()) {
			Usuario usu = new Usuario();
			usu.setMatricula(result.getLong("matricula"));
			usu.setNome(result.getString("nome"));
			lista.add(usu);

		}
		return lista;

	}

	public ArrayList<Usuario> listarPorNome(Usuario u) throws SQLException, ClassNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT matricula, nome ");
		sql.append("FROM usuario ");
		sql.append("WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC ");

		Connection conexao = ConexaoFactory.getInstance().Conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + u.getNome() + "%");
		ResultSet result = comando.executeQuery();
		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (result.next()) {
			Usuario item = new Usuario();
			item.setMatricula(result.getLong("matricula"));
			item.setNome(result.getString("nome"));
			lista.add(item);

		}

		return lista;

	}public Long GerarMatricula() throws SQLException, ClassNotFoundException{
		Calendar cal = GregorianCalendar.getInstance();
		 int year = cal.get(Calendar.YEAR);
		 int id = 0;
		 
		 	StringBuilder sql = new StringBuilder();
			sql.append("SELECT MAX(ID) FROM usuario");
			Connection conexao = ConexaoFactory.getInstance().Conectar();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			ResultSet resultado = comando.executeQuery();
		
			while(resultado.next()){	
				id = resultado.getInt(1);	
			}
			
			StringBuilder gerador = new StringBuilder();
			gerador.append(year);
			gerador.append(id+1);
			Long matricula = Long.parseLong((gerador.toString()));
			
		return matricula;
		
		
	}
	
	public Usuario buscarPorEmail(Usuario u) throws SQLException, ClassNotFoundException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nome, senha, matricula ");
		sql.append("FROM usuario ");
		sql.append("WHERE email = ? ");

		Connection conexao = ConexaoFactory.getInstance().Conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, u.getEmail());
		ResultSet resultado = comando.executeQuery();
		Usuario retorno = null;

		if (resultado.next()) {
			retorno = new Usuario();
			retorno.setMatricula(resultado.getLong("matricula"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setSenha(resultado.getString("senha"));
		}
		
		return retorno;

	}
//	public static void main(String[] args) {
//		UsuarioDAO usu = new UsuarioDAO();
//		Usuario usuario = new Usuario();
//		usuario.setEmail("mvp_ermeson");
//		try {
//		
//		Usuario usa = usu.buscarPorEmail(usuario);
//		System.out.println(usa.getSenha());
//			
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	

}
