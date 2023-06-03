package Classes_principais;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

import Utilidades.Entradas;

public abstract class Cliente {

	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	Scanner entrada = Entradas.entrada;
	
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
	
	public static int calculaIdade(Date data) {
		/* Funcao que retorna a idade do cliente baseado no ano de nascimento */
		LocalDate dataAtual = LocalDate.now();
		LocalDate date = LocalDate.ofInstant(data.toInstant(), ZoneId.systemDefault());
		Period idade = Period.between(date, dataAtual);
		return idade.getYears();
	}
	
	public boolean cadastrarVeiculo(String placa, String marca, String modelo,
			int anoFabricacao) {			
		return false;
	}
	
	public boolean cadastrarFrota(String code) {
		return false;
	}
	
	public abstract String getId();
	public abstract String posicaoVeiculoFrota(String id);
	
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