package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Servicio;

public class SQLServicio {
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
	public SQLServicio(PersistenciaAlohAndes pa) {
		this.pa = pa;
	}

	public long adicionarServicio(PersistenceManager pm, long idServicio, String descripcion, double costo,
			long idHorario, long idAlojamiento) {
		Query q = pm.newQuery(SQL,
				"INSERT INTO " + pa.darTablaServicio() + "(ID, DESCRIPCION, COSTO, HORARIO_ID) values (?, ?, ?, ?)");
		q.setParameters(idServicio, descripcion, costo, idHorario);
		Query p = pm.newQuery(SQL,
				"INSERT INTO " + pa.darTablaServicios_Alojamientos() + "(ALOJAMIENTO_ID, SERVICIO_ID) values (?, ?)");
		p.setParameters(idAlojamiento, idServicio);
		p.executeUnique();
		return (long) q.executeUnique();
	}

	public long eliminarServicioPorId(PersistenceManager pm, long idServicio) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaServicios_Alojamientos() + " WHERE SERVICIO_ID = ?");
		q.setParameters(idServicio);
		q.executeUnique();
		q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaServicio() + " WHERE id = ?");
		q.setParameters(idServicio);
		return (long) q.executeUnique();
	}

	public List<Servicio> darServicios(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaServicio());
		q.setResultClass(Servicio.class);
		return (List<Servicio>) q.executeList();
	}
}