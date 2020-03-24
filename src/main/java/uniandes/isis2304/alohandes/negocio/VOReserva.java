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
	public boolean isEstado();

	/**
	 * @param estado - El nuevo estado de la reserva
	 */
	public void setEstado(boolean estado);

	/**
	 * @return El valor total de la reserva
	 */
	public int getValorTotal();

	/**
	 * @param valorTotal - El nuevo valor total de la reserva
	 */
	public void setValorTotal(int valorTotal);

	/**
	 * @return El cliente propietario de la reserva
	 */
	public Cliente getCliente();

	/**
	 * @param cliente - El nuevo cliente propietario de la reserva
	 */
	public void setCliente(Cliente cliente);
	
	/**
	 * @return Las ofertas que componen la reserva
	 */
	public List<Oferta> getOfertas();
	
	/**
	 * @param ofertas - Las nuevas ofertas que componen la reserva
	 */
	public void setOfertas(List<Oferta> ofertas);

	/**
	 * @return Una cadena de caracteres con todos los atributos de la reserva
	 */
	public String toString();
}
