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
	public String getUsuario();

	/**
	 * @param nombreUsuario - El nuevo nombre de usuario del usuario
	 */
	public void setUsuario(String usuario);

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
	public int getNumero_Documento();

	/**
	 * @param numeroDocumento - El nuevo numero de documento del usuario
	 */
	public void setNumero_Documento(int numero_Documento);

	/**
	 * @return El tipo de documento del usuario
	 */
	public int getTipo_Documento();

	/**
	 * @param tipoDocumento - El nuevo tipo de documento del usuario
	 */
	public void setTipo_Documento(int tipo_Documento);

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del usuario
	 */
	public String toString();
}
