package Enumeradores;

public enum MenuOperacoes {

	CADASTROS(1), LISTAR(2), EXCLUIR(3), GERAR_SINISTRO(5), GERAR_SEGURO(6), TRANSFERIR_SEGURO(7), CALCULAR_RECEITA(8),
	TROCAR_SEGURADORA(9), TROCAR_CLIENTE(10), SAIR(11);

	public final int operacao;

	MenuOperacoes(int operacao) {
		// TODO Auto-generated constructor stub
		this.operacao = operacao;
	}

	public double getOperacao() {
		return operacao;
	}
}
