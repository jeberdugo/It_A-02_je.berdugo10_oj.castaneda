package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Seguro;

public class SQLSeguro {
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
	public SQLSeguro(PersistenciaAlohAndes pa) {
		this.pa = pa;
	}

	public long adicionarSeguro(PersistenceManager pm, long idSeguro, String caracteristicas, double costo,
			String vigencia, long alojamientoId) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaSeguro()
				+ "(ID, CARACTERISTICAS, COSTO, VIGENCIA, ALOJAMIENTO_ID) values (?, ?, ?, ?, ?)");
		q.setParameters(idSeguro, caracteristicas, costo, vigencia, alojamientoId);
		return (long) q.executeUnique();
	}

	public long eliminarSeguroPorId(PersistenceManager pm, long idSeguro) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaSeguro() + " WHERE id = ?");
		q.setParameters(idSeguro);
		return (long) q.executeUnique();
	}

	public List<Seguro> darSeguros(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaSeguro());
		q.setResultClass(Seguro.class);
		return (List<Seguro>) q.executeList();
	}
}