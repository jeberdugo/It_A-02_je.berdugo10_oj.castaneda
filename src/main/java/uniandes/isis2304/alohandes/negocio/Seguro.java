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
	private long alojamiento_id;

	/**
	 * Constructor por defecto
	 */
	public Seguro() {
		this.id = 0;
		this.caracteristicas = "";
		this.costo = 0;
		this.vigencia = null;
		this.alojamiento_id = 0;
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
	public Seguro(long id, String caracteristicas, double costo, Date vigencia, long alojamiento_id) {
		this.id = id;
		this.caracteristicas = caracteristicas;
		this.costo = costo;
		this.vigencia = vigencia;
		this.alojamiento_id = alojamiento_id;
	}

	/**
	 * @return El id del seguro
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id del seguro
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return  Las caracteristicas del seguro
	 */
	@Override
	public String getCaracteristicas() {
		return caracteristicas;
	}

	/**
	 * @param caracteristicas -  Las nuevas caracteristicas del seguro
	 */
	@Override
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	/**
	 * @return El costo del seguro
	 */
	@Override
	public double getCosto() {
		return costo;
	}

	/**
	 * @param costo - El nuevo costo del seguro
	 */
	@Override
	public void setCosto(double costo) {
		this.costo = costo;
	}

	/**
	 * @return La vigencia del seguro
	 */
	@Override
	public Date getVigencia() {
		return vigencia;
	}

	/**
	 * @param vigencia - La nueva vigencia del seguro
	 */
	@Override
	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}

	/**
	 * @return El alojamiento al que pertenece el seguro
	 */
	@Override
	public long getAlojamiento_id() {
		return alojamiento_id;
	}

	/**
	 * @param alojamiento - El nuevo alojamiento al que pertenece el seguro
	 */
	@Override
	public void setAlojamiento_id(long alojamiento_id) {
		this.alojamiento_id = alojamiento_id;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del seguro
	 */
	@Override
	public String toString() {
		return "Seguro [id=" + id + ", caracteristicas=" + caracteristicas + ", costo=" + costo + ", vigencia="
				+ vigencia + ", alojamiento_id=" + alojamiento_id + "]";
	}
}
