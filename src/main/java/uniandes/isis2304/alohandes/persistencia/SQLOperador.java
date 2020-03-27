package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Cliente;
import uniandes.isis2304.alohandes.negocio.Operador;

public class SQLOperador {
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
	public SQLOperador(PersistenciaAlohAndes pa) {
		this.pa = pa;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un OPERADOR a la base de datos
	 * de AlohAndes
	 * 
	 * @param pm         - El manejador de persistencia
	 * @param idOperador - El id del operador
	 * @param tipo       - El tipo del operador
	 * @return El número de tuplas insertadas
	 */
	public long adicionarOperador(PersistenceManager pm, long idOperador, int tipo) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOperador() + "(id, tipo) values (?, ?)");
		q.setParameters(idOperador, tipo);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar OPERADORES de la base de datos
	 * de AlohAndes, por su id
	 * 
	 * @param pm         - El manejador de persistencia
	 * @param idOperador - El id del operador
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarClientePorId(PersistenceManager pm, long idCliente) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOperador() + " WHERE id = ?");
		q.setParameters(idCliente);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
	 * OPERADORES de la base de datos de AlohAndes
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos OPERADOR
	 */
	public List<Operador> darClientes(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador());
		q.setResultClass(Operador.class);
		return (List<Operador>) q.executeList();
	}
	
	public Operador darOperadorPorId (PersistenceManager pm, String idUsuario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador() + " WHERE ID = ?");
		q.setResultClass(Operador.class);
		q.setParameters(idUsuario);
		return (Operador) q.executeUnique();
	}
}
