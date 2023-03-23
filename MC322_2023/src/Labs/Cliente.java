package Labs;

public class Cliente {
	
	private String nome;
	private String cpf;
	private String dataNascimento;
	private String idade;
	private String endereco;
	
	//Contrutor
	public Cliente (String nome, String cpf, String dataNascimento, String idade, String endereco)
	{	
		
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.endereco = endereco;
		
	}

	//Getters e setters
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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Nome: "+nome+"\nCPF: "+cpf;
	}
	
	private boolean VerificaDigitoCPF(String cpf) {
		int dig1, dig2, valor1 = 0, valor2 = 0;
		
		for(int i = 0; i < 10; i++) {
			
			valor2 += Integer.parseInt(String.valueOf(cpf.charAt(i)))*(11-i);
			
			if(i != 9) {
				valor1 += Integer.parseInt(String.valueOf(cpf.charAt(i)))*(10-i);
			}}
		
		if(valor1%11 == 0 || valor1%11 == 1) {
			dig1 = 0;
		}else {	
			dig1 = 11-(valor1%11);
		}
		
		if(valor2%11 == 0 || valor2%11 == 1) {
			dig2 = 0;
		}else {	
			dig2 = 11-(valor2%11);
		}
		
		
		if(dig1 != Integer.parseInt(String.valueOf(cpf.charAt(9)))) {	
			return false;
		}
		
		if(dig2 != Integer.parseInt(String.valueOf(cpf.charAt(10)))) {	
			return false;
		}
		
		
		return true;
	}
	
	public boolean ValidarCPF(String cpf) {
		
		/*Terminar essa funcao*/
		cpf = cpf.replaceAll("[^0-9]+", "");
		
		char texto1 = 0, texto2;
		int aux = 0;
		
		if(cpf.length() == 11) {
			
			for(int i = 0; i<11; i++){
				
				if(i == 0) {
					
					texto1 = cpf.charAt(i);
				
				}else{
					
					texto2 = texto1;
					texto1 = cpf.charAt(i);
					
					if(texto1 != texto2){
						aux = 1;
						break;
					}}}
			
				if(aux == 0) {
					return false;
				}
			
			if(!VerificaDigitoCPF(cpf)) {
				return false;
			}
				
		}else {
			
			return false;
			
		}
		
		return true;
	}
}
