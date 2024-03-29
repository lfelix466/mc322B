package Utilidades;

import Enumeradores.Cadastro_Cliente_PF_PJ_enum;
import Enumeradores.Cadastro_enum;
import Enumeradores.Excluir_enum;
import Enumeradores.Listar_enum;
import Enumeradores.MenuOperacoes;

import java.util.ArrayList;
import java.util.Scanner;
import Classes_principais.Cliente_PF;
import Classes_principais.Cliente_PJ;
import Classes_principais.Seguradora;
import Classes_principais.Sinistro;

public class Menu {

	String nomeSeguradora, nomeCliente, placaVeiculo, resultado;
	int opcao, seguradoraEscolhida = 0, clienteEscolhido = 0;

	Scanner entrada = Entradas.entrada;

	// Construtor
	public Menu() {

	}

	private void opcaoEscolhida(ArrayList<Seguradora> listaSeguradora) {
		/* Funcao que mostra a seguradora e o cliente escolhido */
		
		System.out.println("\nSEGURADORA: " + listaSeguradora.get(seguradoraEscolhida).getNome());
		if (clienteEscolhido != -1) {
			System.out.println("CLIENTE: "
					+ listaSeguradora.get(seguradoraEscolhida).getListaClientes().get(clienteEscolhido).getNome());
		} else {
			System.out.println("CLIENTE: Nao ha clientes nessa seguradora!");
		}
		System.out.println("\nDigite a opcao desejada:");
	}

