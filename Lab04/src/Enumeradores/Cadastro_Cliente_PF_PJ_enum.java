package Enumeradores;

public enum Cadastro_Cliente_PF_PJ_enum {

	CLIENTE_PF(0), CLIENTE_PJ(1), VOLTAR(2);

	public final int operacao;

	Cadastro_Cliente_PF_PJ_enum(int operacao) {
		this.operacao = operacao;
	}
}
