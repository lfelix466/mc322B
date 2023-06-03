package Classes_principais;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Utilidades.Entradas;

public class Condutor {

	private final String cpf;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private Date dataNasc;
	private ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
	
	Scanner entrada = Entradas.entrada;
	
	public Condutor(String cpf, String nome, String telefone, String endereco, String email, Date dataNasc,
			ArrayList<Sinistro> listaSinistros) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.dataNasc = dataNasc;
		this.listaSinistros = listaSinistros;
	}
	
	public Condutor(String cpf) {
		this.cpf = cpf;
	}
	
	public static boolean cadastrarCondutor(Seguro seguro, String cpf, String nome, String telefone, 
			String endereco, String email, String dataNascTexto) {
		/* Funcao atribui valores aos cliente do tipo PJ */

		Scanner entrada = Entradas.entrada;

		if (cpf == "") {

			System.out.println("Digite o cnpj do condutor");
			cpf = entrada.nextLine();

			if (!Validacao.validarCPF(cpf)) {
				System.out.println("Cpf invalido!");
				return false;
			}

			System.out.println("Digite a data de nascimento do condutor");
			dataNascTexto = entrada.nextLine();

			if (!Validacao.verificaData(dataNascTexto)) {
				return false;
			}

			System.out.println("Digite o nome do condutor");
			nome = entrada.nextLine();

			if (!Validacao.verificaNome(nome)) {
				System.out.println("Nome invalido!");
				return false;
			}
			
			System.out.println("Digite o telefone do condutor");
			telefone = entrada.nextLine();

			if (!Validacao.verificaNumerosInteiros(telefone)) {
				System.out.println("Telefone invalido!");
				return false;
			}
			
			System.out.println("Digite o endereco do condutor");
			endereco = entrada.nextLine();

			if (!Validacao.verificaNome(endereco)) {
				System.out.println("Endereco invalido!");
				return false;
			}

			System.out.println("Digite o email do condutor");
			cpf = entrada.nextLine();

			if (!Validacao.verificaNome(email)) {
				System.out.println("Email invalido!");
				return false;
			}
		}

		Date dataNasc = null;
		Condutor condutor_Aux = new Condutor(cpf);

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			dataNasc = formatter.parse(dataNascTexto);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		condutor_Aux.setNome(nome);
		condutor_Aux.setEmail(email);
		condutor_Aux.setEndereco(endereco);
		condutor_Aux.setTelefone(telefone);
		condutor_Aux.setDataNasc(dataNasc);
		seguro.autorizarCondutor(condutor_Aux);

		System.out.println("Condutor cadastrado com sucesso!");
		return true;
	}	
	
	public boolean adicionarSinistro(Sinistro sinistro) {
		
		listaSinistros.add(sinistro);
		System.out.println("Sinistro adicionado ao condutor com sucesso !");
		
		return true;
	}
	
	public boolean listarSinistro() {
		
		if(listaSinistros.isEmpty()) {
			System.out.println("Nao ha sinistros cadastrados neste condutor!");
			return false;
		}
		System.out.println("Sinistros do condutor\n");
		for(int i = 0; i < listaSinistros.size(); i++) {
			System.out.println(listaSinistros.get(i).toString());
		}
		return true;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public String getCpf() {
		return cpf;
	}

	@Override
	public String toString() {
		return "Dados do condutor"
				+"Cpf:"+cpf+"\n"
					+"Nome:"+nome+"\n"
						+"Telefone:"+telefone+"\n"
							+"Endereco:"+endereco+"\n"
								+"Email:"+email+"\n"
									+"Data de nascimento:"+dataNasc+"\n"
										+"ListaSinistros:"+listaSinistros;
	}
	
}
