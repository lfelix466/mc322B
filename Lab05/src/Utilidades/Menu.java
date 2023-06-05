package Utilidades;

import Enumeradores.Cadastro_Cliente_PF_PJ_enum;
import Enumeradores.Cadastro_enum;
import Enumeradores.Excluir_enum;
import Enumeradores.Listar_enum;
import Enumeradores.MenuOperacoes;
import Enumeradores.Seguro_enum;

import java.util.ArrayList;
import java.util.Scanner;

import Classes_principais.Cliente;
import Classes_principais.Cliente_PF;
import Classes_principais.Cliente_PJ;
import Classes_principais.Seguradora;
import Classes_principais.Seguro;
import Classes_principais.Sinistro;

public class Menu {

	String cpfSeguradora, idCliente, placaVeiculo;
	Seguradora seguradoraAux;
	Cliente clienteAux;
	Seguro seguroAux;

	Scanner entrada = Entradas.entrada;

	// Construtor
	public Menu() {

	}
	
	private void IniciaValores(ArrayList<Seguradora> listaSeguradora) {
		/* Funcao que inicia os valores de seguradora e cliente no inicio do menu*/
		seguradoraAux = listaSeguradora.get(0);
		clienteAux = listaSeguradora.get(0).getListaClientes().get(0);
	}

	private void opcaoEscolhida(ArrayList<Seguradora> listaSeguradora) {
		/* Funcao que mostra a seguradora e o cliente escolhido */
		
		System.out.println("\nSEGURADORA: " + seguradoraAux.getNome());
		
		if (clienteAux != null) {
			
			System.out.println("CLIENTE nome:"+clienteAux.getNome());
			if(clienteAux.getClass().getSimpleName().equals("Cliente_PF")) {
				System.out.println("CLIENTE Tipo:PF");
			}else{
				System.out.println("CLIENTE Tipo:PJ");
			}
			
		} else {
			System.out.println("CLIENTE:Nao ha clientes nessa seguradora!");
		}
		System.out.println("------------------------------------------------");
		System.out.println("Digite a opcao desejada:");
	}
	
	private boolean verificaCliente(Cliente cliente) {
		/*Funcao que verifica se o cliente seleciona e nulo*/
		
		if(cliente == null) {
			System.out.println("Esta seguradora nao possui clientes cadastrados!");
			return false;
		}
		return true;
		
	}

	public void menu_principal(ArrayList<Seguradora> listaSeguradora) {
		/* Funcao que mostra as opcoes iniciais do menu */

		int opcao;
		IniciaValores(listaSeguradora);
		
		MenuOperacoes menu[] = MenuOperacoes.values();
		do {
			System.out.println("------------------------------------------------");
			System.out.println("MENU PRINCIPAL");
			for (int i = 0; i < menu.length; i++) {
				System.out.println(i + " - " + menu[i]);
			}
			opcaoEscolhida(listaSeguradora);

			try {
				opcao = Integer.parseInt(entrada.nextLine());
				switch (menu[opcao]) {
				case CADASTROS:
					menu_Cadastro(listaSeguradora);
					break;

				case LISTAR:
					menu_Listar(listaSeguradora);
					break;

				case EXCLUIR:
					menu_Excluir(listaSeguradora);
					break;

				case GERAR_SINISTRO:
					seguroAux = Seguro.encontraSeguro(seguradoraAux, 0);
					
					if(seguroAux == null) {
						System.out.println("Seguro nao encontrado!");
						break;
					}
					
					seguroAux.gerarSinistro(seguroAux, "", "", null, "");
					break;
					
				case SEGUROS:
					menu_seguros(listaSeguradora);
					break;

				case TRANSFERIR_SEGURO:
					seguradoraAux.transferirSeguros(seguradoraAux, clienteAux);
					break;
				case CALCULAR_RECEITA:
					seguradoraAux.calcularReceita();
					break;
					
				case ATUALIZAR_FROTA:
					if(clienteAux.atualizarFrota("", "", "", "", 0, "")) {
						seguradoraAux.atualizaSeguros();
					}
					break;
				
				case TROCAR_SEGURADORA:
					Seguradora.listarSeguradoras(listaSeguradora);
					Seguradora seguradoraAux2;
					
					System.out.println("Qual o CPNJ da seguradora que voce quer encontrar?");
					cpfSeguradora = entrada.nextLine();

					seguradoraAux2 = Seguradora.encontraSeguradora(listaSeguradora, cpfSeguradora);

					if(seguradoraAux2 == null){
						System.out.println("Seguradora nao encontrada!");
					}else{
						seguradoraAux = seguradoraAux2;
						System.out.println("\nSeguradora trocada com sucesso!");
						if (seguradoraAux.getListaClientes().isEmpty()) {
							clienteAux = null;
						} else {
							System.out.println("\nCliente trocado com sucesso!");
							clienteAux = seguradoraAux.getListaClientes().get(0);
						}
					}
					break;

				case TROCAR_CLIENTE:
					
					if(verificaCliente(clienteAux)) {
						
						if(seguradoraAux.listarClientes()) {
							
							Cliente clienteAux2;
							
							System.out.println("Qual o CPF/CNPJ do cliente que voce quer encontrar?");
							idCliente = entrada.nextLine();

							clienteAux2 = Seguradora.encontraCliente(seguradoraAux, idCliente);

							if (clienteAux2 == null) {
								System.out.println("\nCliente nao encontrado!");
							} else {
								clienteAux = clienteAux2;
								System.out.println("Cliente trocado com sucesso!");
					}}}

					break;

				case SAIR:
					System.out.println("Saiu");
					break;
				default:
					System.out.println("Opcao invalida!");
					break;
				}

			} catch (Exception e) {
				opcao = 0;
				System.out.println("Erro, valor digitado incorreto!");
			}

		} while (menu[opcao] != MenuOperacoes.SAIR);
	}

