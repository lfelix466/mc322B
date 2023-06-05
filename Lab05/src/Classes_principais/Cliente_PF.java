package Classes_principais;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import Utilidades.Entradas;

public class Cliente_PF extends Cliente {

	private final String cpf;
	private String genero;
	private String educacao;
	private Date dataNasc;
	private ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
	Scanner entrada = Entradas.entrada;

	// Construtores
	public Cliente_PF(String nome, String telefone, String endereco, String email, String cpf, String genero,
			String educacao, Date dataNasc, ArrayList<Veiculo> listaVeiculos) {
		super(nome, telefone, endereco, email);
		this.cpf = cpf;
		this.genero = genero;
		this.educacao = educacao;
		this.dataNasc = dataNasc;
		this.listaVeiculos = listaVeiculos;
	}

	public Cliente_PF(String cpf) {
		this.cpf = cpf;
	}

	public static boolean CadastrarCliente(Seguradora seguradora, String cpf, String nome, String telefone,
			String endereco, String email, String genero, String educacao, String dataNascTexto) {
		/* Funcao que insere dados no cliente do tipo PF */

		Scanner entrada = Entradas.entrada;

		if (cpf == "") {

			System.out.println("Digite o cpf do cliente");
			cpf = entrada.nextLine();

			if (!Validacao.validarCPF(cpf)) {
				System.out.println("CPF invalido!");
				return false;
			}

			System.out.println("Digite o genero do cliente");
			genero = entrada.nextLine();

			System.out.println("Digite a educacao do cliente");
			cpf = entrada.nextLine();

			if (!Validacao.verificaNome(educacao)) {
				System.out.println("Educacao invalida!");
				return false;
			}

			System.out.println("Digite data de nascimento do cliente");
			dataNascTexto = entrada.nextLine();

			if (!Validacao.verificaData(dataNascTexto)) {
				System.out.println("Data invalida!");
				return false;
			}
		}

		Date dataNasc;
		Cliente_PF cliente_aux = new Cliente_PF(cpf);

		if (!cliente_aux.cadastraDadosCliente(nome, telefone, endereco, email)) {
			return false;
		}

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			dataNasc = formatter.parse(dataNascTexto);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		cliente_aux.setDataNasc(dataNasc);
		cliente_aux.setEmail(email);
		cliente_aux.setGenero(genero);
		cliente_aux.setEducacao(educacao);

		seguradora.cadastrarCliente(cliente_aux);
		System.out.println("Cliente cadastrado com sucesso!");
		return true;
	}

	@Override
	public boolean cadastrarVeiculo(String placa, String marca, String modelo, int anoFabricacao) {
		/* Funcao que cadastra veiculos no cliente do tipoPF */
		Veiculo veiculo = new Veiculo();
		if (veiculo.cadastrarVeiculo(placa, marca, modelo, anoFabricacao)) {

			System.out.println("Veiculo cadastrado com sucesso!");
			listaVeiculos.add(veiculo);

			return true;
		}
		System.out.println("Erro ao cadastrar veiculo!");
		return false;
	}

	@Override
	public boolean removerVeiculo(String placa) {

		if (listaVeiculos.isEmpty()) {

			System.out.println("Cliente nao possui veiculos cadastrados!");
			return false;
		}

		if (placa == "") {

			System.out.println("Digite a placa do veiculo que deseja remover");
			placa = entrada.nextLine();

			for (int i = 0; i < listaVeiculos.size(); i++) {

				if (listaVeiculos.get(i).getPlaca().equals(placa)) {

					listaVeiculos.remove(i);
					System.out.println("Veiculo removido com sucesso!");
					return true;

				}
			}
		}

		System.out.println("Veiculo nao encontrado!");

		return false;
	}

	public static Veiculo encontraVeiculo(String placa, Cliente_PF cliente) {
		/* Funcao que encontra um veiculo de um cliente baseado em na placa */
		for (int i = 0; i < cliente.getListaVeiculos().size(); i++) {
			if (cliente.getListaVeiculos().get(i).getPlaca().equals(placa)) {
				return cliente.getListaVeiculos().get(i);
			}
		}
		return null;
	}

	@Override
	public String getId() {
		/* Funcao que retorna a identificacao do cliente */
		return this.cpf;
	}

	@Override
	public String posicaoVeiculoFrota(String id) {
		/* Funcao que retorna o indice da frota selecionado por ID */

		for (int i = 0; i < listaVeiculos.size(); i++) {

			if (listaVeiculos.get(i).getPlaca().equals(id)) {
				return "" + i;
			}
		}

		return "Falso";
	}

	@Override
	public boolean listarVeiculos() {
		/* Funcao que lista os veiculos do cliente */

		if (listaVeiculos.isEmpty()) {
			System.out.println("Nao ha veiculos cadastrados neste cliente!");
			return false;
		}
		System.out.println("Veiculos do cliente:" + getNome() + "\n");
		for (int i = 0; i < listaVeiculos.size(); i++) {
			System.out.println(listaVeiculos.get(i).toString());
		}
		return true;
	}

	// Getters e setters
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	public String getCpf() {
		return cpf;
	}

	@Override
	public String toString() {
		return "Dados do cliente:" + getNome() + "\n" + "CPF:" + cpf + "\n" + "Genero:" + genero + "\n" + "Educacao:"
				+ educacao + "\n" + "Data de nascimento:" + dataNasc + "\n";
	}

}
