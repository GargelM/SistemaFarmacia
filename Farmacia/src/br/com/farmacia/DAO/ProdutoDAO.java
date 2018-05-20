package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.ConexaoFactory;

public class ProdutoDAO {
	public void salvar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();// Stringbuid usa ele para n ficar concatenando com + valor e etc. ...
												// ele faz concatenar valores sem ficar utilizando os parametros
		sql.append("INSERT INTO produtos ");
		sql.append("(descricao, quantidade, preco, fornecedores_codigo) ");
		sql.append("VALUES (?,?,?,?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setLong(2, p.getQuantidade());
		comando.setDouble(3, p.getPreco());
		comando.setLong(4, p.getFornecedores().getCodigo());
		comando.executeUpdate();

	}
}