	private void menu_Cadastro(ArrayList<Seguradora> listaSeguradora) {
		/* Funcao que mostra as opcoes de cadastro */
		
		int opcao;
		Cadastro_enum menu[] = Cadastro_enum.values();

		do {
			System.out.println("------------------------------------------------");
			System.out.println("MENU CADASTRO");
			for (int i = 0; i < menu.length; i++) {
				System.out.println(i + " - " + menu[i]);
			}
			opcaoEscolhida(listaSeguradora);

			try {
				opcao = Integer.parseInt(entrada.nextLine());
				switch (menu[opcao]) {
				case CADASTRAR_CLIENTE_PF_PJ:
					menu_cliente_PF_PJ(listaSeguradora);
					break;

				case CADASTRAR_VEICULO:
					clienteAux.cadastrarVeiculo("", "", "", 0);
					seguradoraAux.atualizaSeguros();
					break;

				case CADASTRAR_SEGURADORA:
					Seguradora.cadastrarSeguradora(listaSeguradora, "", "", "", "", "");
					break;

				case CADASTRAR_SEGURO:
					seguradoraAux.gerarSeguro(0, seguradoraAux, null, null, null, null, "", "");
					break;
					
				case VOLTAR:
					break;
				}

			} catch (Exception e) {
				opcao = 0;
				System.out.println("Erro, valor digitado incorreto!");
			}

		} while (menu[opcao] != Cadastro_enum.VOLTAR);
	}

	private void menu_Listar(ArrayList<Seguradora> listaSeguradora) {
		/* Funcao que mostra as opcoes de listar */
		
		int opcao;
		Listar_enum menu[] = Listar_enum.values();

		do {
			System.out.println("------------------------------------------------");
			System.out.println("MENU LISTAR");
			for (int i = 0; i < menu.length; i++) {
				System.out.println(i + " - " + menu[i]);
			}
			opcaoEscolhida(listaSeguradora);

			try {
				opcao = Integer.parseInt(entrada.nextLine());
				switch (menu[opcao]) {
				case LISTAR_CLIENTES_POR_SEGURO:
					
					if(verificaCliente(clienteAux)) {
						System.out.println("0 - Cliente_PF");
						System.out.println("1 - Cliente_PJ");
						System.out.println("Qual o tipo de cliente que voce deseja listar?");
						String tipoCliente = entrada.nextLine();

						if (tipoCliente.equals("0")) {
							tipoCliente = "Cliente_PF";
						} else if (tipoCliente.equals("1")) {
							tipoCliente = "Cliente_PJ";
						}
						seguradoraAux.listarClientes(tipoCliente);
					}
					break;

				case LISTAR_SINISTROS_POR_CLIENTE:
					
					if(verificaCliente(clienteAux)) {
						clienteAux.listarSinistrosCliente(seguradoraAux, clienteAux);
					}
					break;

				case LISTAR_SINISTROS_POR_SEGURADORA:
					seguradoraAux.listarSinistrosSeguradora();
					break;
					
				case LISTAR_SEGUROS_POR_CLIENTE:
					
					if(verificaCliente(clienteAux)) {
						clienteAux.listarSegurosCliente(seguradoraAux, clienteAux);
					}
					break;
					
				case LISTAR_SEGUROS_POR_SEGURADORA:
					seguradoraAux.listarSegurosSeguradora();
					break;
					

				case LISTAR_VEICULOS_POR_CLIENTE:
					
					if(verificaCliente(clienteAux)) {
						clienteAux.listarVeiculos();
					}
					break;

				case LISTAR_VEICULOS_POR_SEGURADORA:
					seguradoraAux.listarVeiculos();
					break;

				case LISTAR_SEGURADORAS:
					Seguradora.listarSeguradoras(listaSeguradora);
					break;
					
				case LISTAR_CONDUTORES_POR_SEGURO:
					Seguradora.listarCondutores(seguradoraAux, "");
					break;
					
				case LISTAR_CONDUTORES_POR_SEGURADORA:
					seguradoraAux.listarCondutoresSeguradora();
					break;
					
				case LISTAR_CONDUTORES_POR_CLIENTE:
					
					if(verificaCliente(clienteAux)) {
						clienteAux.listarCondutoresCliente(seguradoraAux, clienteAux);
					}
					break;
					
				case LISTAR_FROTAS_POR_CLIENTE:
					clienteAux.listarFrota();
					break;
					
				case LISTAR_FROTAS_POR_SEGURADORA:
					seguradoraAux.listaFrotaSeguradora();
					break;

				case VOLTAR:
					break;
				}

			} catch (Exception e) {
				opcao = 0;
				System.out.println("Erro, valor digitado incorreto!");
			}

		} while (menu[opcao] != Listar_enum.VOLTAR);
	}

