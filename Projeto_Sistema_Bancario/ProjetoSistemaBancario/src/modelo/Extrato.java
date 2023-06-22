package modelo;



import java.time.LocalDate;
public abstract class Extrato {
		private int idPessoa;
	    private LocalDate data;
	    private String descricao;
	    private double valor;
	    private int mes;
	    private int ano;

	    public Extrato(int idPessoa,String data, String descricao, double valor, String mes, String ano) {
	    	this.idPessoa = idPessoa;
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
			return "\nNumero da Conta: "+ this.getIdPessoa()+
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
	    public int getIdPessoa() {
	        return idPessoa;
	    }
	   
	}


