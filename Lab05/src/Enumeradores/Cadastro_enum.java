package Enumeradores;

public enum Cadastro_enum {

	CADASTRAR_CLIENTE_PF_PJ(0), CADASTRAR_VEICULO(1), CADASTRAR_SEGURO(2), CADASTRAR_SEGURADORA(3), VOLTAR(4);

	public final int operacao;

	Cadastro_enum(int operacao) {
		this.operacao = operacao;
	}

}
