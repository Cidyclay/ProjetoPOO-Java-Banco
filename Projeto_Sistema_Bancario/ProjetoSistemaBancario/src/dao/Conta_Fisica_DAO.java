package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bd.Conexao_Banco;
import modelo.Conta_Fisica;


public class Conta_Fisica_DAO  {



	public void save(Conta_Fisica conta) {
		 
        String sql = "INSERT INTO conta_fisica (id_conta_Fisica,id_pessoa_Fisica,saldo, status) VALUES (?,?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
        	// Criar uma conexão com o banco de dados 
        	
        	conn = Conexao_Banco.createConnectionToMysql();
        	
        	pstm = (PreparedStatement) conn.prepareStatement(sql);
        	pstm.setInt(1, conta.getIdPessoa_Fisica());
            pstm.setInt(2, conta.getIdPessoa_Fisica());
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
	
	public void updateSaldo(Conta_Fisica conta) {
		
		
		String sql = "UPDATE conta_fisica set saldo = ? " +"WHERE id_conta_Fisica = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Cria a conexão com o banco
			conn = Conexao_Banco.createConnectionToMysql();
			
			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores para atualizar
			pstm.setDouble(1, conta.getSaldo());
			//O id da pessoa_Fisica
			pstm.setInt(2,conta.getIdPessoa_Fisica());
			
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

	public void updateStatus(Conta_Fisica conta) {
		
		String sql = "UPDATE conta_fisica set status = ? "+"WHERE id_conta_Fisica = ?";
		
		Connection conn = null;
		PreparedStatement pstm =null;
		
		try {
			
			//Criar a conexão com o banco
			conn = Conexao_Banco.createConnectionToMysql();
			
			//Criar a classe para executar a query 
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			
			  pstm.setBoolean(1, conta.getStatus());
			  pstm.setInt(2, conta.getIdPessoa_Fisica());
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
		
		String sql = "DELETE  FROM conta_fisica WHERE id_conta_Fisica = ?";
		
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
	public List<Conta_Fisica> ListarContas_Fisicas(){
		
		String sql = "SELECT * FROM conta_fisica";
		
		List<Conta_Fisica> conta_Fisica = new ArrayList<Conta_Fisica>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = Conexao_Banco.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				 
				Pessoa_Fisica_DAO c = new Pessoa_Fisica_DAO();
				@SuppressWarnings("unused")
				int id = rset.getInt("id_conta_Fisica");
				int idd = rset.getInt("id_pessoa_Fisica");
				double saldo = rset.getDouble("saldo");
				boolean status = rset.getBoolean("status");
				Conta_Fisica conta = new Conta_Fisica(c.acharPessoa_Fisica(idd),saldo,status);
				conta_Fisica.add(conta);
				
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
		
		return conta_Fisica;
		
		
	}
	public Conta_Fisica acharConta_Fisica(int id) {
		  Conta_Fisica Conta_F = null;
	
		Conta_Fisica_DAO c = new Conta_Fisica_DAO();
		 for(Conta_Fisica p : c.ListarContas_Fisicas()) {
			 	if(p.getIdPessoa_Fisica()==(id)) {
			 		
			 		 Conta_F = p;
			 		 break;
			 		
			 		
			 	}
			 
		}
		 return Conta_F;
		
		
		
	}
	


}
