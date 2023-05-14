package Classes_principais;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Utilidades.Entradas;

public class Cliente {

	private String nome;
	private String endereco;
	private Date dataLicensa;
	private String educacao;
	private String genero;
	private String classeEconomica;
	private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
	private Double valorSeguro;
	Scanner entrada = Entradas.entrada;

	// Contrutores
	public Cliente(String nome, String endereco, Date dataLicensa, String educacao, String genero,
			String classeEconomica, ArrayList<Veiculo> listaVeiculos, Double valorSeguro) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.dataLicensa = dataLicensa;
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
		this.valorSeguro = valorSeguro;
	}

	public Cliente() {

	}

	public Boolean cadastraDadosCliente(String nome, String endereco, String dataLicensaTexto, String educacao,
			String genero, String classeEconomica) {
		/* Funcao que adiciona valores aos clientes */

		if (nome == "") {

			System.out.println("Digite o nome do cliente");
			nome = entrada.nextLine();

			if (!Validacao.verificaNome(nome)) {
				return false;
			}

			System.out.println("Digite o endereco do cliente");
			endereco = entrada.nextLine();

			if (!Validacao.verificaNome(endereco)) {
				return false;
			}

			System.out.println("Digite a data de licenca do cliente");
			dataLicensaTexto = entrada.nextLine();

			if (!Validacao.verificaNome(dataLicensaTexto)) {
				return false;
			}

			System.out.println("Digite a educacao do cliente");
			educacao = entrada.nextLine();

			if (!Validacao.verificaNome(educacao)) {
				return false;
			}

			System.out.println("Digite o genero do cliente");
			genero = entrada.nextLine();

			if (!Validacao.verificaNome(genero)) {
				return false;
			}

			System.out.println("Digite a classe economica do cliente");
			classeEconomica = entrada.nextLine();

			if (!Validacao.verificaNome(classeEconomica)) {
				return false;
			}
		}

		Date dataLicensa = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			dataLicensa = formatter.parse(dataLicensaTexto);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		this.nome = nome;
		this.endereco = endereco;
		this.dataLicensa = dataLicensa;
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;

		return true;
	}

	public boolean adicionarVeiculo(String placa, String marca, String modelo, int dataFabricacao) {
		/* Funcao que cria e adiciona veiculos em cada cliente */
		Veiculo veiculo = new Veiculo();
		if (veiculo.cadastrarVeiculo(placa, marca, modelo, dataFabricacao)) {
			listaVeiculos.add(veiculo);
			System.out.println("Veiculo cadastrado com sucesso!");
			return true;
		}
		return false;
	}

	public boolean listarVeiculo(int tipo) {
		/*
		 * Funcao que lista os veiculos do cliente O tipo serve pra saber se a funcao
		 * precisa printar algo na tela ou somente precisa verificar se o cliente possui
		 * algum veiculo cadastrado
		 */

		if (listaVeiculos.size() == 0 && tipo == 0) {
			System.out.println("Esse cliente nao possui veiculos nessa seguradora!");
			return false;
		} else {
			for (int i = 0; i < listaVeiculos.size(); i++) {
				System.out.println("\n" + i + " - " + listaVeiculos.get(i).toString());
			}
		}
		return true;
	}

	public boolean removerVeiculo() {
		/* Funcao que remove o veiculo de um cliente */
		System.out.println("Digite a placa do veiculo a ser removido");
		String placa = entrada.nextLine();

		for (int i = 0; i < listaVeiculos.size(); i++) {
			if (listaVeiculos.get(i).getPlaca().equals(placa)) {
				listaVeiculos.remove(i);
				System.out.println("Veiculo removido com sucesso!");
				return true;
			}
		}
		System.out.println("Nenhum veiculo desse cliente possui essa placa!");
		return false;
	}

	// Getters e setters
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

	public Double getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(Double valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	@Override
	public String toString() {

		String veiculos = "";

		for (int i = 0; i < getListaVeiculos().size(); i++) {

			veiculos = veiculos + getListaVeiculos().get(i);
			veiculos = veiculos + "\n";
		}

		return "Informações do cliente:\n" + "Tipo: PJ\n" + "Nome:" + getNome() + "\n" + "Endereco:" + getEndereco()
				+ "\n" + "Data da licensa:" + getDataLicensa() + "\n" + "Educacao:" + getEducacao() + "\n" + "Genero:"
				+ getGenero() + "\n" + "Classe econonmica:" + getClasseEconomica() + "\n" + "Quantidade de veiculos:"
				+ getListaVeiculos().size() + "\n" + veiculos;
	}
}