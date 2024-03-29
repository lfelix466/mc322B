package Classes_principais;

import java.util.Scanner;

import Utilidades.Entradas;

public class Veiculo {

	private String placa;
	private String marca;
	private String modelo;
	private int anoFabricacao;
	Scanner entrada = Entradas.entrada;

	// Contrutores
	public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
	}

	public Veiculo() {

	}

	public boolean cadastrarVeiculo(String placa, String marca, String modelo, int anoFabricacao) {
		/* Funcao que adiciona os valores ao veiculo */

		if (placa == "") {

			System.out.println("Digite a placa do veiculo");
			placa = entrada.nextLine();
			System.out.println("Digite a marca do veiculo");
			marca = entrada.nextLine();
			System.out.println("Digite o modelo do veiculo");
			modelo = entrada.nextLine();

			System.out.println("Digite o ano de fabricacao");
			try {
				anoFabricacao = Integer.parseInt(entrada.nextLine());
			} catch (Exception e) {
				System.out.println("Ano de fabricacao invalido!");
				return false;
			}

			if (placa == "" || modelo == "" || marca == "") {
				System.out.println("Dados invalidos!");
				return false;
			}
		}

		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;

		return true;
	}

	// Getters e setters
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	@Override
	public String toString() {
		return "Veiculo\n" + "Placa = " + placa + "\n" + "Marca = " + marca + "\n" + "Modelo = " + modelo + "\n"
				+ "anoFabricacao = " + anoFabricacao + "\n";
	}
}