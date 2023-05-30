package Classes_principais;

import java.util.ArrayList;
import java.util.Scanner;

import Utilidades.Entradas;

public class Frota {
	
	private String code;
	private ArrayList<Veiculo> listaVeiculos;
	Scanner entrada = Entradas.entrada;
	
	public Frota(String code, ArrayList<Veiculo> listaVeiculos) {
		super();
		this.code = code;
		this.listaVeiculos = listaVeiculos;
	}
	
	public Frota() {
		
	}
	
	public boolean addVeiculo(String placa, String marca, String modelo, int anoFabricacao) {
		
		Veiculo veiculo = new Veiculo();
		veiculo.cadastrarVeiculo(placa, marca, modelo, anoFabricacao);
		listaVeiculos.add(veiculo);
		
		System.out.println("Veiculo adicionado na frota com sucesso!");
		return true;
	}
	
	public boolean calcularValor() {
		
		
		return true;
	}
	
	public boolean removeVeiculo(String placa) {
		
		if(listaVeiculos.size() != 0) {
			
			if(placa.equals("")) {
				
				System.out.println("Digite a placa do veiculo que deseja remover?");
				placa = entrada.nextLine();
				
			}
			
			for(int i = 0; i < listaVeiculos.size(); i++) {
				
				if(listaVeiculos.get(i).getPlaca().equals(placa)) {
					
					listaVeiculos.remove(i);
					System.out.println("Veiculo removido da frota com sucesso!");
					return true;
				}
				
			}
			
			System.out.println("Veiculo nao encontrado na frota!");
			
			
		}else {
			
			System.out.println("Nao existem veiculos cadastrados nesta frota!");
			
		}
		
		return false;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	@Override
	public String toString() {
		return "Dados da frota\n"
				+"Code:"+code+"\n"
					+"ListaVeiculos:"+listaVeiculos;
	}

}
