package uniandes.isis2304.alohandes.negocio;

public class Cliente implements VOCliente{
	/**
	 * El identificador ÚNICO de los clientes
	 */
	private long id;

	/**
	 * El nombre del cliente
	 */
	private String nombre;

	/**
	 * La universidad del cliente
	 */
	private long universidad;

	/**
	 * El rol del cliente (0: Profesor, 1: Empleado, 2: Egresado, 3: Estudiante, 4:
	 * Padre)
	 */
	private int rol;

	/**
	 * Constructor por defecto
	 */
	public Cliente() {
		this.id = 0;
		this.nombre = "";
		this.universidad = 0;
		this.rol = 0;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id del cliente
	 * @param nombre      - El nombre del cliente
	 * @param universidad - La universidad del cliente
	 * @param presupuesto - El rol del cliente (0: Profesor, 1: Empleado, 2:
	 *                    Egresado, 3: Estudiante, 4: Padre)
	 */
	public Cliente(long id, String nombre, long universidad, int rol) {
		this.id = id;
		this.nombre = nombre;
		this.universidad = universidad;
		this.rol = rol;
	}

	/**
	 * @return El id del cliente
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id del cliente
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre - El nuevo nombre del cliente
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return La universidad del cliente
	 */
	public long getUniversidad() {
		return universidad;
	}

	/**
	 * @param universidad - La nueva universidad del cliente
	 */
	public void setUniversidad(long universidad) {
		this.universidad = universidad;
	}

	/**
	 * @return El rol del cliente
	 */
	public int getRol() {
		return rol;
	}

	/**
	 * @param rol - El nuevo rol del cliente
	 */
	public void setRol(int rol) {
		this.rol = rol;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del cliente
	 */
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", universidad=" + universidad + ", rol=" + rol + "]";
	}
}