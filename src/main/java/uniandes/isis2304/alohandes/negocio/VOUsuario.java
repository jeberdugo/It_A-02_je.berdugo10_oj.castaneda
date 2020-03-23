package uniandes.isis2304.alohandes.negocio;

public interface VOUsuario {
	
	/**
	 * @return El id del usuario
	 */
	public long getId();

	/**
	 * @param id - El nuevo id del usuario
	 */
	public void setId(long id);

	/**
	 * @return El nombre de usuario del usuario
	 */
	public String getNombreUsuario();

	/**
	 * @param nombreUsuario - El nuevo nombre de usuario del usuario
	 */
	public void setNombreUsuario(String nombreUsuario);

	/**
	 * @return El correo del usuario
	 */
	public String getCorreo();

	/**
	 * @param correo - El nuevo correo del usuario
	 */
	public void setCorreo(String correo);

	/**
	 * @return La contraseña del usuario
	 */
	public String getContrasena();

	/**
	 * @param contrasena - La nueva contraseña del usuario
	 */
	public void setContrasena(String contrasena);

	/**
	 * @return El numero de documento del usuario
	 */
	public int getNumeroDocumento();

	/**
	 * @param numeroDocumento - El nuevo numero de documento del usuario
	 */
	public void setNumeroDocumento(int numeroDocumento);

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del usuario
	 */
	public String toString();
}
