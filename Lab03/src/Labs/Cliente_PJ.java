package Labs;

import java.util.Date;

public class Cliente_PJ extends Cliente{
	
	private final String cnpj;
	private Date dataFUndacao;
	
	public Cliente_PJ(String nome, String endereco, Date dataLicensa, String educacao, String genero,
			String classeEconomica, String cnpj, Date dataFUndacao) {
		super(nome, endereco, dataLicensa, educacao, genero, classeEconomica);
		this.cnpj = cnpj;
		this.dataFUndacao = dataFUndacao;
	}


	public Date getDataFUndacao() {
		return dataFUndacao;
	}


	public void setDataFUndacao(Date dataFUndacao) {
		this.dataFUndacao = dataFUndacao;
	}


	public String getCnpj() {
		return cnpj;
	}


	@Override
	public String toString() {
		return "Cliente_PJ [cnpj=" + cnpj + ", dataFUndacao=" + dataFUndacao + "]";
	}
	
	private boolean VerificaDigitoCNPJ(String cnpj) {
		int dig1, dig2, valor1 = 0, valor2 = 0;
		
		for(int i = 0; i < 5; i++) {
			
			valor2 += Integer.parseInt(String.valueOf(cnpj.charAt(i)))*(5-i);
			
		
		}
		
		for(int i = 5; i < 11; i++) {
			
			valor2 += Integer.parseInt(String.valueOf(cnpj.charAt(i)))*(9-i);
			
		
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
		
		
		if(dig1 != Integer.parseInt(String.valueOf(cpf.charAt(9)))) {	
			return false;
		}
		
		if(dig2 != Integer.parseInt(String.valueOf(cpf.charAt(10)))) {	
			return false;
		}
		
		
		return true;
	}
	
	public boolean ValidarCNPJ(String cnpj) {
		
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
			
			if(!VerificaDigitoCNPJ(cpf)) {
				return false;
			}
				
		}else {
			return false;
		}
		return true;
	}

	
}
