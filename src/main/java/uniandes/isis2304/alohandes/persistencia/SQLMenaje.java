package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Menaje;

public class SQLMenaje {
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
	public SQLMenaje(PersistenciaAlohAndes pa) {
		this.pa = pa;
	}

	public long adicionarMenaje(PersistenceManager pm, long idMenaje, String descripcion, int disponibilidad,
			long alojamientoId) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaMenaje()
				+ "(ID, DESCRIPCION, DISPONIBILIDAD, ALOJAMIENTO_ID) values (?, ?, ?, ?)");
		q.setParameters(idMenaje, descripcion, disponibilidad, alojamientoId);
		return (long) q.executeUnique();
	}

	public long eliminarMenajePorId(PersistenceManager pm, long idMenaje) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaMenaje() + " WHERE id = ?");
		q.setParameters(idMenaje);
		return (long) q.executeUnique();
	}

	public List<Menaje> darMenajeList(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaMenaje());
		q.setResultClass(Menaje.class);
		return (List<Menaje>) q.executeList();
	}
}