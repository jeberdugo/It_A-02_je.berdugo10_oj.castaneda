package uniandes.isis2304.alohandes.negocio;

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
	 * @return Una cadena de caracteres con todos los atributos del cliente
	 */
	@Override
	public String toString();
}
