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
	private	Date dataFundacao;
	private ArrayList<Frota> listaFrota;

	public Cliente_PJ(String nome, String telefone, String endereco, String email, String cnpj, Date dataFundacao,
			ArrayList<Frota> listaFrota) {
		super(nome, telefone, endereco, email);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.listaFrota = listaFrota;
	}

	public Cliente_PJ(String cnpj) {
		this.cnpj = cnpj;
	}

	public static boolean CadastrarCliente(Seguradora seguradora, String nome, String telefone, 
			String endereco, String email, String cnpj, String dataFundacaoTexto) {
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
		seguradora.cadastrarCliente(cliente_aux);

		System.out.println("Cliente cadastrado com sucesso!");
		return true;
	}

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

	@Override
	public String toString() {
		return "Dados do cliente PJ\n"
				+"CNPJ:"+cnpj+"\n"
					+"Data de fundacao:"+dataFundacao+"\n"
						+"Lista da frota:"+listaFrota;
	}
}
