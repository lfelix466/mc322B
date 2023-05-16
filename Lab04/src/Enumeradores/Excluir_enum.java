package Enumeradores;

public enum Excluir_enum {

	EXCLUIR_CLIENTE(0), EXCLUIR_VEICULO(1), EXCLUIR_SINISTRO(2), VOLTAR(3);

	public final int operacao;

	Excluir_enum(int operacao) {
		this.operacao = operacao;
	}

	public int getOperacao() {
		return operacao;
	}
}
