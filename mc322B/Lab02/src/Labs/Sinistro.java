package Labs;

public class Sinistro {
	
	private int id;
	private String data;
	private String endereco;
	static int id_gerado = 0;
	
	//Contrutor
	public Sinistro(String data, String endereco) {
		this.id = GeraId();
		this.data = data;
		this.endereco = endereco;
	}

	
	//Getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	/*Verificar essa funcao depois*/
	private int GeraId() {
	
		id_gerado = id_gerado+1;
		return id_gerado;
		
	}

}
