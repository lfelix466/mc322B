package Classes_principais;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	public static boolean gerarSinistro(Seguro seguro, String dataTexto, String endereco,
			Condutor condutor, String dataNascTexto) {

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
		
		condutor.adicionarSinistro(sinistro);
		seguro.getListaSinistros().add(sinistro);
		return true;
	}

	private int geraId() {
		/* Funcao que gera os ids */
		id_gerado = id_gerado + 1;
		return id_gerado;
	}

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
		return "Dados do sinistro\n"
				+"Id:"+id+"\n"
					+"Data:"+data+"\n"
						+"Endereco:"+endereco+"\n"
							+"Condutor:"+condutor+"\n"
								+"Data de nascimento:"+dataNasc+"\n"
									+"Seguro:"+seguro;
	}

}