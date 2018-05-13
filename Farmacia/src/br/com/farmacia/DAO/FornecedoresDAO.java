package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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

	public void excluir(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());// 1 primeiro registro
		comando.executeUpdate();

	}

	public void editar(Fornecedores f) throws SQLException {
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

	public Fornecedores buscaPorCodigo(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;

		if (resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getLong("codigo"));// se ele encontrar algo passa a variavel de retorno
															// e guarda a informaçao que foi pega ali dentro que seria a
															// informaçao q esta no resultado
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;
	}
	
	public ArrayList<Fornecedores>buscarPorDescricao(Fornecedores f)throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		
		sql.append("WHERE descricao LIKE ? ");//like buscar por partes
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + f.getDescricao() + "%");//%sempre q utilizar like usa % pois entende q pode buscar por parte
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedores>lista = new ArrayList<Fornecedores>();
		while(resultado.next()) {
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			lista.add(item);
		}
		return lista;
	}

	public ArrayList<Fornecedores> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		while (resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));

			lista.add(f);
		}
		return lista;
	}

	public static void main(String[] args) {
		// Fornecedores f1 = new Fornecedores();
		// f1.setDescricao("DESCRICAO 1");

		// Fornecedores f2 = new Fornecedores();
		// f2.setDescricao("DESCICAO 2");

		// Fornecedores f3 = new Fornecedores();
		// f3.setCodigo(2L);

		/*
		 * Fornecedores f4 = new Fornecedores(); teste edicao f4.setCodigo(2);
		 * f4.setDescricao("bbb");
		 */

		/*
		 * busca por codigo Fornecedores f5 = new Fornecedores(); f5.setCodigo(3);
		 * Fornecedores f6 = new Fornecedores(); f6.setCodigo(9);
		 */
		
		Fornecedores f7 = new Fornecedores();
		f7.setDescricao("e");
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			// fdao.salvar(f1);
			// fdao.salvar(f2);
			// fdao.excluir(f3);
			// fdao.editar(f4);

			/*
			 * busca por codigo Fornecedores f7 = fdao.buscaPorCodigo(f5); Fornecedores f8 =
			 * fdao.buscaPorCodigo(f6); System.out.println("buscado com sucesso resultado: "
			 * + f7); System.out.println("buscado com sucesso resultado: " + f8);
			 */

			//ArrayList<Fornecedores> lista = fdao.listar();
			ArrayList<Fornecedores> lista = fdao.buscarPorDescricao(f7);
			for (Fornecedores f : lista) {
				System.out.println("Resultado " + f);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar");
			e.printStackTrace();
		}

	}
}
