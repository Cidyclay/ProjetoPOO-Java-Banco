package modelo;



import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
public abstract class Extrato {
		private int idPessoa_Fisica_Extrato;
	    private LocalDate data;
	    private String descricao;
	    private double valor;
	    private int mes;
	    private int ano;

	    public Extrato(int idPessoa_Fisica,String data, String descricao, double valor, String mes, String ano) {
	    	this.idPessoa_Fisica_Extrato = idPessoa_Fisica;
	        this.data = LocalDate.now();
	        this.descricao = descricao;
	        this.valor = valor;
	        this.mes = LocalDate.now().getMonthValue();
	        this.ano = LocalDate.now().getYear();
	    }

	    public Extrato(LocalDate data2, String descricao2, double valor2) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "\nId_Pessoa_Fisica: "+ this.getIdPessoa_Fisica()+
					"\nMês: "+ this.getMes()+
					"\nAno: "+ this.getAno() +
					"\nData: "+this.getData()+
					"\nDescrição: "+this.getDescricao()+
					"\nValor: "+this.getValor()+
					"\n";
		}

		public LocalDate getData() {
	        return data;
	    }

	    public String getDescricao() {
	        return descricao;
	    }

	    public double getValor() {
	        return valor;
	    }

	    public int getMes() {
	        return mes;
	    }

	    public int getAno() {
	        return ano;
	    }
	    public int getIdPessoa_Fisica() {
	        return idPessoa_Fisica_Extrato;
	    }
	   
	}


