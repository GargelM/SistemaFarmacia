package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

	
	public void editar(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao  = ? ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();
	}
	
	public Fornecedores buscaPorCodigo(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;
		
		
		
		comando.executeUpdate();
	}
	
	public static void main(String[] args) {
		//Fornecedores f1 = new Fornecedores();
		//f1.setDescricao("DESCRICAO 1");
		
		//Fornecedores f2 = new Fornecedores();
		//f2.setDescricao("DESCICAO 2");
		
		//Fornecedores f3 = new Fornecedores();
		//f3.setCodigo(2L);
		
		Fornecedores f4 = new Fornecedores();
		f4.setCodigo(2);
		f4.setDescricao("bbb");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			//fdao.salvar(f1);
			//fdao.salvar(f2);
			//fdao.excluir(f3);
			fdao.editar(f4);
			System.out.println("editado com sucesso");
		} catch (SQLException e) {
			System.out.println("Erro ao salvar");
			e.printStackTrace();
		}
		
		
	}
}
