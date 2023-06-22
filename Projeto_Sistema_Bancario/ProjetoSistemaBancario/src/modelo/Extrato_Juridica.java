package modelo;

import java.time.LocalDate;

public class Extrato_Juridica extends Extrato {

	public Extrato_Juridica(int idPessoa, String data, String descricao, double valor, String mes, String ano) {
		super(idPessoa, data, descricao, valor, mes, ano);

	}

	public Extrato_Juridica(int idPessoa, LocalDate data, String descricao, double valor) {
		super(idPessoa, data.toString(), descricao, valor, null, null);

	}
}
