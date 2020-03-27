package uniandes.isis2304.alohandes.negocio;

public interface VOMenaje {
	/**
	 * @return El id del menaje
	 */
	public long getId();

	/**
	 * @param id - El nuevo id del menaje
	 */
	public void setId(long id);

	/**
	 * @return La descripcion del menaje
	 */
	public String getDescripcion();

	/**
	 * @param descripcion - La nueva descripcion del menaje
	 */
	public void setDescripcion(String descripcion);

	/**
	 * @return La disponibilidad del menaje
	 */
	public boolean isDisponibilidad();

	/**
	 * @param disponibilidad - La nueva disponibilidad del menaje
	 */
	public void setDisponibilidad(boolean disponibilidad);

	/**
	 * @return El alojamiento al que pertenece del menaje
	 */
	public Alojamiento getAlojamiento();

	/**
	 * @param alojamiento - El nuevo alojamiento al que pertenece del menaje
	 */
	public void setAlojamiento(Alojamiento alojamiento);

	/**
	 * @return Una cadena de caracteres con todos los atributos del menaje
	 */
	public String toString();
}
