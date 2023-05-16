package Classes_principais;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Seguradora {
	
	private String nome ;
	private String telefone ;
	private String email ;
	private String endereco ;
	private ArrayList<Sinistro> listaSinistro = new ArrayList<Sinistro>();
	private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	
	Scanner entrada = new Scanner(System.in);
	
	//Construtor
	public Seguradora(String nome, String telefone, String email, String endereco, ArrayList<Sinistro> listaSinistro,
			ArrayList<Cliente> listaClientes) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaSinistro.add(null);
		this.listaClientes.add(null);
	}
	
	public Seguradora() {
		
	}

	public boolean cadastrarCliente(Cliente cliente){
		/*Funcao de adicionar os clientes na lista*/
		
			listaClientes.add(cliente);

			return true;
	}
	
	public List<Cliente> listarClientes(String tipoCliente) {
		/*Funcao que vizualiza o cliente pelo nome*/
		
		if(verificaPosicaoCliente(listaClientes)) {
			System.out.println("Clientes:");
			
			for(int i = 0; i<listaClientes.size(); i++) {
				if(listaClientes.get(i).getClass().getSimpleName().equals(tipoCliente)) {
					System.out.println("ID: "+i+" - Nome: "+listaClientes.get(i).getNome());
				}}}
		return listaClientes;
	}
	
	public boolean visualizarCliente(String cliente) {
		/*Funcao que vizualiza o cliente pelo nome*/
		
		if(verificaPosicaoCliente(listaClientes)) {
			for(int i = 0; i < listaClientes.size(); i++) {
				if(listaClientes.get(i).getNome().equals(cliente)) {
					System.out.println(listaClientes.get(i));
					return true;
				}}}
		return false;
	}
	
	public boolean removerCliente(String cliente) {
		/*Funcao que remove clientes da lista*/
		
		if(verificaPosicaoCliente(listaClientes)) {
			for(int i = 0; i<listaClientes.size(); i++) {
				if(listaClientes.get(i).getNome().equals(cliente)) {				
					listaClientes.remove(i);
					return true;
			}}}
		return false;
	}
	
	public boolean gerarSinitro(String dataSinistro, String endereco, Seguradora seguradora, Veiculo veiculo,
			Cliente cliente) {
		/*Funcao que gera sinistros e os adiciona na lista*/
		
		Sinistro sinistro = new Sinistro(0, dataSinistro, endereco, seguradora, veiculo, cliente);
		
		listaSinistro.add(sinistro);
		
		return true;
	}
	
	public List<Sinistro> listarSinistro() {
		/*Funcao que lista os sinistros*/
		
		if(verificaPosicaoSinistro(listaSinistro)) {
			System.out.println("Sinistros:");
			
			for(int i = 0; i<listaSinistro.size(); i++) {
				System.out.println("ID:"+listaSinistro.get(i).getId()+" - Data:"+listaSinistro.get(i).getData());
		}}
		
		return listaSinistro;
		
	}
	
	public boolean visualizarSinistro(String cliente) {
		
		if(verificaPosicaoSinistro(listaSinistro)) {
		
			for(int i = 0; i < listaSinistro.size(); i++) {
				
				//listaSinistro.get(i);
				
				if(listaSinistro.get(i).getCliente().getNome().equals(cliente)) {
					
					System.out.println(listaSinistro.get(i));
					return true;
					
				}}}
		
		return false;
	}
	
	private boolean verificaPosicaoSinistro(List<Sinistro> lista) {
		/*Funcao que verifica se a lista de sinistros esta vazia*/
		if(lista.isEmpty()) {
			System.out.println("Nenhum sinistro encontrado!");
			return false;
		}
		return true;
	}
	
	private boolean verificaPosicaoCliente(List<Cliente> lista) {
		/*Funcao que verifica se a lista de clientes esta vazia*/
		if(lista.isEmpty()) {
			System.out.println("Nenhum cliente encontrado!");
			return false;
		}
		return true;
	}
	
	//getters e setters
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
