package Classes_principais;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import Utilidades.Entradas;

public class Cliente_PJ extends Cliente {

	private final String cnpj;
	private Date dataFundacao;
	private ArrayList<Frota> listaFrota = new ArrayList<Frota>();
	private int qtdFuncionarios;

	// Construtores
	public Cliente_PJ(String nome, String telefone, String endereco, String email, String cnpj, Date dataFundacao,
			int qtdFuncionarios, ArrayList<Frota> listaFrota) {
		super(nome, telefone, endereco, email);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.qtdFuncionarios = qtdFuncionarios;
	}

	public Cliente_PJ(String cnpj) {
		this.cnpj = cnpj;
	}

	public static boolean CadastrarCliente(Seguradora seguradora, String nome, String telefone, String endereco,
			String email, String cnpj, String dataFundacaoTexto, String qtdFuncionariosTexto) {
		/* Funcao atribui valores aos cliente do tipo PJ */

		Scanner entrada = Entradas.entrada;

		if (cnpj == "") {

			System.out.println("Digite o cnpj do cliente");
			cnpj = entrada.nextLine();

			if (!Validacao.validarCNPJ(cnpj)) {
				System.out.println("CNPJ invalido!");
				return false;
			}

			System.out.println("Digite a data de fundacao do cliente");
			dataFundacaoTexto = entrada.nextLine();

			if (!Validacao.verificaData(dataFundacaoTexto)) {
				System.out.println("Data invalida!");
				return false;
			}

			System.out.println("Digite a quantidade de funcionarios do cliente");
			qtdFuncionariosTexto = entrada.nextLine();

			if (!Validacao.verificaNumerosInteiros(qtdFuncionariosTexto)) {
				System.out.println("Valor invalido!");
				return false;
			}
		}

		Date dataFundacao;
		Cliente_PJ cliente_aux = new Cliente_PJ(cnpj);

		if (!cliente_aux.cadastraDadosCliente(nome, telefone, endereco, email)) {
			return false;
		}

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			dataFundacao = formatter.parse(dataFundacaoTexto);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		cliente_aux.setDataFundacao(dataFundacao);
		cliente_aux.setQtdFuncioanios(Integer.parseInt(qtdFuncionariosTexto));
		seguradora.cadastrarCliente(cliente_aux);

		System.out.println("Cliente cadastrado com sucesso!");
		return true;
	}

	@Override
	public boolean cadastrarFrota(String code) {
		/* Funcao que cadastra frota de um cliente */
		if (code == "") {
			System.out.println("Digite o code da frota");
			code = entrada.nextLine();
		}

		Frota frota = new Frota();
		frota.setCode(code);
		listaFrota.add(frota);
		System.out.println("Frota cadastrada com sucesso!");

		return true;
	}

	@Override
	public boolean atualizarFrota(String tipo, String placa, String marca, String modelo, int anoFabricacao,
			String code) {
		/*
		 * Funcao que atualiza os dados de uma frota cadastrando ou removendo veiculos
		 * ou a frota toda
		 */

		String resultado;
		int indiceFrota = 0;

		resultado = getFrota(code);

		if (resultado == "") {
			return false;
		}

		indiceFrota = Integer.parseInt(resultado);

		if (tipo == "") {
			System.out.println("1 - Cadastrar veiculo\n" + "2 - Remover veiculo\n" + "3 - Apagar frota\n"
					+ "Digite a opcao desejada");
			tipo = entrada.nextLine();
		}

		switch (tipo) {

		case "1":
			listaFrota.get(indiceFrota).addVeiculo(placa, marca, modelo, anoFabricacao);
			break;

		case "2":
			System.out.println("Digite a placa do veiculo que deseja remover");
			placa = entrada.nextLine();

			for (int i = 0; i < listaFrota.get(indiceFrota).getListaVeiculos().size(); i++) {

				if (listaFrota.get(indiceFrota).getListaVeiculos().get(i).getPlaca().equals(placa)) {

					listaFrota.get(indiceFrota).getListaVeiculos().remove(i);
					System.out.println("Veiculo removido com sucesso!");
					return true;
				}
			}
			System.out.println("Veiculo nao encontrado nesta frota!");
			break;

		case "3":
			listaFrota.remove(indiceFrota);
			System.out.println("Frota removida com sucesso!");
			return true;

		default:
			System.out.println("Opcao invalida!");
			break;

		}
		return false;
	}

