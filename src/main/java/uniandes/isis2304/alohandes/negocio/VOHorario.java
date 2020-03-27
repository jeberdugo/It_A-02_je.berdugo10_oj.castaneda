package uniandes.isis2304.alohandes.negocio;

import java.sql.Date;

public interface VOHorario {
	/**
	 * @return El id del horario
	 */
	public long getId();

	/**
	 * @param id - El nuevo id del horario
	 */
	public void setId(long id);

	/**
	 * @return La hora de inicio del horario
	 */
	public Date getHora_inicio();

	/**
	 * @param hora_inicio - La nueva hora de inicio del horario
	 */
	public void setHora_inicio(Date hora_inicio);

	/**
	 * @return La hora de finalizacion del horario
	 */
	public Date getHora_fin();

	/**
	 * @param hora_fin - La nueva hora de finalizacion del horario
	 */
	public void setHora_fin(Date hora_fin);

	/**
	 * @return Los dias de la semana en donde se efectua el horario
	 */
	public String getDias_semana();

	/**
	 * @param dias_semana - Los nuevos dias de la semana en donde se efectua el
	 *                    horario
	 */
	public void setDias_semana(String dias_semana);

	/**
	 * @return El servicio responsable del horario
	 */
	public long getServicio_id();

	/**
	 * @param servicio_id - El nuevo servicio responsable del horario
	 */
	public void setServicio_id(long servicio_id);

	/**
	 * @return Una cadena de caracteres con todos los atributos del horario
	 */
	public String toString();
}
