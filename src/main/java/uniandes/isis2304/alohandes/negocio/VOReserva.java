package uniandes.isis2304.alohandes.negocio;

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
	public double getValor_total();

	/**
	 * @param valorTotal - El nuevo valor total de la reserva
	 */
	public void setValor_total(double valor_total);

	/**
	 * @return El cliente propietario de la reserva
	 */
	public long getCliente_id();

	/**
	 * @param cliente_id - El nuevo cliente propietario de la reserva
	 */
	public void setCliente_id(long cliente_id);

	/**
	 * @return Una cadena de caracteres con todos los atributos de la reserva
	 */
	@Override
	public String toString();
}
