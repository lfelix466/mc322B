package Enumeradores;

public enum MenuOperacoes {

	CADASTROS(1), LISTAR(2), EXCLUIR(3), GERAR_SINISTRO(4), SEGUROS(5), TRANSFERIR_SEGURO(6), CALCULAR_RECEITA(7),
	TROCAR_SEGURADORA(8), TROCAR_CLIENTE(9), ATUALIZAR_FROTA(10), SAIR(11);

	public final int operacao;

	MenuOperacoes(int operacao) {
		// TODO Auto-generated constructor stub
		this.operacao = operacao;
	}

	public double getOperacao() {
		return operacao;
	}
}
