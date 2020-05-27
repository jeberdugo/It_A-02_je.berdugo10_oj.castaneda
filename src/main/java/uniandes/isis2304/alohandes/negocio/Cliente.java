package uniandes.isis2304.alohandes.negocio;

public class Cliente implements VOCliente {
	/**
	 * El identificador ÚNICO de los clientes
	 */
	private long id;

	/**
	 * El nombre del cliente
	 */
	private String nombre;

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
		this.rol = 0;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id del cliente
	 * @param nombre      - El nombre del cliente
	 * @param presupuesto - El rol del cliente (0: Profesor, 1: Empleado, 2:
	 *                    Egresado, 3: Estudiante, 4: Padre)
	 */
	public Cliente(long id, String nombre, int rol) {
		this.id = id;
		this.nombre = nombre;
		this.rol = rol;
	}

	/**
	 * @return El id del cliente
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id del cliente
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El nombre del cliente
	 */
	@Override
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre - El nuevo nombre del cliente
	 */
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return El rol del cliente
	 */
	@Override
	public int getRol() {
		return rol;
	}

	/**
	 * @param rol - El nuevo rol del cliente
	 */
	@Override
	public void setRol(int rol) {
		this.rol = rol;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del cliente
	 */
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", rol=" + rol + "]";
	}

}
