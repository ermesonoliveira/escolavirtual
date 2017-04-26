package br.com.evd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.evd.domain.Escola;
import br.com.evd.factory.ConexaoFactory;

public class EscolaDAO {


	public void salvar(Escola e) throws SQLException, ClassNotFoundException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO escola ");
		sql.append("(matricula, nome, cnpj) ");
		sql.append("VALUES (?, ?, ?) ");
		
		Connection conexao = ConexaoFactory.getInstance().Conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, e.getMatricula());
		comando.setString(2, e.getNome());
		comando.setString(3, e.getCnpj());
		comando.executeUpdate();
		
	}public ArrayList<Escola> listarEscolas() throws SQLException, ClassNotFoundException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, matricula, nome, cnpj ");
		sql.append("FROM escola ");
		sql.append("ORDER BY id ASC ");

		Connection conexao = ConexaoFactory.getInstance().Conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet result = comando.executeQuery();
		ArrayList<Escola> lista = new ArrayList<Escola>();

		while (result.next()) {
			Escola esc = new Escola();
			esc.setId(result.getInt("id"));
			esc.setMatricula(result.getLong("matricula"));
			esc.setNome(result.getString("nome"));
			esc.setCnpj(result.getString("cnpj"));
			lista.add(esc);

		}
		return lista;

	}public static void main(String[] args) {
		EscolaDAO esc = new EscolaDAO();
		
		try {
			ArrayList<Escola> list = esc.listarEscolas();
			
			for(Escola e : list){
				
				System.out.println(e);
				
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	

}
