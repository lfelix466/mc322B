package Classes_principais;

import java.text.SimpleDateFormat;

public class Validacao {

	static boolean verificaDigitoCPF(String cpf) {
		/* Funcao de verifica se os digitos sao validos */
		int dig1, dig2, valor1 = 0, valor2 = 0;

		for (int i = 0; i < 9; i++) {
			valor2 += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (11 - i);
			valor1 += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (10 - i);
		}

		valor2 += Integer.parseInt(String.valueOf(cpf.charAt(9))) * (2);
		if (valor1 % 11 == 0 || valor1 % 11 == 1) {
			dig1 = 0;
		} else {
			dig1 = 11 - (valor1 % 11);
		}

		if (valor2 % 11 == 0 || valor2 % 11 == 1) {
			dig2 = 0;
		} else {
			dig2 = 11 - (valor2 % 11);
		}

		if (dig1 != Integer.parseInt(String.valueOf(cpf.charAt(9)))) {
			return false;
		}

		if (dig2 != Integer.parseInt(String.valueOf(cpf.charAt(10)))) {
			return false;
		}
		return true;
	}

	static boolean validarCPF(String cpf) {
		/* Funcao que verifica se o cpf e valido */
		cpf = cpf.replaceAll("[^0-9]+", "");

		char texto1 = 0, texto2;
		int aux = 0;

		if (cpf.length() == 11) {
			for (int i = 0; i < 11; i++) {
				if (i == 0) {
					texto1 = cpf.charAt(i);
				} else {
					texto2 = texto1;
					texto1 = cpf.charAt(i);
					if (texto1 != texto2) {
						aux = 1;
						break;
					}
				}
			}

			if (aux == 0) {
				return false;
			}

			if (!verificaDigitoCPF(cpf)) {
				return false;
			}

		} else {
			return false;
		}
		return true;
	}

	static boolean verificaDigitoCNPJ(String cnpj) {
		/* Funcao que verifica se os digitos sao validos */
		int dig1, dig2, valor1 = 0, valor2 = 0;

		for (int i = 0; i < 4; i++) {
			valor1 += Integer.parseInt(String.valueOf(cnpj.charAt(i))) * (5 - i);
		}

		for (int i = 4; i < 12; i++) {
			valor1 += Integer.parseInt(String.valueOf(cnpj.charAt(i))) * ((9) - i + 4);
		}

		for (int i = 0; i < 5; i++) {
			valor2 += Integer.parseInt(String.valueOf(cnpj.charAt(i))) * (6 - i);
		}

		for (int i = 5; i < 13; i++) {
			valor2 += Integer.parseInt(String.valueOf(cnpj.charAt(i))) * ((9) - i + 5);
		}

		if (valor1 % 11 == 0 || valor1 % 11 == 1) {
			dig1 = 0;
		} else {
			dig1 = 11 - (valor1 % 11);
		}

		if (valor2 % 11 == 0 || valor2 % 11 == 1) {
			dig2 = 0;
		} else {
			dig2 = 11 - (valor2 % 11);
		}

		if (dig1 != Integer.parseInt(String.valueOf(cnpj.charAt(12)))) {
			return false;
		}

		if (dig2 != Integer.parseInt(String.valueOf(cnpj.charAt(13)))) {
			return false;
		}
		return true;
	}

	static boolean validarCNPJ(String cnpj) {
		/* Funcao que verifica o cnpj */
		cnpj = cnpj.replaceAll("[^0-9]+", "");

		char texto1 = 0, texto2;
		int aux = 0;

		if (cnpj.length() == 14) {
			for (int i = 0; i < 14; i++) {
				if (i == 0) {
					texto1 = cnpj.charAt(i);
				} else {
					texto2 = texto1;
					texto1 = cnpj.charAt(i);
					if (texto1 != texto2) {
						aux = 1;
						break;
					}
				}
			}

			if (aux == 0) {
				return false;
			}

			if (!verificaDigitoCNPJ(cnpj)) {
				return false;
			}

		} else {
			return false;
		}
		return true;
	}

	static boolean verificaNome(String texto) {
		/* Funcao que verifica se os nomes sao validos */
		if (texto.matches("[a-zA-Z]+")) {
			return true;
		}
		return true;
	}

	static boolean verificaNumerosInteiros(String texto) {
		/* Funcao que verifica se os valores sao inteiros */
		try {
			Integer.parseInt(texto);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	static boolean verificaNumerosDouble(String texto) {
		/* Funcao que verifica se os valores sao Doubles validos */
		try {
			Double.parseDouble(texto);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	static boolean verificaData(String texto) {
		/* Funcao que verifica se a data passada e valida */
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			formatter.parse(texto);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}