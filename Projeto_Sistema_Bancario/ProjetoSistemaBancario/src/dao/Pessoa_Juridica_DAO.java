package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bd.Conexao_Banco;
import modelo.Pessoa_Juridica;

public class Pessoa_Juridica_DAO {
	
	public void save(Pessoa_Juridica pessoa) {
		
		String sql = "INSERT INTO pessoa_juridica (nome,cpf,mei,data_nascimento) VALUES (?,?,?,?) ";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			// Criar uma conexão com o banco de dados
			
			conn = Conexao_Banco.createConnectionToMysql();
			
			// Criamos uma PreparedStatement,  Para executar uma query
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			// Adicionar os valores que são esperados pela query
			
			pstm.setString(1, pessoa.getNome());
			pstm.setString(2, pessoa.getCpf());
			pstm.setString(3, pessoa.getMei());
			//Estamos usando o método valueOf da classe 
			//java.sql.Date para converter o objeto LocalDate para 
			//java.sql.Date, que é o tipo aceito pelo método setDate do PreparedStatement.
			LocalDate dataNascimento = pessoa.getDataNascimento();
			java.sql.Date sqlDate= java.sql.Date.valueOf(dataNascimento);
				
			pstm.setDate(4, sqlDate);
			
			// Executar a query sql
			
			pstm.execute();
				System.out.println("Pessoa Juridica, Cadastrada!");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			// Fechar as conexões com o banco
		try {
			if (pstm !=null) {
				pstm.close();
			}
			if(conn !=null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		}
		
	}
	
	public void update(Pessoa_Juridica pessoa) {
		
		String sql = "UPDATE pessoa_juridica SET nome = ?, cpf =?, mei= ?, data_nascimento = ?" +
		"WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			// Cria a conexão com o banco
			conn = Conexao_Banco.createConnectionToMysql();
			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valroes apra atualizar
			pstm.setString(1, pessoa.getNome());
			pstm.setString(2, pessoa.getCpf());
			pstm.setString(3, pessoa.getMei());
			//Nesse exemplo, estamos usando o método valueOf da classe 
			//java.sql.Date para converter o objeto LocalDate para 
			//java.sql.Date, que é o tipo aceito pelo método setDate do PreparedStatement.
			LocalDate dataNascimento = pessoa.getDataNascimento();
			
			java.sql.Date sqlDate = java.sql.Date.valueOf(dataNascimento);
			pstm.setDate(3,sqlDate);
			
			//Qual o ID do registro que deseja atualziar ?

			pstm.setInt(4, pessoa.getId());
			
			// Executa a query
			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
					
				}

			}catch(Exception e) {
				e.printStackTrace();
			}
				
		}
		
		
	}
	
	public void deleteByID(int id) {
		
		String sql = "DELETE FROM pessoa_juridica where id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = Conexao_Banco.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm !=null) {
					pstm.close();
				}
				if(conn !=null) {
					conn.close();
					
				}
				
			}catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		
	}
	
	public List<Pessoa_Juridica> getPessoa_Juridica(){
		
		String sql ="SELECT * FROM pessoa_juridica";
		
		List<Pessoa_Juridica> pessoa_juridica = new ArrayList<Pessoa_Juridica>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = Conexao_Banco.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				int id = rset.getInt("id");
				String nome = rset.getString("nome");
				String cpf = rset.getString("cpf");
				String mei = rset.getString("mei");
				String dataNascimentoStr = rset.getString("data_nascimento");
				Pessoa_Juridica pessoa = new Pessoa_Juridica(nome,cpf,mei,dataNascimentoStr);
				pessoa.setId(id);
				pessoa_juridica.add(pessoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		return pessoa_juridica;
		
	}

}

