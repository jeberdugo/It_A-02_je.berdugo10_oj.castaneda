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
	public Date getHoraInicio();

	/**
	 * @param horaInicio - La nueva hora de inicio del horario
	 */
	public void setHoraInicio(Date horaInicio);

	/**
	 * @return La hora de finalizacion del horario
	 */
	public Date getHoraFin();

	/**
	 * @param horaFin - La nueva hora de finalizacion del horario
	 */
	public void setHoraFin(Date horaFin);

	/**
	 * @return Los dias de la semana en donde se efectua el horario
	 */
	public String getDiasSemana();

	/**
	 * @param diasSemana - Los nuevos dias de la semana en donde se efectua el horario
	 */
	public void setDiasSemana(String diasSemana);

	/**
	 * @return El servicio responsable del horario
	 */
	public Servicio getServicio();

	/**
	 * @param servicio - El nuevo servicio responsable del horario
	 */
	public void setServicio(Servicio servicio);

	/**
	 * @return Una cadena de caracteres con todos los atributos del horario
	 */
	public String toString();
}
