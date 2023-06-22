package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bd.Conexao_Banco;
import modelo.Conta_Juridica;


public class Conta_Juridica_DAO  {



	public void save(Conta_Juridica conta) {
		 
        String sql = "INSERT INTO conta_juridica (id_conta_juridica,id_pessoa_juridica,saldo, status) VALUES (?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
        	// Criar uma conexão com o banco de dados 
        	
        	conn = Conexao_Banco.createConnectionToMysql();
        	
        	pstm = (PreparedStatement) conn.prepareStatement(sql);
        	pstm.setInt(1, conta.getIdPessoa_Juridica());
            pstm.setInt(2, conta.getIdPessoa_Juridica());
            pstm.setDouble(3, conta.getSaldo());
            pstm.setBoolean(4, conta.getStatus());

            pstm.execute();

            System.out.println("Conta cadastrada com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	
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
	
	public void updateSaldo(Conta_Juridica conta) {
		
		
		String sql = "UPDATE conta_juridica set saldo = ? " +"WHERE id_conta_juridica = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Cria a conexão com o banco
			conn = Conexao_Banco.createConnectionToMysql();
			
			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores para atualizar
			pstm.setDouble(1, conta.getSaldo());
			//O id da pessoa_Juridica
			pstm.setInt(2,conta.getIdPessoa_Juridica());
			
			pstm.execute();
			
			}catch(Exception e) {
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

	public void updateStatus(Conta_Juridica conta) {
		
		String sql = "UPDATE conta_juridica set status = ? "+"WHERE id_conta_juridica = ?";
		
		Connection conn = null;
		PreparedStatement pstm =null;
		
		try {
			
			//Criar a conexão com o banco
			conn = Conexao_Banco.createConnectionToMysql();
			
			//Criar a classe para executar a query 
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			
			  pstm.setBoolean(1, conta.getStatus());
			  pstm.setInt(2, conta.getIdPessoa_Juridica());
			  pstm.execute();
		}catch(Exception e) {
			
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
		
		String sql = "DELETE  FROM conta_juridica WHERE id_conta_juridica = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = Conexao_Banco.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1,id);
			pstm.execute();
		}catch (Exception e) {
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
		
		
	}
	public List<Conta_Juridica> ListarContas_Juridicas(){
		
		String sql = "SELECT * FROM conta_juridica";
		
		List<Conta_Juridica> conta_Juridica = new ArrayList<Conta_Juridica>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = Conexao_Banco.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				 
				Pessoa_Juridica_DAO c = new Pessoa_Juridica_DAO();
				@SuppressWarnings("unused")
				int id = rset.getInt("id_conta_Juridica");
				int idd = rset.getInt("id_pessoa_Juridica");
				double saldo = rset.getDouble("saldo");
				boolean status = rset.getBoolean("status");
				Conta_Juridica conta = new Conta_Juridica(c.acharPessoa_Juridica(idd),saldo,status);
				conta_Juridica.add(conta);
				
			}
			
		}catch(Exception e){
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
		
		return conta_Juridica;
		
		
	}
	public Conta_Juridica acharConta_Juridica(int id) {
		  Conta_Juridica Conta_F = null;
	
		Conta_Juridica_DAO c = new Conta_Juridica_DAO();
		 for(Conta_Juridica p : c.ListarContas_Juridicas()) {
			 	if(p.getIdPessoa_Juridica()==(id)) {
			 		
			 		 Conta_F = p;
			 		 break;
			 		
			 		
			 	}
			 
		}
		 return Conta_F;
		
		
		
	}
	public Conta_Juridica acharConta_Juridica_cpf(String cpf) {
		  Conta_Juridica Conta_F = null;
	
		Conta_Juridica_DAO c = new Conta_Juridica_DAO();
		 for(Conta_Juridica p : c.ListarContas_Juridicas()) {
			 	if(p.getCpf()==(cpf)) {
			 		
			 		 Conta_F = p;
			 		 break;
			 		
			 		
			 	}
			 
		}
		 return Conta_F;
		
		
		
	}
	
	


}
