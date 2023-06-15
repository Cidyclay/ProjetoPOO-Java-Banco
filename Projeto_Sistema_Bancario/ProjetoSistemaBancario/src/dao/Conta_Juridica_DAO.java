package dao;

import java.time.format.DateTimeFormatter;

import modelo.Conta;
import modelo.Pessoa;
import dao.Pessoa_Juridica_DAO;

@SuppressWarnings("unused")
public class Conta_Juridica_DAO extends Conta{

	protected Conta_Juridica_DAO(Pessoa pessoa) {
		super(pessoa);
		this.pessoa = pessoa;
		//Toda vez que uma conta for criada, o id, e auto-incrementado
		contadorDeContas = contadorDeContas+ 1;
		this.numeroConta = contadorDeContas;
		
	}

	@Override
	public String toString() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    String dataNascimentoFormatada = this.pessoa.getDataNascimento().format(formatter);
		 return "\nNumero Da Conta: " + this.getNumeroConta() +
	                "\nCliente: " + this.pessoa.getNome() +
	                "\nCPF: " + this.pessoa.getCpf() +
	                "\nMei: " + this.pessoa.getMei()+
	                "\nNascimento: " + dataNascimentoFormatada+
	                "\nIdade: " + pessoa.getIdade()+
	                "\nSaldo: " + this.getSaldo() +
	                "\n" ;
	}

	@Override
	public void depositar(Double valor) {
		if(valor >0) {
			this.saldo = this.getSaldo() + valor;
			System.out.println("Seu depósito foi realizado com sucesso!");
		}else {
			System.out.println("Não foi possível realizar o depósito!");
		}
		
	}

	@Override
	public void sacar(Double valor) {
		if(valor> 0 && this.getSaldo() >= valor) {
			this.saldo = this.getSaldo() -valor;
			System.out.println("Saque realizado com sucesso!");
		}else {
			System.out.println("Não foi possível realziar o saque!");
		}
		
	}

	@Override
	public void fecharConta() {
		if(this.getStatus()==false) {
			System.out.println("Conta ja inativa.");
		}
		else if(this.getSaldo()>0) {
			System.out.println("Conta com saldo. Nao e possivel fecha-la.");
		}else {
			this.setStatus(false);
				System.out.println("Conta fechada com sucesso!");
				System.out.println("Obrigado volte sempre");
		}
		
	}

	@Override
	public void reabrirConta() {
		if (this.getStatus()==true) {
			System.out.println("Conta já ativa");
		}else {
			this.setStatus(true);
		}
		
		
	}

	@Override
	public void realizarTransferencia(double quantia, Conta destino) {
		if(this.getStatus()==false) {
			System.out.println("Conta de origem inativa.");
			
		}else if(destino.getStatus()==false) {
			System.out.println("Conta de destino inativa");
		}else if(quantia>=getSaldo()){
			System.out.println("Saldo insuficiente para transferencia.");
		}else {
			
			this.setSaldo(this.getSaldo() - quantia);
			destino.setSaldo(destino.getSaldo() + quantia);
			//Pq quando eu coloco o SetSaldo em private ele não pode ser utilizado aqui ? 
			System.out.println("Transferência realizada com sucesso!");
		}
		
	}
		
	}




