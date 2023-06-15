package modelo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
public  abstract class Pessoa {
	protected String nome;
	protected String cpf;
	protected String mei;
	protected LocalDate dataNascimento;
	
	protected  Pessoa(String nome, String cpf,String mei, String dataNascimento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.mei = mei;
		this.dataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	public int getIdade() {
		LocalDate hoje = LocalDate.now();
		int Idade = hoje.getYear() - dataNascimento.getYear();
			if(hoje.getMonthValue() < dataNascimento.getMonthValue()||
					
					(hoje.getMonthValue() == dataNascimento.getMonthValue() && hoje.getDayOfMonth() < dataNascimento.getDayOfMonth())) {
				
			Idade--;	
				
			} 
			return Idade;
		
		
	}
	
	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", mei=" + mei + ", dataNascimento=" + dataNascimento + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(cpf, dataNascimento, mei, nome);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(mei, other.mei) && Objects.equals(nome, other.nome);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getMei() {
		return mei;
	}
	public void setMei(String mei) {
		this.mei = mei;
	}
	
	
}
