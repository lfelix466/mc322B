package Classes_principais;

import java.util.ArrayList;
import Utilidades.Menu;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Inicializacao das variaveis
		ArrayList<Seguradora> listaSeguradora = new ArrayList<Seguradora>();

		// Instanciando os objetos do lab
		Seguradora seguradora = new Seguradora();
		Cliente_PF cliente_pf = new Cliente_PF("0");
		Cliente_PJ cliente_pj = new Cliente_PJ("0");
		Veiculo veiculo1 = new Veiculo();
		Veiculo veiculo2 = new Veiculo();
		Sinistro sinistro1 = new Sinistro("teste1", "teste1", seguradora, veiculo1, cliente_pf);
		Sinistro sinistro2 = new Sinistro("teste2", "teste2", seguradora, veiculo2, cliente_pj);
		seguradora.cadastrarSeguradora("Seguradora", "111111", "segurado.com", "endereco");
		listaSeguradora.add(seguradora);

		// Cadastro de clientes
		Cliente_PJ.CadastrarCliente(seguradora, "74.542.431/0001-16", "20-10-2012", "21-10-2012", 1, "cliente1", "rua1",
				"medio", "masculino", "baixa");

		Cliente_PJ.CadastrarCliente(seguradora, "97.322.958/0001-30", "23-10-2012", "21-11-2012", 1, "cliente2", "rua2",
				"medio", "feminino", "baixa");

		Cliente_PF.CadastrarCliente(seguradora, "507.244.420-14", "10-10-2013", "15-10-2018", "cliente3", "rua3",
				"superior", "feminino", "alta");

		Cliente_PF.CadastrarCliente(seguradora, "385.344.140-84", "11-10-2015", "18-11-2022", "cliente4", "rua4",
				"medio", "masculina", "media");

		// Clientes que serao removidos
		Cliente_PF.CadastrarCliente(seguradora, "077.181.680-42", "11-10-2015", "18-11-2022", "cliente5", "rua5",
				"medio", "masculina", "media");

		Cliente_PJ.CadastrarCliente(seguradora, "89.841.142/0001-95", "11-10-2015", "18-11-2022", 1, "cliente6", "rua6",
				"medio", "feminina", "media");

		// Remocao de clientes
		System.out.println("Resultados da remocao de clientes");
		seguradora.removerCliente(listaSeguradora.get(0).getListaClientes().get(2).getNome());
		seguradora.removerCliente(listaSeguradora.get(0).getListaClientes().get(1).getNome());

		// Adicionando os veiculos em cada cliente
		seguradora.getListaClientes().get(0).adicionarVeiculo("235d", "Tesla", "Tesla1111", 2020);
		seguradora.getListaClientes().get(1).adicionarVeiculo("fdhthdf444", "Chevrolet", "Chevrolet11", 1950);
		seguradora.getListaClientes().get(2).adicionarVeiculo("fgfg546", "Chevrolet", "Chevrolet222", 1999);
		seguradora.getListaClientes().get(3).adicionarVeiculo("rtrty4", "Ferrari", "Ferrari222", 2023);

		// Calculando os precos de cada cliente
		seguradora.calcularPrecoSeguroCliente(seguradora.getListaClientes().get(0));
		seguradora.calcularPrecoSeguroCliente(seguradora.getListaClientes().get(1));
		seguradora.calcularPrecoSeguroCliente(seguradora.getListaClientes().get(2));
		seguradora.calcularPrecoSeguroCliente(seguradora.getListaClientes().get(3));

		// Gerar sinistro
		Sinistro.gerarSinistro(listaSeguradora, "20-10-2023", "Rua teste", 0,
				seguradora.getListaClientes().get(0).getListaVeiculos().get(0).getPlaca(), 0);

		Sinistro.gerarSinistro(listaSeguradora, "20-10-2025", "Rua teste2", 0,
				seguradora.getListaClientes().get(0).getListaVeiculos().get(0).getPlaca(), 0);

		// Chamando o metodo listar clientes
		seguradora.listarClientes("Cliente_PF");
		seguradora.listarClientes("Cliente_PJ");

		// Chamando o metodo listar sinistros
		seguradora.listarSinistrosSeguradora();

		// Calculando a receita da seguradora
		seguradora.calcularReceita();

		// Chamando o metodo visualizar sinistro
		seguradora.visualizarSinistro("cliente1");

		// Chamada ddo menu principal
		Menu menu = new Menu();
		menu.menu_principal(listaSeguradora);
	}
}
