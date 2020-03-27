package uniandes.isis2304.alohandes.negocio;

public interface VORegla {
	/**
	 * @return El id de la regla
	 */
	public long getId();

	/**
	 * @param id - El nuevo id de la regla
	 */
	public void setId(long id);

	/**
	 * @return  La descripcion de la regla
	 */
	public String getDescripcion();

	/**
	 * @param descripcion -  La nueva descripcion de la regla
	 */
	public void setDescripcion(String descripcion);

	/**
	 * @return El alojamiento al que pertenece la regla
	 */
	public long getAlojamiento_id();

	/**
	 * @param alojamiento_id - El nuevo alojamiento al que pertenece la regla
	 */
	public void setAlojamiento_id(long alojamiento_id);

	/**
	 * @return Una cadena de caracteres con todos los atributos de la regla
	 */
	public String toString();
}
