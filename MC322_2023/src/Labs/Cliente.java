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
	
	public boolean validarCPF(String cpf) {
		
		/*Terminar essa funcao*/
		cpf = cpf.replaceAll("[^0-9]+", "");
		
		if(cpf.length() == 11) {
			
			
			
		}else {
			
			return false;
			
		}
		
		
		
		return false;
	}
}
