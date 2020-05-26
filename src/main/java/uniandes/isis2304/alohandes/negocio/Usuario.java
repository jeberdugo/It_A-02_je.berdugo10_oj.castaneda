package uniandes.isis2304.alohandes.negocio;

public class Usuario implements VOUsuario {
	/**
	 * El identificador ÚNICO del usuario
	 */
	private long id;

	/**
	 * El nombre de usuario del usuario
	 */
	private String usuario;

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
	private int numero_Documento;

	/**
	 * El tipo de documento del usuario (0: CC, 1: NIT, 2: CE)
	 */
	private int tipo_Documento;

	/**
	 * Constructor por defecto
	 */
	public Usuario() {
		this.id = 0;
		this.setUsuario("");
		this.correo = "";
		this.contrasena = "";
		this.setNumero_Documento(0);
		this.setTipo_Documento(0);
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id              - El id del usuario
	 * @param nombreUsuario   - El nommbre de usuario del usuario
	 * @param correo          - El correo del usuario
	 * @param contrasena      - La contraseña del usuario
	 * @param numeroDocumento - El numero de documento del usuario}
	 * @param tipoDocumento   - El tipo de documento del usuario
	 */
	public Usuario(long id, String nombreUsuario, String correo, String contrasena, int numeroDocumento,
			int tipoDocumento) {
		this.id = id;
		this.setUsuario(nombreUsuario);
		this.correo = correo;
		this.contrasena = contrasena;
		this.setNumero_Documento(numeroDocumento);
		this.setTipo_Documento(tipoDocumento);
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
	 * @return El nombre de usuario del usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario - El nuevo nombre de usuario del usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return El tipo de documento del usuario (0: CC, 1: NIT, 2: CE)
	 */
	public int getTipo_Documento() {
		return tipo_Documento;
	}

	/**
	 * @param tipo_Documento - El nuevo tipo de documento del usuario (0: CC, 1:
	 *                       NIT, 2: CE)
	 */
	public void setTipo_Documento(int tipo_Documento) {
		this.tipo_Documento = tipo_Documento;
	}

	/**
	 * @return El numero de documento del usuario
	 */
	public int getNumero_Documento() {
		return numero_Documento;
	}

	/**
	 * @param numero_Documento - El nuevo numero de documento del usuario
	 */
	public void setNumero_Documento(int numero_Documento) {
		this.numero_Documento = numero_Documento;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del usuario
	 */
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + usuario + ", correo=" + correo 
				+ ", numeroDocumento=" + numero_Documento + ", tipoDocumento=" + tipo_Documento + "]";
	}
}
