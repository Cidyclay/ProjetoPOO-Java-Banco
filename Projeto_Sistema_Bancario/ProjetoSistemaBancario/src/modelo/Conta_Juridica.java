package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dao.Extrato_Juridica_DAO;

public class Conta_Juridica extends Conta {


	protected int idPessoa_Juridica;
	protected String cpf;

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Conta_Juridica(Pessoa_Juridica pessoa, Double saldo, Boolean status) {
		super(pessoa);
		this.idPessoa_Juridica= pessoa.getId();
		pessoa.cpf = cpf;
		this.saldo = saldo;
		this.status = status;
	}
	public Conta_Juridica(Pessoa_Juridica pessoa) {
	    this(pessoa, 0.0, true);
	}

	@Override
	public String toString() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    String dataNascimentoFormatada = pessoa.getDataNascimento().format(formatter);
		 return "\nNumero Da Conta: " + idPessoa_Juridica +
	                "\nCliente: " + pessoa.getNome() +
	                "\nCPF: " + pessoa.getCpf() +
	                "\nCPF: " + pessoa.getMei() +
	                "\nNascimento: " + dataNascimentoFormatada+
	                "\nIdade: " + pessoa.getIdade()+
	                "\nSaldo: " + getSaldo() +
	                "\nStatus: " + getStatus() +
	                "\n" ;
		
		
	}


	public int getIdPessoa_Juridica() {
		return idPessoa_Juridica;
	}

	public void setIdPessoa_Juridica(int idPessoa_Juridica) {
		this.idPessoa_Juridica = idPessoa_Juridica;
	}
	@Override
	public void sacar(Double valor) {
		 if(valor > 0 && this.getSaldo() >= valor) {
			 double tarifa = valor * 0.1;
			 valor = valor+tarifa;
	            setSaldo(getSaldo() - valor);
	            System.out.println("Saque realizado com sucesso!");
	            Extrato_Juridica_DAO x = new Extrato_Juridica_DAO();
	            Extrato_Juridica extrato = new Extrato_Juridica(getIdPessoa_Juridica(),LocalDate.now().toString(),"Saque",valor,LocalDate.now().toString(),LocalDate.now().toString());
	            System.out.println(extrato);
	            x.save(extrato);

	        }else {
	            System.out.println("Não foi possível realizar o saque!");
	        }
		 
	    }
	@Override
	public void depositar(Double valor) {
		  if(valor > 0) {
	            setSaldo(getSaldo() + valor);
	            System.out.println("Seu depósito foi realizado com sucesso!");
	            Extrato_Juridica_DAO x = new Extrato_Juridica_DAO();
	            Extrato_Juridica extrato = new Extrato_Juridica(getIdPessoa_Juridica(),LocalDate.now().toString(),"Deposito",valor,LocalDate.now().toString(),LocalDate.now().toString());
	            System.out.println(extrato);
	            x.save(extrato);
	            
	        }else {
	            System.out.println("Não foi possível realizar o depósito!");
	        }
		
	}
	@Override
	public void transferir(Conta contaDestino, Double valor) {
		 Conta_Juridica contaDestinoJuridica = (Conta_Juridica) contaDestino;
		  if(valor > 0 && this.getSaldo() >= valor) {
	            setSaldo(getSaldo() - valor);
	            //this.saldo = this.getSaldo() - valor;
	            contaDestinoJuridica.saldo = contaDestinoJuridica.getSaldo() + valor;
	            System.out.println("Transferência realizada com sucesso!");
	            Extrato_Juridica_DAO x = new Extrato_Juridica_DAO();
	            Extrato_Juridica extrato = new Extrato_Juridica(getIdPessoa_Juridica(),LocalDate.now().toString(),"Transferencia",valor,LocalDate.now().toString(),LocalDate.now().toString());
	            System.out.println(extrato);
	            x.save(extrato);
	        }else {
	            System.out.println("Não foi possível realizar a tranferência");
	        }

	    }
		
	@Override
	public void fecharConta() {
		if(status==false){
			System.out.print("Conta ja inativa.");
		}if(this.saldo>0){
			System.out.print("Conta com saldo. Nao eh possivel fecha-la.");
		}else if(status==true){
			status=false;
			
		}
		
		
	}
	@Override
	public void abrirConta() {
		
		if(status==true){
			System.out.print("Conta já ativa.");
		}else{
			status=true;
		}
		
	}

}
