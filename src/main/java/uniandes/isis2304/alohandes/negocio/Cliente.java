package uniandes.isis2304.alohandes.negocio;

import java.util.List;

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
	 * Las reservas del cliente
	 */
	private List<Reserva> reservas;

	/**
	 * Constructor por defecto
	 */
	public Cliente() {
		this.id = 0;
		this.nombre = "";
		this.rol = 0;
		this.reservas = null;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id del cliente
	 * @param nombre      - El nombre del cliente
	 * @param presupuesto - El rol del cliente (0: Profesor, 1: Empleado, 2:
	 *                    Egresado, 3: Estudiante, 4: Padre)
	 * @param reservas    - Las reservas del cliente
	 */
	public Cliente(long id, String nombre, int rol, List<Reserva> reservas) {
		this.id = id;
		this.nombre = nombre;
		this.rol = rol;
		this.reservas = reservas;
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

	/**
	 * @return Las reservas del cliente
	 */
	public List<Reserva> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas - Las nuevas reservas del cliente
	 */
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del cliente
	 */
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", rol=" + rol + ", reservas=" + reservas + "]";
	}

}
