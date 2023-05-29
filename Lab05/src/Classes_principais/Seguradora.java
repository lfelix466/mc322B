package Classes_principais;

import java.util.ArrayList;
import java.util.Scanner;

import Utilidades.Entradas;

import java.util.List;

public class Seguradora {

	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private ArrayList<Sinistro> listaSinistro = new ArrayList<Sinistro>();
	private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

	Scanner entrada = Entradas.entrada;

	// Construtor
	public Seguradora(String nome, String telefone, String email, String endereco, ArrayList<Sinistro> listaSinistro,
			ArrayList<Cliente> listaClientes) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	public Seguradora() {

	}

	public boolean cadastrarSeguradora(String nome, String telefone, String email, String endereco) {
		/* Funcao que adiciona os valores em cada seguradora */
		if (nome == "") {

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

		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;

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

	public boolean listarVeiculos() {
		/* Funcao que lista todos os veiculos dos clientes que a seguradora possui */
		int aux = 0;

		if (listaClientes.size() == 0) {
			System.out.println("Essa seguradora nao possui clientes cadastrados");
			return false;

		} else {
			for (int i = 0; i < listaClientes.size(); i++) {
				if (listaClientes.get(i).listarVeiculo(1)) {
					aux = 1;
		}}}

		if (aux == 0) {
			System.out.println("Essa seguradora nao possui veiculos cadastrados!");
			return false;
		}
		return true;
	}

	public boolean gerarSinitro(String dataSinistro, String endereco, Seguradora seguradora, Veiculo veiculo,
			Cliente cliente) {
		/* Funcao que gera sinistros e os adiciona na lista */

		Sinistro sinistro = new Sinistro(dataSinistro, endereco, seguradora, veiculo, cliente);
		listaSinistro.add(sinistro);
		return true;
	}

	public boolean visualizarSinistro(String cliente) {
		/* Funcao que visisualiza os sinistros do cliente presentes na seguradora */

		if (listaSinistro.isEmpty()) {
			System.out.println("Nenhum sinistro cadastrado nessa seguradora!");
		} else {
			for (int i = 0; i < listaSinistro.size(); i++) {
				if (listaSinistro.get(i).getCliente().getNome().equals(cliente)) {
					System.out.println(listaSinistro.get(i));
					return true;
				}
			}
		}

		return false;
	}
	
	public static boolean transferirSeguro(ArrayList<Seguradora> listaSeguros, int indiceSeguro, int indiceCliente) {
		/* Funcao que transfere os veiculos dos clientes */
		String nome, resultado = "", opcao = "";
		int indiceSeguro2 = 0, indiceCliente2 = 0, aux = 0;
		Scanner entrada = Entradas.entrada;

		System.out.println("Qual o outro cliente?");

		nome = entrada.nextLine();
		
		if(nome.equals(listaSeguros.get(indiceSeguro).getListaClientes().get(indiceCliente).getNome())) {
			System.out.println("Nao se pode transferir o seguro para ele mesmo!");
		}else {
			
			if (!Validacao.verificaNome(nome)) {
				System.out.println("Nome invalido!");
				return false;
			}

			for (int i = 0; i < listaSeguros.size(); i++) {

				resultado = Seguradora.encontraCliente(listaSeguros, i, nome);
				if (resultado != "Falso") {
					indiceSeguro2 = i;
					indiceCliente2 = Integer.parseInt(resultado);
					aux = 1;
					break;
				}
			}

			if (aux == 0) {
				System.out.println("Cliente nao econtrado");
				return false;
			}

			System.out.println("0 - Transferir o seguro de '"
					+ listaSeguros.get(indiceSeguro).getListaClientes().get(indiceCliente).getNome() + "' para '"
					+ listaSeguros.get(indiceSeguro2).getListaClientes().get(indiceCliente2).getNome() + "'?");
			System.out.println("1 - Transferir o seguro de '"
					+ listaSeguros.get(indiceSeguro2).getListaClientes().get(indiceCliente2).getNome() + "' para '"
					+ listaSeguros.get(indiceSeguro).getListaClientes().get(indiceCliente).getNome() + "'?");
			System.out.println("Digite a opcao desejada:");
			opcao = entrada.nextLine();

			// Verifica a ordem de transferencia escolhida
			if (opcao.equals("0")) {
				for (int i = listaSeguros.get(indiceSeguro).getListaClientes().get(indiceCliente).getListaVeiculos().size()
						- 1; i >= 0; i--) {

					listaSeguros.get(indiceSeguro2).getListaClientes().get(indiceCliente2).getListaVeiculos().add(
							listaSeguros.get(indiceSeguro).getListaClientes().get(indiceCliente).getListaVeiculos().get(i));

					listaSeguros.get(indiceSeguro).getListaClientes().get(indiceCliente).getListaVeiculos().remove(i);
				}

			} else if (opcao.equals("1")) {
				for (int i = listaSeguros.get(indiceSeguro2).getListaClientes().get(indiceCliente2).getListaVeiculos()
						.size() - 1; i >= 0; i--) {

					listaSeguros.get(indiceSeguro).getListaClientes().get(indiceCliente).getListaVeiculos().add(listaSeguros
							.get(indiceSeguro2).getListaClientes().get(indiceCliente2).getListaVeiculos().get(i));

					listaSeguros.get(indiceSeguro2).getListaClientes().get(indiceCliente2).getListaVeiculos().remove(i);
				}

			} else {
				System.out.println("Opcao inexistente");
				return false;
			}

			// Calcula os novos valores para os clientes
			listaSeguros.get(indiceSeguro)
					.calcularPrecoSeguroCliente((listaSeguros.get(indiceSeguro).getListaClientes().get(indiceCliente)));
			listaSeguros.get(indiceSeguro2)
					.calcularPrecoSeguroCliente((listaSeguros.get(indiceSeguro2).getListaClientes().get(indiceCliente2)));

			System.out.println("Seguradora transferida com sucesso!");
			return true;
			
		}
		
		return false;
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

	// getters e setters
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Sinistro> getListaSinistro() {
		return listaSinistro;
	}

	public void setListaSinistro(ArrayList<Sinistro> listaSinistro) {
		this.listaSinistro = listaSinistro;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	@Override
	public String toString() {
		return "Seguradora [nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", endereco=" + endereco
				+ ", listaSinistro=" + listaSinistro + ", listaClientes=" + listaClientes + "]";
	}
}