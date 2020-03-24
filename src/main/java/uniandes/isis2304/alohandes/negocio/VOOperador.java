package uniandes.isis2304.alohandes.negocio;

import java.util.List;

public interface VOOperador {
	/**
	 * @return El id del operador
	 */
	public long getId();

	/**
	 * @param id - El nuevo id del operador
	 */
	public void setId(long id);

	/**
	 * @return El tipo del operador
	 */
	public int getTipo();

	/**
	 * @param tipo - El nuevo tipo del operador
	 */
	public void setTipo(int tipo);

	/**
	 * @return Los alojamientos pertenecientes al operador
	 */
	public List<Alojamiento> getAlojamientos();
	
	/**
	 * @param alojamientos - Los nuevos alojamientos pertenecientes al operador
	 */
	public void setAlojamientos(List<Alojamiento> alojamientos);

	/**
	 * @return Una cadena de caracteres con todos los atributos del operador
	 */
	public String toString();
}
