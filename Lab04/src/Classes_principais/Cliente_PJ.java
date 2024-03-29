package Classes_principais;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Enumeradores.CalcSeguro;
import Utilidades.Entradas;

public class Cliente_PJ extends Cliente {

	private final String cnpj;
	private Date dataFundacao;
	private int qtdFuncionarios;

	// contrutores
	public Cliente_PJ(String nome, String endereco, Date dataLicensa, String educacao, String genero,
			String classeEconomica, ArrayList<Veiculo> listaVeiculos, String cnpj, Date dataFundacao,
			int qtdFuncionarios, Double valorDouble) {
		super(nome, endereco, dataLicensa, educacao, genero, classeEconomica, listaVeiculos, valorDouble);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.qtdFuncionarios = qtdFuncionarios;
	}

	public Cliente_PJ(String cnpj) {
		this.cnpj = cnpj;
	}

	public static boolean CadastrarCliente(Seguradora seguradora, String cnpj, String dataFundacaoTexto,
			String dataLicensaTexto, int numeroFuncionarios, String nome, String endereco, String educacao,
			String genero, String classeEconomica) {
		/* Funcao atribui valores aos cliente do tipo PJ */

		Scanner entrada = Entradas.entrada;

		if (cnpj == "") {

			System.out.println("Digite o cnpj do cliente");
			cnpj = entrada.nextLine();

			if (!Validacao.validarCNPJ(cnpj)) {
				System.out.println("CNPJ invalido!");
				return false;
			}

			System.out.println("Digite a data de nascimento do cliente");
			dataFundacaoTexto = entrada.nextLine();

			if (!Validacao.verificaData(dataFundacaoTexto)) {
				return false;
			}

			System.out.println("Digite o numero de funcionarios do cliente");
			numeroFuncionarios = Integer.parseInt(entrada.nextLine());

			if (!Validacao.verificaData(dataFundacaoTexto)) {
				return false;
			}
		}

		Date dataFundacao = null;
		Cliente_PJ cliente_aux = new Cliente_PJ(cnpj);

		if (!cliente_aux.cadastraDadosCliente(nome, endereco, dataLicensaTexto, educacao, genero, classeEconomica)) {
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
		cliente_aux.setQtdFuncionario(numeroFuncionarios);
		seguradora.cadastrarCliente(cliente_aux);

		System.out.println("Cliente cadastrado com sucesso!");
		return true;
	}

	public Double calculaScore() {
		/* Funcao que calcula o score dos clientes do tipo PJ */

		double valor = 0;
		valor = CalcSeguro.VALOR_BASE.getValor() * (1 + (qtdFuncionarios) / 100) * getListaVeiculos().size();
		setValorSeguro(valor);
		return valor;
	}

	// getters e setters
	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setQtdFuncionario(int qtdFuncionarios) {
		this.qtdFuncionarios = qtdFuncionarios;
	}

	public int getQtdFuncionario() {
		return qtdFuncionarios;
	}

	@Override
	public String toString() {

		String veiculos = "";

		for (int i = 0; i < getListaVeiculos().size(); i++) {

			veiculos = veiculos + getListaVeiculos().get(i);
			veiculos = veiculos + "\n";
		}

		return "Informações do cliente:\n" + "Tipo: PJ\n" + "Nome:" + getNome() + "\n" + "CNPJ:" + getCnpj() + "\n"
				+ "Endereco:" + getEndereco() + "\n" + "Data da licensa:" + getDataLicensa() + "\n" + "Educacao:"
				+ getEducacao() + "\n" + "Genero:" + getGenero() + "\n" + "Classe econonmica:" + getClasseEconomica()
				+ "\n" + "Data de fundacao:" + getDataFundacao() + "\n" + "Quantidade de veiculos:"
				+ getListaVeiculos().size() + "\n" + veiculos;
	}
}
