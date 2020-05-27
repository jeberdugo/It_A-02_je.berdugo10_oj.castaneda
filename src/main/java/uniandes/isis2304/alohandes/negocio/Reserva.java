package uniandes.isis2304.alohandes.negocio;

import java.sql.Date;

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
	
	private Date fecha_realizacion;
	
	private Long reserva_colectiva_id;

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
		this.fecha_realizacion = null;
		this.reserva_colectiva_id = null;
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
	public Reserva(long id, int estado, int valor_total, Date fecha_realizacion, Long reserva_colectiva_id, long cliente_id) {
		this.id = id;
		this.estado = estado;
		this.valor_total = valor_total;
		this.fecha_realizacion = fecha_realizacion;
		this.reserva_colectiva_id = reserva_colectiva_id;
		this.cliente_id = cliente_id;

	}

	/**
	 * @return El id del horario
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id de la reserva
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El estado de la reserva
	 */
	@Override
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado - El nuevo estado de la reserva
	 */
	@Override
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * @return El valor total de la reserva
	 */
	@Override
	public double getValor_total() {
		return valor_total;
	}

	/**
	 * @param valorTotal - El nuevo valor total de la reserva
	 */
	@Override
	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
	
	/**
	 * @return El valor total de la reserva
	 */
	public Date getFecha_realizacion() {
		return fecha_realizacion;
	}

	/**
	 * @param valorTotal - El nuevo valor total de la reserva
	 */
	public void setFecha_realizacion(Date fecha_realizacion) {
		this.fecha_realizacion = fecha_realizacion;
	}
	
	/**
	 * @return El cliente propietario de la reserva
	 */
	public long getReserva_colectiva_id() {
		return this.reserva_colectiva_id;
	}

	/**
	 * @param reserva_colectiva_id - El nuevo cliente propietario de la reserva
	 */
	public void setReserva_colectiva_id(long reserva_colectiva_id) {
		this.reserva_colectiva_id = reserva_colectiva_id;
	}
	
	/**
	 * @return El cliente propietario de la reserva
	 */
	@Override
	public long getCliente_id() {
		return cliente_id;
	}

	/**
	 * @param cliente_id - El nuevo cliente propietario de la reserva
	 */
	@Override
	public void setCliente_id(long cliente_id) {
		this.cliente_id = cliente_id;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos de la reserva
	 */
}
