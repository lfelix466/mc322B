package Labs;

import java.util.Date;

public class Cliente_PF extends Cliente{
	
	private final String cpf;
	private Date dataNascimento;

	public Cliente_PF(String nome, String endereco, Date dataLicensa, String educacao, String genero,
			String classeEconomica, String cpf, Date dataNascimento) {
		super(nome, endereco, dataLicensa, educacao, genero, classeEconomica);
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

	@Override
	public String toString() {
		return "Cliente_PF [cpf=" + cpf + ", dataNascimento=" + dataNascimento + "]";
	}
	
	
	private boolean VerificaDigitoCPF(String cpf) {
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
	
	public boolean ValidarCPF(String cpf) {
		
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
			
			if(!VerificaDigitoCPF(cpf)) {
				return false;
			}
				
		}else {
			return false;
		}
		return true;
	}
	
}
