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
	 * @return La universidad del cliente
	 */
	public long getUniversidad();

	/**
	 * @param universidad - La nueva universidad del cliente
	 */
	public void setUniversidad(long universidad);

	/**
	 * @return El rol del cliente
	 */
	public int getRol();

	/**
	 * @param rol - El nuevo rol del cliente
	 */
	public void setRol(int rol);

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del cliente
	 */
	public String toString();
}
