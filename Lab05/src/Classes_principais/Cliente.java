package Classes_principais;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Utilidades.Entradas;

public abstract class Cliente {

	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	Scanner entrada = Entradas.entrada;

	// Construtores
	public Cliente(String nome, String telefone, String endereco, String email) {
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}

	public Cliente() {

	}

	public Boolean cadastraDadosCliente(String nome, String telefone, String endereco, String email) {
		/* Funcao que adiciona valores aos clientes */

		if (nome == "") {

			System.out.println("Digite o nome do cliente");
			nome = entrada.nextLine();

			if (!Validacao.verificaNome(nome)) {
				System.out.println("Nome invalido!");
				return false;
			}

			System.out.println("Digite o endereco do cliente");
			endereco = entrada.nextLine();

			if (!Validacao.verificaNome(endereco)) {
				System.out.println("Endereco invalido!");
				return false;
			}

			System.out.println("Digite o telefone do cliente");
			telefone = entrada.nextLine();

			if (!Validacao.verificaNumerosInteiros(telefone)) {
				System.out.println("Telefone invalido!");
				return false;
			}

			System.out.println("Digite o email do cliente");
			email = entrada.nextLine();

		}
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;

		return true;
	}

	public boolean removerVeiculo(String placa) {
		/* Funcao que remove veiculos pra clientes do tipo PF */
		System.out.println("Cliente não possui veiculos. (Use a opcao atualizar frota" + " para os clientes PJ)!");

		return false;
	}

	public boolean listarFrota() {
		/* Funcao que lista a frota dos clientes do tipo PJ */
		System.out
				.println("Este cliente nao possui frota. (Use a opcao listar veiculos para " + "clientes do tipo PF)");
		return false;
	}

	public boolean listarVeiculos() {
		/* Funcao que lista todos os veiculos do cliente */
		return false;
	}

	public boolean listarSinistrosCliente(Seguradora seguradora, Cliente cliente) {
		/* Funcao que lista todos os sinistros de um cliente em uma seguradora */

		ArrayList<Sinistro> sinistros = seguradora.getSinistrosPorCliente(cliente);

		if (sinistros.isEmpty()) {
			System.out.println("Nao ha sinistros cadastrados para esse cliente!");
			return false;
		}

		System.out.println("Sinistros do cliente: " + nome + "\n");
		for (int i = 0; i < sinistros.size(); i++) {
			System.out.println(sinistros.toString());
		}
		return true;
	}

	public boolean listarCondutoresCliente(Seguradora seguradora, Cliente cliente) {
		/* Funcao que lista todos os condutores que estao relacionados a um cliente */

		int aux = 0;
		ArrayList<Seguro> seguros = seguradora.getSegurosPorCliente(cliente);

		if (seguros.isEmpty()) {
			System.out.println("Nao ha seguros cadastrados para esse cliente!");
			return false;
		}

		System.out.println("Condutores do cliente: " + nome + "\n");
		for (int i = 0; i < seguros.size(); i++) {

			if (seguros.get(i).getIdCliente().equals(cliente.getId())) {

				for (int j = 0; j < seguros.get(i).getListacondutores().size(); j++) {

					System.out.println(seguros.get(i).getListacondutores().get(j).toString());
				}
			}
		}

		if (aux == 0) {
			System.out.println("Cliente nao possui nenhum condutor associado");
		}

		return true;
	}

	public boolean listarSegurosCliente(Seguradora seguradora, Cliente cliente) {
		/* Funcao que lista todos os seguros relacionados a um cliente */
		ArrayList<Seguro> seguros = seguradora.getSegurosPorCliente(cliente);

		if (seguros.isEmpty()) {
			System.out.println("Nao ha seguros cadastrados para esse cliente!");
			return false;
		}

		System.out.println("Seguros do cliente: " + nome + "\n");
		for (int i = 0; i < seguros.size(); i++) {
			System.out.println(seguros.toString());
		}
		return true;
	}

	public static int calculaIdade(Date data) {
		/* Funcao que retorna a idade do cliente baseado na data de nascimento */
		LocalDate dataAtual = LocalDate.now();
		LocalDate date = LocalDate.ofInstant(data.toInstant(), ZoneId.systemDefault());
		Period idade = Period.between(date, dataAtual);
		return idade.getYears();
	}

	public boolean cadastrarVeiculo(String placa, String marca, String modelo, int anoFabricacao) {
		/* Funcao que cadastra um veiculo para os clientes do tipo PF */
		System.out.println(
				"Este cliente nao possui veiculos. (Use a funcao atualizar frota para clientes" + " do tipo PJ.)");
		return false;
	}

	public boolean cadastrarFrota(String code) {
		/* Funcao que cadastra uma frota para um cliente do tipo PJ */
		System.out.println(
				"Este cliente nao possui frota. (Use a funcao cadastrar veiculos para clientes" + " do tipo PF.)");
		return false;
	}

	public boolean atualizarFrota(String tipo, String placa, String marca, String modelo, int anoFabricacao,
			String code) {
		/* Funcao que atualiza uma frota de um cliente do tipo PJ */

		System.out.println("Este cliente nao possui frota. (Utilize a funcao cadastrar ou remover veiculo"
				+ " para clientes do tipo PF).");

		return false;
	}

	// Interfaces que devem ser seguidas pelas classes filhas
	public abstract String getId();

	public abstract String posicaoVeiculoFrota(String id);

	public ArrayList<Frota> getListaFrota() {
		/* Funcoa quw que retorna uma lista de frotas do cliente do tipo PJ */
		return null;
	}

	///// Getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Scanner getEntrada() {
		return entrada;
	}

	public void setEntrada(Scanner entrada) {
		this.entrada = entrada;
	}

	@Override
	public String toString() {
		return "Dados do cliente\n" + "Nome:" + nome + "\n" + "Telefone:" + telefone + "\n" + "Endereco:" + endereco
				+ "\n" + "Email:" + email;
	}

}