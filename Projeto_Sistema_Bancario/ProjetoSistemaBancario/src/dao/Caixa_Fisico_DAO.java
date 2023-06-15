package dao;
import modelo.Caixa;


import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Caixa_Fisico_DAO extends Caixa {
	
	static Scanner input = new Scanner(System.in);
	static ArrayList<Conta_Fisica_DAO> contasBancarias = new ArrayList<Conta_Fisica_DAO>();


	@Override
	// estou aplicando um metodo operacoes, que ao passar um switch case
    // ele dara determinadas operações do caixa que ira realizar
    // determinadas funções.
	public void operacoes() {
		
		

		int operacao =
    			Integer.parseInt(JOptionPane.showInputDialog("--- Selecione uma operação ---" + 
    	"|   Opção 1 - Criar conta" +
    	"|   Opção 2 - Depositar" +
    	"|   Opção 3 - Sacar" +
    	"|   Opção 4 - Transferir" +
    	"|   Opção 5 - Listar" +
    	"|   Opção 6 - Sair"));
    	
    	switch (operacao) {
    	case 1:
    		criarConta();
    		break;
    	case 2:
    		depositar();
    		break;
    	case 3:
    		sacar();
    		break;
    	case 4:
    		transferir();
    		break;
    	case 5:
    		listarContas();
    		break;
    	case 6:
    		JOptionPane.showMessageDialog(null, "Obrigado por utilizar, O sistema da caixa!");
    		break;
    		
    		default:
    			JOptionPane.showMessageDialog(null, "Opção inválida");
    			operacoes();
    			break;
    	}
    	
		
	}

	@Override
	public void criarConta() {
		System.out.println("Você está criando uma conta\n");

        System.out.println("\nNome: ");
        String nome = input.next();

        System.out.println("\nCPF: ");
        String cpf = input.next();
        while (cpf.length() !=11) {
        	System.out.println("\nCPF inválido. O CPF deve conter 11 dígitos");
        	System.out.println("\nCPF: ");
        	cpf =input.next();
        }
  
        // Como e uma conta fisica, ou seja usuário comum
        // o mei será colocado como valor null.
        String mei = "null";

        System.out.println("Nascimento: ");
        String nascimento = input.next();

        Pessoa_Fisica_DAO cliente = new Pessoa_Fisica_DAO(nome,cpf,mei,nascimento);

        Conta_Fisica_DAO conta = new Conta_Fisica_DAO(cliente);

        contasBancarias.add(conta);

        System.out.println("--- Sua conta foi criada com sucesso! ---");

        operacoes();
		
	}

	@Override
	protected void depositar() {
		 System.out.println("Número da conta: ");
	        int numeroConta = input.nextInt();
	        Conta_Fisica_DAO conta = encontrarConta(numeroConta);

	        if(conta != null) {
	            System.out.println("Qual valor deseja depositar? ");
	            Double valorDeposito = input.nextDouble();

	            conta.depositar(valorDeposito);
	        }else {
	            System.out.println("--- Conta não encontrada ---");
	        }

	        operacoes();
		
	}

	@Override
	protected void sacar() {
		 System.out.println("Número da conta: ");
	        int numeroConta = input.nextInt();

	        Conta_Fisica_DAO conta = encontrarConta(numeroConta);

	        if(conta != null) {
	            System.out.println("Qual valor deseja sacar? ");
	            Double valorSaque = input.nextDouble();

	            conta.sacar(valorSaque);
	            System.out.println("--- Saque realizado com sucesso! ---");
	        }else {
	            System.out.println("--- Conta não encontrada ---");
	        }

	        operacoes();
	}

	@Override
	protected void transferir() {
		 System.out.println("Número da conta que vai enviar a transferência: ");
	        int numeroContaRemetente = input.nextInt();

	        Conta_Fisica_DAO contaRemetente = encontrarConta(numeroContaRemetente);

	        if(contaRemetente != null) {
	            System.out.println("Número da conta do destinatário: ");
	            int numeroContaDestinatario = input.nextInt();

	            Conta_Fisica_DAO contaDestinatario = encontrarConta(numeroContaDestinatario);

	            if(contaDestinatario != null) {
	                System.out.println("Valor da transferência: ");
	                Double valor = input.nextDouble();

	                contaRemetente.realizarTransferencia(valor, contaDestinatario);

	            }else {
	                System.out.println("--- A conta para depósito não foi encontrada ---");
	            }

	        }else {
	            System.out.println("--- Conta para transferência não encontrada ---");
	        }
	        operacoes();
		
	}

	@Override
	protected void listarContas() {
		if(contasBancarias.size() > 0) {
            for(Conta_Fisica_DAO conta: contasBancarias) {
                System.out.println(conta);
            }
        }else {
            System.out.println("--- Não há contas cadastradas ---");
        }

        operacoes();
    }
	
	protected Conta_Fisica_DAO encontrarConta(int numeroConta) {
        Conta_Fisica_DAO conta = null;
        if(contasBancarias.size() > 0) {
            for(Conta_Fisica_DAO contaa : contasBancarias) {
                if(contaa.getNumeroConta() == numeroConta) {
                    conta = contaa;
                }
            }
        }
        return conta;
    }
		
	}

