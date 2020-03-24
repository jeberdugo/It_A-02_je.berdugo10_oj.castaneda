package uniandes.isis2304.alohandes.negocio;

public interface VOReserva {
	/**
	 * @return El id de la Reserva
	 */
	public long getId();

	/**
	 * @param id - El nuevo id de la reserva
	 */
	public void setId(long id);
	
	/**
	 * @return El numero de documento del usuario
	 */
	public int getValorTotal();

	/**
	 * @param numeroDocumento - El nuevo numero de documento del usuario
	 */
	public void setValorTotal(int valorTotal);


	/**
	 * @return El nombre de usuario del usuario
	 */
	public int getEstado();

	/**
	 * @param nombreUsuario - El nuevo nombre de usuario del usuario
	 */
	public void setEstado(int estado);

}
