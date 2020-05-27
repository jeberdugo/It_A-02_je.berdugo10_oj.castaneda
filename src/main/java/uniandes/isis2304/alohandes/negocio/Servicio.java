package uniandes.isis2304.alohandes.negocio;

public class Servicio implements VOServicio {
	/**
	 * El identificador ÚNICO de los servicios
	 */
	private long id;

	/**
	 * La descripcion del servicio
	 */
	private String descripcion;

	/**
	 * El costo del servicio
	 */
	private double costo;

	/**
	 * El horario del servicio
	 */
	private long horario_id;

	/**
	 * Constructor por defecto
	 */
	public Servicio() {
		this.id = 0;
		this.descripcion = "";
		this.costo = 0;
		this.horario_id = 0;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id del horario
	 * @param descripcion - La descripcion del servicio
	 * @param costo       - El costo del servicio
	 * @param horario_id  - El horario del servicio
	 */
	public Servicio(long id, String descripcion, double costo, long horario_id) {
		this.id = id;
		this.descripcion = descripcion;
		this.costo = costo;
		this.horario_id = horario_id;
	}

	/**
	 * @return El id del servicio
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id del servicio
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return La descripcion del servicio
	 */
	@Override
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion - La nueva descripcion del servicio
	 */
	@Override
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return El costo del servicio
	 */
	@Override
	public double getCosto() {
		return costo;
	}

	/**
	 * @param costo - El nuevo costo del servicio
	 */
	@Override
	public void setCosto(double costo) {
		this.costo = costo;
	}

	/**
	 * @return El horario del servicio
	 */
	@Override
	public long getHorario_id() {
		return horario_id;
	}

	/**
	 * @param horario_id - El nuevo horario del servicio
	 */
	@Override
	public void setHorario_id(long horario_id) {
		this.horario_id = horario_id;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del servicio
	 */
	@Override
	public String toString() {
		return "Servicio [id=" + id + ", descripcion=" + descripcion + ", costo=" + costo + ", horario_id=" + horario_id
				+ "]";
	}

}
