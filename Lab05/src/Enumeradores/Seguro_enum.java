package Enumeradores;

public enum Seguro_enum {

	GERAR_SEGURO(0), AUTORIZAR_CONDUTOR(1), DESEAUTORIZAR_CONDUTOR(2), EXCLUIR_SEGURO(3), VOLTAR(4);

	public final int operacao;

	Seguro_enum(int operacao) {
		this.operacao = operacao;
	}

}
