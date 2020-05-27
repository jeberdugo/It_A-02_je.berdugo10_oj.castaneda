package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Regla;

public class SQLRegla {
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
	public SQLRegla(PersistenciaAlohAndes pa) {
		this.pa = pa;
	}

	public long adicionarRegla(PersistenceManager pm, long idRegla, String descripcion, long idAlojamiento) {
		Query q = pm.newQuery(SQL,
				"INSERT INTO " + pa.darTablaRegla() + "(ID, DESCRIPCION, ALOJAMIENTO_ID) values (?, ?, ?)");
		q.setParameters(idRegla, descripcion, idAlojamiento);
		return (long) q.executeUnique();
	}

	public long eliminarReglaPorId(PersistenceManager pm, long idRegla) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaRegla() + " WHERE id = ?");
		q.setParameters(idRegla);
		return (long) q.executeUnique();
	}

	public List<Regla> darReglas(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaRegla());
		q.setResultClass(Regla.class);
		return q.executeList();
	}
}