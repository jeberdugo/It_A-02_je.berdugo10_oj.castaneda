package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Habitacion;

public class SQLHabitacion {
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
	public SQLHabitacion(PersistenciaAlohAndes pa) {
		this.pa = pa;
	}

	public long adicionarHorario(PersistenceManager pm, long idHabitacion, int capacidad, int tipo,
			long alojamientoId) {
		Query q = pm.newQuery(SQL,
				"INSERT INTO " + pa.darTablaHabitacion() + "(ID, CAPACIDAD, TIPO, ALOJAMIENTO_ID) values (?, ?, ?, ?)");
		q.setParameters(idHabitacion, capacidad, tipo, alojamientoId);
		return (long) q.executeUnique();
	}

	public long eliminarHabitacionPorId(PersistenceManager pm, long idHabitacion) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacion() + " WHERE id = ?");
		q.setParameters(idHabitacion);
		return (long) q.executeUnique();
	}

	public List<Habitacion> darHabitaciones(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacion());
		q.setResultClass(Habitacion.class);
		return q.executeList();
	}
}
