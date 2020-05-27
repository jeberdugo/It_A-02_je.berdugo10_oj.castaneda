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
	public long getReserva_id();

	/**
	 * @param reserva_id - La nueva reserva hecha para la oferta
	 */
	public void setReserva_id(long reserva_id);

	/**
	 * @return El alojamiento al que pertenece la oferta
	 */
	public long getAlojamiento_id();

	/**
	 * @param alojamiento_id - El nuevo alojamiento al que pertenece la oferta
	 */
	public void setAlojamiento_id(long alojamiento_id);

	/**
	 * @return Una cadena de caracteres con todos los atributos de la oferta
	 */
	@Override
	public String toString();

}
