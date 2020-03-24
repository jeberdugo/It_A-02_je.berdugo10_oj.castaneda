package uniandes.isis2304.alohandes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Oferta;

public class SQLOferta {
	
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
	public SQLOferta(PersistenciaAlohAndes pa) {
		this.pa = pa;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un OFERTA a la base de datos
	 * de AlohAndes
	 * 
	 * @param pm          - El manejador de persistencia
	 * @param idOferta   - El id del oferta
	 * @param nombre      - El nombre del oferta
	 * @param presupuesto - El rol del oferta (0: Profesor, 1: Empleado, 2:
	 *                    Egresado, 3: Estudiante, 4: Padre)
	 * @return El número de tuplas insertadas
	 */
	public long adicionarOferta(PersistenceManager pm, String idOferta, Timestamp dia, int precio, String alojamientoid) {
		Query q = pm.newQuery(SQL,
				"INSERT INTO " + pa.darTablaOferta() + "(id, dia, precio, alojamientoid) values (?, ?, ?, ?)");
		q.setParameters(idOferta, dia, precio, alojamientoid);
		
		return (long) q.executeUnique();
		
	}
	
	
	public long actualizarReserva(PersistenceManager pm, String idOferta, String reservaid) {
		Query q = pm.newQuery(SQL,
				"UPDATE " + pa.darTablaOferta() + " SET reservaid = ? WHERE id LIKE ?");
		q.setParameters(reservaid,idOferta);
		
		return (long) q.executeUnique();
		
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar OFERTAS de la base de datos de
	 * AlohAndes, por su id
	 * 
	 * @param pm        - El manejador de persistencia
	 * @param idOferta - El id del oferta
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarOfertaPorId(PersistenceManager pm, long idOferta) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta() + " WHERE id = ?");
		q.setParameters(idOferta);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS OFERTAS
	 * de la base de datos de AlohAndes
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos OFERTA
	 */
	public List<Oferta> darOfertas(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta());
		q.setResultClass(Oferta.class);
		return (List<Oferta>) q.executeList();
	}
	
	public Oferta darUsuarioPorId (PersistenceManager pm, long idReserva) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta() + " WHERE reservaid = ?");
		q.setResultClass(Oferta.class);
		q.setParameters(idReserva);
		return (Oferta) q.executeUnique();
	}

}
