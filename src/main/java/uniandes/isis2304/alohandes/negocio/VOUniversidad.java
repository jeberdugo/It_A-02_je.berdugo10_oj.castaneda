package uniandes.isis2304.alohandes.negocio;

public interface VOUniversidad {
	/**
	 * @return El id de la universidad
	 */
	public long getId();

	/**
	 * @param id - El nuevo id de la universidad
	 */
	public void setId(long id);

	/**
	 * @return El nombre de la universidad
	 */
	public String getNombre();

	/**
	 * @param nombre - El nuevo nombre de la universidad
	 */
	public void setNombre(String nombre);

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la universidad
	 */
	public String toString();
}
