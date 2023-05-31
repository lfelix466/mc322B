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
	private String genero;
	private String educacao;
	private Date dataNasc;
	private ArrayList<Veiculo> listaVeiculos;
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
			String endereco, String email,String genero, String educacao, String dataNascTexto) {
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

	public boolean cadastrarVeiculo(String placa, String marca, String modelo,
			int anoFabricacao) {
		
		Veiculo veiculo = new Veiculo();
		if(veiculo.cadastrarVeiculo(placa, marca, modelo, anoFabricacao)) {
		
			System.out.println("Veiculo cadastrado com sucesso!");
			listaVeiculos.add(veiculo);
			return true;
			
		}
		
		System.out.println("Erro ao cadastrar veiculo!");
		return false;

	}
	
	public boolean removerVeiculo(String placa) {
		
		if(listaVeiculos.isEmpty()) {
			
			System.out.println("Cliente nao possui veiculos cadastrados!");
			return false;
		}
		
		if(placa == "") {
			
			System.out.println("Digite a placa do veiculo que deseja remover");
			placa = entrada.nextLine();
			
			for(int i = 0; i<listaVeiculos.size(); i++) {
				
				if(listaVeiculos.get(i).getPlaca().equals(placa)) {
					
					listaVeiculos.remove(i);
					System.out.println("Veiculo removido com sucesso!");
					return true;
					
			}}}
		
		System.out.println("Veiculo nao encontrado!");
		
		return false;
	}

	public int calculaIdade(Date data) {
		/* Funcao que retorna a idade do cliente baseado no ano de nascimento */
		LocalDate dataAtual = LocalDate.now();
		LocalDate date = LocalDate.ofInstant(data.toInstant(), ZoneId.systemDefault());
		Period idade = Period.between(date, dataAtual);
		return idade.getYears();

	}

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

	public Scanner getEntrada() {
		return entrada;
	}

	public void setEntrada(Scanner entrada) {
		this.entrada = entrada;
	}

	public String getCpf() {
		return cpf;
	}

	@Override
	public String toString() {
		return "Dados do cliente PF\n"
				+"CPF:"+cpf+"\n"
					+"Genero:"+genero+"\n"
						+"Educacao:"+educacao+"\n"
							+"Data de nascimento:"+dataNasc+"\n"
								+"Lista de veiculos:"+listaVeiculos;
	}
	
}
