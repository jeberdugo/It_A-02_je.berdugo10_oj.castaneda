package uniandes.isis2304.alohandes.negocio;

import java.util.Date;

public interface VOOferta {
	
	/**
	 * @return El id de la Oferta
	 */
	public long getId();

	/**
	 * @param id - El nuevo id de la oferta
	 */
	public void setId(long id);
	
	/**
	 * @return El numero de documento del usuario
	 */
	public int getPrecio();

	/**
	 * @param numeroDocumento - El nuevo numero de documento del usuario
	 */
	public void setPrecio(int precio);


	/**
	 * @return El nombre de usuario del usuario
	 */
	public Date getDia();

	/**
	 * @param nombreUsuario - El nuevo nombre de usuario del usuario
	 */
	public void setDia(Date dia);

}
