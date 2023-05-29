package Enumeradores;

public enum Cadastro_enum {

	CADASTRAR_CLIENTE_PF_PJ(0), CADASTRAR_VEICULO(1), CADASTRAR_SEGURADORA(2), VOLTAR(3);

	public final int operacao;

	Cadastro_enum(int operacao) {
		this.operacao = operacao;
	}

}
