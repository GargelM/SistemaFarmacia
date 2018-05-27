package br.com.farmacia.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import com.mysql.fabric.xmlrpc.base.Array;

import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;


public class ProdutoDAOTeste {
	@Test
	@Ignore
	public void salvar() throws SQLException{

		Produtos p1 = new Produtos();
		p1.setDescricao("Dipirona");
		p1.setPreco(2.92);
		p1.setQuantidade(12L);

		Fornecedores f = new Fornecedores();
		f.setCodigo(16);
		p1.setFornecedores(f);

		ProdutoDAO fdao = new ProdutoDAO();

		fdao.salvar(p1);

	}
	@Test
	@Ignore
	public void listar() throws SQLException{
		ProdutoDAO fdao = new ProdutoDAO();
		ArrayList<Produtos>lista = fdao.listar();
		
		for(Produtos p : lista) {
			System.out.println("Codigo do Produto: " + p.getCodigo());
			System.out.println("Descrição do Produto: " + p.getDescricao());
			System.out.println("Preço do Produto: " + p.getPreco());
			System.out.println("Quantidade do Produto: " + p.getQuantidade());
			System.out.println("Codigo do Fornecedor: " + p.getFornecedores().getCodigo());
			System.out.println("Descrição do Fornecedor: " + p.getFornecedores().getDescricao());
			System.out.println("");
		}
	}
	
	@Test
	public void excluir() throws SQLException{
		Produtos p = new Produtos();
		p.setCodigo(3L);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(p);
	}
}