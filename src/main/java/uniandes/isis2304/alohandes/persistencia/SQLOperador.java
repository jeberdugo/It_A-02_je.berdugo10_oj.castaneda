package uniandes.isis2304.alohandes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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
	public long eliminarOperadorePorId(PersistenceManager pm, long idCliente) {
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
	public List<Operador> darOperadores(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador());
		q.setResultClass(Operador.class);
		return (List<Operador>) q.executeList();
	}

	public Operador darOperadorPorId(PersistenceManager pm, String idUsuario) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador() + " WHERE ID = ?");
		q.setResultClass(Operador.class);
		q.setParameters(idUsuario);
		return (Operador) q.executeUnique();
	}

	public Operador darOperadorMayorPorSemana(PersistenceManager pm, String semana, int anio) {
		Query q = pm.newQuery(SQL,
				"SELECT * FROM OPERADOR OP WHERE OP.ID = ( " + "SELECT * FROM(" + "SELECT OPER.ID "
						+ "FROM ALOJAMIENTO ALO " + "FULL OUTER JOIN OFERTA OFER ON OFER.ALOJAMIENTO_ID = ALO.ID "
						+ "FULL OUTER JOIN RESERVA RES ON RES.ID = OFER.RESERVA_ID "
						+ "FULL OUTER JOIN OPERADOR OPER ON ALO.OPERADOR_ID = OPER.ID "
						+ "WHERE TO_CHAR(OFER.DIA,'YYYY') LIKE '" + anio + "' " + "AND TO_CHAR(DIA - 7/24,'IW') LIKE '"
						+ semana + "' " + "GROUP BY OPER.ID, TO_CHAR(DIA - 7/24,'IW') "
						+ "ORDER BY COUNT(RES.ID) DESC) " + "WHERE ROWNUM = 1)");
		q.setResultClass(Operador.class);
		Object resp = q.executeUnique();
		if (resp == null) {
			return null;
		}
		return (Operador) resp;
	}

	public Operador darOperadorMenorPorSemana(PersistenceManager pm, String semana, int anio) {
		Query q = pm.newQuery(SQL,
				"SELECT * FROM OPERADOR OP WHERE OP.ID = ( " + "SELECT * FROM(" + "SELECT OPER.ID "
						+ "FROM ALOJAMIENTO ALO " + "FULL OUTER JOIN OFERTA OFER ON OFER.ALOJAMIENTO_ID = ALO.ID "
						+ "FULL OUTER JOIN RESERVA RES ON RES.ID = OFER.RESERVA_ID "
						+ "FULL OUTER JOIN OPERADOR OPER ON ALO.OPERADOR_ID = OPER.ID "
						+ "WHERE TO_CHAR(OFER.DIA,'YYYY') LIKE '" + anio + "' " + "AND TO_CHAR(DIA - 7/24,'IW') LIKE '"
						+ semana + "' " + "GROUP BY OPER.ID, TO_CHAR(DIA - 7/24,'IW') " + "ORDER BY COUNT(RES.ID) ASC) "
						+ "WHERE ROWNUM = 1)");
		q.setResultClass(Operador.class);
		Object resp = q.executeUnique();
		if (resp == null) {
			return null;
		}
		return (Operador) resp;
	}
}
