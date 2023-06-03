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
	ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	ArrayList<Seguro> listaSeguros = new ArrayList<Seguro>();
	Scanner entrada = Entradas.entrada;

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
		/* Funcao que adiciona os valores em cada seguradora */

		Scanner entrada = Entradas.entrada;
		
		if (cnpj == "") {

			System.out.println("Digite o cpf da seguradora");
			cnpj = entrada.nextLine();
			if (!Validacao.validarCPF(cnpj)) {
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

		Seguradora seguradora = new Seguradora(cnpj);
		seguradora.setNome(nome);
		seguradora.setEmail(email);
		seguradora.setEndereco(endereco);

		return true;
	}
	
	//////////////////////////////////////////////////////////////////////
	
	public boolean gerarSeguro(int tipo, Seguradora seguradora, Veiculo veiculo, Frota frota,
			Cliente_PF clientePF, Cliente_PJ clientePJ,  String dataFimTexto,
			String dataInicioTexto) {
		
		if(tipo == 0) {
			
			System.out.println("Qual tipo de seguro deseja cadastrar\n"
					+ "1 - seguro do tipo PF\n"
					+ "2 - seguro do tipo PJ");
			try{
				tipo = Integer.parseInt(entrada.nextLine());
			}catch (Exception e) {
				System.out.println("Tipo invalido!");
				return false;
			}
		}
		
		if (tipo == 1){
			
			if(Seguro_PF.cadastraSeguro(seguradora, veiculo, clientePF, dataFimTexto, dataInicioTexto)) {
				
				System.out.println("Seguro PF cadastrado com sucesso!");
				return true;
			}
			
		}else if (tipo == 2){
			
			if(Seguro_PJ.cadastraSeguro(seguradora, frota, clientePJ, dataFimTexto, dataInicioTexto)) {
				System.out.println("Seguro PJ cadastrado com sucesso!");
				return true;
			}
		}
		System.out.println("Opcao invalida!");
		return false;
	}
	
	
	
	public boolean cancelaSeguro(String idTexto) {
		
		int id;
		
		if(idTexto == "") {
			
			System.out.println("Digite o ID do seguro que deseja remover");
			idTexto = entrada.nextLine();
			
			if(!Validacao.verificaNumerosInteiros(idTexto)) {
				System.out.println("ID invalido!");
				return false;
			}
		}
		
		id = Integer.parseInt(idTexto);
		
		for(int i = 0; i < listaSeguros.size(); i++) {
			
			if(listaSeguros.get(i).getId() == id) {
				
				for(int j = 0; j < listaSeguros.get(i).getListacondutores().size(); j++) {
					
					for(int z = 0; z < listaSeguros.get(i).getListacondutores().get(j).
							getListaSinistros().size(); i++) {
						
						if(listaSeguros.get(i).getListacondutores().get(j).
								getListaSinistros().get(z).getSeguro().equals(listaSeguros.get(i))) {
							
							listaSeguros.get(i).getListacondutores().get(j).
							getListaSinistros().remove(z);
							
				}}}
				
				listaSeguros.remove(i);
				System.out.println("Seguro removido com sucesso!");
				return true;
			}
			
		}
		System.out.println("Seguro nao encontrado!");
		return false;
	}
	
	public boolean cadastrarCliente(Cliente cliente) {
		/* Funcao de adicionar os clientes na listaClientes da seguradora */		
		listaClientes.add(cliente);
		return true;
	}
	
	public boolean removerCliente(Cliente cliente) {
		/* Funcao que remove clientes da listaClientes */

		for (int i = 0; i < listaClientes.size(); i++) {
			if (listaClientes.get(i) == cliente) {
				listaClientes.remove(i);
				System.out.println("Cliente removido com sucesso!");
				return true;
		}}
		return true;
	}
	
	public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente){
	
		ArrayList<Seguro> lista = new ArrayList<Seguro>();
		
			for(int i = 0; i < listaSeguros.size(); i++) {		
				if(listaSeguros.get(i).getIdCliente() == cliente.getId()) {
					lista.add(listaSeguros.get(i));
		}}
		
		return lista;
	}
	
	public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente){
		
		ArrayList<Sinistro> lista = new ArrayList<Sinistro>();
		
			for(int i = 0; i < listaSeguros.size(); i++) {
				if(listaSeguros.get(i).getIdCliente() == cliente.getId()) {
					for(int j = 0; j<listaSeguros.get(i).getListaSinistros().size(); j++) {			
						lista.add(listaSeguros.get(i).getListaSinistros().get(j));
			}}}
		return lista;
	}
	
	public void calcularReceita() {
		/* Funcao que calcula a receita total da seguradora */
		Double receita = (double) 0;
		ArrayList<Seguro> lista = new ArrayList<Seguro>();
		
		for (int i = 0; i < listaSeguros.size(); i++) {
			receita += listaSeguros.get(i).getValorMensal();
		}
		
		for(int i = 0; i < listaClientes.size(); i++) {
			
			System.out.println("Balanco dos seguros do cliente: "+listaClientes.get(i).getNome());
			
			lista = getSegurosPorCliente(listaClientes.get(i));
			
			for(int j = 0; j < lista.size(); i++) {
					
				System.out.println(lista.get(j).toString());
					
		}}
		
		System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		System.out.println("Receita total: " + receita);
	}
	
	///////////////////////////////////////////////////////////////////////
	
	public static void listarSeguradoras(ArrayList<Seguradora> listaSeguradora) {
		/* Funcao que lista as seguradoras cadastradas */
		System.out.println("Seguradoras\n");
		for (int i = 0; i < listaSeguradora.size(); i++) {
			System.out.println(listaSeguradora.get(i).toString());
		}
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

	public boolean atualizaSeguros() {

		for(int i = 0; i < listaSeguros.size(); i++) {
			listaSeguros.get(i).setValorMensal(listaSeguros.get(i).calcularValor()); 	
		}	
		return true;
	}
	
	public static Seguradora encontraSeguradora(ArrayList<Seguradora> listaSeguradora, String nome) {
		
		for (int i = 0; i < listaSeguradora.size(); i++) {
			if (listaSeguradora.get(i).getNome().equals(nome)) {
				return listaSeguradora.get(i);
			}
		}
		return null;
	}

	public static Cliente encontraCliente(Seguradora seguradora, String id) {
		
		for(int i = 0; i<seguradora.getListaClientes().size(); i++) {	
			if(seguradora.getListaClientes().get(i).getId().equals(id)) {
				return seguradora.getListaClientes().get(i);
		}}
		
		return null;
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

	public String getCnpj() {
		return cnpj;
	}

	@Override
	public String toString() {
		return "Dados da seguradora\n"
				+ "CNPJ:"+cnpj+"\n"
					+"Nome:"+nome+"\n"
						+"Telefone:"+telefone+"\n"
							+"Endereco:"+endereco+"\n"
								+"Email:"+email+"\n"
									+"Lista de clientes:"+listaClientes+"\n"
										+ "Lista de seguros:"+listaSeguros;
	}
		
}