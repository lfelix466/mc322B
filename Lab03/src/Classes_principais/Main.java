package Classes_principais;

import java.util.Scanner;
import Utillidades.MenuInicial;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Inicializacao das variaveis
		int opcao;
		String nome;
		
		//Instanciando os objetos do lab
		MenuInicial menu = new MenuInicial();
		Scanner entrada = new Scanner(System.in);
		Seguradora seguradora = new Seguradora();
		Cliente_PF cliente_pf = new Cliente_PF("0");
		Cliente_PJ cliente_pj = new Cliente_PJ("0");
		Veiculo veiculo = new Veiculo();
		Cliente cliente = new Cliente();
		Sinistro sinistro = new Sinistro(0, "teste", "teste", seguradora, veiculo, cliente);
		
		//Chamando os metodos valida cpf e cnpj
		System.out.println("Resultados da verificacao de cnpj e cpf");
		System.out.println(cliente_pf.validarCPF("507.244.420-14") == true ? "Cpf valido" : "Cpf invalido");
		System.out.println(cliente_pf.validarCPF("546546") == true ? "Cpf valido" : "Cpf invalido");
		System.out.println(cliente_pj.validarCNPJ("74.542.431/0001-16") == true ? "Cnpj valido" : "Cnpj invalido");
		System.out.println(cliente_pj.validarCNPJ("2") == true ? "Cnpj valido" : "Cnpj invalido");
		
		//Cadastro de clientes
		cliente_pj.CadastrarCliente(seguradora, "74.542.431/0001-16", "20-10-2012", "21-10-2012", 1, "cliente1", "rua1",
				"medio", "masculino", "baixa");
		
		cliente_pj.CadastrarCliente(seguradora, "97.322.958/0001-30", "23-10-2012", "21-11-2012", 1, "cliente2", "rua2",
				"medio", "feminino", "baixa");
		
		cliente_pf.CadastrarCliente(seguradora, "507.244.420-14", "10-10-2013", "15-10-2018", 1, "cliente3", "rua3",
				"superior", "feminino", "alta");
		
		cliente_pf.CadastrarCliente(seguradora, "385.344.140-84", "11-10-2015", "18-11-2022", 1, "cliente4", "rua4",
				"medio", "masculina", "media");
		
		//Clientes que serao removidos
		cliente_pf.CadastrarCliente(seguradora, "077.181.680-42", "11-10-2015", "18-11-2022", 1, "cliente5", "rua5",
				"medio", "masculina", "media");
		
		cliente_pj.CadastrarCliente(seguradora, "89.841.142/0001-95", "11-10-2015", "18-11-2022", 1, "cliente6", "rua6",
				"medio", "feminina", "media");

		//Remocao de clientes
		System.out.println("Resultados da remocao de clientes");
		System.out.println(seguradora.removerCliente("cliente5") == true ? "Cliente removido" : "cliente nao encontrado");
		System.out.println(seguradora.removerCliente("cliente6") == true ? "Cliente removido" : "cliente nao encontrado");
		
		//Gerar sinistro
		seguradora.gerarSinitro("20-10-2023", "Rua teste", seguradora, seguradora.getListaClientes().get(0).getListaVeiculos().get(0),
				seguradora.getListaClientes().get(0));
		
		//Chamando o metodo listar clientes
		seguradora.listarClientes("Cliente_PF");
		seguradora.listarClientes("Cliente_PJ");
		
		//Chamando o metodo listar sinistros
		seguradora.listarSinistro();
		
		//Chamando o metodo visualizar sinistro
		seguradora.visualizarSinistro("cliente1");
		
		//Chamando os metodos toString de todas as classes
		System.out.println(cliente_pf);
		System.out.println(cliente_pj);
		System.out.println(cliente);
		System.out.println(seguradora);
		System.out.println(sinistro);
		System.out.println(veiculo);
		
		//Chamada de entrada para fazer verificacoes
		do {
			
			System.out.println(menu.toString());
			opcao = Integer.parseInt(entrada.nextLine());
			
			if(opcao == 1) {
				
				//////////////////////
				
			}else if(opcao == 2) {
				
				System.out.println("Qual tipo de cliente deseja cadastrar?\n"
						+ "1 - PF\n"
						+ "2 - PJ");
				
				opcao = Integer.parseInt(entrada.nextLine());
				if(opcao == 1) {
			
					///////////////////
					
				}else if(opcao == 2){
					
					////////////////
					
				}else {
					
					System.out.println("Opcao nao existente");
					
				}
				
			}else if(opcao == 3) {
				
				seguradora.listarSinistro();
				
			}else if(opcao == 4) {
				
				System.out.println("Qual tipo de cliente deseja verificar?\n"
						+ "1 - PF\n"
						+ "2 - PJ");
				
				opcao = Integer.parseInt(entrada.nextLine());
				
				if(opcao == 1) {
					seguradora.listarClientes("Cliente_PF");
				}else if(opcao == 2) {
					seguradora.listarClientes("Cliente_PJ");
				}else{
					System.out.println("Opcao nao encontrada");
				}
				
				
			}else if(opcao == 5) {
				
				System.out.println("Qual o nome do cliente que deseja ver os dados?");
				
				if(!seguradora.visualizarCliente(entrada.nextLine())) {
					System.out.println("Cliente nao encontrado!");
				}
				
				
			}else if(opcao == 6) {
				System.out.println("Qual o nome do cliente que deseja ver o sinistro?");
				nome = entrada.nextLine();
				
				if(!seguradora.visualizarSinistro(nome)) {
					System.out.println("Sinistro nao encontrado!");
				}
				
			}else if(opcao == 7) {
				System.out.println("Qual o nome do cliente que deseja remover?");
				nome = entrada.nextLine();
				
				if(seguradora.removerCliente(nome)) {
					System.out.println("Cliente removido com sucesso!");
				}else {
					System.out.println("Cliente nao encontrado!");
				}}
			
		}while(opcao != 8);
		
		entrada.close();
		System.out.println("Saiu");
	}
}
