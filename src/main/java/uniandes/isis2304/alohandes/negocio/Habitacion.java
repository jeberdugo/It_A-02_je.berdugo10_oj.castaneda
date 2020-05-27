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
	private long alojamiento_id;

	/**
	 * Constructor por defecto
	 */
	public Habitacion() {
		this.id = 0;
		this.capacidad = 0;
		this.tipo = 0;
		this.alojamiento_id = 0;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id de la habitacion
	 * @param capacidad   - La capacidad de la habitacion
	 * @param tipo        - El tipo de la habitacion
	 * @param alojamiento_id - El alojamiento al que pertenece la habitacion
	 */
	public Habitacion(long id, int capacidad, int tipo, long alojamiento_id) {
		this.id = id;
		this.capacidad = capacidad;
		this.tipo = tipo;
		this.alojamiento_id = alojamiento_id;
	}

	/**
	 * @return El id de la habitacion
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id de la habitacion
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return La capacidad de la habitacion
	 */
	@Override
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * @param capacidad - La nueva capacidad de la habitacion
	 */
	@Override
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * @return El tipo de la habitacion
	 */
	@Override
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo - El nuevo tipo de la habitacion
	 */
	@Override
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return El alojamiento al que pertenece la habitacion
	 */
	@Override
	public long getAlojamiento_id() {
		return alojamiento_id;
	}

	/**
	 * @param alojamiento_id - El nuevo alojamiento al que pertenece la habitacion
	 */
	@Override
	public void setAlojamiento_id(long alojamiento_id) {
		this.alojamiento_id = alojamiento_id;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos de la habitacion
	 */
	@Override
	public String toString() {
		return "Habitacion [id=" + id + ", capacidad=" + capacidad + ", tipo=" + tipo + ", alojamiento_id=" + alojamiento_id
				+ "]";
	}
}
