package Classes_principais;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Scanner;
import java.util.List;
=======
import java.util.Date;
import java.util.Scanner;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63

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
<<<<<<< HEAD
	
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
=======

	//Funcoes
	public boolean cadastrarCliente(Cliente cliente){

			
		Date dataLicensa = null;
		int numeroCarros;
			
		System.out.println("Digite o nome do cliente:");
		cliente.setNome(entrada.nextLine());
		System.out.println("Digite o endereco do cliente:");
		cliente.setEndereco(entrada.nextLine());
			
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				
				System.out.println("Digite a data da licenca do cliente:");
				dataLicensa = formatter.parse(entrada.nextLine());
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			cliente.setDataLicensa(dataLicensa);
			
			System.out.println("Digite a educacao do cliente:");
			cliente.setEducacao(entrada.nextLine());
			System.out.println("Digite o genero do cliente:");
			cliente.setGenero(entrada.nextLine());
			System.out.println("Digite a classe economica do cliente:");
			cliente.setClasseEconomica(entrada.nextLine());
			
			System.out.println("Quantos carros o cliente tem?");
			numeroCarros = entrada.nextInt();
			
			for(int i = 0; i<numeroCarros; i++) {
				
				Veiculo veiculo = new Veiculo();
				
				if(i == 0) {
					cliente.getListaVeiculos().set(0, veiculo.cadastrarVeiculo());
				}else {
					cliente.getListaVeiculos().add(veiculo.cadastrarVeiculo());
				}
				
				System.out.println("Veiculo cadastrado!");
				
			}
			
			return true;
		
	}
	
	
	public List<Cliente> listarClientes(String tipoCliente) {
		
		if(verificaPosicaoCliente(listaClientes)) {
		
			System.out.println("Clientes:\n");
			
			for(int i = 0; i<listaClientes.size(); i++) {
				
				System.out.println(i+" - "+listaClientes.get(i).getNome()+"\n");
				
			}}
		
		return listaClientes;
		
		
	}
	
	public boolean visualizarCliente(String cliente) {
		
		if(verificaPosicaoCliente(listaClientes)) {
		
			for(int i = 0; i < listaClientes.size(); i++) {
				
				if(listaClientes.get(i).getNome().equals(cliente)) {
					
					System.out.println(listaClientes.get(i));
					return true;
					
				}}}
		
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
		return false;
	}
	
	public boolean removerCliente(String cliente) {
<<<<<<< HEAD
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
=======
		
		if(verificaPosicaoCliente(listaClientes)) {
		
			if(listaClientes.size() == 1 && listaClientes.get(0).getNome().equals(cliente)) {
				
				listaClientes.set(0, null);
				return true;
				
			}else {
			
				for(int i = 1; i<listaClientes.size(); i++) {
					
					if(listaClientes.get(i).getNome().equals(cliente)) {				
						listaClientes.remove(i);
						return true;
		}}}}
		
		return false;
	}
	
	public boolean gerarSinitro() {
		
		Sinistro sinistro = new Sinistro(0, null, null, null, null, null);
		
		System.out.println("Digite a data do sinistro:");
		sinistro.setData(entrada.nextLine());
		System.out.println("Digite a endereco do sinistro:");
		sinistro.setEndereco(entrada.nextLine());
		
		Seguradora seguradora = new Seguradora(null, null, null, null, null, null);
		Veiculo veiculo = new Veiculo(null, null, null, 0);
		Cliente cliente = new Cliente("teste", null, null, null, null, null, null);
		
		sinistro.setSeguradora(seguradora);
		sinistro.setVeiculo(veiculo);
		sinistro.setCliente(cliente);
		
		if(listaSinistro.get(0) == null) {
			listaSinistro.set(0, sinistro);
		}else{
			listaSinistro.add(sinistro);
		}
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
		
		return true;
	}
	
	public List<Sinistro> listarSinistro() {
<<<<<<< HEAD
		/*Funcao que lista os sinistros*/
		
		if(verificaPosicaoSinistro(listaSinistro)) {
			System.out.println("Sinistros:");
			
			for(int i = 0; i<listaSinistro.size(); i++) {
				System.out.println("ID:"+listaSinistro.get(i).getId()+" - Data:"+listaSinistro.get(i).getData());
=======
		
		if(verificaPosicaoSinistro(listaSinistro)) {
			System.out.println("Sinistros:\n");
			
			for(int i = 0; i<listaSinistro.size(); i++) {
				System.out.println(i+" - Data:"+listaSinistro.get(i).getData()+""
						+ " ID:"+listaSinistro.get(i).getId()+"\n");
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
		}}
		
		return listaSinistro;
		
	}
	
	public boolean visualizarSinistro(String cliente) {
		
		if(verificaPosicaoSinistro(listaSinistro)) {
		
			for(int i = 0; i < listaSinistro.size(); i++) {
				
<<<<<<< HEAD
				//listaSinistro.get(i);
				
=======
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
				if(listaSinistro.get(i).getCliente().getNome().equals(cliente)) {
					
					System.out.println(listaSinistro.get(i));
					return true;
					
				}}}
		
		return false;
	}
	
	private boolean verificaPosicaoSinistro(List<Sinistro> lista) {
<<<<<<< HEAD
		/*Funcao que verifica se a lista de sinistros esta vazia*/
		if(lista.isEmpty()) {
			System.out.println("Nenhum sinistro encontrado!");
			return false;
		}
=======
		
		if(lista.get(0) == null) {
			System.out.println("Nenhum sinistro encontrado!");
			return false;
		}
		
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
		return true;
	}
	
	private boolean verificaPosicaoCliente(List<Cliente> lista) {
<<<<<<< HEAD
		/*Funcao que verifica se a lista de clientes esta vazia*/
		if(lista.isEmpty()) {
			System.out.println("Nenhum cliente encontrado!");
			return false;
		}
		return true;
	}
	
	//getters e setters
=======
		
		if(lista.get(0) == null) {
			System.out.println("Nenhum cliente encontrado!");
			return false;
		}
		
		return true;
	}
	
	//gets e setters
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
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
