package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bd.Conexao_Banco;
import modelo.Extrato_Juridica;

public class Extrato_Juridica_DAO {
	

	public void save(Extrato_Juridica extrato) {
		
		String sql = "INSERT INTO transacao_Juridica (id_conta,tipo,valor,data) VALUES (?,?,?,?)";
		
		
		  	Connection conn = null;
	        PreparedStatement pstm = null;
	        
	        try {
	        	// Criar uma conexão com o banco de dados 
	        	
	        	conn = Conexao_Banco.createConnectionToMysql();
	        	
	        	pstm = (PreparedStatement) conn.prepareStatement(sql);
	        	pstm.setInt(1, extrato.getIdPessoa());
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
	
	public List<Extrato_Juridica> ListarExtratos_Juridicas(){
		
		String sql = "SELECT * FROM transacao_juridica";
		
		List<Extrato_Juridica> extrato_fisico = new ArrayList<Extrato_Juridica>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = Conexao_Banco.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				int idPessoa = rset.getInt("id_conta");
				String descricao = rset.getString("tipo");
				double valor = rset.getDouble("valor");
				Date  dataSql = rset.getDate("data");
				LocalDate data = dataSql.toLocalDate();
				Extrato_Juridica extrato = new Extrato_Juridica(idPessoa,data,descricao,valor);
				
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
	
	public List <Extrato_Juridica> acharExtratos(int id) {
		List<Extrato_Juridica> extratosEncontrados =  new ArrayList<>();
		List<Extrato_Juridica> extratos = ListarExtratos_Juridicas();
		
		for(Extrato_Juridica extrato : extratos) {
			if(extrato.getIdPessoa() == id) {
				extratosEncontrados.add(extrato);
				
			}
		}
		return   extratosEncontrados;
		
	}
	
	
	public  List<Extrato_Juridica> filtroExtrato(int id,int mes, int ano){
		List<Extrato_Juridica> extratosSeparados = new ArrayList<>();
		List<Extrato_Juridica> extratos = acharExtratos(id); 
		for(Extrato_Juridica extrato : extratos ) {
			if(extrato.getMes() ==mes && extrato.getAno() ==ano) {
				extratosSeparados.add(extrato);
				
			}
		
		}
		
		
		
		return extratosSeparados;
		
		
	}
	

}
