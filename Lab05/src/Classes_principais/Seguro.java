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
	private ArrayList<Sinistro> listaSinistros;
	private ArrayList<Condutor> listacondutores;
	private int valorMensal;
	Scanner entrada = Entradas.entrada;
	
	public Seguro(int id, Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
			ArrayList<Condutor> listacondutores, int valorMensal) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.seguradora = seguradora;
		this.listaSinistros = listaSinistros;
		this.listacondutores = listacondutores;
		this.valorMensal = 0;
	}

	public Seguro() {
		this.id = 0;
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
		
		this.dataInicio= dataInicio;
		this.dataFim = dataFim;
		
		return true;
	}
	
	private int geraId() {
		/* Funcao que gera os ids */
		id_gerado = id_gerado + 1;
		return id_gerado;
	}

	public abstract boolean desautorizarCondutor(String cpf);
	public abstract boolean autorizarCondutor(Condutor condutor);
	public abstract boolean calcularValor();
	public abstract boolean gerarSinistro(Seguro seguro, String dataTexto, String endereco,
			Condutor condutor, String dataNascTexto);
	

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

	public int getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(int valorMensal) {
		this.valorMensal = valorMensal;
	}

	public int getId() {
		return id;
	}

}
