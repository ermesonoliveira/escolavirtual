package br.com.evd.bean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.evd.dao.EscolaDAO;
import br.com.evd.domain.Escola;

@ManagedBean(name = "MBEscola")
@ViewScoped
public class EscolaBean {
	private static Escola escola;

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		escola = EscolaBean.escola;
	}

	public String prepararEscola() {
		escola = new Escola();
		System.out.println(escola);
		return "cadastroUm.xhtml";
	}

	public String novaEscola() throws ClassNotFoundException {
		EscolaDAO e = new EscolaDAO();
		System.out.println(escola);
		try {
			escola.setMatricula(1L);
			e.salvar(escola);

		} catch (SQLException e1) {
			e1.printStackTrace();
			
		}return "escolhaCadastro.xhtml";

	}

}
