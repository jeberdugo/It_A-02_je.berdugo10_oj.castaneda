package uniandes.isis2304.alohandes.negocio;

public class Menaje implements VOMenaje {
	/**
	 * El identificador ÚNICO del menaje
	 */
	private long id;

	/**
	 * La descripcion del menaje
	 */
	private String descripcion;

	/**
	 * La disponibilidad del menaje
	 */
	private boolean disponibilidad;

	/**
	 * El alojamiento al que pertenece del menaje
	 */
	private Alojamiento alojamiento;

	/**
	 * Constructor por defecto
	 */
	public Menaje() {
		this.id = 0;
		this.descripcion = "";
		this.disponibilidad = false;
		this.alojamiento = null;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id             - El id del menaje
	 * @param descripcion    - La descripcion del menaje
	 * @param disponibilidad - La disponibilidad del menaje
	 * @param alojamiento    - El alojamiento al que pertenece del menaje
	 */
	public Menaje(long id, String descripcion, boolean disponibilidad, Alojamiento alojamiento) {
		this.id = id;
		this.descripcion = descripcion;
		this.disponibilidad = disponibilidad;
		this.alojamiento = alojamiento;
	}

	/**
	 * @return El id del menaje
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id del menaje
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return La descripcion del menaje
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion - La nueva descripcion del menaje
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return La disponibilidad del menaje
	 */
	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	/**
	 * @param disponibilidad - La nueva disponibilidad del menaje
	 */
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	/**
	 * @return El alojamiento al que pertenece del menaje
	 */
	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	/**
	 * @param alojamiento - El nuevo alojamiento al que pertenece del menaje
	 */
	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del menaje
	 */
	public String toString() {
		return "Menaje [id=" + id + ", descripcion=" + descripcion + ", disponibilidad=" + disponibilidad
				+ ", alojamiento=" + alojamiento + "]";
	}

}
