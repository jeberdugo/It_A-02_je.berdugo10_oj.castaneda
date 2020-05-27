package uniandes.isis2304.alohandes.negocio;

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
	 * Constructor por defecto
	 */
	public Operador() {
		this.id = 0;
		this.tipo = 0;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id   - El id del operador
	 * @param tipo - El tipo del operador
	 */
	public Operador(long id, int tipo) {
		this.id = id;
		this.tipo = tipo;
	}

	/**
	 * @return El id del operador
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id del operador
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El tipo del operador
	 */
	@Override
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo - El nuevo tipo del operador
	 */
	@Override
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del operador
	 */
	@Override
	public String toString() {
		return "Operador [id=" + id + ", tipo=" + tipo + "]";
	}

}
