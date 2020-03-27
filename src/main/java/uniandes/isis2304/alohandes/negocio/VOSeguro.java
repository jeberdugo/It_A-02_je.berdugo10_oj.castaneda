package uniandes.isis2304.alohandes.negocio;

import java.sql.Date;

public interface VOSeguro {
	/**
	 * @return El id del seguro
	 */
	public long getId();
	
	/**
	 * @param id - El nuevo id del seguro
	 */
	public void setId(long id);

	/**
	 * @return  Las caracteristicas del seguro
	 */
	public String getCaracteristicas();

	/**
	 * @param caracteristicas -  Las nuevas caracteristicas del seguro
	 */
	public void setCaracteristicas(String caracteristicas);

	/**
	 * @return El costo del seguro
	 */
	public double getCosto();

	/**
	 * @param costo - El nuevo costo del seguro
	 */
	public void setCosto(double costo);

	/**
	 * @return La vigencia del seguro
	 */
	public Date getVigencia();

	/**
	 * @param vigencia - La nueva vigencia del seguro
	 */
	public void setVigencia(Date vigencia);

	/**
	 * @return El alojamiento al que pertenece el seguro
	 */
	public Alojamiento getAlojamiento();

	/**
	 * @param alojamiento - El nuevo alojamiento al que pertenece el seguro
	 */
	public void setAlojamiento(Alojamiento alojamiento);

	/**
	 * @return Una cadena de caracteres con todos los atributos del seguro
	 */
	public String toString();
}
