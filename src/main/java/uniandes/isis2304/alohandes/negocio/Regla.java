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
	private long alojamiento_id;

	/**
	 * Constructor por defecto
	 */
	public Regla() {
		this.id = 0;
		this.descripcion = "";
		this.alojamiento_id = 0;
	}
	
	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id de la regla
	 * @param descripcion      - La descripcion de la regla
	 * @param alojamiento_id - El alojamiento al que pertenece la regla
	 */
	public Regla(long id, String descripcion, long alojamiento_id) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.alojamiento_id = alojamiento_id;
	}

	/**
	 * @return El id de la regla
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id de la regla
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return  La descripcion de la regla
	 */
	@Override
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion -  La nueva descripcion de la regla
	 */
	@Override
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return El alojamiento al que pertenece la regla
	 */
	@Override
	public long getAlojamiento_id() {
		return alojamiento_id;
	}

	/**
	 * @param alojamiento_id - El nuevo alojamiento al que pertenece la regla
	 */
	@Override
	public void setAlojamiento_id(long alojamiento_id) {
		this.alojamiento_id = alojamiento_id;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos de la regla
	 */
	@Override
	public String toString() {
		return "Regla [id=" + id + ", descripcion=" + descripcion + ", alojamiento_id=" + alojamiento_id + "]";
	}
}
