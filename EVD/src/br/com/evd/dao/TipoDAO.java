package br.com.evd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.evd.domain.Tipo;
import br.com.evd.factory.ConexaoFactory;

public class TipoDAO {
	
	public void salvar(Tipo t) throws SQLException, ClassNotFoundException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tipo ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.getInstance().Conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, t.getDescricao());
		
		comando.executeUpdate();

	}public ArrayList<Tipo> listarTipo() throws SQLException, ClassNotFoundException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, descricao ");
		sql.append("FROM tipo ");
		sql.append("ORDER BY id ASC ");

		Connection conexao = ConexaoFactory.getInstance().Conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet result = comando.executeQuery();
		ArrayList<Tipo> lista = new ArrayList<Tipo>();

		while (result.next()) {
			Tipo tp = new Tipo();
			tp.setId(result.getInt("id"));
			tp.setDescricao(result.getString("descricao"));
			lista.add(tp);

		}
		return lista;
	}
	

}
