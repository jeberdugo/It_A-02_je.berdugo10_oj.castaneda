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
	 * @return El nombre de usuario del usuario
	 */
	public String getUsuario();
	
	/**
	 * @param usuario - El nuevo nombre de usuario del usuario
	 */
	public void setUsuario(String usuario);
	
	/**
	 * @return El tipo de documento del usuario (0: CC, 1: NIT, 2: CE)
	 */
	public int getTipo_Documento();
	
	/**
	 * @param tipo_Documento - El nuevo tipo de documento del usuario (0: CC, 1: NIT, 2: CE)
	 */
	public void setTipo_Documento(int tipo_Documento);
	
	/**
	 * @return El numero de documento del usuario
	 */
	public int getNumero_Documento();
	
	/**
	 * @param numero_Documento - El nuevo numero de documento del usuario
	 */
	public void setNumero_Documento(int numero_Documento);
	
	/**
	 * @return Una cadena de caracteres con todos los atributos del usuario
	 */
	public String toString();
}
