package br.com.pismo.enumerator;

public enum OperationTypeEnum {

	COMPRA_VISTA(1), COMPRA_PARCELADA(2), SAQUE(3), PAGAMENTO(4);

	private int id;

	private OperationTypeEnum(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
