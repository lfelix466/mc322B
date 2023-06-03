package Classes_principais;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Enumeradores.CalcSeguro;
import Utilidades.Entradas;

public class Seguro_PF extends Seguro{
	
	private Veiculo veiculo;
	private Cliente_PF cliente;
	Scanner entrada = Entradas.entrada;
	
	public Seguro_PF(int id, Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
			ArrayList<Condutor> listacondutores, int valorMensal, Veiculo veiculo, Cliente_PF cliente) {
		super(id, dataInicio, dataFim, seguradora, listaSinistros, listacondutores, valorMensal);
		this.veiculo = veiculo;
		this.cliente = cliente;
	}
	
	public Seguro_PF() {
		
	}
	
	public static boolean cadastraSeguro(Seguradora seguradora, Veiculo veiculo, Cliente_PF cliente,
			String dataFimTexto, String dataInicioTexto) {
		
		String cpf, placa;
		Cliente clienteAux = null;
		
		Seguro_PF seguro = new Seguro_PF();
		Scanner entrada = Entradas.entrada;
		
		if(cliente == null) {
			
			System.out.println("Digite o CPF do cliente!");
			cpf = entrada.nextLine();
			
			if(!Validacao.validarCPF(cpf)) {
				System.out.println("CPF invalido!");
				return false;
			}
			
			clienteAux = Seguradora.encontraCliente(seguradora, cpf);
			
			if(clienteAux == null) {	
				System.out.println("Cliente nao encontrado!");
				return false;
			}
			
			if(clienteAux.getClass().getSimpleName().equals("Cliente_PF")) {	
				System.out.println("Cliente nao é do tipo PF!");
				return false;
			}
			
			cliente = (Cliente_PF) clienteAux;
			
			System.out.println("Digite a placa do veiculo!");
			placa = entrada.nextLine();
			
			veiculo = Cliente_PF.encontraVeiculo(placa, cliente);
			
			if(veiculo == null) {	
				System.out.println("Veiculo nao encontrado!");
				return false;
			}
		}
		
		if(seguro.cadastraDadosSeguro(dataFimTexto, dataFimTexto)) {
			seguro.setCliente(cliente);
			seguro.setVeiculo(veiculo);
		}else {
			return false;
		}
		
		seguradora.getListaSeguros().add(seguro);
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
			
			System.out.println("Digite o CPF do condutor");
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
			
			System.out.println("Digite o CPF do condutor que voce ira cadastrar o sinistro");
			cpf = entrada.nextLine();
			
			if(!Validacao.validarCPF(cpf)) {
				System.out.println("Cpf invalido!");
				return false;
			}
			
			for(int i = 0; i < super.getListacondutores().size(); i++) {
				
				if(getListacondutores().get(i).getCpf() == cpf) {
					
					if(Sinistro.gerarSinistro(seguro, dataTexto, endereco, condutor, dataNascTexto)) {
						System.out.println("Sinistro gerado com sucesso!");
						return true;
					}else {
						System.out.println("Erro ao gerar o sinistro!");
						return false;
			}}}}
		
		return true;
	}
	
public double calcularValor() {
		
		double valor = 0, quantidadeVeiculos = 0, idade = 0, 
			quantidadeSinistrosCliente = 0, quantidadeSinistrosCondutor = 0;
		
		for(int i = 0; i<cliente.getListaVeiculos().size(); i++ ) {
			quantidadeVeiculos = quantidadeVeiculos++;	
		}
		
		idade = Cliente.calculaIdade(cliente.getDataNasc());
		quantidadeSinistrosCliente = getListaSinistros().size();
		
		for(int i = 0; i<getListacondutores().size(); i++ ) {
			quantidadeSinistrosCondutor = quantidadeSinistrosCondutor + 
					getListacondutores().get(i).getListaSinistros().size();
		}
		
		if(idade < 30) {
			valor = (CalcSeguro.VALOR_BASE.getValor()*CalcSeguro.FATOR_18_30.getValor()*
				(1+ 1/( quantidadeVeiculos +2) ) * (2 + quantidadeSinistrosCliente /10) *
				(5 + quantidadeSinistrosCondutor/10));
			
		}else if(idade <= 60){
			valor = (CalcSeguro.VALOR_BASE.getValor()*CalcSeguro.FATOR_30_60.getValor()*
					(1+ 1/( quantidadeVeiculos +2) ) * (2 + quantidadeSinistrosCliente /10) *
					(5 + quantidadeSinistrosCondutor/10));
			
		}else {
			valor = (CalcSeguro.VALOR_BASE.getValor()*CalcSeguro.FATOR_60_90.getValor()*
					(1+ 1/( quantidadeVeiculos +2) ) * (2 + quantidadeSinistrosCliente /10) *
					(5 + quantidadeSinistrosCondutor/10));
		}
		return valor;
	}

	@Override
	public String getIdCliente() {
		return this.cliente.getCpf();
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
