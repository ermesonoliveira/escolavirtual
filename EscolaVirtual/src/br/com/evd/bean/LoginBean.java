package br.com.evd.bean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.evd.dao.UsuarioDAO;
import br.com.evd.domain.Usuario;

@ManagedBean(name = "MBLogar")
@ViewScoped
public class LoginBean {
	
	private static Usuario usuario;
	
	public String prepararLogin(){
		usuario = new Usuario();
		
		return "login.xhtml";
		
	}public String efetuarLogin(){
		UsuarioDAO dao = new UsuarioDAO();
		try {
			
			Usuario resultado= dao.buscarPorEmail(usuario);
			
			if (resultado.getSenha().equals(usuario.getSenha())) {
				return "home.xhtml";
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return "PF('dlg3').show()";
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		LoginBean.usuario = usuario;
	}

	

}
