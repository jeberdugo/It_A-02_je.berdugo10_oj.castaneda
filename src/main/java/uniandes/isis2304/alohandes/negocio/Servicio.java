package uniandes.isis2304.alohandes.negocio;

import java.util.List;

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
	private Horario horario;
	
	/**
	 * Los alojamientos en los que se ofrece el servicio
	 */
	private List<Alojamiento> alojamientos;

	/**
	 * Constructor por defecto
	 */
	public Servicio() {
		this.id = 0;
		this.descripcion = "";
		this.costo = 0;
		this.horario = null;
		this.alojamientos=null;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id del horario
	 * @param descripcion      - La descripcion del servicio
	 * @param costo - El costo del servicio
	 * @param horario    - El horario del servicio
	 * @param alojamientos - Los alojamientos en los que se ofrece el servicio
	 */
	public Servicio(long id, String descripcion, double costo, Horario horario, List<Alojamiento>alojamientos) {
		this.id = id;
		this.descripcion = descripcion;
		this.costo = costo;
		this.horario = horario;
		this.alojamientos=alojamientos;
	}

	/**
	 * @return El id del servicio
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id del servicio
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return La descripcion del servicio
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion - La nueva descripcion del servicio
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return El costo del servicio
	 */
	public double getCosto() {
		return costo;
	}

	/**
	 * @param costo - El nuevo costo del servicio
	 */
	public void setCosto(double costo) {
		this.costo = costo;
	}

	/**
	 * @return El horario del servicio
	 */
	public Horario getHorario() {
		return horario;
	}

	/**
	 * @param horario - El nuevo horario del servicio
	 */
	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	/**
	 * @return Los alojamientos en los que se ofrece el servicio
	 */
	public List<Alojamiento> getAlojamientos() {
		return alojamientos;
	}

	/**
	 * @param alojamientos - Los nuevos alojamientos en los que se ofrece el servicio
	 */
	public void setAlojamientos(List<Alojamiento> alojamientos) {
		this.alojamientos = alojamientos;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del servicio
	 */
	public String toString() {
		return "Servicio [id=" + id + ", descripcion=" + descripcion + ", costo=" + costo + ", horario=" + horario
				+ ", alojamientos=" + alojamientos + "]";
	}

}
