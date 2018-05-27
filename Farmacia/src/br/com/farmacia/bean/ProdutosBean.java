package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBProduto") // uma forma de passar referencia usara para chamar em vez de chamar
									// fornecedoresbean
@ViewScoped
public class ProdutosBean {
	private Produtos produtos;
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;

	private ArrayList<Fornecedores> comboFornecedores;

	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}

	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	// ira construir o q esta abaixo assim q a pagina for iniciada
	@PostConstruct
	public void prepararPesquisa() {

		try {
			ProdutoDAO pdao = new ProdutoDAO();
			itens = pdao.listar();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void prepararNovo() {
		produtos = new Produtos();

	}

	/*
	 * public void novo() {
	 * 
	 * try { ProdutoDAO pdao = new ProdutoDAO(); pdao.salvar(produtos);
	 * 
	 * itens = pdao.listar();//atualizar a pagina
	 * 
	 * JSFUtil.adicionarMensagemSucesso("Salvo com sucesso"); } catch (SQLException
	 * e) { // TODO Auto-generated catch block
	 * JSFUtil.adicionarMensagemErro("ex.getMessage()"); e.printStackTrace(); } }
	 * 
	 * 
	 * public void excluir() { try { ProdutoDAO pdao = new ProdutoDAO();
	 * pdao.excluir(produtos);
	 * 
	 * itens = pdao.listar();//atualizar a pagina
	 * JSFUtil.adicionarMensagemSucesso("Fornecedor excluido com sucesso"); } catch
	 * (SQLException e) { // TODO Auto-generated catch block JSFUtil.
	 * adicionarMensagemErro("Não é possivel excluir um fornecedor que tenha um produto associado!"
	 * ); e.printStackTrace(); } }
	 * 
	 * 
	 * 
	 * public void editar() { try { ProdutoDAO pdao = new ProdutoDAO();
	 * pdao.editar(produtos);
	 * 
	 * itens = pdao.listar();
	 * JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso"); } catch
	 * (SQLException e) { // TODO: handle exception
	 * JSFUtil.adicionarMensagemErro("ex.getMessage()"); e.printStackTrace(); } }
	 */
}
