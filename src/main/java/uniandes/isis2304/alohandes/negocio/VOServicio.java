package uniandes.isis2304.alohandes.negocio;

public interface VOServicio {
	/**
	 * @return El id del servicio
	 */
	public long getId();

	/**
	 * @param id - El nuevo id del servicio
	 */
	public void setId(long id);

	/**
	 * @return La descripcion del servicio
	 */
	public String getDescripcion();

	/**
	 * @param descripcion - La nueva descripcion del servicio
	 */
	public void setDescripcion(String descripcion);

	/**
	 * @return El costo del servicio
	 */
	public double getCosto();

	/**
	 * @param costo - El nuevo costo del servicio
	 */
	public void setCosto(double costo);

	/**
	 * @return El horario del servicio
	 */
	public long getHorario_id();

	/**
	 * @param horario_id - El nuevo horario del servicio
	 */
	public void setHorario_id(long horario_id);

	/**
	 * @return Una cadena de caracteres con todos los atributos del servicio
	 */
	public String toString();
}
