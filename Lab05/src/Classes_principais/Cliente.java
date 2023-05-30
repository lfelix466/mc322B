package Classes_principais;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Utilidades.Entradas;

public abstract class Cliente {

	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	Scanner entrada = Entradas.entrada;
	
	public Cliente(String nome, String telefone, String endereco, String email) {
		super();
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
				System.out.println(listaVeiculos.get(i).toString());
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
		return "Dados do cliente\n"
				+"Nome:"+nome+"\n"
					+"Telefone:"+telefone+"\n"
						+"Endereco:"+endereco+"\n"
							+"Email:"+email;
	}


}