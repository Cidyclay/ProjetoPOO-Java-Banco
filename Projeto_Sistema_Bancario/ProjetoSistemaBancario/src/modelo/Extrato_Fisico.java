package modelo;

import java.time.LocalDate;

public class Extrato_Fisico extends Extrato{

	public Extrato_Fisico(int idPessoa_Fisica, String data, String descricao, double valor, String mes, String ano) {
		super(idPessoa_Fisica, data, descricao, valor, mes, ano);
		
	}
	  public Extrato_Fisico(int idPessoa_Fisica, LocalDate data, String descricao, double valor) {
	        super(data, descricao, valor);
	       
	    }

}
