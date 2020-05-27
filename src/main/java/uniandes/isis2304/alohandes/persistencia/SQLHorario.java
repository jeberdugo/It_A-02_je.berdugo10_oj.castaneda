package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Horario;

public class SQLHorario {
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las
	 * sentencias de acceso a la base de datos Se renombra acá para facilitar la
	 * escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohAndes.SQL;

	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohAndes pa;

	/**
	 * Constructor
	 * 
	 * @param pa - El Manejador de persistencia de la aplicación
	 */
	public SQLHorario(PersistenciaAlohAndes pa) {
		this.pa = pa;
	}

	public long adicionarHorario(PersistenceManager pm, long idHorario, String horaInicio, String horaFin,
			String diasSemana) {
		Query q = pm.newQuery(SQL,
				"INSERT INTO " + pa.darTablaHorario() + "(ID, HORA_INICIO, HORA_FIN, DIAS_SEMANA) values (?, ?, ?, ?)");
		q.setParameters(idHorario, horaInicio, horaFin, diasSemana);
		return (long) q.executeUnique();
	}

	public long eliminarHorarioPorId(PersistenceManager pm, long idHorario) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHorario() + " WHERE id = ?");
		q.setParameters(idHorario);
		return (long) q.executeUnique();
	}

	public List<Horario> darHorarios(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHorario());
		q.setResultClass(Horario.class);
		return q.executeList();
	}
}
