package Classes_principais;

import java.util.ArrayList;
import Utilidades.Menu;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Cliente_PF cliente_PF = new Cliente_PF("");
		Cliente_PJ cliente_PJ = new Cliente_PJ("");
		Condutor condutor = new Condutor("");		
		Frota frota = new Frota("1");
		Seguradora seguradora = new Seguradora("");
		Seguro_PF seguroPF = new Seguro_PF();
		Seguro_PJ seguroPJ = new Seguro_PJ();
		Sinistro sinitro = new Sinistro();
		Veiculo veiculo = new Veiculo("1010", "CHEVROLET", "CORSA", 1998);
		
		// Inicializacao das variaveis
		ArrayList<Seguradora> listaSeguradora = new ArrayList<Seguradora>();
		
		// Instanciando os objetos do lab
		Seguradora.cadastrarSeguradora(listaSeguradora, "955.492.700-21",
				"Seguradora1", "999999999", "Rua1", "seguradora1@email.com");
		Seguradora.cadastrarSeguradora(listaSeguradora, "555.405.450-10", "Seguradora2",
				"222222222", "Rua2", "seguradora2@email.com");

		// Cadastro de clientes
		Cliente_PJ.CadastrarCliente(listaSeguradora.get(0), "Comercio1", "999999999", 
				"Rua1", "Comercio1@email.com", "43.179.374/0001-29", "10-10-2009", "2");

		Cliente_PJ.CadastrarCliente(listaSeguradora.get(0), "Comercio2", "999999999", 
				"Rua2", "Comercio2@email.com", "55.474.339/0001-11", "10-10-2007", "1");
		
		Cliente_PJ.CadastrarCliente(listaSeguradora.get(0), "Comercio3", "999999999", 
				"Rua3", "Comercio3@email.com", "44.945.450/0001-22", "10-10-2009", "5");

		Cliente_PF.CadastrarCliente(listaSeguradora.get(0), "164.095.440-65", "Pessoa1", "8888888888",
				 "Rua4", "Pessoa1@email.com", "Masculino", "Medio", "03-06-1996");

		Cliente_PF.CadastrarCliente(listaSeguradora.get(0), "168.145.130-13", "Pessoa2", "8888888888",
				 "Rua5", "Pessoa5@email.com", "Feminino", "Superior", "03-06-2003");
		
		Cliente_PF.CadastrarCliente(listaSeguradora.get(0), "497.459.910-07", "Pessoa3", "8888888888",
				 "Rua6", "Pessoa6@email.com", "Masculino", "Medio", "03-08-1999");
		
		listaSeguradora.get(0).getListaClientes().get(0).cadastrarFrota("10");
		listaSeguradora.get(0).getListaClientes().get(1).cadastrarFrota("11");
		listaSeguradora.get(0).getListaClientes().get(2).cadastrarFrota("12");
		listaSeguradora.get(0).getListaClientes().get(3).cadastrarVeiculo("1010", "CHEVROLET", "CORSA", 1998);
		listaSeguradora.get(0).getListaClientes().get(4).cadastrarVeiculo("2020", "FIAT", "PUNTO", 2012);
		listaSeguradora.get(0).getListaClientes().get(5).cadastrarVeiculo("3030", "VOLKSWAGEN", "GOL", 2017);
		
		listaSeguradora.get(0).gerarSeguro(1, listaSeguradora.get(0), null, frota, null,
				(Cliente_PJ)listaSeguradora.get(0).getListaClientes().get(0), "10-10-2022", "10-10-2020");
		
		listaSeguradora.get(0).gerarSeguro(1, listaSeguradora.get(0), veiculo, null, 
				(Cliente_PF)listaSeguradora.get(0).getListaClientes().get(5),
				null, "10-10-2022", "10-10-2020");
		
		// Chamada ddo menu principal
		Menu menu = new Menu();
		menu.menu_principal(listaSeguradora);
	}
}
