package uniandes.isis2304.alohandes.negocio;

public class Reserva_Colectiva {

	private long id;

	private double valor_total;

	public Reserva_Colectiva() {
		this.id = 0;
		this.valor_total = 0;
	}

	public Reserva_Colectiva(long id, double valor_total) {
		this.id = id;
		this.valor_total = valor_total;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	@Override
	public String toString() {
		return "Reserva_Colectiva [id=" + id + ", valor_total=" + valor_total + "]";
	}

}
