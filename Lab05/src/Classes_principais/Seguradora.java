package Classes_principais;

import java.util.ArrayList;
import java.util.Scanner;

import Utilidades.Entradas;

public class Seguradora {

	private final String cnpj;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	ArrayList<Seguro> listaSeguros = new ArrayList<Seguro>();
	Scanner entrada = Entradas.entrada;

	/// Construtores
	public Seguradora(String cnpj, String nome, String telefone, String endereco, String email,
			ArrayList<Cliente> listaClientes, ArrayList<Seguro> listaSeguros) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}

	public Seguradora(String cnpj) {
		this.cnpj = cnpj;
	}

	public static boolean cadastrarSeguradora(ArrayList<Seguradora> listaSeguradoras, String cnpj, String nome,
			String telefone, String endereco, String email) {
		/* Funcao que adiciona os valores em um seguradora */

		Scanner entrada = Entradas.entrada;

		if (cnpj == "") {

			System.out.println("Digite o CNPJ da seguradora");
			cnpj = entrada.nextLine();
			if (!Validacao.validarCNPJ(cnpj)) {
				System.out.println("CNPJ invalido");
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

		Seguradora seguradora = new Seguradora(cnpj);
		seguradora.setNome(nome);
		seguradora.setEmail(email);
		seguradora.setEndereco(endereco);
		seguradora.setTelefone(telefone);
		listaSeguradoras.add(seguradora);

		System.out.println("Seguradora cadastrada com sucesso!");

		return true;
	}

	public boolean gerarSeguro(int tipo, Seguradora seguradora, Veiculo veiculo, Frota frota, Cliente_PF clientePF,
			Cliente_PJ clientePJ, String dataFimTexto, String dataInicioTexto) {
		/* Funcao que gera um seguro e adiciona na lista de seguros da seguradora */

		if (tipo == 0) {

			System.out.println(
					"Qual tipo de seguro deseja cadastrar\n" + "1 - seguro do tipo PF\n" + "2 - seguro do tipo PJ");
			try {
				tipo = Integer.parseInt(entrada.nextLine());
			} catch (Exception e) {
				System.out.println("Tipo invalido!");
				return false;
			}
		}

		if (tipo == 1) {

			if (Seguro_PF.cadastraSeguro(seguradora, veiculo, clientePF, dataFimTexto, dataInicioTexto)) {

				System.out.println("Seguro PF cadastrado com sucesso!");
				return true;
			}

		} else if (tipo == 2) {

			if (Seguro_PJ.cadastraSeguro(seguradora, frota, clientePJ, dataFimTexto, dataInicioTexto)) {
				System.out.println("Seguro PJ cadastrado com sucesso!");
				return true;
			}
		}
		System.out.println("Opcao invalida!");
		return false;
	}

	public boolean cancelaSeguro(String idTexto) {
		/* Funcao que remove um seguro baseado no id */

		int id;

		if (idTexto == "") {

			System.out.println("Digite o ID do seguro que deseja remover");
			idTexto = entrada.nextLine();

			if (!Validacao.verificaNumerosInteiros(idTexto)) {
				System.out.println("ID invalido!");
				return false;
			}
		}

		id = Integer.parseInt(idTexto);

		for (int i = 0; i < listaSeguros.size(); i++) {

			if (listaSeguros.get(i).getId() == id) {

				for (int j = 0; j < listaSeguros.get(i).getListacondutores().size(); j++) {

					for (int z = 0; z < listaSeguros.get(i).getListacondutores().get(j).getListaSinistros()
							.size(); i++) {

						if (listaSeguros.get(i).getListacondutores().get(j).getListaSinistros().get(z).getSeguro()
								.equals(listaSeguros.get(i))) {

							listaSeguros.get(i).getListacondutores().get(j).getListaSinistros().remove(z);

						}
					}
				}

				listaSeguros.remove(i);
				System.out.println("Seguro removido com sucesso!");
				return true;
			}

		}
		System.out.println("Seguro nao encontrado!");
		return false;
	}

	public void cancelaSegurosCliente(Cliente cliente) {
		/* Funcao que cancela todos os seguros associados a um cliente */

		for (int i = listaSeguros.size() - 1; i > -1; i--) {
			if (listaSeguros.get(i).getIdCliente().equals(cliente.getId())) {
				listaSeguros.remove(i);
			}
		}
	}

	public boolean cadastrarCliente(Cliente cliente) {
		/* Funcao de adicionar os clientes na listaClientes da seguradora */
		listaClientes.add(cliente);
		return true;
	}

	public boolean removerCliente(Cliente cliente) {
		/* Funcao que remove um clientes da listaClientes */

		for (int i = 0; i < listaClientes.size(); i++) {
			if (listaClientes.get(i) == cliente) {
				listaClientes.remove(i);
				System.out.println("Cliente removido com sucesso!");
				return true;
			}
		}
		return true;
	}

	public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente) {
		/*
		 * Funcao que retorna uma lista de todos os seguros de um cliente em uma
		 * seguradora
		 */

		ArrayList<Seguro> lista = new ArrayList<Seguro>();
		for (int i = 0; i < listaSeguros.size(); i++) {
			if (listaSeguros.get(i).getIdCliente() == cliente.getId()) {
				lista.add(listaSeguros.get(i));
			}
		}

		return lista;
	}

	public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente) {
		/*
		 * Funcao que retorna uma lista com todos os sinistros associdos a um cliente de
		 * uma seguradora
		 */

		ArrayList<Sinistro> lista = new ArrayList<Sinistro>();

		for (int i = 0; i < listaSeguros.size(); i++) {
			if (listaSeguros.get(i).getIdCliente() == cliente.getId()) {
				for (int j = 0; j < listaSeguros.get(i).getListaSinistros().size(); j++) {
					lista.add(listaSeguros.get(i).getListaSinistros().get(j));
				}
			}
		}
		return lista;
	}

	public void calcularReceita() {
		/* Funcao que calcula a receita total da seguradora */
		Double receita = (double) 0;
		ArrayList<Seguro> lista = new ArrayList<Seguro>();

		for (int i = 0; i < listaSeguros.size(); i++) {
			receita += listaSeguros.get(i).getValorMensal();
		}

		int aux = 0;
		for (int i = 0; i < listaClientes.size(); i++) {

			aux = 0;
			System.out.println("Balanco dos seguros do cliente: " + listaClientes.get(i).getNome());
			lista = getSegurosPorCliente(listaClientes.get(i));

			for (int j = 0; j < lista.size(); j++) {
				aux = 1;
				System.out.println(lista.get(j).toString());
			}

			if (aux == 0) {
				System.out.println("Cliente nao possui seguros cadastrados!\n");
			}
		}

		System.out.println("Receita total da seguradora: " + receita);
	}

	public boolean transferirSeguros(Seguradora seguradora, Cliente cliente1) {
		/* Funcao que transfere um seguro de um cliente a outro */
		String id, opcao;
		Cliente cliente2 = null;

		if (cliente1.getClass().getSimpleName().equals("Cliente_PF")) {

			System.out.println("Digite CPF do outro cliente");
			id = entrada.nextLine();

			if (!Validacao.validarCPF(id)) {
				System.out.println("CPF invalido!");
			}

		} else {

			System.out.println("Digite CNPJ do outro cliente");
			id = entrada.nextLine();

			if (!Validacao.validarCNPJ(id)) {
				System.out.println("CNPJ invalido!");
			}
		}

		if (cliente1.getId().equals(id)) {

			System.out.println("Nao se pode transferir seguro para o mesmo cliente!");
			return false;

		}

		cliente2 = Seguradora.encontraCliente(seguradora, id);

		if (cliente2 == null) {
			System.out.println("Cliente nao econtrado!");
			return false;
		}

		System.out.println("1 - Mover seguro de " + cliente1.getNome() + " para " + cliente2.getNome());
		System.out.println("2 - Mover seguro de " + cliente2.getNome() + " para " + cliente1.getNome());
		System.out.println("Digite a opcao desejada");
		opcao = entrada.nextLine();

		switch (opcao) {
		case "1":
			trocarSeguroAux(cliente1, cliente2);

			break;
		case "2":
			trocarSeguroAux(cliente2, cliente1);

			break;
		default:
			System.out.println("Opcao invalida!");
			return false;
		}

		return true;
	}

	private boolean listarSeguradora(ArrayList<Seguro> lista) {
		/*
		 * Funcao que lista todas as seguradoras disponiveis para a transferencia
		 * verificando o tipo de seguro e o tipo de cliente
		 */
		if (lista.isEmpty()) {
			System.out.println("Cliente nao possui seguros cadastrados!");
			return false;
		}

		System.out.println("Seguros disponiveis para transferencia");
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).toString());
		}

		return true;
	}

	private boolean trocarSeguroAux(Cliente cliente1, Cliente cliente2) {
		/*
		 * Funcao que auxilia na transferencia de um seguro Essa funcao muda o cliente
		 * que esta cadastrado em um seguro
		 */
		String opcao;
		ArrayList<Seguro> lista;

		lista = getSegurosPorCliente(cliente1);

		if (!listarSeguradora(lista)) {
			System.out.println("Nenhum seguro encontrado!");
			return false;
		}

		System.out.println("Digite o id do seguro que voce quer trocar");
		opcao = entrada.nextLine();

		if (!Validacao.verificaNumerosInteiros(opcao)) {
			System.out.println("Opcao invalida!");
			return false;
		}

		int id = Integer.parseInt(opcao);

		for (int i = 0; i < lista.size(); i++) {

			if (lista.get(i).getId() == id) {

				lista.get(i).setCliente(cliente2);
				System.out.println("Seguro transferido com sucesso!");
				System.out.println("Seguro ID:" + lista.get(i).getId());
				System.out.println("Foi do cliente:" + cliente1.getNome() + " para o cliente:" + cliente2.getNome());
				return true;
			}
		}

		System.out.println("ID inexistente!");

		return false;
	}

	public static void listarSeguradoras(ArrayList<Seguradora> listaSeguradora) {
		/* Funcao que lista as seguradoras cadastradas */
		System.out.println("Seguradoras\n");
		for (int i = 0; i < listaSeguradora.size(); i++) {
			System.out.println(listaSeguradora.get(i).toString());
		}
	}

	public boolean listarClientes(String tipoCliente) {
		/* Funcao que lista os clientes pelo seu tipo */
		int aux = 0;
		if (listaClientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado nessa seguradora!");
		} else {
			System.out.println("Clientes:");

			for (int i = 0; i < listaClientes.size(); i++) {
				if (listaClientes.get(i).getClass().getSimpleName().equals(tipoCliente)) {
					aux++;
					System.out.println(aux + " - " + listaClientes.get(i).getNome());
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

	public boolean atualizaSeguros() {
		/* Funcao que atualiza os valores mensais da seguradora */

		for (int i = 0; i < listaSeguros.size(); i++) {
			listaSeguros.get(i).setValorMensal(listaSeguros.get(i).calcularValor());
		}
		return true;
	}

	public static Seguradora encontraSeguradora(ArrayList<Seguradora> listaSeguradora, String cnpj) {
		/* Funcao que retorna um objeto do tipo seguradora de acordo com cpnj */
		for (int i = 0; i < listaSeguradora.size(); i++) {
			if (listaSeguradora.get(i).getCnpj().equals(cnpj)) {
				return listaSeguradora.get(i);
			}
		}
		return null;
	}

	public static Cliente encontraCliente(Seguradora seguradora, String id) {
		/* Funcao que retorna um objeto cliente de acordo com a identificacao */
		for (int i = 0; i < seguradora.getListaClientes().size(); i++) {
			if (seguradora.getListaClientes().get(i).getId().equals(id)) {
				return seguradora.getListaClientes().get(i);
			}
		}

		return null;
	}

	public static Seguro encontraSeguro(Seguradora seguradora, String idTexto) {
		/* Funcao que retorna um objeto do tipo seguro de acordo com o id */
		int id = Integer.parseInt(idTexto);

		for (int i = 0; i < seguradora.getListaSeguros().size(); i++) {
			if (seguradora.getListaSeguros().get(i).getId() == id) {
				return seguradora.getListaSeguros().get(i);
			}
		}
		return null;
	}

	public void listarSinistrosSeguradora() {
		/* Funcao que lista todos os sinistros da seguradora */
		int aux = 0;
		if (listaSeguros.isEmpty()) {
			System.out.println("Nao existem seguros cadastrados nesta seguradora!");
		}

		for (int i = 0; i < listaSeguros.size(); i++) {
			for (int j = 0; j < listaSeguros.get(i).getListaSinistros().size(); j++) {

				if (listaSeguros.get(i).getListaSinistros().get(j) != null) {
					System.out.println(listaSeguros.get(i).getListaSinistros().get(j).toString());
					aux = 1;
				}
			}
		}

		if (aux == 0) {
			System.out.println("Nao existem sinistros associados a essa seguradora!");
		}
	}

	public void listarCondutoresSeguradora() {
		/* Funcao que lista todos os condutores que estao associados a seguradora */
		int aux = 0;
		if (listaSeguros.isEmpty()) {
			System.out.println("Nao existem seguros cadastrados nesta seguradora!");
		}

		for (int i = 0; i < listaSeguros.size(); i++) {
			for (int j = 0; j < listaSeguros.get(i).getListacondutores().size(); j++) {

				if (listaSeguros.get(i).getListacondutores().get(j) != null) {
					System.out.println("Seguro ID:" + listaSeguros.get(i).getId());
					System.out.println(listaSeguros.get(i).getListacondutores().get(j).toString());
					aux = 1;
				}
			}
		}

		if (aux == 0) {
			System.out.println("Nao existem condutores associados a essa seguradora!");
		}
	}

	public void listarSegurosSeguradora() {
		/* Funcao que lista todos os seguros da seguradora */
		if (listaSeguros.isEmpty()) {
			System.out.println("Nao existem seguros cadastrados nesta seguradora!");
		}

		for (int i = 0; i < listaSeguros.size(); i++) {
			System.out.println(listaSeguros.get(i).toString());

		}
	}

	public boolean listarClientes() {
		/* Funcao que retorna todos os clientes cadastrados na seguradora */

		if (listaClientes.isEmpty()) {
			System.out.println("Seguradora nao possui nenhum cliente cadastrado!");
			return false;
		}

		System.out.println("------------------------------------------------");
		System.out.println("Clientes desta seguradora\n");
		for (int i = 0; i < listaClientes.size(); i++) {

			System.out.println(listaClientes.get(i).toString());

		}
		System.out.println("------------------------------------------------");
		return true;
	}

	public boolean listaFrotaSeguradora() {
		/* Funcao que lista todas as frotas associadas a seguradora */

		System.out.println("Frotas da seguradora\n");
		for (int i = 0; i < listaClientes.size(); i++) {

			if (listaClientes.get(i).getListaFrota() != null) {

				for (int j = 0; j < listaClientes.get(i).getListaFrota().size(); j++) {

					if (listaClientes.get(i).getListaFrota().get(j) != null) {
						System.out.println("Cliente nome:" + listaClientes.get(i).getNome());
						System.out.println("Cliente CNPJ:" + listaClientes.get(i).getId());
						System.out.println(listaClientes.get(i).getListaFrota().get(j).toString() + "\n");
					}
				}
			}
		}

		return true;
	}

	public boolean listarVeiculos() {
		/* Funcao que lista todos os veiculos associados a seguradora */

		if (listaClientes.isEmpty()) {
			System.out.println("Seguradora nao possui clientes cadastrados!");
			return false;
		}

		for (int i = 0; i < listaClientes.size(); i++) {
			listaClientes.get(i).listarVeiculos();
		}

		return true;
	}
	
	public static boolean listarCondutores(Seguradora seguradora, String idTexto) {
		/* Funcao que lista todos os condutores associados ao seguro */
		Scanner entrada = Entradas.entrada;

		int id, aux = 0;

		if (idTexto == "") {

			System.out.println("Digite o id do seguro que voce quer listar os condutores");
			idTexto = entrada.nextLine();

			if (!Validacao.verificaNumerosInteiros(idTexto)) {
				System.out.println("ID invalido!");
				return false;
			}
		}

		id = Integer.parseInt(idTexto);

		if (seguradora.getListaSeguros().isEmpty()) {
			System.out.println("Nao ha seguros cadastrados neste seguro!");
			return false;
		}

		for (int i = 0; i < seguradora.getListaSeguros().size(); i++) {

			if (seguradora.getListaSeguros().get(i).getId() == id) {
				aux = 1;

				if (seguradora.getListaSeguros().get(i).getListacondutores().isEmpty()) {
					System.out.println("Seguradora nao possui condutores cadastrados!");
					return false;
				}
				System.out.println("Condutores do seguro de id:" + seguradora.getListaSeguros().get(i).getId() + "\n");
				for (int j = 0; j < seguradora.getListaSeguros().get(i).getListacondutores().size(); j++) {
					System.out.println(seguradora.getListaSeguros().get(i).getListacondutores().get(j).toString());
					aux = 1;
				}
			}
		}

		if (aux == 0) {
			System.out.println("Seguro nao encontrado!");
			return false;
		}
		return true;
	}

	/// Getters e setters
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

	public String getCnpj() {
		return cnpj;
	}

	@Override
	public String toString() {
		return "Dados da seguradora\n" + "CNPJ:" + cnpj + "\n" + "Nome:" + nome + "\n" + "Telefone:" + telefone + "\n"
				+ "Endereco:" + endereco + "\n" + "Email:" + email + "\n";
	}

}