package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Universidad;

public class SQLUniversidad {
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
	public SQLUniversidad(PersistenciaAlohAndes pa) {
		this.pa = pa;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una UNIVERSIDAD a la base de
	 * datos de AlohAndes
	 * 
	 * @param pm            - El manejador de persistencia
	 * @param idUniversidad - El id de la universidad
	 * @param nombre        - El nommbre de la universidad
	 * @return El número de tuplas insertadas
	 */
	public long adicionarUniversidad(PersistenceManager pm, long idUniversidad, String universidad) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaUniversidad() + "(id, universidad) values (?, ?)");
		q.setParameters(idUniversidad, universidad);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNIVERSIDADES de la base de
	 * datos de AlohAndes, por su id
	 * 
	 * @param pm            - El manejador de persistencia
	 * @param idUniversidad - El id de la universidad
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarUniversidadPorId(PersistenceManager pm, long idCliente) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaUniversidad() + " WHERE id = ?");
		q.setParameters(idCliente);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS
	 * UNIVERSIDADES de la base de datos de AlohAndes
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos UNIVERSIDAD
	 */
	public List<Universidad> darUniversidades(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaUniversidad());
		q.setResultClass(Universidad.class);
		return (List<Universidad>) q.executeList();
	}
}
