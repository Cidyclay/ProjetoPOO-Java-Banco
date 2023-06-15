package modelo;

public abstract class Conta {
	protected Pessoa pessoa;
	protected Double saldo = 0.0;
	protected Boolean status = true;
	protected Conta(Pessoa pessoa) {
		this.pessoa = pessoa;
		this.saldo = 0.0;
		this.status = true;
	}
	protected Pessoa getPessoa() {
		return pessoa;
	}
	protected void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public abstract void sacar(Double valor);
	public abstract void depositar(Double valor);
	public abstract void transferir(Conta contaDestino, Double valor);
	public abstract void fecharConta();
	public abstract void abrirConta();
	

	
	
	
	
}

	
