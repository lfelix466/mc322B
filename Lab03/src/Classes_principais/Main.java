package Classes_principais;

import java.util.Scanner;

import Utillidades.MenuInicial;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Inicializacao dos contrutores
		
		int opcao;
		
		MenuInicial menu = new MenuInicial();
		Scanner entrada = new Scanner(System.in);
		
		Seguradora seguradora = new Seguradora(null, null, null, null, null, null);
		Cliente cliente = new Cliente(null, null, null, null, null, null, null);
		
		/*
		System.out.println("Digite o nome da seguradora");
		seguradora.setNome(entrada.nextLine());
		System.out.println("Digite o telefone da seguradora");
		seguradora.setTelefone(entrada.nextLine());
		System.out.println("Digite o Email da seguradora");
		seguradora.setEmail(entrada.nextLine());
		System.out.println("Digite o Endereco da seguradora");
		seguradora.setEndereco(entrada.nextLine());
		*/
		
		do {
			
			System.out.println(menu.toString());
			
			opcao = entrada.nextInt();
			System.out.println(opcao);
			
			if(opcao == 1) {
			
				if(seguradora.gerarSinitro()) {
					System.out.println("Sinistro gerado com sucesso!");
				}else {
					System.out.println("Falha ao gerar sinistro!");
				}
				
			}else if(opcao == 2) {
				
				if(seguradora.cadastrarCliente(cliente)) {
					System.out.println("Cliente cadastrado!");
				}else {
					System.out.println("Cliente nao cadastrado!");
				}
				
			}else if(opcao == 3) {
				
				seguradora.listarSinistro();
				
			}else if(opcao == 4) {
				
				seguradora.listarClientes("teste");
				
			}else if(opcao == 5) {
				entrada.nextLine();
				System.out.println("Qual o nome do cliente que deseja ver os dados?");
				
				if(!seguradora.visualizarCliente(entrada.nextLine())) {
					System.out.println("Cliente nao encontrado!");
				}
				
				
			}else if(opcao == 6) {
				System.out.println("Qual o nome do cliente que deseja ver o sinistro?");
				
				if(!seguradora.visualizarSinistro(entrada.nextLine())) {
					System.out.println("Sinistro nao encontrado!");
				}
				
			}else if(opcao == 7) {
				entrada.nextLine();
				System.out.println("Qual o nome do cliente que deseja remover?");
				
				if(seguradora.removerCliente(entrada.nextLine())) {
					System.out.println("Cliente removido com sucesso!");
				}else {
					System.out.println("Cliente nao encontrado!");
				}}
			
		}while(opcao != 8);
		
		System.out.println("Saiu");
	}
}
