package uniandes.isis2304.alohandes.negocio;

public class Usuario implements VOUsuario{
	/**
	 * El identificador ÚNICO del usuario
	 */
	private long id;

	/**
	 * El nombre de usuario del usuario
	 */
	private String nombreUsuario;

	/**
	 * El correo del usuario
	 */
	private String correo;

	/**
	 * La contraseña del usuario
	 */
	private String contrasena;

	/**
	 * El numero de documento del usuario
	 */
	private int numeroDocumento;

	/**
	 * Constructor por defecto
	 */
	public Usuario() {
		this.id = 0;
		this.nombreUsuario = "";
		this.correo = "";
		this.contrasena = "";
		this.numeroDocumento = 0;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id              - El id del usuario
	 * @param nombreUsuario   - El nommbre de usuario del usuario
	 * @param correo          - El correo del usuario
	 * @param contrasena      - La contraseña del usuario
	 * @param numeroDocumento - El numero de documento del usuario
	 */
	public Usuario(long id, String nombreUsuario, String correo, String contrasena, int numeroDocumento) {
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.correo = correo;
		this.contrasena = contrasena;
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return El id del usuario
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id del usuario
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El nombre de usuario del usuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param nombreUsuario - El nuevo nombre de usuario del usuario
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * @return El correo del usuario
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo - El nuevo correo del usuario
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return La contraseña del usuario
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena - La nueva contraseña del usuario
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * @return El numero de documento del usuario
	 */
	public int getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento - El nuevo numero de documento del usuario
	 */
	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del usuario
	 */
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", correo=" + correo + ", contrasena="
				+ contrasena + ", numeroDocumento=" + numeroDocumento + "]";
	}
}
