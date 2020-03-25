package uniandes.isis2304.alohandes.negocio;

public interface VOHabitacion {
	/**
	 * @return El id de la habitacion
	 */
	public long getId();

	/**
	 * @param id - El nuevo id de la habitacion
	 */
	public void setId(long id);

	/**
	 * @return La capacidad de la habitacion
	 */
	public int getCapacidad();

	/**
	 * @param capacidad - La nueva capacidad de la habitacion
	 */
	public void setCapacidad(int capacidad);

	/**
	 * @return El tipo de la habitacion
	 */
	public int getTipo();

	/**
	 * @param tipo - El nuevo tipo de la habitacion
	 */
	public void setTipo(int tipo);

	/**
	 * @return El alojamiento al que pertenece la habitacion
	 */
	public Alojamiento getAlojamiento();

	/**
	 * @param alojamiento - El nuevo alojamiento al que pertenece la habitacion
	 */
	public void setAlojamiento(Alojamiento alojamiento);

	/**
	 * @return Una cadena de caracteres con todos los atributos de la habitacion
	 */
	public String toString();
}