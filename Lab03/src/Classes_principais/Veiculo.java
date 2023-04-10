package Classes_principais;

import java.util.Scanner;

public class Veiculo {
	
	private String placa;
	private String marca;
	private String modelo;
	private int anoFabricacao;
	private Scanner entrada = new Scanner(System.in);
	
	//Contrutor
	public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
	}
		
	//Getters e setters
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
		return "Veiculo [placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", anoFabricacao="
				+ anoFabricacao + "]";
	}
	
	public Veiculo cadastrarVeiculo() {
		
		Veiculo veiculo = new Veiculo(null, null, null, 0);
		
		System.out.println("Digite a placa do veiculo:");
		veiculo.setPlaca(entrada.nextLine());
		System.out.println("Digite a marca do veiculo:");
		veiculo.setMarca(entrada.nextLine());
		System.out.println("Digite o modelo do veiculo:");
		veiculo.setModelo(entrada.nextLine());
		System.out.println("Digite o ano de fabricacao do veiculo:");
		veiculo.setAnoFabricacao(entrada.nextInt());
		
		return veiculo;
	}

}
