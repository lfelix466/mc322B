package Classes_principais;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Utilidades.Entradas;

public abstract class Seguro {

	static int id_gerado = 0;
	private final int id;
	private Date dataInicio;
	private Date dataFim;
	private Seguradora seguradora;
	private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
	private ArrayList<Condutor> listacondutores = new ArrayList<Condutor>();
	private double valorMensal;
	Scanner entrada = Entradas.entrada;

	/// Construtores
	public Seguro(int id, Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
			ArrayList<Condutor> listacondutores, double valorMensal) {
		this.id = geraId();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.seguradora = seguradora;
		this.listaSinistros = listaSinistros;
		this.listacondutores = listacondutores;
		this.valorMensal = 0;
	}

	public Seguro() {
		this.id = geraId();
	}

	public boolean cadastraDadosSeguro(String dataFimTexto, String dataInicioTexto) {
		/* Funcao atribui valores aos cliente do tipo PJ */

		if (dataInicioTexto == "") {

			System.out.println("Digite a data de inicio do seguro");
			dataInicioTexto = entrada.nextLine();

			if (!Validacao.verificaData(dataInicioTexto)) {
				System.out.println("Data invalida");
				return false;
			}

			System.out.println("Digite a data do fim do seguro");
			dataFimTexto = entrada.nextLine();

			if (!Validacao.verificaData(dataFimTexto)) {
				System.out.println("Data invalida");
				return false;
			}
		}

		Date dataInicio, dataFim;

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			dataInicio = formatter.parse(dataInicioTexto);
			dataFim = formatter.parse(dataFimTexto);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		return true;
	}

	public static Seguro encontraSeguro(Seguradora seguradora, int id) {
		/* Funcao que retorna um objeto do tipo Seguro de acordo com id */

		String idTexto;
		Scanner entrada = Entradas.entrada;

		if (id == 0) {

			System.out.println("Digite o id do Seguro que voce quer cadastrar o sinistro");
			idTexto = entrada.nextLine();

			if (!Validacao.verificaNumerosInteiros(idTexto)) {
				System.out.println("Id invalido!");
				return null;
			}

			id = Integer.parseInt(idTexto);
		}

		for (int i = 0; i < seguradora.getListaSeguros().size(); i++) {
			if (seguradora.getListaSeguros().get(i).getId() == id) {
				return seguradora.getListaSeguros().get(i);
			}
		}
		return null;
	}

	private int geraId() {
		/* Funcao que gera os ids */
		id_gerado = id_gerado + 1;
		return id_gerado;
	}

	public static boolean autorizarCondutorEntrada(Seguradora seguradora, Seguro seguro, Condutor condutor) {
		/*
		 * Funcao que adiciona que auxilia no cadastro de um condutor e na insercao dele
		 * na seguradora
		 */
		Scanner entrada = Entradas.entrada;
		String idTexto;
		int id;

		if (condutor == null) {

			System.out.println("Digite o ID do seguro que voce quer encontrar");
			idTexto = entrada.nextLine();

			if (!Validacao.verificaNumerosInteiros(idTexto)) {
				System.out.println("ID invalido!");
				return false;
			}

			id = Integer.parseInt(idTexto);
			seguro = Seguro.encontraSeguro(seguradora, id);

			if (seguro == null) {
				System.out.println("Seguro nao encontrado!");
				return false;
			}
			Condutor.cadastrarCondutor(seguro, "", "", "", "", "", "");
			return true;

		} else {
			seguro.autorizarCondutor(condutor);
		}

		return true;
	}

	public static boolean desautorizarCondutorEntrada(Seguradora seguradora, String cpf) {
		/*
		 * Funcao que auxilia na remocao de um condutor apagando ele das dependencias do
		 * seguro e apaga os sinistros relacionados ao condutor
		 */

		Scanner entrada = Entradas.entrada;
		int aux = 0;

		if (cpf == "") {

			System.out.println("Digite o CPF do condutor que voce deseja remover");
			cpf = entrada.nextLine();

			if (!Validacao.validarCPF(cpf)) {
				System.out.println("CPF invalido!");
				return false;
			}
		}

		for (int i = 0; i < seguradora.getListaSeguros().size(); i++) {

			for (int j = 0; j < seguradora.getListaSeguros().get(i).getListacondutores().size(); j++) {

				if (seguradora.getListaSeguros().get(i).getListacondutores().get(j).getCpf().equals(cpf)) {

					Seguro.removeSinistrosCondutores(seguradora.getListaSeguros().get(i),
							seguradora.getListaSeguros().get(i).getListacondutores().get(j));
					seguradora.getListaSeguros().get(i).desautorizarCondutor(j);
					aux = 1;
				}
			}
		}

		if (aux == 0) {
			System.out.println("Condutor nao encontrado nessa seguradora!");
		}

		return true;
	}

	public static void removeSinistrosCondutores(Seguro seguro, Condutor condutor) {
		/* Funcao wue remove os sinistros associados a um condutor */

		for (int i = seguro.getListaSinistros().size() - 1; i > -1; i--) {
			if (seguro.getListaSinistros().get(i).getCondutor() == condutor) {
				seguro.getListaSinistros().remove(i);
			}
		}
	}

	public boolean listarSinistro() {
		/* Funcao que lista os sinistros cadastrados no seguro */
		if (listaSinistros.isEmpty()) {
			System.out.println("Nao ha sinistros cadastrados neste seguro!");
			return false;
		}
		System.out.println("Sinistros do seguro\n");
		for (int i = 0; i < listaSinistros.size(); i++) {
			System.out.println(listaSinistros.get(i).toString());
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

	// Interfaces que devem ser seguidas pelas classes filhas
	public abstract boolean desautorizarCondutor(int indice);

	public abstract boolean autorizarCondutor(Condutor condutor);

	public abstract double calcularValor();

	public abstract boolean gerarSinistro(Seguro seguro, String cpf, String dataTexto, String endereco,
			String dataNascTexto);

	public abstract String getIdCliente();

	/* Funcao que auxilia na transferencia de seguros */
	public abstract void setCliente(Cliente cliente);

	// Getters e setters
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public ArrayList<Condutor> getListacondutores() {
		return listacondutores;
	}

	public void setListacondutores(ArrayList<Condutor> listacondutores) {
		this.listacondutores = listacondutores;
	}

	public double getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(double valorMensal) {
		this.valorMensal = valorMensal;
	}

	public int getId() {
		return id;
	}

}
