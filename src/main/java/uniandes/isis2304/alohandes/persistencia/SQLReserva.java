package uniandes.isis2304.alohandes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Reserva;

public class SQLReserva {

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
	public SQLReserva(PersistenciaAlohAndes pa) {
		this.pa = pa;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un RESERVA a la base de datos
	 * de AlohAndes
	 * 
	 * @param pm          - El manejador de persistencia
	 * @param idReserva   - El id del reserva
	 * @param nombre      - El nombre del reserva
	 * @param presupuesto - El rol del reserva (0: Profesor, 1: Empleado, 2:
	 *                    Egresado, 3: Estudiante, 4: Padre)
	 * @return El número de tuplas insertadas
	 */
	public long adicionarReserva(PersistenceManager pm, String idReserva, int estado, int valorTotal, long clienteid, Timestamp fechaRealizacion, String reservaColectivaId) {
		Query q = pm.newQuery(SQL,
				"INSERT INTO " + pa.darTablaReserva() + "(id, estado, valor_total, cliente_id, fecha_realizacion, reserva_colectiva_id) values (?, ?, ?, ?, ?, ?)");
		q.setParameters(idReserva, estado, valorTotal, clienteid, fechaRealizacion, reservaColectivaId);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar RESERVAS de la base de datos de
	 * AlohAndes, por su id
	 * 
	 * @param pm        - El manejador de persistencia
	 * @param idReserva - El id del reserva
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarReservaPorId(PersistenceManager pm, long idReserva) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaReserva() + " WHERE id = ?");
		q.setParameters(idReserva);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS RESERVAS
	 * de la base de datos de AlohAndes
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos RESERVA
	 */
	public List<Reserva> darReservas(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva());
		q.setResultClass(Reserva.class);
		return (List<Reserva>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS RESERVAS
	 * de la base de datos de AlohAndes
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos RESERVA
	 */
	public List darReservasPorCliente(PersistenceManager pm, long idUsuario) {
		Query q = pm.newQuery(SQL, "SELECT id FROM " + pa.darTablaReserva() + " WHERE cliente_id = ?");

		q.setParameters(idUsuario);

		List tipoServicio = (List) q.executeList();
		return tipoServicio;
	}

}
