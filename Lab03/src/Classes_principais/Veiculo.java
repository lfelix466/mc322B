package Classes_principais;

<<<<<<< HEAD
=======
import java.util.Scanner;

>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
public class Veiculo {
	
	private String placa;
	private String marca;
	private String modelo;
	private int anoFabricacao;
<<<<<<< HEAD
	
	//Contrutores
=======
	private Scanner entrada = new Scanner(System.in);
	
	//Contrutor
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
	public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
	}
	
	public Veiculo() {
		
	}
<<<<<<< HEAD
	
	public Veiculo cadastrarVeiculo(String placa, String marca, String modelo, int dataFabricacao) {
		/*Funcao que cadastra veiculo e o retorna*/
		
		Veiculo veiculo = new Veiculo(null, null, null, 0);
		veiculo.setPlaca(placa);
		veiculo.setMarca(marca);
		veiculo.setModelo(modelo);
		veiculo.setAnoFabricacao(dataFabricacao);
		
		return veiculo;
	}
=======
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63
		
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
	
<<<<<<< HEAD
=======
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
>>>>>>> 600e24fb983042bbc545a10310ff68d0c1ac2b63

}
