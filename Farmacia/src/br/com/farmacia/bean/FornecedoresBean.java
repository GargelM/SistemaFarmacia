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
import br.com.farmacia.util.JSFUtil;

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
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
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
			JSFUtil.adicionarMensagemSucesso("Salvo com sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	
	public void prepararexcluir() {
		fornecedores = itens.getRowData();
	}
	
	
	public void excluir() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.excluir(fornecedores);
			
			ArrayList<Fornecedores>lista = fdao.listar();//atualizar a pagina
			itens = new ListDataModel<Fornecedores>(lista);//esse codigo faz com que atualiza a lista  
			JSFUtil.adicionarMensagemSucesso("Fornecedor excluido com sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JSFUtil.adicionarMensagemErro("Não é possivel excluir um fornecedor que tenha um produto associado!");
			e.printStackTrace();
		}
	}
	
	public void preparareditar() {
		fornecedores = itens.getRowData();
	}
	
	public void editar() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.editar(fornecedores);
			
			ArrayList<Fornecedores>lista = fdao.listar();
			itens = new ListDataModel<Fornecedores>(lista);
			JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso");
		} catch (SQLException e) {
			// TODO: handle exception
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
}
