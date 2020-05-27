package uniandes.isis2304.alohandes.negocio;

import java.sql.Date;

public class Horario implements VOHorario {
	/**
	 * El identificador ÚNICO de los horarios
	 */
	private long id;

	/**
	 * La hora de inicio del horario
	 */
	private Date hora_inicio;

	/**
	 * La hora de finalizacion del horario
	 */
	private Date hora_fin;

	/**
	 * Los dias de la semana en donde se efectua el horario
	 */
	private String dias_semana;

	/**
	 * Constructor por defecto
	 */
	public Horario() {
		this.id = 0;
		this.hora_inicio = null;
		this.hora_fin = null;
		this.dias_semana = "";
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id del horario
	 * @param horaInicio  - La hora de inicio del horario
	 * @param horaFin     - La hora de finalizacion del horario
	 * @param diasSemana  - Los dias de la semana en donde se efectua el horario
	 */
	public Horario(long id, Date hora_inicio, Date hora_fin, String dias_semana) {
		this.id = id;
		this.hora_inicio = hora_fin;
		this.hora_fin = hora_fin;
		this.dias_semana = dias_semana;
	}

	/**
	 * @return El id del horario
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id del horario
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return La hora de inicio del horario
	 */
	@Override
	public Date getHora_inicio() {
		return hora_inicio;
	}

	/**
	 * @param hora_inicio - La nueva hora de inicio del horario
	 */
	@Override
	public void setHora_inicio(Date hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	/**
	 * @return La hora de finalizacion del horario
	 */
	@Override
	public Date getHora_fin() {
		return hora_fin;
	}

	/**
	 * @param hora_fin - La nueva hora de finalizacion del horario
	 */
	@Override
	public void setHora_fin(Date hora_fin) {
		this.hora_fin = hora_fin;
	}

	/**
	 * @return Los dias de la semana en donde se efectua el horario
	 */
	@Override
	public String getDias_semana() {
		return dias_semana;
	}

	/**
	 * @param dias_semana - Los nuevos dias de la semana en donde se efectua el
	 *                    horario
	 */
	@Override
	public void setDias_semana(String dias_semana) {
		this.dias_semana = dias_semana;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del horario
	 */
	@Override
	public String toString() {
		return "Horario [id=" + id + ", hora_inicio=" + hora_inicio + ", hora_fin=" + hora_fin + ", dias_semana="
				+ dias_semana + "]";
	}
}
