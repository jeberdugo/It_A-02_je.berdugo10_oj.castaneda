package uniandes.isis2304.alohandes.negocio;

import java.sql.Date;

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
	private String registro_cam;

	/**
	 * El registro de la Super Intendencia del alojamiento
	 */
	private String registro_sup;
	
	private Date ultima_reserva;
	
	private int habilitado;

	/**
	 * El operador propetario del alojamiento
	 */
	private long operador_id;

	/**
	 * La capacidad del alojamiento
	 */
	public Alojamiento() {
		this.id = 0;
		this.capacidad = 0;
		this.ubicacion = "";
		this.descripcion = "";
		this.tipo = 0;
		this.registro_cam = "";
		this.registro_sup = "";
		this.habilitado = 1;
		this.ultima_reserva = null;
		this.operador_id = 0;

	}

	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id del establecimiento
	 * @param capacidad   - La capacidad del alojamiento
	 * @param ubicacion   - La ubicacion del alojamiento
	 * @param descripcion - La descipcion del alojamiento
	 * @param tipo        - El tipo del alojamiento
	 * @param registroCam - El registro de camara de comercio del alojamiento
	 * @param registroSup - El registro de la Super Intendencia del alojamiento
	 */
	public Alojamiento(long id, int capacidad, String ubicacion, String descripcion, int tipo, String registro_cam,
			String registro_sup, int habilitado, Date ultima_reserva, long operador_id) {
		this.id = id;
		this.capacidad = capacidad;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.registro_cam = registro_cam;
		this.registro_sup = registro_sup;
		this.ultima_reserva = ultima_reserva;
		this.habilitado = habilitado;
		this.operador_id = operador_id;

	}

	/**
	 * @return El id del alojamiento
	 */
	@Override
	public long getId() {
		return this.id;
	}

	/**
	 * @param id - El nuevo id del alojamiento
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return La capacidad del alojamiento
	 */
	@Override
	public int getCapacidad() {
		return this.capacidad;
	}

	/**
	 * @param capacidad - La nueva capacidad del alojamiento
	 */
	@Override
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;

	}

	/**
	 * @return La ubicacion del alojamiento
	 */
	@Override
	public String getUbicacion() {
		return this.ubicacion;
	}

	/**
	 * @param ubicacion - La nueva ubicacion del alojamiento
	 */
	@Override
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return La descipcion del alojamiento
	 */
	@Override
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * @param descripcion - La nueva descipcion del alojamiento
	 */
	@Override
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return El tipo del alojamiento
	 */
	@Override
	public int getTipo() {
		return this.tipo;
	}

	/**
	 * @param tipo - El nuevo tipo del alojamiento
	 */
	@Override
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String getRegistro_cam() {
		return registro_cam;
	}

	@Override
	public void setRegistro_cam(String registro_cam) {
		this.registro_cam = registro_cam;
	}

	@Override
	public String getRegistro_sup() {
		return registro_sup;
	}

	@Override
	public void setRegistro_sup(String registro_sup) {
		this.registro_sup = registro_sup;
	}
	
	public int getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(int habilitado) {
		this.habilitado = habilitado;
	}
	
	public Date getUltima_reserva() {
		return ultima_reserva;
	}

	public void setUltima_reserva(Date ultima_reserva) {
		this.ultima_reserva = ultima_reserva;
	}

	@Override
	public long getOperador_id() {
		return operador_id;
	}

	@Override
	public void setOperador_id(long operador_id) {
		this.operador_id = operador_id;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del alojamiento
	 */
	@Override
	public String toString() {
		return "Alojamiento [id=" + id + ", capacidad=" + capacidad + ", ubicacion=" + ubicacion + ", descripcion="
				+ descripcion + ", tipo=" + tipo + ", registro_cam=" + registro_cam + ", registro_sup=" + registro_sup
				+ ", habilitado=" + habilitado + ", ultima_reserva=" + ultima_reserva +", operador_id=" + operador_id + "]";
	}
	
}
