package uniandes.isis2304.alohandes.negocio;

public class Universidad implements VOUniversidad{
	/**
	 * El identificador ÚNICO de las universidades
	 */
	private long id;

	/**
	 * El nombre de la universidad
	 */
	private String nombre;

	/**
	 * Constructor por defecto
	 */
	public Universidad() {
		this.id = 0;
		this.nombre = "";
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id     - El id de la universidad
	 * @param nombre - El nommbre de la universidad
	 */
	public Universidad(long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * @return El id de la universidad
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id de la universidad
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El nombre de la universidad
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre - El nuevo nombre de la universidad
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la universidad
	 */
	public String toString() {
		return "Universidad [id=" + id + ", nombre=" + nombre + "]";
	}
}
