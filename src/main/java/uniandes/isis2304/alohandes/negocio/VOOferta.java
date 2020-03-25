package uniandes.isis2304.alohandes.negocio;

import java.util.Date;

public interface VOOferta {
	/**
	 * @return El id de la oferta
	 */
	public long getId();

	/**
	 * @param id - El nuevo id de la oferta
	 */
	public void setId(long id);

	/**
	 * @return El precio de la oferta
	 */
	public int getPrecio();

	/**
	 * @param precio - El nuevo precio de la oferta
	 */
	public void setPrecio(int precio);

	/**
	 * @return El dia de la oferta
	 */
	public Date getDia();

	/**
	 * @param dia - El nuevo dia de la oferta
	 */
	public void setDia(Date dia);

	/**
	 * @return La reserva hecha para la oferta
	 */
	public Reserva getReserva_Id();

	/**
	 * @param reserva - La nueva reserva hecha para la oferta
	 */
	public void setReserva_Id(Reserva reserva);

	/**
	 * @return El alojamiento al que pertenece la oferta
	 */
	public Alojamiento getAlojamiento_Id();

	/**
	 * @param alojamiento - El nuevo alojamiento al que pertenece la oferta
	 */
	public void setAlojamiento_Id(Alojamiento alojamiento);

	/**
	 * @return Una cadena de caracteres con todos los atributos de la oferta
	 */
	public String toString();

}
