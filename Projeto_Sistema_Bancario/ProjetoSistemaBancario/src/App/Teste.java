package App;

import dao.Conta_Fisica_DAO;
import dao.Extrato_Fisico_DAO;
import dao.Pessoa_Fisica_DAO;
import modelo.Conta_Fisica;
import modelo.Pessoa_Fisica;


public class Teste {
		public static void main(String args[]) {
		
			Conta_Fisica conta1;
			Conta_Fisica conta2;
		Pessoa_Fisica_DAO y = new Pessoa_Fisica_DAO();
		Conta_Fisica_DAO x = new Conta_Fisica_DAO();
		Extrato_Fisico_DAO z = new Extrato_Fisico_DAO();
		conta1 =x.acharConta_Fisica(11);
		conta2 =x.acharConta_Fisica(20);
		
		
		
		//conta1.fecharConta();
	
			System.out.println(z.ListarExtratos_Fisicos());
		//x.updateStatus(conta1);
		//conta1 = x.acharConta_Fisica(11);
		//System.out.println(conta1);
		//conta1.abrirConta();
		//x.updateStatus(conta1);
		
			
		 
		
		
		
		
		
		
		//conta = x.acharConta_Fisica(11);
		//System.out.println(x.acharConta_Fisica(11));
		//conta.sacar(160.0);
		//x.updateSaldo(conta);
		//System.out.println(x.acharConta_Fisica(11));
		
		
		
		
			
		
			
			
			
			
			
			 
		}
		
			
}
			
		
			
		
