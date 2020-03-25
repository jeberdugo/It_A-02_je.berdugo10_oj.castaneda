package uniandes.isis2304.alohandes.negocio;

import java.util.List;

public class Alojamiento implements VOAlojamiento {
	/**
	 * El identificador ÚNICO de los alojamientos
	 */
	private long id;

	/**
	 * La capacidad del alojamiento
	 */
	private int capacidad;

	/**
	 * La ubicacion del alojamiento
	 */
	private String ubicacion;

	/**
	 * La descipcion del alojamiento
	 */
	private String descripcion;

	/**
	 * El tipo del alojamiento
	 */
	private int tipo;

	/**
	 * El registro de camara de comercio del alojamiento
	 */
	private String registro_Cam;

	/**
	 * El registro de la Super Intendencia del alojamiento
	 */
	private String registro_Sup;

	/**
	 * El operador propetario del alojamiento
	 */
	private Operador operador_Id;

	

	/**
	 * La capacidad del alojamiento
	 */
	public Alojamiento() {
		this.id = 0;
		this.capacidad = 0;
		this.ubicacion = "";
		this.descripcion = "";
		this.tipo = 0;
		this.setRegistro_Cam("");
		this.setRegistro_Sup("");
		this.setOperador_Id(null);
		
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id           - El id del establecimiento
	 * @param capacidad    - La capacidad del alojamiento
	 * @param ubicacion    - La ubicacion del alojamiento
	 * @param descripcion  - La descipcion del alojamiento
	 * @param tipo         - El tipo del alojamiento
	 * @param registroCam  - El registro de camara de comercio del alojamiento
	 * @param registroSup  - El registro de la Super Intendencia del alojamiento
	 * @param operador     - El operardor propetario del alojamiento
	 * @param seguro       - El seguro del alojamiento
	 * @param servicios    - Los servicios del alojamiento
	 * @param habitaciones - Las habitaciones del alojamiento
	 * @param reglas       - Las reglas del alojamiento
	 * @param menaje       - El menaje del alojamiento
	 * @param ofertas      - Las ofertas del alojamiento
	 */
	public Alojamiento(long id, int capacidad, String ubicacion, String descripcion, int tipo, String registroCam,
			String registroSup, Operador operador) {
		this.id = id;
		this.capacidad = capacidad;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.setRegistro_Cam(registroCam);
		this.setRegistro_Sup(registroSup);
		this.setOperador_Id(operador);
		
	}

	/**
	 * @return El id del alojamiento
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @param id - El nuevo id del alojamiento
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return La capacidad del alojamiento
	 */
	public int getCapacidad() {
		return this.capacidad;
	}

	/**
	 * @param capacidad - La nueva capacidad del alojamiento
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;

	}

	/**
	 * @return La ubicacion del alojamiento
	 */
	public String getUbicacion() {
		return this.ubicacion;
	}

	/**
	 * @param ubicacion - La nueva ubicacion del alojamiento
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return La descipcion del alojamiento
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * @param descripcion - La nueva descipcion del alojamiento
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return El tipo del alojamiento
	 */
	public int getTipo() {
		return this.tipo;
	}

	/**
	 * @param tipo - El nuevo tipo del alojamiento
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	

	/**
	 * @return Una cadena de caracteres con todos los atributos del alojamiento
	 */
	public String toString() {
		return "Alojamiento [id=" + id + ", capacidad=" + capacidad + ", ubicacion=" + ubicacion + ", descripcion="
				+ descripcion + ", tipo=" + tipo + ", registroCam=" + registro_Cam + ", registroSup=" + registro_Sup
				+ ", operador=" + "]";
	}

	public String getRegistro_Cam() {
		return registro_Cam;
	}

	public void setRegistro_Cam(String registro_Cam) {
		this.registro_Cam = registro_Cam;
	}

	public String getRegistro_Sup() {
		return registro_Sup;
	}

	public void setRegistro_Sup(String registro_Sup) {
		this.registro_Sup = registro_Sup;
	}

	public Operador getOperador_Id() {
		return operador_Id;
	}

	public void setOperador_Id(Operador operador_Id) {
		this.operador_Id = operador_Id;
	}

}
