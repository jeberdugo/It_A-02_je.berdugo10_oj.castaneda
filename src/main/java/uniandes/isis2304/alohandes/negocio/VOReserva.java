package uniandes.isis2304.alohandes.negocio;

import java.util.List;

public interface VOReserva {
	/**
	 * @return El id del horario
	 */
	public long getId();

	/**
	 * @param id - El nuevo id de la reserva
	 */
	public void setId(long id);

	/**
	 * @return El estado de la reserva
	 */
	public int getEstado();

	/**
	 * @param estado - El nuevo estado de la reserva
	 */
	public void setEstado(int estado);

	/**
	 * @return El valor total de la reserva
	 */
	public int getValor_Total();

	/**
	 * @param valorTotal - El nuevo valor total de la reserva
	 */
	public void setValor_Total(int valorTotal);

	/**
	 * @return El cliente propietario de la reserva
	 */
	public Cliente getCliente_Id();

	/**
	 * @param cliente - El nuevo cliente propietario de la reserva
	 */
	public void setCliente_Id(Cliente cliente);
	
	/**
	 * @return Las ofertas que componen la reserva
	 */
	

	/**
	 * @return Una cadena de caracteres con todos los atributos de la reserva
	 */
	public String toString();
}
