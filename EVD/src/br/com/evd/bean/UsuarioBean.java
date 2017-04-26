package br.com.evd.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.evd.dao.EscolaDAO;
import br.com.evd.dao.TipoDAO;
import br.com.evd.dao.UsuarioDAO;
import br.com.evd.domain.Escola;
import br.com.evd.domain.Tipo;
import br.com.evd.domain.Usuario;

@ManagedBean(name = "MBUsuario")
@ViewScoped
public class UsuarioBean {
	private Tipo tipo;
	private Escola escola;
	private static Usuario usuario;
	private static ArrayList<Escola> listaEscola;
	private static ArrayList<Tipo> listaTipo = new ArrayList<Tipo>();

	public String prepararNovo() {
		try {
			usuario = new Usuario();
			
			EscolaDAO dao = new EscolaDAO();
			listaEscola = dao.listarEscolas();

			TipoDAO daotp = new TipoDAO();
			listaTipo = daotp.listarTipo();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "cadastroDois";

	}

	public String novo() throws ClassNotFoundException {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			usuario.setMatricula(dao.GerarMatricula());
			dao.salvar(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "login.xhtml";

	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		UsuarioBean.usuario = usuario;

	}

	public ArrayList<Escola> getListaEscola() {
		return listaEscola;
	}

	public void setListaEscola(ArrayList<Escola> listaEscola) {
		UsuarioBean.listaEscola = listaEscola;
	
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	
	}

	public ArrayList<Tipo> getListaTipo() {
		return listaTipo;
	}

	public void setListaTipo(ArrayList<Tipo> listaTipo) {
		UsuarioBean.listaTipo = listaTipo;
	}

}
