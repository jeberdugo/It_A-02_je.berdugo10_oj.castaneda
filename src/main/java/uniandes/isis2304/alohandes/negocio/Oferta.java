package uniandes.isis2304.alohandes.negocio;

import java.util.Date;

public class Oferta implements VOOferta {
	/**
	 * El identificador ÚNICO de los horarios
	 */
	private long id;

	/**
	 * El dia de la oferta
	 */
	private Date dia;

	/**
	 * El precio de la oferta
	 */
	private int precio;

	/**
	 * La reserva hecha para la oferta
	 */
	private long reserva_id;

	/**
	 * El alojamiento al que pertenece la oferta
	 */
	private long alojamiento_id;

	/**
	 * Constructor por defecto
	 */
	public Oferta() {
		this.id = 0;
		this.dia = null;
		this.precio = 0;
		this.reserva_id = 0;
		this.alojamiento_id = 0;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id de la oferta
	 * @param dia         - El dia de la oferta
	 * @param precio      - El precio de la oferta
	 * @param reserva_id     - La reserva hecha para la oferta
	 * @param alojamiento_id - El alojamiento al que pertenece la oferta
	 */
	public Oferta(long id, Date dia, int precio, long reserva_id, long alojamiento_id) {
		super();
		this.id = id;
		this.dia = dia;
		this.precio = precio;
		this.reserva_id = reserva_id;
		this.alojamiento_id = alojamiento_id;
	}

	/**
	 * @return El id de la oferta
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @param id - El nuevo id de la oferta
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El precio de la oferta
	 */
	public int getPrecio() {
		return this.precio;
	}

	/**
	 * @param precio - El nuevo precio de la oferta
	 */
	public void setPrecio(int precio) {
		this.precio = precio;
	}

	/**
	 * @return El dia de la oferta
	 */
	public Date getDia() {
		return this.dia;
	}

	/**
	 * @param dia - El nuevo dia de la oferta
	 */
	public void setDia(Date dia) {
		this.dia = dia;

	}

	public long getReserva_id() {
		return reserva_id;
	}

	public void setReserva_id(long reserva_id) {
		this.reserva_id = reserva_id;
	}

	public long getAlojamiento_id() {
		return alojamiento_id;
	}

	public void setAlojamiento_id(long alojamiento_id) {
		this.alojamiento_id = alojamiento_id;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos de la oferta
	 */
	public String toString() {
		return "Oferta [id=" + id + ", dia=" + dia + ", precio=" + precio + ", reserva_id=" + reserva_id
				+ ", alojamiento_id=" + alojamiento_id + "]";
	}

}
