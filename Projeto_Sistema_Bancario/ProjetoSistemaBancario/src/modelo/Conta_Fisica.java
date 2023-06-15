package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dao.Extrato_Fisico_DAO;

public class Conta_Fisica extends Conta {
	
	protected int idPessoa_Fisica;

	public Conta_Fisica(Pessoa_Fisica pessoa, Double saldo, Boolean status) {
		super(pessoa);
		this.idPessoa_Fisica= pessoa.getId();
		this.saldo = saldo;
		this.status = status;
	}
	public Conta_Fisica(Pessoa_Fisica pessoa) {
	    this(pessoa, 0.0, true);
	}

	@Override
	public String toString() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    String dataNascimentoFormatada = pessoa.getDataNascimento().format(formatter);
		 return "\nNumero Da Conta: " + idPessoa_Fisica +
	                "\nCliente: " + pessoa.getNome() +
	                "\nCPF: " + pessoa.getCpf() +
	                "\nNascimento: " + dataNascimentoFormatada+
	                "\nIdade: " + pessoa.getIdade()+
	                "\nSaldo: " + getSaldo() +
	                "\nStatus: " + getStatus() +
	                "\n" ;
		
		
	}


	public int getIdPessoa_Fisica() {
		return idPessoa_Fisica;
	}

	public void setIdPessoa_Fisica(int idPessoa_Fisica) {
		this.idPessoa_Fisica = idPessoa_Fisica;
	}
	@Override
	public void sacar(Double valor) {
		 if(valor > 0 && this.getSaldo() >= valor) {
	            setSaldo(getSaldo() - valor);
	            System.out.println("Saque realizado com sucesso!");
	            Extrato_Fisico_DAO x = new Extrato_Fisico_DAO();
	            Extrato_Fisico extrato = new Extrato_Fisico(getIdPessoa_Fisica(),LocalDate.now().toString(),"Saque",valor,LocalDate.now().toString(),LocalDate.now().toString());
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
	            Extrato_Fisico_DAO x = new Extrato_Fisico_DAO();
	            Extrato_Fisico extrato = new Extrato_Fisico(getIdPessoa_Fisica(),LocalDate.now().toString(),"Deposito",valor,LocalDate.now().toString(),LocalDate.now().toString());
	            System.out.println(extrato);
	            x.save(extrato);
	            
	        }else {
	            System.out.println("Não foi possível realizar o depósito!");
	        }
		
	}
	@Override
	public void transferir(Conta contaDestino, Double valor) {
		 Conta_Fisica contaDestinoFisica = (Conta_Fisica) contaDestino;
		  if(valor > 0 && this.getSaldo() >= valor) {
	            setSaldo(getSaldo() - valor);
	            //this.saldo = this.getSaldo() - valor;
	            contaDestinoFisica.saldo = contaDestinoFisica.getSaldo() + valor;
	            System.out.println("Transferência realizada com sucesso!");
	            Extrato_Fisico_DAO x = new Extrato_Fisico_DAO();
	            Extrato_Fisico extrato = new Extrato_Fisico(getIdPessoa_Fisica(),LocalDate.now().toString(),"Transferencia",valor,LocalDate.now().toString(),LocalDate.now().toString());
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
