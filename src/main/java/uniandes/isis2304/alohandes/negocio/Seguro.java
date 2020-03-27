package uniandes.isis2304.alohandes.negocio;

import java.sql.Date;

public class Seguro implements VOSeguro {
	/**
	 * El identificador ÚNICO de los seguros
	 */
	private long id;

	/**
	 * Las caracteristicas del seguro
	 */
	private String caracteristicas;

	/**
	 * El costo del seguro
	 */
	private double costo;

	/**
	 * La vigencia del seguro
	 */
	private Date vigencia;

	/**
	 * El alojamiento al que pertenece el seguro
	 */
	private Alojamiento alojamiento;

	/**
	 * Constructor por defecto
	 */
	public Seguro() {
		this.id = 0;
		this.caracteristicas = "";
		this.costo = 0;
		this.vigencia = null;
		this.alojamiento = null;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id del horario
	 * @param horaInicio      - Las caracteristicas del seguro
	 * @param horaFin - El costo del seguro
	 * @param diasSemana    - La vigencia del seguro
	 * @param servicio - El alojamiento al que pertenece el seguro
	 */
	public Seguro(long id, String caracteristicas, double costo, Date vigencia, Alojamiento alojamiento) {
		this.id = id;
		this.caracteristicas = caracteristicas;
		this.costo = costo;
		this.vigencia = vigencia;
		this.alojamiento = alojamiento;
	}

	/**
	 * @return El id del seguro
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id del seguro
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return  Las caracteristicas del seguro
	 */
	public String getCaracteristicas() {
		return caracteristicas;
	}

	/**
	 * @param caracteristicas -  Las nuevas caracteristicas del seguro
	 */
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	/**
	 * @return El costo del seguro
	 */
	public double getCosto() {
		return costo;
	}

	/**
	 * @param costo - El nuevo costo del seguro
	 */
	public void setCosto(double costo) {
		this.costo = costo;
	}

	/**
	 * @return La vigencia del seguro
	 */
	public Date getVigencia() {
		return vigencia;
	}

	/**
	 * @param vigencia - La nueva vigencia del seguro
	 */
	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}

	/**
	 * @return El alojamiento al que pertenece el seguro
	 */
	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	/**
	 * @param alojamiento - El nuevo alojamiento al que pertenece el seguro
	 */
	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del seguro
	 */
	public String toString() {
		return "Seguro [id=" + id + ", caracteristicas=" + caracteristicas + ", costo=" + costo + ", vigencia="
				+ vigencia + ", alojamiento=" + alojamiento + "]";
	}
}
