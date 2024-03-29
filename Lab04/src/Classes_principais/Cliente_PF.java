package Classes_principais;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Enumeradores.CalcSeguro;
import Utilidades.Entradas;

public class Cliente_PF extends Cliente {

	private final String cpf;
	private Date dataNascimento;

	// Construtores
	public Cliente_PF(String nome, String endereco, Date dataLicensa, String educacao, String genero,
			String classeEconomica, ArrayList<Veiculo> listaVeiculos, String cpf, Date dataNascimento,
			Double valorSeguro) {
		super(nome, endereco, dataLicensa, educacao, genero, classeEconomica, listaVeiculos, valorSeguro);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public Cliente_PF(String cpf) {
		this.cpf = cpf;
	}

	public static boolean CadastrarCliente(Seguradora seguradora, String cpf, String dataNascimentoTexto,
			String dataLicensaTexto, String nome, String endereco, String educacao, String genero,
			String classeEconomica) {
		/* Funcao que insere dados no cliente do tipo PF */

		Scanner entrada = Entradas.entrada;

		if (cpf == "") {

			System.out.println("Digite o cpf do cliente");
			cpf = entrada.nextLine();

			if (!Validacao.validarCPF(cpf)) {
				System.out.println("CPF invalido!");
				return false;
			}

			System.out.println("Digite a data de nascimento do cliente");
			dataNascimentoTexto = entrada.nextLine();

			if (!Validacao.verificaData(dataNascimentoTexto)) {
				System.out.println("Data incorreta!");
				return false;
			}
		}

		Date dataNascimento = null;
		Cliente_PF cliente_aux = new Cliente_PF(cpf);

		if (!cliente_aux.cadastraDadosCliente(nome, endereco, dataLicensaTexto, educacao, genero, classeEconomica)) {
			return false;
		}

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			dataNascimento = formatter.parse(dataNascimentoTexto);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		cliente_aux.setDataNascimento(dataNascimento);
		seguradora.cadastrarCliente(cliente_aux);
		System.out.println("Cliente cadastrado com sucesso!");
		return true;
	}

	public Double calculaScore() {
		/* Funcao que calcula score do cliente PF */

		double valor = 0;
		int idade = calculaIdade(dataNascimento);

		if (idade <= 30) {
			valor = CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.FATOR_18_30.getValor() * getListaVeiculos().size();
		} else if (idade <= 60) {
			valor = CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.FATOR_30_60.getValor() * getListaVeiculos().size();
		} else {
			valor = CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.FATOR_60_90.getValor() * getListaVeiculos().size();
		}
		return valor;
	}

	public int calculaIdade(Date data) {
		/* Funcao que retorna a idade do cliente baseado no ano de nascimento */
		LocalDate dataAtual = LocalDate.now();
		LocalDate date = LocalDate.ofInstant(data.toInstant(), ZoneId.systemDefault());
		Period idade = Period.between(date, dataAtual);
		return idade.getYears();

	}

	// getters e setters
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	@Override
	public String toString() {

		String veiculos = "";

		for (int i = 0; i < getListaVeiculos().size(); i++) {

			veiculos = veiculos + getListaVeiculos().get(i);
			veiculos = veiculos + "\n";
		}

		return "Informações do cliente:\n" + "Tipo: PJ\n" + "Nome:" + getNome() + "\n" + "CPF:" + getCpf() + "\n"
				+ "Endereco:" + getEndereco() + "\n" + "Data da licensa:" + getDataLicensa() + "\n" + "Educacao:"
				+ getEducacao() + "\n" + "Genero:" + getGenero() + "\n" + "Classe econonmica:" + getClasseEconomica()
				+ "\n" + "Data de nascimento:" + getDataNascimento() + "\n" + "Quantidade de veiculos:"
				+ getListaVeiculos().size() + "\n" + veiculos;
	}
}
