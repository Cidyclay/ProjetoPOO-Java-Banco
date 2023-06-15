package modelo;

public class Pessoa_Juridica extends Pessoa {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Pessoa_Juridica(String nome, String cpf, String mei, String dataNascimento) {
		super(nome, cpf, mei, dataNascimento);
		
	}

	
}
