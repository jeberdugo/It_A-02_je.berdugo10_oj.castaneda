package uniandes.isis2304.alohandes.negocio;

public interface VOAlojamiento {

	/**
	 * @return El id del usuario
	 */
	public long getId();

	/**
	 * @param id - El nuevo id del usuario
	 */
	public void setId(long id);
	
	/**
	 * @return El numero de documento del usuario
	 */
	public int getCapacidad();

	/**
	 * @param numeroDocumento - El nuevo numero de documento del usuario
	 */
	public void setCapacidad(int capacidad);


	/**
	 * @return El nombre de usuario del usuario
	 */
	public String getUbicacion();

	/**
	 * @param nombreUsuario - El nuevo nombre de usuario del usuario
	 */
	public void setUbicacion(String ubicacion);

	/**
	 * @return El correo del usuario
	 */
	public String getDescripcion();

	/**
	 * @param correo - El nuevo correo del usuario
	 */
	public void setDescripcion(String descripcion);

	/**
	 * @return La contraseña del usuario
	 */
	public int getTipo();

	/**
	 * @param contrasena - La nueva contraseña del usuario
	 */
	public void setTipo(int tipo);

	/**
	 * @return El numero de documento del usuario
	 */
	public long getRegistroCamara();

	/**
	 * @param numeroDocumento - El nuevo numero de documento del usuario
	 */
	public void setRegistroCamara(long registroCamara);
	
	/**
	 * @return El numero de documento del usuario
	 */
	public long getRegistroSuperI();

	/**
	 * @param numeroDocumento - El nuevo numero de documento del usuario
	 */
	public void setRegistroSuperI(long registroSuperI);


	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del usuario
	 */
	public String toString();

}
