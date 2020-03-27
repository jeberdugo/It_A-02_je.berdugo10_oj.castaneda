package uniandes.isis2304.alohandes.negocio;

import java.util.List;

public interface VOCliente {
	/**
	 * @return El id del cliente
	 */
	public long getId();

	/**
	 * @param id - El nuevo id del cliente
	 */
	public void setId(long id);

	/**
	 * @return El nombre del cliente
	 */
	public String getNombre();

	/**
	 * @param nombre - El nuevo nombre del cliente
	 */
	public void setNombre(String nombre);

	/**
	 * @return El rol del cliente
	 */
	public int getRol();

	/**
	 * @param rol - El nuevo rol del cliente
	 */
	public void setRol(int rol);

	/**
	 * @return Las reservas del cliente
	 */
	public List<Reserva> getReservas();

	/**
	 * @param reservas - Las nuevas reservas del cliente
	 */
	public void setReservas(List<Reserva> reservas);

	/**
	 * @return Una cadena de caracteres con todos los atributos del cliente
	 */
	public String toString();
}
