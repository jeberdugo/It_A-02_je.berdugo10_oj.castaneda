package uniandes.isis2304.alohandes.negocio;

import java.util.List;

public class Operador implements VOOperador {
	/**
	 * El identificador ÚNICO de los operadores
	 */
	private long id;

	/**
	 * El tipo del operador (0: Empresa, 1: Independiente)
	 */
	private int tipo;

	/**
	 * Los alojamientos pertenecientes al operador
	 */
	private List<Alojamiento> alojamientos;

	/**
	 * Constructor por defecto
	 */
	public Operador() {
		this.id = 0;
		this.tipo = 0;
		this.alojamientos = null;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id   - El id del operador
	 * @param tipo - El tipo del operador
	 * @param alojamientos - Los alojamientos pertenecientes al operador
	 */
	public Operador(long id, int tipo, List<Alojamiento> alojamientos) {
		this.id = id;
		this.tipo = tipo;
		this.alojamientos = alojamientos;
	}

	/**
	 * @return El id del operador
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id del operador
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El tipo del operador
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo - El nuevo tipo del operador
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Los alojamientos pertenecientes al operador
	 */
	public List<Alojamiento> getAlojamientos() {
		return alojamientos;
	}
	
	/**
	 * @param alojamientos - Los nuevos alojamientos pertenecientes al operador
	 */
	public void setAlojamientos(List<Alojamiento> alojamientos) {
		this.alojamientos = alojamientos;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del operador
	 */
	public String toString() {
		return "Operador [id=" + id + ", tipo=" + tipo + ", alojamientos=" + alojamientos + "]";
	}

}