	private void menu_Excluir(ArrayList<Seguradora> listaSeguradora) {
		/* Fucao que mostra as opcoes de exlusao */
		
		int opcao;
		Excluir_enum menu[] = Excluir_enum.values();

		do {
			System.out.println("------------------------------------------------");
			System.out.println("MENU EXCLUIR");
			for (int i = 0; i < menu.length; i++) {
				System.out.println(i + " - " + menu[i]);
			}
			opcaoEscolhida(listaSeguradora);

			try {
				opcao = Integer.parseInt(entrada.nextLine());
				switch (menu[opcao]) {
				case EXCLUIR_CLIENTE:
					if(verificaCliente(clienteAux)) {
					
						seguradoraAux.cancelaSegurosCliente(clienteAux);
						seguradoraAux.removerCliente(clienteAux); 
						
						if (seguradoraAux.getListaClientes().isEmpty()) {
							System.out.println("Todos os clientes dessa seguradora foram apagados!");
							clienteAux = null;
						} else {
							clienteAux = seguradoraAux.getListaClientes().get(0);
						}
					}
					break;

				case EXCLUIR_SINISTRO:
					if(verificaCliente(clienteAux)) {
						if(Sinistro.excluirSinistro(seguradoraAux, "")) {
							seguradoraAux.atualizaSeguros();
					}}
					break;

				case EXCLUIR_VEICULO:
					if(verificaCliente(clienteAux)) {
						if(clienteAux.removerVeiculo("")) {
							seguradoraAux.atualizaSeguros();
					}}
					break;
					
				case VOLTAR:
					break;
				}

			} catch (Exception e) {
				opcao = 0;
				System.out.println("Erro, valor digitado incorreto!");
			}

		} while (menu[opcao] != Excluir_enum.VOLTAR);
	}

	public void menu_cliente_PF_PJ(ArrayList<Seguradora> listaSeguradora) {
		/* Menu que mostra as opcoes de cliente */
		
		int opcao;
		Cadastro_Cliente_PF_PJ_enum menu[] = Cadastro_Cliente_PF_PJ_enum.values();

		do {
			System.out.println("------------------------------------------------");
			System.out.println("MENU CLIENTE PF/PJ");
			for (int i = 0; i < menu.length; i++) {
				System.out.println(i + " - " + menu[i]);
			}
			opcaoEscolhida(listaSeguradora);

			try {

				opcao = Integer.parseInt(entrada.nextLine());

				switch (menu[opcao]) {
				case CLIENTE_PF:
					if(Cliente_PF.CadastrarCliente(seguradoraAux, "", "", "", "", "", "", "", "")) {
						if(clienteAux == null) {
							clienteAux = seguradoraAux.getListaClientes().get(0);
						}
					}
					break;
					
				case CLIENTE_PJ:
					if(Cliente_PJ.CadastrarCliente(seguradoraAux, "", "", "", "", "", "", "")){
						if(clienteAux == null) {
							clienteAux = seguradoraAux.getListaClientes().get(0);
						}
					}
					break;
					
				case VOLTAR:
					break;
				}

			} catch (Exception e) {
				opcao = 0;
				System.out.println("Erro, valor digitado incorreto!");
			}

		} while (menu[opcao] != Cadastro_Cliente_PF_PJ_enum.VOLTAR);
	}
	
	public void menu_seguros(ArrayList<Seguradora> listaSeguradora) {
		/* Menu que mostra todas as opcoes associadas ao seguro*/
		
		int opcao;
		Seguro_enum menu[] = Seguro_enum.values();

		do {
			System.out.println("------------------------------------------------");
			System.out.println("MENU SEGUROS");
			for (int i = 0; i < menu.length; i++) {
				System.out.println(i + " - " + menu[i]);
			}

			try {

				opcao = Integer.parseInt(entrada.nextLine());

				switch (menu[opcao]) {
				case GERAR_SEGURO:
					if(seguradoraAux.gerarSeguro(0, seguradoraAux, null, null, null, null, "", "")) {
						seguradoraAux.atualizaSeguros();
					}
					break;
					
				case AUTORIZAR_CONDUTOR:
					if(Seguro.autorizarCondutorEntrada(seguradoraAux, null, null)) {
					}
					break;
					
				case DESEAUTORIZAR_CONDUTOR:
					if(Seguro.desautorizarCondutorEntrada(seguradoraAux, "")) {
					}
					break;
				
				case EXCLUIR_SEGURO:
					seguradoraAux.cancelaSeguro("");
					break;
					
				case VOLTAR:
					break;
				}

			} catch (Exception e) {
				opcao = 0;
				System.out.println("Erro, valor digitado incorreto!");
			}

		} while (menu[opcao] != Seguro_enum.VOLTAR);
	}
}