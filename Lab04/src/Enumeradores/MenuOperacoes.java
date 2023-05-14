package Enumeradores;

public enum MenuOperacoes {

	CADASTROS(1), LISTAR(2), EXCLUIR(3), GERAR_SINISTRO(5), TRANSFERIR_SEGURO(6), CALCULAR_RECEITA(7),
	TROCAR_SEGURADORA(8), TROCAR_CLIENTE(9), SAIR(10);

	public final int operacao;

	MenuOperacoes(int operacao) {
		// TODO Auto-generated constructor stub
		this.operacao = operacao;
	}

	public double getOperacao() {
		return operacao;
	}
}
