package dao;

public class Balanco_Contas {
	
	public void consultar(String cpf) {
		Conta_Fisica_DAO contaf = new Conta_Fisica_DAO();
		Conta_Juridica_DAO contaj = new Conta_Juridica_DAO();
		if(contaj.acharConta_Juridica_cpf(cpf)==true) {
			
		}
		
		
		
		return ;
		
		
	}
}