	public boolean getVeiculosPorFrota(String code, Cliente_PJ cliente) {
		/* Funcao que mostra os dados de uma frota selecionada pelo code */

		Frota frota = null;

		if (code == "") {

			System.out.println("Digite o code da frota que deseja encontrar");
			code = entrada.nextLine();

			frota = Cliente_PJ.encontraFrota(code, cliente);

			if (frota == null) {
				System.out.println("Frota nao encontrada!");
				return false;
			}

			if (frota.getListaVeiculos().isEmpty()) {

				System.out.println("Frota nao possui veiculos!");
				return false;

			}

			System.out.println("Veiculos da frota com code:" + code + "\n");
			for (int i = 0; i < frota.getListaVeiculos().size(); i++) {
				System.out.println(frota.getListaVeiculos().get(i).toString());
			}

		}
		return true;
	}

	private String getFrota(String code) {
		/* Funcao que retorna se a frota existe e retorna o indice caso exista */

		if (code == "") {
			System.out.println("Digite o code da frota");
			code = entrada.nextLine();
		}

		if (listaFrota.isEmpty()) {
			System.out.println("Nao ha frotas cadastradas neste cliente!");
			return "";
		}

		for (int i = 0; i < listaFrota.size(); i++) {
			if (listaFrota.get(i).getCode().equals(code)) {
				return "" + i;
			}
		}

		System.out.println("Frota nao encontrada!");
		return "";
	}

	@Override
	public boolean listarFrota() {
		/* Funcao lista todas as frotas de um cliente */

		if (listaFrota.isEmpty()) {
			System.out.println("Nao ha frotas cadastrados neste cliente!");
			return false;
		}
		System.out.println("Frotas do cliente:" + getNome() + "\n");
		for (int i = 0; i < listaFrota.size(); i++) {
			System.out.println(listaFrota.get(i).toString());
		}
		return true;
	}

	@Override
	public boolean listarVeiculos() {
		/* Funcao quw lista os veiculos de um cliente do tipo PJ */
		int aux = 0;

		if (listaFrota.isEmpty()) {
			System.out.println("Nao ha frota cadastradas neste cliente!");
			return false;
		}
		System.out.println("Veiculos do cliente:" + getNome() + "\n");
		for (int i = 0; i < listaFrota.size(); i++) {

			for (int j = 0; j < listaFrota.get(i).getListaVeiculos().size(); j++) {
				System.out.println(listaFrota.get(i).getListaVeiculos().get(j).toString());
				aux = 1;
			}
		}

		if (aux == 0) {
			System.out.println("Cliente nao possui nenhum veiculo cadastrado em nenhuma frota!");
		}
		return true;
	}

	@Override
	public String getId() {
		/* Funcao que retorna a identificacao do cliente */
		return this.cnpj;

	}

	@Override
	public String posicaoVeiculoFrota(String code) {
		/* Funcao que retorna a posicao de um veiculo em uma forta */
		for (int i = 0; i < listaFrota.size(); i++) {

			if (listaFrota.get(i).getCode().equals(code)) {
				return "" + i;
			}
		}

		return "Falso";
	}

	public static Frota encontraFrota(String code, Cliente_PJ cliente) {
		/* Funcao que retorna um objeto frota de acordo com o code */
		for (int i = 0; i < cliente.getListaFrota().size(); i++) {
			if (cliente.getListaFrota().get(i).getCode().equals(code)) {
				return cliente.getListaFrota().get(i);
			}
		}
		return null;
	}

	//// Getters e setters
	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public ArrayList<Frota> getListaFrota() {
		return listaFrota;
	}

	public void setListaFrota(ArrayList<Frota> listaFrota) {
		this.listaFrota = listaFrota;
	}

	public String getCnpj() {
		return cnpj;
	}

	public int getQtdFuncioanios() {
		return qtdFuncionarios;
	}

	public void setQtdFuncioanios(int qtdFuncionarios) {
		this.qtdFuncionarios = qtdFuncionarios;
	}

	@Override
	public String toString() {
		return "Dados do cliente:" + getNome() + "\n" + "CNPJ:" + cnpj + "\n" + "Data de fundacao:" + dataFundacao
				+ "\n" + "Quantidade de funcionarios:" + qtdFuncionarios + "\n";
	}
}