	public void menu_principal(ArrayList<Seguradora> listaSeguradora) {
		/* Funcao que mostra as opcoes iniciais do menu */

		MenuOperacoes menu[] = MenuOperacoes.values();
		do {

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
					Sinistro.gerarSinistro(listaSeguradora, "", "", seguradoraEscolhida, "", clienteEscolhido);
					break;

				case TRANSFERIR_SEGURO:
					Seguradora.transferirSeguro(listaSeguradora, seguradoraEscolhida, clienteEscolhido);
					break;
				case CALCULAR_RECEITA:
					listaSeguradora.get(seguradoraEscolhida).calcularReceita();
					break;
				case TROCAR_SEGURADORA:

					System.out.println("Qual o nome da seguradora que voce quer encontrar?");
					nomeSeguradora = entrada.nextLine();

					resultado = Seguradora.encontraSeguradora(listaSeguradora, nomeSeguradora);

					if (resultado == "Falso") {
						System.out.println("znSeguradora nao encontrada!");
					} else {
						seguradoraEscolhida = Integer.parseInt(resultado);
						System.out.println("\nSeguradora trocada com sucesso!");
						if (listaSeguradora.get(seguradoraEscolhida).getListaClientes().isEmpty()) {
							clienteEscolhido = -1;
						} else {
							System.out.println("\nCliente trocado com sucesso!");
							clienteEscolhido = 0;
						}
					}
					break;

				case TROCAR_CLIENTE:

					if (clienteEscolhido == -1) {
						System.out.println("Essa seguradora nao tem clientes!");
					} else {
						System.out.println("Qual o nome do cliente que voce quer encontrar?");
						nomeCliente = entrada.nextLine();

						resultado = Seguradora.encontraCliente(listaSeguradora, seguradoraEscolhida, nomeCliente);

						if (resultado == "Falso") {
							System.out.println("\nCliente nao encontrado!");
						} else {
							clienteEscolhido = Integer.parseInt(resultado);
							System.out.println("znCliente trocado com sucesso!");
						}
					}

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
		Cadastro_enum menu[] = Cadastro_enum.values();

		do {
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
					listaSeguradora.get(seguradoraEscolhida).getListaClientes().get(clienteEscolhido)
							.adicionarVeiculo("", "", "", 0);
					listaSeguradora.get(seguradoraEscolhida).calcularPrecoSeguroCliente(
							(listaSeguradora.get(seguradoraEscolhida).getListaClientes().get(clienteEscolhido)));
					break;

				case CADASTRAR_SEGURADORA:
					Seguradora.adicionarSeguradora(listaSeguradora);
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
		Listar_enum menu[] = Listar_enum.values();

		do {
			System.out.println("MENU LISTAR");
			for (int i = 0; i < menu.length; i++) {
				System.out.println(i + " - " + menu[i]);
			}
			opcaoEscolhida(listaSeguradora);

			try {
				opcao = Integer.parseInt(entrada.nextLine());
				switch (menu[opcao]) {
				case LISTAR_CLIENTES_POR_SEGURO:
					System.out.println("0 - Cliente_PF");
					System.out.println("1 - Cliente_PJ");
					System.out.println("Qual o tipo de cliente que voce deseja listar?");
					String tipoCliente = entrada.nextLine();

					if (tipoCliente.equals("0")) {
						tipoCliente = "Cliente_PF";
					} else if (tipoCliente.equals("1")) {
						tipoCliente = "Cliente_PF";
					}
					listaSeguradora.get(seguradoraEscolhida).listarClientes(tipoCliente);
					break;

				case LISTAR_SINISTROS_POR_CLIENTE:
					if (clienteEscolhido == -1) {
						System.out.println("Essa seguradora não possui clientes cadastrados!");
					} else {
						listaSeguradora.get(seguradoraEscolhida).listarSinistrosCliente(
								listaSeguradora.get(seguradoraEscolhida).getListaClientes().get(clienteEscolhido));
					}
					break;

				case LISTAR_SINISTROS_POR_SEGURADORA:
					listaSeguradora.get(seguradoraEscolhida).listarSinistrosSeguradora();
					break;

				case LISTAR_VEICULOS_POR_CLIENTE:
					listaSeguradora.get(seguradoraEscolhida).getListaClientes().get(clienteEscolhido).listarVeiculo(0);
					break;

				case LISTAR_VEICULOS_POR_SEGURADORA:
					listaSeguradora.get(seguradoraEscolhida).listarVeiculos();
					break;

				case LISTAR_SEGURADORAS:
					Seguradora.listarSeguradoras(listaSeguradora);
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
		Excluir_enum menu[] = Excluir_enum.values();

		do {

			System.out.println("MENU EXCLUIR");
			for (int i = 0; i < menu.length; i++) {
				System.out.println(i + " - " + menu[i]);
			}
			opcaoEscolhida(listaSeguradora);

			try {
				opcao = Integer.parseInt(entrada.nextLine());
				switch (menu[opcao]) {
				case EXCLUIR_CLIENTE:

					if (clienteEscolhido == -1) {
						System.out.println("Essa seguradora nao possui clientes cadastrados!");
					} else {
						listaSeguradora.get(seguradoraEscolhida).removerCliente(listaSeguradora.get(seguradoraEscolhida)
								.getListaClientes().get(clienteEscolhido).getNome());

						if (listaSeguradora.get(seguradoraEscolhida).getListaClientes().isEmpty()) {
							System.out.println("Todos os clientes dessa seguradora foram apagados!");
							clienteEscolhido = -1;
						} else {
							clienteEscolhido = 0;
						}
					}
					break;

				case EXCLUIR_SINISTRO:

					if (Sinistro.listarSinistros(listaSeguradora)) {
						Sinistro.removerSinistro(listaSeguradora);
					}
					break;

				case EXCLUIR_VEICULO:

					if (listaSeguradora.get(seguradoraEscolhida).getListaClientes().get(clienteEscolhido)
							.listarVeiculo(0)) {
						listaSeguradora.get(seguradoraEscolhida).getListaClientes().get(clienteEscolhido)
								.removerVeiculo();
						listaSeguradora.get(seguradoraEscolhida).getListaClientes().get(clienteEscolhido);
						listaSeguradora.get(seguradoraEscolhida).calcularPrecoSeguroCliente(
								(listaSeguradora.get(seguradoraEscolhida).getListaClientes().get(clienteEscolhido)));
					}
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
		Cadastro_Cliente_PF_PJ_enum menu[] = Cadastro_Cliente_PF_PJ_enum.values();

		do {

			System.out.println("MENU CLIENTE PF/PJ");
			for (int i = 0; i < menu.length; i++) {
				System.out.println(i + " - " + menu[i]);
			}
			opcaoEscolhida(listaSeguradora);

			try {

				opcao = Integer.parseInt(entrada.nextLine());

				switch (menu[opcao]) {
				case CLIENTE_PF:
					if (Cliente_PF.CadastrarCliente(listaSeguradora.get(seguradoraEscolhida), "", "", "", "", "", "",
							"", "")) {
						if (clienteEscolhido == -1) {
							clienteEscolhido = 0;
					}}

					break;
				case CLIENTE_PJ:
					if (Cliente_PJ.CadastrarCliente(listaSeguradora.get(seguradoraEscolhida), "", "", "", 0, "", "", "",
							"", "")) {
						if (clienteEscolhido == -1) {
							clienteEscolhido = 0;
					}}
					
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
}