package uniandes.isis2304.alohandes.negocio;

import java.util.List;

public interface VOAlojamiento {

	/**
	 * @return El id del alojamiento
	 */
	public long getId();

	/**
	 * @param id - El nuevo id del alojamiento
	 */
	public void setId(long id);

	/**
	 * @return La capacidad del alojamiento
	 */
	public int getCapacidad();

	/**
	 * @param capacidad - La nueva capacidad del alojamiento
	 */
	public void setCapacidad(int capacidad);

	/**
	 * @return La ubicacion del alojamiento
	 */
	public String getUbicacion();

	/**
	 * @param ubicacion - La nueva ubicacion del alojamiento
	 */
	public void setUbicacion(String ubicacion);

	/**
	 * @return La descipcion del alojamiento
	 */
	public String getDescripcion();

	/**
	 * @param descripcion - La nueva descipcion del alojamiento
	 */
	public void setDescripcion(String descripcion);

	/**
	 * @return El tipo del alojamiento
	 */
	public int getTipo();

	/**
	 * @param tipo - El nuevo tipo del alojamiento
	 */
	public void setTipo(int tipo);

	/**
	 * @return El registro de camara de comercio del alojamiento
	 */
	public String getRegistroCam();

	/**
	 * @param registroCam - El nuevo registro de camara de comercio del alojamiento
	 */
	public void setRegistroCam(String registroCam);

	/**
	 * @return El registro de la Super Intendencia del alojamiento
	 */
	public String getRegistroSup();

	/**
	 * @param registroSup - El nuevo registro de la Super Intendencia del
	 *                    alojamiento
	 */
	public void setRegistroSup(String registroSup);

	/**
	 * @return El operador propetario del alojamiento
	 */
	public Operador getOperador();

	/**
	 * @param operador - El nuevo operador propetario del alojamiento
	 */
	public void setOperador(Operador operador);

	/**
	 * @return El seguro del alojamiento
	 */
	public Seguro getSeguro();

	/**
	 * @param seguro - El nuevo seguro del alojamiento
	 */
	public void setSeguro(Seguro seguro);

	/**
	 * @return Los servicios del alojamiento
	 */
	public List<Servicio> getServicios();

	/**
	 * @param servicios - Los nuevos servicios del alojamiento
	 */
	public void setServicios(List<Servicio> servicios);

	/**
	 * @return Las habitaciones del alojamiento
	 */
	public List<Habitacion> getHabitaciones();

	/**
	 * @param habitaciones - Las nuevas habitaciones del alojamiento
	 */
	public void setHabitaciones(List<Habitacion> habitaciones);

	/**
	 * @return Las reglas del alojamiento
	 */
	public List<Regla> getReglas();

	/**
	 * @param reglas - Las nuevas reglas del alojamiento
	 */
	public void setReglas(List<Regla> reglas);

	/**
	 * @return El menaje del alojamiento
	 */
	public List<Menaje> getMenaje();

	/**
	 * @param menaje - El nuevo menaje del alojamiento
	 */
	public void setMenaje(List<Menaje> menaje);

	/**
	 * @return Las ofertas del alojamiento
	 */
	public List<Oferta> getOfertas();

	/**
	 * @param ofertas - Las nuevas ofertas del alojamiento
	 */
	public void setOfertas(List<Oferta> ofertas);

	/**
	 * @return Una cadena de caracteres con todos los atributos del alojamiento
	 */
	public String toString();

}
