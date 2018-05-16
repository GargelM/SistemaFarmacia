//fazer comunicaçao parte web com banco de dados. java vx xhtml
package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;

@ManagedBean(name = "MBFornecedores") //uma forma de passar referencia  usara para chamar em vez de chamar fornecedoresbean 
@ViewScoped
public class FornecedoresBean {

	private Fornecedores fornecedores;
	
	private ListDataModel<Fornecedores>itens;
//acessar forma externa o itens
	public ListDataModel<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Fornecedores> itens) {
		this.itens = itens;
	}
	
	//ira construir o q esta abaixo assim q a pagina for iniciada
	@PostConstruct
	public void prepararPesquisa() {
		
		
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			ArrayList<Fornecedores>lista = fdao.listar();
			itens = new ListDataModel<Fornecedores>(lista);//converter a variavel em datamodel
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public void prepararNovo() {
		fornecedores = new Fornecedores();
		
	}
	
	public void novo() {
		
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);
			
			ArrayList<Fornecedores>lista = fdao.listar();//atualizar a pagina
			itens = new ListDataModel<Fornecedores>(lista);//esse codigo faz com que atualiza a lista  
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
