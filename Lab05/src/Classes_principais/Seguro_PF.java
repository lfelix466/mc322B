package Classes_principais;

import java.util.ArrayList;
import java.util.Date;

public class Seguro_PF extends Seguro{
	
	private Veiculo veiculo;
	private Cliente_PF cliente;
	
	public Seguro_PF(int id, Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
			ArrayList<Condutor> listacondutores, int valorMensal, Veiculo veiculo, Cliente_PF cliente) {
		super(id, dataInicio, dataFim, seguradora, listaSinistros, listacondutores, valorMensal);
		this.veiculo = veiculo;
		this.cliente = cliente;
	}
	
	public Seguro_PF() {
		
	}
	
	public static boolean cadastraSeguro_PF(Seguradora seguradora, Veiculo veiculo, Cliente_PF cliente) {
		
		Seguro_PF seguro = new Seguro_PF();
		
		seguro.cadastraDadosSeguro("", "");
		
		seguro.setCliente(cliente);
		seguro.setVeiculo(veiculo);
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

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente_PF getCliente() {
		return cliente;
	}

	public void setCliente(Cliente_PF cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Dados do seguro PF\n"
				+ "Veiculo:"+veiculo+"\n"
					+"Cliente:"+cliente;
	}

}
