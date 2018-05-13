package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

public class FornecedoresDAO {
	public void salvar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();// Stringbuid usa ele para n ficar concatenando com + valor e etc. ...
												// ele faz concatenar valores sem ficar utilizando os parametros
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();

	}
	
	public void excluir(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());//1 primeiro registro
		comando.executeUpdate();
		
	}


	public static void main(String[] args) {
		//Fornecedores f1 = new Fornecedores();
		//f1.setDescricao("DESCRICAO 1");
		
		//Fornecedores f2 = new Fornecedores();
		//f2.setDescricao("DESCICAO 2");
		
		Fornecedores f3 = new Fornecedores();
		f3.setCodigo(2L);
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			//fdao.salvar(f1);
			//fdao.salvar(f2);
			fdao.excluir(f3);
			System.out.println("deledado com sucesso");
		} catch (SQLException e) {
			System.out.println("Erro ao salvar");
			e.printStackTrace();
		}
		
		
	}
}
