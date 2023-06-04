package Classes_principais;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Utilidades.Entradas;

public class Sinistro {

	static int id_gerado = 0;
	private final int id;
	private Date data;
	private String endereco;
	private Condutor condutor;
	private Date dataNasc;
	private Seguro seguro;

	/// Construtores
	public Sinistro(int id, Date data, String endereco, Condutor condutor, Date dataNasc, Seguro seguro) {
		this.id = geraId();
		this.data = data;
		this.endereco = endereco;
		this.condutor = condutor;
		this.dataNasc = dataNasc;
		this.seguro = seguro;
	}

	public Sinistro() {
		this.id = geraId();
	}

	public static boolean gerarSinistro(Seguro seguro, String dataTexto, String endereco, Condutor condutor,
			String dataNascTexto) {
		/* Funcao que cadastra os dados do sinistro */
		Scanner entrada = Entradas.entrada;

		if (dataTexto == "") {

			System.out.println("Digite a data do sinistro");
			dataTexto = entrada.nextLine();

			if (!Validacao.verificaData(dataTexto)) {
				System.out.println("Data invalida!");
				return false;
			}

			System.out.println("Digite a data de nascimento do condutor");
			dataNascTexto = entrada.nextLine();

			if (!Validacao.verificaData(dataNascTexto)) {
				System.out.println("Data invalida!");
				return false;
			}

			System.out.println("Digite o endereco do sinistro");
			endereco = entrada.nextLine();

			if (!Validacao.verificaNome(endereco)) {
				System.out.println("Endereco invalido!");
				return false;
			}

		}

		Date dataNasc, data;

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			dataNasc = formatter.parse(dataNascTexto);
			data = formatter.parse(dataNascTexto);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		Sinistro sinistro = new Sinistro();
		sinistro.setCondutor(condutor);
		sinistro.setData(data);
		sinistro.setDataNasc(dataNasc);
		sinistro.setEndereco(endereco);
		sinistro.setSeguro(seguro);

		condutor.adicionarSinistro(sinistro);
		seguro.getListaSinistros().add(sinistro);
		return true;
	}

	public static boolean excluirSinistro(Seguradora seguradora, String idTexto) {
		/*
		 * Funcao que remove os sinsitros de uma seguradora e remove os sinistros dos
		 * condutores
		 */
		Scanner entrada = Entradas.entrada;

		int id;

		if (idTexto == "") {

			System.out.println("Digite o id do sinistro que deseja remover");
			idTexto = entrada.nextLine();

			if (!Validacao.verificaNumerosInteiros(idTexto)) {
				System.out.println("ID invalido!");
			}
		}

		id = Integer.parseInt(idTexto);

		for (int i = 0; i < seguradora.getListaSeguros().size(); i++) {

			for (int j = 0; j < seguradora.getListaSeguros().get(i).getListaSinistros().size(); j++) {

				if (seguradora.getListaSeguros().get(i).getListaSinistros().get(j).getId() == id) {

					for (int z = 0; z < seguradora.getListaSeguros().get(i).getListacondutores().size(); z++) {

						if (seguradora.getListaSeguros().get(i).getListacondutores().get(z) == seguradora
								.getListaSeguros().get(i).getListaSinistros().get(j).getCondutor()) {

							for (int x = 0; x < seguradora.getListaSeguros().get(i).getListacondutores().get(z)
									.getListaSinistros().size(); x++) {

								if (seguradora.getListaSeguros().get(i).getListacondutores().get(z).getListaSinistros()
										.get(x).getId() == id) {

									seguradora.getListaSeguros().get(i).getListacondutores().get(z).getListaSinistros()
											.remove(x);

									seguradora.getListaSeguros().get(i).getListaSinistros().remove(j);

									System.out.println("Sinistro removido com sucesso!");
									return true;
								}
							}
						}
					}
				}
			}
		}

		System.out.println("Sinistro nao econtrado em nenhum seguro da seguradora!");

		return false;
	}

	private int geraId() {
		/* Funcao que gera os ids */
		id_gerado = id_gerado + 1;
		return id_gerado;
	}

	// Getters e setters
	public static int getId_gerado() {
		return id_gerado;
	}

	public static void setId_gerado(int id_gerado) {
		Sinistro.id_gerado = id_gerado;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Dados do sinistro\n" + "Id:" + id + "\n" + "Data:" + data + "\n" + "Endereco:" + endereco + "\n"
				+ "Dados do condutor:\n" + "Condutor nome:" + condutor.getNome() + "\n" + "Condutor CPF:"
				+ condutor.getCpf() + "\n" + "Data de nascimento:" + dataNasc + "\n" + "Seguro ID:" + seguro.getId();
	}

}