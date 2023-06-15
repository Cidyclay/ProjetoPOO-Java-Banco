package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Extrato;
import modelo.Extrato_Fisico;
import java.sql.Date;
import bd.Conexao_Banco;

public class Extrato_Fisico_DAO {
	
	
	public void save(Extrato_Fisico extrato) {
		
		String sql = "INSERT INTO transacao_fisica (id_conta,tipo,valor,data) VALUES (?,?,?,?)";
		
		
		  	Connection conn = null;
	        PreparedStatement pstm = null;
	        
	        try {
	        	// Criar uma conexão com o banco de dados 
	        	
	        	conn = Conexao_Banco.createConnectionToMysql();
	        	
	        	pstm = (PreparedStatement) conn.prepareStatement(sql);
	        	pstm.setInt(1, extrato.getIdPessoa_Fisica());
	        	pstm.setString(2, extrato.getDescricao());
	        	pstm.setDouble(3, extrato.getValor());
	        	
	        	//Vai converter o local date para o tipo data SQL
	        	LocalDate localDate = extrato.getData();
	        	Date sqlDate = Date.valueOf(localDate);
	        	pstm.setDate(4, sqlDate);
	        	pstm.execute();
	        	
	        	System.out.println("Operação registrada!");
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }finally {
	        	
	        	try {
	        		if(pstm!=null) {
	        			
	        			pstm.close();
	        		}
	        		if (conn !=null) {
	        			conn.close();
	        		}
	        	}catch(Exception e) {
	        		
	        		e.printStackTrace();
	        	}
	        }
	        

	}
	
	public List<Extrato_Fisico> ListarExtratos_Fisicos(){
		
		String sql = "SELECT * FROM transacao_fisica";
		
		List<Extrato_Fisico> extrato_fisico = new ArrayList<Extrato_Fisico>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = Conexao_Banco.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				int idPessoa_Fisica = rset.getInt("id_conta");
				String descricao = rset.getString("tipo");
				double valor = rset.getDouble("valor");
				Date  dataSql = rset.getDate("data");
				LocalDate data = dataSql.toLocalDate();
				Extrato_Fisico extrato = new Extrato_Fisico(idPessoa_Fisica,data,descricao,valor);
				
			extrato_fisico.add(extrato);
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(pstm!=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return extrato_fisico;
		
	}
	
	public Extrato_Fisico acharExtratos(int id) {
		Extrato_Fisico extratosEncontrados = null;
		List<Extrato_Fisico> extratos = ListarExtratos_Fisicos();
		
		for(Extrato_Fisico extrato : extratos) {
			if(extrato.getIdPessoa_Fisica() == id) {
				extratosEncontrados = extrato;
				
			}
		}
		return extratosEncontrados;
		
	}
	
}
