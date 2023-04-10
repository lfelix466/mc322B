package Classes_principais;

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
	private List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
	
	
	public Cliente(String nome, String endereco, Date dataLicensa, String educacao, String genero,
			String classeEconomica, List<Veiculo> listaVeiculos) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.dataLicensa = dataLicensa;
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
		this.listaVeiculos.add(null);
	}


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


	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
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
