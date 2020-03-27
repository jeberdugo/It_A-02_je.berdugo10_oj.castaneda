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
	private Reserva reserva_Id;

	/**
	 * El alojamiento al que pertenece la oferta
	 */
	private Alojamiento alojamiento_Id;

	/**
	 * Constructor por defecto
	 */
	public Oferta() {
		this.id = 0;
		this.dia = null;
		this.precio = 0;
		this.setReserva_Id(null);
		this.setAlojamiento_Id(null);
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id de la oferta
	 * @param dia         - El dia de la oferta
	 * @param precio      - El precio de la oferta
	 * @param reserva     - La reserva hecha para la oferta
	 * @param alojamiento - El alojamiento al que pertenece la oferta
	 */
	public Oferta(long id, Date dia, int precio, Reserva reserva, Alojamiento alojamiento) {
		super();
		this.id = id;
		this.dia = dia;
		this.precio = precio;
		this.setReserva_Id(reserva);
		this.setAlojamiento_Id(alojamiento);
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

	

	/**
	 * @return Una cadena de caracteres con todos los atributos de la oferta
	 */
	public String toString() {
		return "Oferta [id=" + id + ", dia=" + dia + ", precio=" + precio + ", reserva=" + 
				  "]";
	}

	public Reserva getReserva_Id() {
		return reserva_Id;
	}

	public void setReserva_Id(Reserva reserva_Id) {
		this.reserva_Id = reserva_Id;
	}

	public Alojamiento getAlojamiento_Id() {
		return alojamiento_Id;
	}

	public void setAlojamiento_Id(Alojamiento alojamiento_Id) {
		this.alojamiento_Id = alojamiento_Id;
	}

}
