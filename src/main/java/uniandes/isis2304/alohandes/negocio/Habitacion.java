package uniandes.isis2304.alohandes.negocio;

public class Habitacion implements VOHabitacion {
	/**
	 * El identificador ÚNICO de las habitaciones
	 */
	private long id;

	/**
	 * La capacidad de la habitacion
	 */
	private int capacidad;

	/**
	 * El tipo de la habitacion
	 */
	private int tipo;

	/**
	 * El alojamiento al que pertenece la habitacion
	 */
	private Alojamiento alojamiento;

	/**
	 * Constructor por defecto
	 */
	public Habitacion() {
		this.id = 0;
		this.capacidad = 0;
		this.tipo = 0;
		this.alojamiento = null;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id de la habitacion
	 * @param capacidad   - La capacidad de la habitacion
	 * @param tipo        - El tipo de la habitacion
	 * @param alojamiento - El alojamiento al que pertenece la habitacion
	 */
	public Habitacion(long id, int capacidad, int tipo, Alojamiento alojamiento) {
		this.id = id;
		this.capacidad = capacidad;
		this.tipo = tipo;
		this.alojamiento = alojamiento;
	}

	/**
	 * @return El id de la habitacion
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id de la habitacion
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return La capacidad de la habitacion
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * @param capacidad - La nueva capacidad de la habitacion
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * @return El tipo de la habitacion
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo - El nuevo tipo de la habitacion
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return El alojamiento al que pertenece la habitacion
	 */
	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	/**
	 * @param alojamiento - El nuevo alojamiento al que pertenece la habitacion
	 */
	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos de la habitacion
	 */
	public String toString() {
		return "Habitacion [id=" + id + ", capacidad=" + capacidad + ", tipo=" + tipo + ", alojamiento=" + alojamiento
				+ "]";
	}
}
