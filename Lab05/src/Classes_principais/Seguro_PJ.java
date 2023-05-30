package Classes_principais;

import java.util.ArrayList;
import java.util.Date;

public class Seguro_PJ extends Seguro{
	
	private Frota frota;
	private Cliente_PJ cliente;
	
	public Seguro_PJ(int id, Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
			ArrayList<Condutor> listacondutores, int valorMensal, Frota frota, Cliente_PJ cliente) {
		super(id, dataInicio, dataFim, seguradora, listaSinistros, listacondutores, valorMensal);
		this.frota = frota;
		this.cliente = cliente;
	}
	
	public Seguro_PJ() {
	}
	
	public static boolean cadastraSeguro_PJ(Seguradora seguradora, Frota frota, Cliente_PJ cliente) {
		
		Seguro_PJ seguro = new Seguro_PJ();
		
		seguro.cadastraDadosSeguro("", "");
		seguro.setCliente(cliente);
		seguro.setFrota(frota);
		seguro.setSeguradora(seguradora);
		
		//seguradora.fdfkdfk
		
		return true;
	}

	@Override
	public boolean autorizarCondutor(Condutor condutor) {
		
		getListacondutores().add(condutor);
		
		return true;
	}
	
	@Override
	public boolean desautorizarCondutor(String cpf) {
		
		if(cpf == "") {
			
			System.out.println("Digite o cpf do condutor");
			cpf = entrada.nextLine();
			
			if(!Validacao.verificaDigitoCPF(cpf)) {
				System.out.println("CPF invalido!");
				return false;
			}
		}
		
		if(getListacondutores().isEmpty()) {
		
			System.out.println("Seguro nao possui condutores cadastraos!");
			
		}else {
			
			for(int i = 0; i < getListacondutores().size(); i++) {
				
				if(getListacondutores().get(i).getCpf() == cpf) {
					
					getListacondutores().remove(i);
					System.out.println("Condutor desautorizado com sucesso!");
					return true;
		}}}

		return false;
	}
	
	public boolean gerarSinistro(Seguro seguro, String dataTexto, String endereco,
			Condutor condutor, String dataNascTexto) {
		
		String cpf;
		
		if(condutor == null) {
			
			System.out.println("Digite o condutor que voce ira cadastrar o sinistro");
			cpf = entrada.nextLine();
			
			if(!Validacao.validarCPF(cpf)) {
				System.out.println("Cpf invalido!");
				return false;
			}
			
			for(int i = 0; i < super.getListacondutores().size(); i++) {
				
				if(super.getListacondutores().get(i).getCpf() == cpf) {
					
					if(gerarSinistro(seguro, dataTexto, endereco, condutor, dataNascTexto)) {
						
						System.out.println("Sinistro gerado com sucesso!");
						
					}else {
						
						System.out.println("Erro ao gerar o sinistro!");
						
			}}}}
		
		return true;
	}
	
	public boolean calcularValor() {
		
		
		return true;
	}

	public Frota getFrota() {
		return frota;
	}

	public void setFrota(Frota frota) {
		this.frota = frota;
	}

	public Cliente_PJ getCliente() {
		return cliente;
	}

	public void setCliente(Cliente_PJ cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Dados seguro PJ\n"
				+"Frota:"+frota+"\n"
					+"Cliente:"+cliente;
	}
	
}
