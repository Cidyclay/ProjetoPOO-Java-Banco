package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import bd.Conexao_Banco;
import modelo.Conta_Fisica;
import modelo.Pessoa_Fisica;
@SuppressWarnings("unused")
public class Pessoa_Fisica_DAO {
		
		public void save(Pessoa_Fisica pessoa) {
			
			Pessoa_Fisica_DAO p = new Pessoa_Fisica_DAO();
			for(Pessoa_Fisica pessoas : p.getPessoa_Fisica()){
				if(pessoas.getCpf().equals(pessoa.getCpf())){
					
					System.out.println("CPF, já cadastrado!!");
					break;

			  }

			
			String sql = "INSERT INTO pessoa_fisica (nome,cpf,data_nascimento) VALUES (?,?,?)"; 
			
			Connection conn = null;
			PreparedStatement pstm = null;
			
			try {
						// Criar uma conexão com o banco de dados
				
					conn = Conexao_Banco.createConnectionToMysql();
				
					// Criamos uma PreparedStatement, Para executar uma query
					
					pstm = (PreparedStatement) conn.prepareStatement(sql);
					
					// Adicionar os valores que são esperados pela query
					
					pstm.setString(1, pessoa.getNome());
					pstm.setString(2,pessoa.getCpf());
						//Estamos usando o método valueOf da classe 
						//java.sql.Date para converter o objeto LocalDate para 
						//java.sql.Date, que é o tipo aceito pelo método setDate do PreparedStatement.
					LocalDate dataNascimento = pessoa.getDataNascimento();
					java.sql.Date sqlDate = java.sql.Date.valueOf(dataNascimento);
						pstm.setDate(3, sqlDate);
						
						// Executa a query sql
							pstm.execute();
							System.out.println("Pessoa cadastrada");
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				// Fechar as conexões
				try {
					if (pstm !=null) {
						pstm.close();
					}
					if (conn != null) {
						conn.close();
					}
					
				}catch(Exception e) {
					 e.printStackTrace();
				}
			}
			
			}
		}public void update(Pessoa_Fisica pessoa) {
			
			String sql = "UPDATE pessoa_fisica SET nome = ?, cpf = ?, data_nascimento = ? "+
			"WHERE id = ?";
			Connection conn = null;
			PreparedStatement pstm = null;
			
			try {
				// Cria a conexão com o banco
				conn = Conexao_Banco.createConnectionToMysql();
				
				// Criar a classe para executar a query
				pstm = (PreparedStatement) conn.prepareStatement(sql);
				
				
				//Adicionar os valores para atualizar
				pstm.setString(1, pessoa.getNome());
				pstm.setString(2, pessoa.getCpf());
				//Nesse exemplo, estamos usando o método valueOf da classe 
				//java.sql.Date para converter o objeto LocalDate para 
				//java.sql.Date, que é o tipo aceito pelo método setDate do PreparedStatement.
			LocalDate dataNascimento = pessoa.getDataNascimento();
			java.sql.Date sqlDate = java.sql.Date.valueOf(dataNascimento);
				pstm.setDate(3, sqlDate);
				
				// Qual o ID do registro que deseja atualizar ?
				pstm.setInt(4, pessoa.getId());
				// Executa a query
				pstm.execute();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
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
			
			
		} public void deleteByID(int id) {
			
			String sql  = "DELETE FROM pessoa_fisica where id = ?";
				
			Connection conn = null;
			PreparedStatement pstm = null;
			
			try {
				conn = Conexao_Banco.createConnectionToMysql();
				pstm = (PreparedStatement) conn.prepareStatement(sql);
				pstm.setInt(1, id);
				pstm.execute();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstm !=null) {
						pstm.close();
					}
					if(conn != null) {
						conn.close();
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public List<Pessoa_Fisica> getPessoa_Fisica() {

		    String sql = "SELECT * FROM pessoa_fisica";
		    
		    List<Pessoa_Fisica> pessoa_Fisica = new ArrayList<Pessoa_Fisica>();

		    Connection conn = null;
		    PreparedStatement pstm = null;
		    ResultSet rset = null;

		    try {
		        conn = Conexao_Banco.createConnectionToMysql();
		        pstm = (PreparedStatement) conn.prepareStatement(sql);
		        rset = pstm.executeQuery();

		        while (rset.next()) {
		            int id = rset.getInt("id");
		            String nome = rset.getString("nome");
		            String cpf = rset.getString("cpf");
		            String dataNascimentoStr = rset.getString("data_nascimento");
		            Pessoa_Fisica pessoa = new Pessoa_Fisica(nome, cpf, "", dataNascimentoStr);
		            pessoa.setId(id);
		            pessoa_Fisica.add(pessoa);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally{
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

		    return pessoa_Fisica;
		}
		public Pessoa_Fisica acharPessoa_Fisica(int id) {
			 Pessoa_Fisica Pessoa_F = null;
		
			Pessoa_Fisica_DAO c = new Pessoa_Fisica_DAO();
			 for(Pessoa_Fisica p : c.getPessoa_Fisica()) {
				 	if(p.getId()==(id)) {
				 		
				 		 Pessoa_F = p;
				 		 break;
				 		
				 		
				 	}
				 
			}
			 return Pessoa_F;
			
			
			
		}
		

			
		
	}






	
	
	