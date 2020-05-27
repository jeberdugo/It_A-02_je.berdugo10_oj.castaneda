package uniandes.isis2304.alohandes.negocio;

public interface VOAlojamiento {

	/**
	 * @return El id del alojamiento
	 */
	public long getId();

	/**
	 * @param id - El nuevo id del alojamiento
	 */
	public void setId(long id);

	/**
	 * @return La capacidad del alojamiento
	 */
	public int getCapacidad();

	/**
	 * @param capacidad - La nueva capacidad del alojamiento
	 */
	public void setCapacidad(int capacidad);

	/**
	 * @return La ubicacion del alojamiento
	 */
	public String getUbicacion();

	/**
	 * @param ubicacion - La nueva ubicacion del alojamiento
	 */
	public void setUbicacion(String ubicacion);

	/**
	 * @return La descipcion del alojamiento
	 */
	public String getDescripcion();

	/**
	 * @param descripcion - La nueva descipcion del alojamiento
	 */
	public void setDescripcion(String descripcion);

	/**
	 * @return El tipo del alojamiento
	 */
	public int getTipo();

	/**
	 * @param tipo - El nuevo tipo del alojamiento
	 */
	public void setTipo(int tipo);

	/**
	 * @return El registro de camara de comercio del alojamiento
	 */
	public String getRegistro_cam();

	/**
	 * @param registroCam - El nuevo registro de camara de comercio del alojamiento
	 */
	public void setRegistro_cam(String registro_cam);

	/**
	 * @return El registro de la Super Intendencia del alojamiento
	 */
	public String getRegistro_sup();

	/**
	 * @param registroSup - El nuevo registro de la Super Intendencia del
	 *                    alojamiento
	 */
	public void setRegistro_sup(String registro_sup);

	/**
	 * @return El operador propetario del alojamiento
	 */
	public long getOperador_id();

	/**
	 * @param operador - El nuevo operador propetario del alojamiento
	 */
	public void setOperador_id(long operador_id);

	/**
	 * @return Una cadena de caracteres con todos los atributos del alojamiento
	 */
	@Override
	public String toString();

}
