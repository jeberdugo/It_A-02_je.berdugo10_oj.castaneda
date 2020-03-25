package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Cliente;

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

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un CLIENTE a la base de datos
	 * de AlohAndes
	 * 
	 * @param pm          - El manejador de persistencia
	 * @param idCliente   - El id del cliente
	 * @param nombre      - El nombre del cliente
	 * @param presupuesto - El rol del cliente (0: Profesor, 1: Empleado, 2:
	 *                    Egresado, 3: Estudiante, 4: Padre)
	 * @return El número de tuplas insertadas
	 */
	public long adicionarHabitacion(PersistenceManager pm, long idHabitacion, String nombre, int rol) {
		Query q = pm.newQuery(SQL,
				"INSERT INTO " + pa.darTablaCliente() + "(id, nombre, rol) values (?, ?, ?)");
		q.setParameters(idHabitacion, nombre, rol);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar CLIENTES de la base de datos de
	 * AlohAndes, por su id
	 * 
	 * @param pm        - El manejador de persistencia
	 * @param idCliente - El id del cliente
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHabitacionPorId(PersistenceManager pm, long idHabitacion) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaCliente() + " WHERE id = ?");
		q.setParameters(idHabitacion);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS CLIENTES
	 * de la base de datos de AlohAndes
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos CLIENTE
	 */
	public List<Cliente> darClientes(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCliente());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	
	public Cliente darClientePorId (PersistenceManager pm, String idUsuario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCliente() + " WHERE ID = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(idUsuario);
		return (Cliente) q.executeUnique();
	}
}