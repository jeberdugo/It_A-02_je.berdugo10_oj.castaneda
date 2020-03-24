package uniandes.isis2304.alohandes.negocio;

public class Regla implements VORegla{
	/**
	 * El identificador ÚNICO de las reglas
	 */
	private long id;
	
	/**
	 * La descripcion de la regla
	 */
	private String descripcion;
	
	/**
	 * El alojamiento al que pertenece la regla
	 */
	private Alojamiento alojamiento;

	/**
	 * Constructor por defecto
	 */
	public Regla() {
		this.id = 0;
		this.descripcion = "";
		this.alojamiento = null;
	}
	
	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id de la regla
	 * @param descripcion      - La descripcion de la regla
	 * @param alojamiento - El alojamiento al que pertenece la regla
	 */
	public Regla(long id, String descripcion, Alojamiento alojamiento) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.alojamiento = alojamiento;
	}

	/**
	 * @return El id de la regla
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id de la regla
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return  La descripcion de la regla
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion -  La nueva descripcion de la regla
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return El alojamiento al que pertenece la regla
	 */
	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	/**
	 * @param alojamiento - El nuevo alojamiento al que pertenece la regla
	 */
	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos de la regla
	 */
	public String toString() {
		return "Regla [id=" + id + ", descripcion=" + descripcion + ", alojamiento=" + alojamiento + "]";
	}
}
