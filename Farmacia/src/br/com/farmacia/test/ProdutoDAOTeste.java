package br.com.farmacia.test;

import java.sql.SQLException;


import org.junit.Test;


import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;


public class ProdutoDAOTeste {
	@Test
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
}