package Classes_principais;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Cliente_PJ extends Cliente{
	
	private final String cnpj;
	private Date dataFundacao;
	private Scanner entrada = new Scanner(System.in);
	
	public Cliente_PJ(String nome, String endereco, Date dataLicensa, String educacao, String genero,
			String classeEconomica, ArrayList<Veiculo> listaVeiculos, String cnpj, Date dataFundacao) {
		super(nome, endereco, dataLicensa, educacao, genero, classeEconomica, listaVeiculos);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}



	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}



	public String getCnpj() {
		return cnpj;
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
				+ "CNPJ:"+getCnpj()+"\n"
				+ "Endereco:"+getEndereco()+"\n"
				+ "Data da licensa:"+getDataLicensa()+"\n"
				+ "Educacao:"+getEducacao()+"\n"
				+ "Genero:"+getGenero()+"\n"
				+ "Classe econonmica:"+getClasseEconomica()+"\n"
				+ "Data de fundacao:"+getDataFundacao()+"\n"
				+ "Quantidade de veiculos:"+getListaVeiculos().size()+"\n"
				+ veiculos;
	}

	private boolean verificaDigitoCNPJ(String cnpj) {
		int dig1, dig2, valor1 = 0, valor2 = 0;
		
		for(int i = 0; i < 4; i++) {	
			valor1 += Integer.parseInt(String.valueOf(cnpj.charAt(i)))*(5-i);
		}
		
		for(int i = 4; i < 12; i++) {
			valor1 += Integer.parseInt(String.valueOf(cnpj.charAt(i)))*((9)-i+4);
		}
		
		for(int i = 0; i < 5; i++) {
			valor2 += Integer.parseInt(String.valueOf(cnpj.charAt(i)))*(6-i);
		}
		
		for(int i = 5; i < 13; i++) {
			valor2 += Integer.parseInt(String.valueOf(cnpj.charAt(i)))*((9)-i+5);
		}
		
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
		
		
		if(dig1 != Integer.parseInt(String.valueOf(cnpj.charAt(12)))) {	
			return false;
		}
		
		if(dig2 != Integer.parseInt(String.valueOf(cnpj.charAt(13)))) {	
			return false;
		}
		
		
		return true;
	}
	
	public boolean validarCNPJ(String cnpj) {
		
		cnpj = cnpj.replaceAll("[^0-9]+", "");
		
		char texto1 = 0, texto2;
		int aux = 0;
		
		if(cnpj.length() == 14) {
			
			for(int i = 0; i<14; i++){
				
				if(i == 0) {
					
					texto1 = cnpj.charAt(i);
				
				}else{
					
					texto2 = texto1;
					texto1 = cnpj.charAt(i);
					
					if(texto1 != texto2){
						aux = 1;
						break;
					}}}
			
				if(aux == 0) {
					return false;
				}
			
			if(!verificaDigitoCNPJ(cnpj)) {
				return false;
			}
				
		}else {
			return false;
		}
		return true;
	}

	
	public boolean CadastrarCliente(Seguradora seguradora) {
		
		String cnpj;
		cnpj = entrada.nextLine();
		
		if(validarCNPJ(cnpj)) {
		
			Cliente_PJ cliente_aux = new Cliente_PJ(null, null, null, null, null, null, null, cnpj, null);
			
			Date dataFundacao = null;
			
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				
				System.out.println("Digite a data de nascimento do cliente:");
				dataFundacao = formatter.parse(entrada.nextLine());
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			cliente_aux.setDataFundacao(dataFundacao);
			
			seguradora.cadastrarCliente(cliente_aux);
			
			return true;
			
		}else {
			
			System.out.println("CNPJ não e valido!");
			
		}
		
		return false;
	
	}
	
	
}
