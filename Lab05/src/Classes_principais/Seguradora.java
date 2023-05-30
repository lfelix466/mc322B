package Classes_principais;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Utilidades.Entradas;

public class Seguradora {

	private final String cnpj;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	ArrayList<Cliente> listaClientes;
	ArrayList<Seguro> listaSeguros;

	public Seguradora(String cnpj, String nome, String telefone, String endereco, String email,
			ArrayList<Cliente> listaClientes, ArrayList<Seguro> listaSeguros) {
		super();
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.listaClientes = listaClientes;
		this.listaSeguros = listaSeguros;
	}
	
	public Seguradora(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
	public static boolean cadastrarSeguradora(ArrayList<Seguradora> listaSeguradoras, String cpf, String nome,
			String telefone, String endereco, String email) {
		/* Funcao que adiciona os valores em cada seguradora */

		Scanner entrada = Entradas.entrada;
		
		if (cpf == "") {

			System.out.println("Digite o cpf da seguradora");
			cpf = entrada.nextLine();
			if (!Validacao.validarCPF(cpf)) {
				System.out.println("CPF invalido");
				return false;
			}
			
			System.out.println("Digite o nome da seguradora");
			nome = entrada.nextLine();
			if (!Validacao.verificaNome(nome)) {
				System.out.println("Nome invalido");
				return false;
			}

			System.out.println("Digite o telefone da seguradora");
			telefone = entrada.nextLine();
			if (!Validacao.verificaNumerosInteiros(telefone)) {
				System.out.println("Telefone invalido");
				return false;
			}

			System.out.println("Digite o email da seguradora");
			email = entrada.nextLine();
			if (!Validacao.verificaNome(email)) {
				System.out.println("Email invalido");
				return false;
			}

			System.out.println("Digite o endereco da seguradora");
			endereco = entrada.nextLine();
			if (!Validacao.verificaNome(endereco)) {
				System.out.println("Endereco invalido");
				return false;
			}
		}

		Seguradora seguradora = new Seguradora(cpf);
		seguradora.setNome(nome);
		seguradora.setEmail(email);
		seguradora.setEndereco(endereco);
		
		listaSeguradoras.add(seguradora);
		//////////////////////////

		return true;
	}

	public static boolean adicionarSeguradora(ArrayList<Seguradora> listaSeguradora) {
		/* Funcao que adiciona a seguradora na lista de seguradoras */
		Seguradora seguradora = new Seguradora();

		if (seguradora.cadastrarSeguradora("", "", "", "")) {
			listaSeguradora.add(seguradora);
			System.out.println("Seguradora cadastrada com sucesso!");
		}
		return true;
	}

	public static void listarSeguradoras(ArrayList<Seguradora> listaSeguradora) {
		/* Funcao que lista as seguradoras cadastradas */
		for (int i = 0; i < listaSeguradora.size(); i++) {
			System.out.println(i + " - Nome da seguadora: " + listaSeguradora.get(i).getNome() + "\n");
		}
	}

	public boolean cadastrarCliente(Cliente cliente) {
		/* Funcao de adicionar os clientes na listaClientes da seguradora */
		listaClientes.add(cliente);
		return true;
	}

	public boolean listarClientes(String tipoCliente) {
		/* Funcao que lista os clientes pelo seu tipo */

		// Serve pra saber se existe pelo menos um cliente na seguradora
		int aux = 0;
		if (listaClientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado nessa seguradora!");
		} else {
			System.out.println("Clientes:");

			for (int i = 0; i < listaClientes.size(); i++) {
				aux++;
				if (listaClientes.get(i).getClass().getSimpleName().equals(tipoCliente)) {
					System.out.println(aux + " - " + listaClientes.get(i).getNome());
					aux = 1;
				}
			}
			if (aux == 0) {
				System.out.println("Nao ha clientes desse tipo na seguradora!");
				return false;
			}
		}
		System.out.println();
		return true;
	}

	public boolean visualizarCliente(String cliente) {
		/* Funcao que vizualiza o cliente pelo nome */

		if (listaClientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado nessa seguradora!");
		} else {
			for (int i = 0; i < listaClientes.size(); i++) {
				if (listaClientes.get(i).getNome().equals(cliente)) {
					System.out.println(i + " - " + listaClientes.get(i).getNome());
					return true;
				}
			}
		}
		return false;
	}

	public Double calcularPrecoSeguroCliente(Cliente cliente) {
		/*
		 * Funcao que verifica o tipo do cliente e chama o calculaScore correspondente
		 */

		int quantidade_de_sinistros = 0;
		Double valor;

		for (int i = 0; i < listaSinistro.size(); i++) {
			if (listaSinistro.get(i).getCliente() == cliente) {
				quantidade_de_sinistros++;
			}
		}

		valor = cliente.calculaScore() * (1 + quantidade_de_sinistros);
		cliente.setValorSeguro(valor);
		
		return valor;
	}

	public boolean calcularReceita() {
		/* Funcao que calcula a receita total da seguradora */
		Double receita = (double) 0;

		for (int i = 0; i < listaClientes.size(); i++) {
			receita += listaClientes.get(i).getValorSeguro();
		}
		System.out.println("Receita total: " + receita);
		return true;
	}

	public boolean removerCliente(String cliente) {
		/* Funcao que remove clientes da listaClientes */

		for (int i = 0; i < listaClientes.size(); i++) {
			if (listaClientes.get(i).getNome().equals(cliente)) {
				listaClientes.remove(i);
				System.out.println("Cliente removido com sucesso!");
				return true;
			}
		}
		return true;
	}

	public boolean listarSinistrosCliente(Cliente cliente) {
		/* Funcao que lista os sinistros do cliente passado */

		// Verifica se existe pelo menos um sinistro no cliente
		int aux = 0;

		for (int i = 0; i < listaSinistro.size(); i++) {
			if (listaSinistro.get(i).getCliente().getNome() == cliente.getNome()) {
				System.out.println(listaSinistro.get(i).toString());
				aux = 1;
			}
		}

		if (aux == 0) {
			System.out.println("Cliente nao possui sinistros nessa seguradora!");
			return false;
		}
		return true;
	}

	public boolean listarSinistrosSeguradora() {
		/* Funcao que lista os sinistros presentes na seguradora */
		if (listaSinistro.size() == 0) {
			System.out.println("Nao existem sinistros nessa seguradora!");
			return false;
		} else {
			for (int i = 0; i < listaSinistro.size(); i++) {
				System.out.println(listaSinistro.get(i).toString());
			}
		}
		return true;
	}

	public static String encontraSeguradora(ArrayList<Seguradora> listaSeguradora, String nome) {
		/*
		 * Funcao que retorna o indice da seguradora na listaSeguradoras de acordo com o
		 * nome digitado
		 */
		for (int i = 0; i < listaSeguradora.size(); i++) {
			if (listaSeguradora.get(i).getNome().equals(nome)) {
				return "" + i;
			}
		}
		return "Falso";
	}

	public static String encontraCliente(ArrayList<Seguradora> listaSeguradora, int indice, String nome) {
		/*
		 * Funcao que retorna o indice da do cliente na seguradora de acordo com o nome
		 * digitado
		 */
		for (int i = 0; i < listaSeguradora.get(indice).getListaClientes().size(); i++) {
			if (listaSeguradora.get(indice).getListaClientes().get(i).getNome().equals(nome)) {
				return "" + i;
			}
		}
		return "Falso";
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

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}

	public Scanner getEntrada() {
		return entrada;
	}

	public void setEntrada(Scanner entrada) {
		this.entrada = entrada;
	}

	public String getCnpj() {
		return cnpj;
	}

	@Override
	public String toString() {
		return "Dados da seguradora\n"
				+ "Cnpj:"+cnpj+"\n"
					+"Nome:"+nome+"\n"
						+"Telefone:"+telefone+"\n"
							+"Endereco:"+endereco+"\n"
								+"Email:"+email+"\n"
									+"Lista de clientes:"+listaClientes+"\n"
										+ "Lista de seguros:"+listaSeguros;
	}
		
}