package Enumeradores;

public enum Listar_enum {

	LISTAR_CLIENTES_POR_SEGURO(0), LISTAR_SINISTROS_POR_SEGURADORA(1), LISTAR_SINISTROS_POR_CLIENTE(2),
	LISTAR_VEICULOS_POR_CLIENTE(3), LISTAR_VEICULOS_POR_SEGURADORA(4), LISTAR_SEGURADORAS(5), VOLTAR(6);

	public final int operacao;

	Listar_enum(int operacao) {
		this.operacao = operacao;
	}

	public int getOperacao() {
		return operacao;
	}
}