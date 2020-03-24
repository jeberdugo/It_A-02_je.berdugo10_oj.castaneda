package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Alojamiento;

public class SQLAlojamiento {
	
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
	public SQLAlojamiento(PersistenciaAlohAndes pa) {
		this.pa = pa;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un CLIENTE a la base de datos
	 * de AlohAndes
	 * 
	 * @param pm          - El manejador de persistencia
	 * @param idAlojamiento   - El id del alojamiento
	 * @param nombre      - El nombre del alojamiento
	 * @param presupuesto - El rol del alojamiento (0: Profesor, 1: Empleado, 2:
	 *                    Egresado, 3: Estudiante, 4: Padre)
	 * @return El número de tuplas insertadas
	 */
	public long adicionarAlojamiento(PersistenceManager pm, String idAlojamiento, String capacidad, String  tipo, String idoperador, String registrocam, String registrosup, String ubicacion, String descripcion) {
		Query q = pm.newQuery(SQL,
				"INSERT INTO " + pa.darTablaAlojamiento() + "(id, capacidad, tipo, idoperador, registrocam, registrosup, ubicacion, descripcion) values (?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idAlojamiento, capacidad, tipo, idoperador, registrocam, registrosup, ubicacion, descripcion);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar CLIENTES de la base de datos de
	 * AlohAndes, por su id
	 * 
	 * @param pm        - El manejador de persistencia
	 * @param idAlojamiento - El id del alojamiento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarAlojamientoPorId(PersistenceManager pm, long idAlojamiento) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaAlojamiento() + " WHERE id = ?");
		q.setParameters(idAlojamiento);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS CLIENTES
	 * de la base de datos de AlohAndes
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos CLIENTE
	 */
	public List<Alojamiento> darAlojamientosPorUserId(PersistenceManager pm, String idUsuario) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaAlojamiento()+ " WHERE idoperador = ?");
		q.setParameters(idUsuario);
		q.setResultClass(Alojamiento.class);
		return (List<Alojamiento>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS CLIENTES
	 * de la base de datos de AlohAndes
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos CLIENTE
	 */
	public List<Alojamiento> darAlojamientos(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaAlojamiento());
		q.setResultClass(Alojamiento.class);
		return (List<Alojamiento>) q.executeList();
	}

}
