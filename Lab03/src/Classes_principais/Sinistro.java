package Classes_principais;

public class Sinistro{
	
	static int id_gerado = 0;
	private final int id;
	private String data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;

<<<<<<< HEAD
	//Contrutores
=======
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
	public Sinistro(int id, String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
		super();
		this.id = geraId();
		this.data = data;
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}
	
<<<<<<< HEAD
	public Sinistro() {
		this.id = 0;
		
	}
	
	private int geraId() {
		/*Funcao que gera os ids*/
		
=======
	/*Verificar essa funcao depois*/
	private int geraId() {
	
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
		id_gerado = id_gerado+1;
		return id_gerado;
		
	}

<<<<<<< HEAD
	//Getters e setters
=======
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
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
		return "Dados do sinistro:\n"
<<<<<<< HEAD
				+ "ID: "+getId()+"\n"
				+ "Data: "+getData()+"\n"
				+ "Endereco: "+getEndereco()+"\n"
				+ "Seguradora: "+getSeguradora().getNome()+"\n"
				+ "Veiculo: "+getVeiculo().getModelo()+"\n"
				+ "Cliente: "+getCliente().getNome()+"\n";
=======
				+ "ID: "+id+"\n"
				+ "Data: "+data+"\n"
				+ "Endereco: "+endereco+"\n"
				+ "Seguradora: "+seguradora+"\n"
				+ "Veiculo: "+veiculo+"\n"
				+ "Cliente: "+cliente.getNome()+"\n";
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
	}

}