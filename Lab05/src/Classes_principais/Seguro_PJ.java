package Classes_principais;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Enumeradores.CalcSeguro;
import Utilidades.Entradas;

public class Seguro_PJ extends Seguro {

	private Frota frota;
	private Cliente_PJ cliente;

	///// Construtores
	public Seguro_PJ(int id, Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
			ArrayList<Condutor> listacondutores, int valorMensal, Frota frota, Cliente_PJ cliente) {
		super(id, dataInicio, dataFim, seguradora, listaSinistros, listacondutores, valorMensal);
		this.frota = frota;
		this.cliente = cliente;
	}

	public Seguro_PJ() {
	}

	public static boolean cadastraSeguro(Seguradora seguradora, Frota frota, Cliente_PJ cliente, String dataFimTexto,
			String dataInicioTexto) {
		/* Funcao que insere os dados do do seguro */

		String cnpj, code;
		Cliente clienteAux = null;

		Seguro_PJ seguro = new Seguro_PJ();
		Scanner entrada = Entradas.entrada;

		if (cliente == null) {

			System.out.println("Digite o CNPJ do cliente");
			cnpj = entrada.nextLine();

			if (!Validacao.validarCNPJ(cnpj)) {
				System.out.println("CNPJ invalido!");
				return false;
			}

			clienteAux = Seguradora.encontraCliente(seguradora, cnpj);

			if (clienteAux == null) {
				System.out.println("Cliente nao encontrado!");
				return false;
			}

			if (clienteAux.getClass().getSimpleName().equals("Cliente_PF")) {
				System.out.println("Cliente nao é do tipo PJ!");
				return false;
			}

			cliente = (Cliente_PJ) clienteAux;

			System.out.println("Digite o code da frota!");
			code = entrada.nextLine();

			frota = Cliente_PJ.encontraFrota(code, cliente);

			if (frota == null) {
				System.out.println("Frota nao encontrada!");
				return false;
			}
		}

		if (seguro.cadastraDadosSeguro(dataFimTexto, dataFimTexto)) {
			seguro.setCliente(cliente);
			seguro.setFrota(frota);
		} else {
			return false;
		}

		seguradora.getListaSeguros().add(seguro);
		seguro.setValorMensal(seguro.calcularValor());
		return true;
	}

	@Override
	public boolean autorizarCondutor(Condutor condutor) {
		/* Funcao que adiciona um condutor ao seguro */
		getListacondutores().add(condutor);
		setValorMensal(calcularValor());
		return true;
	}

	@Override
	public boolean desautorizarCondutor(int indice) {
		/* Funcao que remove um condutor do seguro */
		getListacondutores().remove(indice);
		System.out.println("Condutor desautorizado com sucesso!");
		setValorMensal(calcularValor());
		return true;
	}

	public boolean gerarSinistro(Seguro seguro, String cpf, String dataTexto, String endereco, String dataNascTexto) {
		/* Funcao que gera sinistros do tipo PJ */
		if (cpf == "") {

			System.out.println("Digite o CPF do condutor que voce ira cadastrar o sinistro");
			cpf = entrada.nextLine();

			if (!Validacao.validarCPF(cpf)) {
				System.out.println("Cpf invalido!");
				return false;
			}
		}

		for (int i = 0; i < super.getListacondutores().size(); i++) {

			if (getListacondutores().get(i).getCpf().equals(cpf)) {

				if (Sinistro.gerarSinistro(seguro, dataTexto, endereco, getListacondutores().get(i), dataNascTexto)) {
					System.out.println("Sinistro gerado com sucesso!");
					setValorMensal(calcularValor());
					return true;
				} else {
					System.out.println("Erro ao gerar o sinistro!");
					return false;
				}
			}
		}

		System.out.println("Condutor nao encontrado!");
		return false;
	}

	public double calcularValor() {
		/* Funcao que calcula o valor mensal do seguro PJ */

		double valor = 0, quantidadeVeiculos = 0, anosPosFundacao = 0, quantidadeSinistrosCliente = 0,
				quantidadeSinistrosCondutor = 0;

		for (int i = 0; i < cliente.getListaFrota().size(); i++) {

			for (int j = 0; j < cliente.getListaFrota().get(i).getListaVeiculos().size(); j++) {
				quantidadeVeiculos = quantidadeVeiculos + 1;
			}
		}

		anosPosFundacao = Cliente.calculaIdade(cliente.getDataFundacao());

		quantidadeSinistrosCliente = getListaSinistros().size();

		for (int i = 0; i < getListacondutores().size(); i++) {

			quantidadeSinistrosCondutor = quantidadeSinistrosCondutor
					+ getListacondutores().get(i).getListaSinistros().size();
		}

		valor = (CalcSeguro.VALOR_BASE.getValor() * (10 + (cliente.getQtdFuncioanios()) / 10)
				* (1 + 1 / (quantidadeVeiculos + 2)) * (1 + 1 / (anosPosFundacao + 2))
				* (2 + quantidadeSinistrosCliente / 10) * (5 + quantidadeSinistrosCondutor / 10));

		return valor;
	}

	@Override
	public String getIdCliente() {
		/* Funcao que retorna a identificacao do cliente */
		return this.cliente.getCnpj();
	}

	/// Getters e setters
	public Frota getFrota() {
		return frota;
	}

	public void setFrota(Frota frota) {
		this.frota = frota;
	}

	public Cliente_PJ getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = (Cliente_PJ) cliente;
	}

	@Override
	public String toString() {
		return "------------------------------------------------\n" + "Dados do seguro\n" + "ID:" + getId() + "\n"
				+ "Data de inicio:" + getDataInicio() + "\n" + "Data do fim:" + getDataFim() + "\n" + "Frota:" + frota
				+ "\n" + "Cliente nome:" + cliente.getNome() + "\n" + "Cliente CNPJ:" + cliente.getCnpj() + "\n"
				+ "Valor mensal:" + getValorMensal() + "\n" + "------------------------------------------------";
	}

}
