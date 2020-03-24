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
	private String registroCam;

	/**
	 * El registro de la Super Intendencia del alojamiento
	 */
	private String registroSup;

	/**
	 * El operador propetario del alojamiento
	 */
	private Operador operador;

	/**
	 * El seguro del alojamiento
	 */
	private Seguro seguro;

	/**
	 * Los servicios del alojamiento
	 */
	private List<Servicio> servicios;

	/**
	 * Las habitaciones del alojamiento
	 */
	private List<Habitacion> habitaciones;

	/**
	 * Las reglas del alojamiento
	 */
	private List<Regla> reglas;

	/**
	 * El menaje del alojamiento
	 */
	private List<Menaje> menaje;

	/**
	 * Las ofertas del alojamiento
	 */
	private List<Oferta> ofertas;

	/**
	 * La capacidad del alojamiento
	 */
	public Alojamiento() {
		this.id = 0;
		this.capacidad = 0;
		this.ubicacion = "";
		this.descripcion = "";
		this.tipo = 0;
		this.registroCam = "";
		this.registroSup = "";
		this.operador = null;
		this.seguro = null;
		this.servicios = null;
		this.habitaciones = null;
		this.reglas = null;
		this.menaje = null;
		this.ofertas = null;
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
			String registroSup, Operador operador, Seguro seguro, List<Servicio> servicios,
			List<Habitacion> habitaciones, List<Regla> reglas, List<Menaje> menaje, List<Oferta> ofertas) {
		this.id = id;
		this.capacidad = capacidad;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.registroCam = registroCam;
		this.registroSup = registroSup;
		this.operador = operador;
		this.seguro = seguro;
		this.servicios = servicios;
		this.habitaciones = habitaciones;
		this.reglas = reglas;
		this.menaje = menaje;
		this.ofertas = ofertas;
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
	 * @return El registro de camara de comercio del alojamiento
	 */
	public String getRegistroCam() {
		return registroCam;
	}

	/**
	 * @param registroCam - El nuevo registro de camara de comercio del alojamiento
	 */
	public void setRegistroCam(String registroCam) {
		this.registroCam = registroCam;
	}

	/**
	 * @return El registro de la Super Intendencia del alojamiento
	 */
	public String getRegistroSup() {
		return registroSup;
	}

	/**
	 * @param registroSup - El nuevo registro de la Super Intendencia del
	 *                    alojamiento
	 */
	public void setRegistroSup(String registroSup) {
		this.registroSup = registroSup;
	}

	/**
	 * @return El operador propetario del alojamiento
	 */
	public Operador getOperador() {
		return operador;
	}

	/**
	 * @param operador - El nuevo operador propetario del alojamiento
	 */
	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	/**
	 * @return El seguro del alojamiento
	 */
	public Seguro getSeguro() {
		return seguro;
	}

	/**
	 * @param seguro - El nuevo seguro del alojamiento
	 */
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	/**
	 * @return Los servicios del alojamiento
	 */
	public List<Servicio> getServicios() {
		return servicios;
	}

	/**
	 * @param servicios - Los nuevos servicios del alojamiento
	 */
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	/**
	 * @return Las habitaciones del alojamiento
	 */
	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	/**
	 * @param habitaciones - Las nuevas habitaciones del alojamiento
	 */
	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	/**
	 * @return Las reglas del alojamiento
	 */
	public List<Regla> getReglas() {
		return reglas;
	}

	/**
	 * @param reglas - Las nuevas reglas del alojamiento
	 */
	public void setReglas(List<Regla> reglas) {
		this.reglas = reglas;
	}

	/**
	 * @return El menaje del alojamiento
	 */
	public List<Menaje> getMenaje() {
		return menaje;
	}

	/**
	 * @param menaje - El nuevo menaje del alojamiento
	 */
	public void setMenaje(List<Menaje> menaje) {
		this.menaje = menaje;
	}

	/**
	 * @return Las ofertas del alojamiento
	 */
	public List<Oferta> getOfertas() {
		return ofertas;
	}

	/**
	 * @param ofertas - Las nuevas ofertas del alojamiento
	 */
	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del alojamiento
	 */
	public String toString() {
		return "Alojamiento [id=" + id + ", capacidad=" + capacidad + ", ubicacion=" + ubicacion + ", descripcion="
				+ descripcion + ", tipo=" + tipo + ", registroCam=" + registroCam + ", registroSup=" + registroSup
				+ ", operador=" + operador + ", seguro=" + seguro + ", servicios=" + servicios + ", habitaciones="
				+ habitaciones + ", reglas=" + reglas + ", menaje=" + menaje + ", ofertas=" + ofertas + "]";
	}

}
