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
	private Date horaInicio;
	
	/**
	 * La hora de finalizacion del horario
	 */
	private Date horaFin;
	
	/**
	 * Los dias de la semana en donde se efectua el horario
	 */
	private String diasSemana;
	/**
	 * El servicio responsable del horario
	 */
	private Servicio servicio;

	/**
	 * Constructor por defecto
	 */
	public Horario() {
		this.id = 0;
		this.horaInicio = null;
		this.horaFin = null;
		this.diasSemana = "";
		this.servicio = null;
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id del horario
	 * @param horaInicio      - La hora de inicio del horario
	 * @param horaFin - La hora de finalizacion del horario
	 * @param diasSemana    - Los dias de la semana en donde se efectua el horario
	 * @param servicio - El servicio responsable del horario
	 */
	public Horario(long id, Date horaInicio, Date horaFin, String diasSemana, Servicio servicio) {
		super();
		this.id = id;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.diasSemana = diasSemana;
		this.servicio = servicio;
	}

	/**
	 * @return El id del horario
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id del horario
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return La hora de inicio del horario
	 */
	public Date getHoraInicio() {
		return horaInicio;
	}

	/**
	 * @param horaInicio - La nueva hora de inicio del horario
	 */
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * @return La hora de finalizacion del horario
	 */
	public Date getHoraFin() {
		return horaFin;
	}

	/**
	 * @param horaFin - La nueva hora de finalizacion del horario
	 */
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	/**
	 * @return Los dias de la semana en donde se efectua el horario
	 */
	public String getDiasSemana() {
		return diasSemana;
	}

	/**
	 * @param diasSemana - Los nuevos dias de la semana en donde se efectua el horario
	 */
	public void setDiasSemana(String diasSemana) {
		this.diasSemana = diasSemana;
	}

	/**
	 * @return El servicio responsable del horario
	 */
	public Servicio getServicio() {
		return servicio;
	}

	/**
	 * @param servicio - El nuevo servicio responsable del horario
	 */
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos del horario
	 */
	public String toString() {
		return "Horario [id=" + id + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", diasSemana="
				+ diasSemana + ", servicio=" + servicio + "]";
	}
}
