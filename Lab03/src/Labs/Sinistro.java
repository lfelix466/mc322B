package Labs;

public class Sinistro extends Seguradora{
	
	static int id_gerado = 0;
	private final int id;
	private String data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;
	
	
	public Sinistro(String nome, String telefone, String email, String endereco, int id, String data, String endereco2,
			Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
		super(nome, telefone, email, endereco);
		this.id = GeraId();
		this.data = data;
		endereco = endereco2;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}

	
	public static int getId_gerado() {
		return id_gerado;
	}



	public static void setId_gerado(int id_gerado) {
		Sinistro.id_gerado = id_gerado;
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



	public Seguradora getSeguradora() {
		return seguradora;
	}



	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}



	public Veiculo getVeiculo() {
		return veiculo;
	}



	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Sinistro [id=" + id + ", data=" + data + ", endereco=" + endereco + ", seguradora=" + seguradora
				+ ", veiculo=" + veiculo + ", cliente=" + cliente + "]";
	}


	/*Verificar essa funcao depois*/
	private int GeraId() {
	
		id_gerado = id_gerado+1;
		return id_gerado;
		
	}
	
	

}
