package uniandes.isis2304.alohandes.negocio;

public class Reserva implements VOReserva {
	/**
	 * El identificador ÚNICO de las reservas
	 */
	private long id;

	/**
	 * El estado de la reserva
	 */
	private int estado;

	/**
	 * El valor total de la reserva
	 */
	private double valor_total;

	/**
	 * El cliente propietario de la reserva
	 */
	private long cliente_id;

	/**
	 * Constructor por defecto
	 */
	public Reserva() {
		this.id = 0;
		this.estado = 0;
		this.valor_total = 0;
		this.cliente_id = 0;

	}

	/**
	 * Constructor con valores
	 * 
	 * @param id         - El id de la reserva
	 * @param estado     - El estado de la reserva
	 * @param valorTotal - El valor total de la reserva
	 * @param cliente_id - El cliente propietario de la reserva
	 */
	public Reserva(long id, int estado, int valor_total, long cliente_id) {
		this.id = id;
		this.estado = estado;
		this.valor_total = valor_total;
		this.cliente_id = cliente_id;

	}

	/**
	 * @return El id del horario
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id de la reserva
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El estado de la reserva
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado - El nuevo estado de la reserva
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * @return El valor total de la reserva
	 */
	public double getValor_total() {
		return valor_total;
	}

	/**
	 * @param valorTotal - El nuevo valor total de la reserva
	 */
	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	/**
	 * @return El cliente propietario de la reserva
	 */
	public long getCliente_id() {
		return cliente_id;
	}

	/**
	 * @param cliente_id - El nuevo cliente propietario de la reserva
	 */
	public void setCliente_id(long cliente_id) {
		this.cliente_id = cliente_id;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos de la reserva
	 */
	public String toString() {
		return "Reserva [id=" + id + ", estado=" + estado + ", valor_total=" + valor_total + ", cliente_id="
				+ cliente_id + "]";
	}

}
