package modelo;


	public class Pessoa_Fisica extends Pessoa  {
		
		private int id;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Pessoa_Fisica(String nome, String cpf, String mei, String dataNascimento) {
			super(nome, cpf, mei, dataNascimento);
			
		}
		
		
	
	
}
