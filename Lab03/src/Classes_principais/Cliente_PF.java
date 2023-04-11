package Classes_principais;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Cliente_PF extends Cliente{

	private String cpf;
	private Date dataNascimento;
	private Scanner entrada = new Scanner(System.in);


	public Cliente_PF(String nome, String endereco, Date dataLicensa, String educacao, String genero,
			String classeEconomica, ArrayList<Veiculo> listaVeiculos, String cpf, Date dataNascimento) {
		super(nome, endereco, dataLicensa, educacao, genero, classeEconomica, listaVeiculos);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}



	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	
	@Override
	public String toString() {
		
		String veiculos = "";
		
		for(int i = 0; i<getListaVeiculos().size(); i++) {
			
			veiculos = veiculos + getListaVeiculos().get(i);
			veiculos = veiculos + "\n";
		}
		
		return "Informações do cliente:\n"
				+ "Tipo: PJ\n"
				+ "Nome:"+getNome()+"\n"
				+ "CPF:"+getCpf()+"\n"
				+ "Endereco:"+getEndereco()+"\n"
				+ "Data da licensa:"+getDataLicensa()+"\n"
				+ "Educacao:"+getEducacao()+"\n"
				+ "Genero:"+getGenero()+"\n"
				+ "Classe econonmica:"+getClasseEconomica()+"\n"
				+ "Data de nascimento:"+getDataNascimento()+"\n"
				+ "Quantidade de veiculos:"+getListaVeiculos().size()+"\n"
				+ veiculos;
	}


	private boolean verificaDigitoCPF(String cpf) {
		int dig1, dig2, valor1 = 0, valor2 = 0;

		for(int i = 0; i < 9; i++) {

			valor2 += Integer.parseInt(String.valueOf(cpf.charAt(i)))*(11-i);

			valor1 += Integer.parseInt(String.valueOf(cpf.charAt(i)))*(10-i);

		}

		valor2 += Integer.parseInt(String.valueOf(cpf.charAt(9)))*(2);

		if(valor1%11 == 0 || valor1%11 == 1) {
			dig1 = 0;
		}else {	
			dig1 = 11-(valor1%11);
		}

		if(valor2%11 == 0 || valor2%11 == 1) {
			dig2 = 0;
		}else {	
			dig2 = 11-(valor2%11);
		}


		if(dig1 != Integer.parseInt(String.valueOf(cpf.charAt(9)))) {	
			return false;
		}

		if(dig2 != Integer.parseInt(String.valueOf(cpf.charAt(10)))) {	
			return false;
		}


		return true;
	}

	public boolean validarCPF(String cpf) {

		cpf = cpf.replaceAll("[^0-9]+", "");

		char texto1 = 0, texto2;
		int aux = 0;

		if(cpf.length() == 11) {

			for(int i = 0; i<11; i++){

				if(i == 0) {

					texto1 = cpf.charAt(i);

				}else{

					texto2 = texto1;
					texto1 = cpf.charAt(i);

					if(texto1 != texto2){
						aux = 1;
						break;
					}}}

			if(aux == 0) {
				return false;
			}

			if(!verificaDigitoCPF(cpf)) {
				return false;
			}

		}else {
			return false;
		}
		return true;
	}

	public boolean CadastrarCliente(Seguradora seguradora) {
		
		String cpf;
		cpf = entrada.nextLine();
		
		if(validarCPF(cpf)) {
		
			Cliente_PF cliente_aux = new Cliente_PF(null, null, null, null, null, null, null, cpf, null);
			
			Date dataNascimento = null;
			
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				
				System.out.println("Digite a data de nascimento do cliente:");
				dataNascimento = formatter.parse(entrada.nextLine());
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			cliente_aux.setDataNascimento(dataNascimento);
			
			seguradora.cadastrarCliente(cliente_aux);
			
			return true;
			
		}else {
			
			System.out.println("CPF não e valido!");
			
		}
		
		return false;
	
	}

}
