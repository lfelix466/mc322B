package Classes_principais;

import java.util.ArrayList;
import java.util.Scanner;

import Utilidades.Entradas;

public class Sinistro {

	static int id_gerado = 0;
	private final int id;
	private String data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;
	Scanner entrada = Entradas.entrada;

	// Contrutores
	public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
		super();
		this.id = geraId();
		this.data = data;
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}

	public Sinistro() {
		this.id = 0;
	}

	public static boolean gerarSinistro(ArrayList<Seguradora> listaSeguradora, String data, String endereco,
			int seguradoraIndice, String veiculoPlaca, int clienteIndice) {
		/*
		 * Funcao que cria e adiciona valores na seguradora e adiciona eles na
		 * seguradora escolhida
		 */

		int indiceVe = 0, aux = 0;

		Scanner entrada = Entradas.entrada;

		System.out.print("Veiculos do cliente:");
		if (listaSeguradora.get(seguradoraIndice).getListaClientes().get(clienteIndice).listarVeiculo(aux)) {

			if (veiculoPlaca == "") {
				System.out.println("Digite a placa do veiculo");
				veiculoPlaca = entrada.nextLine();
			}
			for (int i = 0; i < listaSeguradora.get(seguradoraIndice).getListaClientes().get(clienteIndice)
					.getListaVeiculos().size(); i++) {

				if (listaSeguradora.get(seguradoraIndice).getListaClientes().get(clienteIndice).getListaVeiculos()
						.get(i).getPlaca().equals(veiculoPlaca)) {
					aux = 1;
					indiceVe = i;
					break;
				}
			}

			if (aux == 0) {
				System.out.println("Veiculo nao econtrado no cliente");
				return false;
			} else {
				if (data == "") {
					System.out.println("Digite a data");
					data = entrada.nextLine();

					System.out.println("Digite o endereco");
					endereco = entrada.nextLine();

					if (!Validacao.verificaNome(endereco)) {
						System.out.println("Endereco invalido!");
					}
				}

				listaSeguradora.get(seguradoraIndice).gerarSinitro(data, endereco,
						listaSeguradora.get(seguradoraIndice),
						listaSeguradora.get(seguradoraIndice).getListaClientes().get(clienteIndice).getListaVeiculos()
								.get(indiceVe),
						listaSeguradora.get(seguradoraIndice).getListaClientes().get(clienteIndice));

				System.out.println("Sinistro gerado com sucesso!");
			}
			return true;
		}
		return false;
	}

	public static boolean listarSinistros(ArrayList<Seguradora> listaSeguradora) {
		/* Funcao que lista todos os sinistros cadastrados em todas as seguradoras */

		int aux = 0;

		System.out.println("Sinistros:\n");
		for (int i = 0; i < listaSeguradora.size(); i++) {
			for (int j = 0; j < listaSeguradora.get(i).getListaSinistro().size(); j++) {
				System.out.println(listaSeguradora.get(i).getListaSinistro().get(j).toString());
				aux = 1;
			}
		}
		if (aux == 0) {
			System.out.println("Nao existem sinistros cadastrados!");
			return false;
		}
		return true;
	}

	public static boolean removerSinistro(ArrayList<Seguradora> listaSeguradora) {
		/* Remove o sinistro cadastrado escolhido de qualquer seguradora */

		System.out.println("Qual o ID do sinistro que deseja remover?");
		Scanner entrada = Entradas.entrada;
		int id = 0;

		try {
			id = Integer.parseInt(entrada.nextLine());
		} catch (Exception e) {
			System.out.println("Id invalido!");
			return false;
		}

		for (int i = 0; i < listaSeguradora.size(); i++) {
			for (int j = 0; j < listaSeguradora.get(i).getListaSinistro().size(); j++) {
				if (listaSeguradora.get(i).getListaSinistro().get(j).getId() == id) {
					listaSeguradora.get(i).getListaSinistro().remove(j);
					System.out.println("Sinistro removido com sucesso!");
					return true;
				}
			}
		}

		System.out.println("Sinistro nao encontrado");
		return false;
	}

	private int geraId() {
		/* Funcao que gera os ids */
		id_gerado = id_gerado + 1;
		return id_gerado;
	}

	// Getters e setters
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
		return "Dados do sinistro:\n" + "ID: " + getId() + "\n" + "Data: " + getData() + "\n" + "Endereco: "
				+ getEndereco() + "\n" + "Seguradora: " + getSeguradora().getNome() + "\n" + "Veiculo: "
				+ getVeiculo().getModelo() + "\n" + "Cliente: " + getCliente().getNome() + "\n";
	}
}