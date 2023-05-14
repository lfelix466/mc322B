package Classes_principais;

<<<<<<< HEAD
import java.text.ParseException;
import java.text.SimpleDateFormat;
=======
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente{
	
	private String nome;
	private String endereco;
	private Date dataLicensa;
	private String educacao;
	private String genero;
	private String classeEconomica;
	private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
	
<<<<<<< HEAD
	//Contrutores
=======
	
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
	public Cliente(String nome, String endereco, Date dataLicensa, String educacao, String genero,
			String classeEconomica, ArrayList<Veiculo> listaVeiculos) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.dataLicensa = dataLicensa;
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
		this.listaVeiculos.add(null);
	}
<<<<<<< HEAD
	
	public Cliente() {
		
	}
	
	public Boolean cadastraDadosCliente(Cliente cliente, String nome, String endereco, 
			String dataLicensaTexto, String educacao, String genero, String classeEconomica) {
		/*Funcao que cadastra os dados comuns dos dois tipos de clientes*/
		
		Date dataLicensa = null;
			
		cliente.setNome(nome);
		cliente.setEndereco(endereco);
					
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				dataLicensa = formatter.parse(dataLicensaTexto);
						
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
					
			cliente.setDataLicensa(dataLicensa);
			cliente.setEducacao(educacao);
			cliente.setGenero(genero);
			cliente.setClasseEconomica(classeEconomica);
			
			//Cadastro do carro para o cliente
			Veiculo veiculo = new Veiculo();
			cliente.getListaVeiculos().add(veiculo.cadastrarVeiculo("123", "teste", "teste", 10));
				
			return true;
	}

	//Getters e setters
=======


>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public Date getDataLicensa() {
		return dataLicensa;
	}


	public void setDataLicensa(Date dataLicensa) {
		this.dataLicensa = dataLicensa;
	}


	public String getEducacao() {
		return educacao;
	}


	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String getClasseEconomica() {
		return classeEconomica;
	}


	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}


	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}


	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	@Override
	public String toString() {
		
		String veiculos = "";
		
		for(int i = 0; i<getListaVeiculos().size(); i++) {
			
			veiculos = veiculos + getListaVeiculos().get(i);
			veiculos = veiculos + "\n";
		}
		
		return "Informações do cliente:\n"
				+ "Tipo: PJ\n"
				+ "Nome:"+getNome()+"\n"
				+ "Endereco:"+getEndereco()+"\n"
				+ "Data da licensa:"+getDataLicensa()+"\n"
				+ "Educacao:"+getEducacao()+"\n"
				+ "Genero:"+getGenero()+"\n"
				+ "Classe econonmica:"+getClasseEconomica()+"\n"
				+ "Quantidade de veiculos:"+getListaVeiculos().size()+"\n"
				+ veiculos;
	}
	
}
