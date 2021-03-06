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
	 * @param pm            - El manejador de persistencia
	 * @param idAlojamiento - El id del alojamiento
	 * @param nombre        - El nombre del alojamiento
	 * @param presupuesto   - El rol del alojamiento (0: Profesor, 1: Empleado, 2:
	 *                      Egresado, 3: Estudiante, 4: Padre)
	 * @return El número de tuplas insertadas
	 */
	public long adicionarAlojamiento(PersistenceManager pm, String idAlojamiento, String capacidad, String tipo,
			String idoperador, String registrocam, String registrosup, String ubicacion, String descripcion,
			String ultimaReserva, int habilitado) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaAlojamiento()
				+ "(id, capacidad, tipo, operador_id, registro_cam, registro_sup, ubicacion, descripcion, ultima_reserva, habilitado) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idAlojamiento, capacidad, tipo, idoperador, registrocam, registrosup, ubicacion, descripcion,
				ultimaReserva, habilitado);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar CLIENTES de la base de datos de
	 * AlohAndes, por su id
	 * 
	 * @param pm            - El manejador de persistencia
	 * @param idAlojamiento - El id del alojamiento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarAlojamientoPorId(PersistenceManager pm, long idAlojamiento) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaAlojamiento() + " WHERE id = ?");
		q.setParameters(idAlojamiento);
		return (long) q.executeUnique();
	}

	public Alojamiento buscarAlojamientoPorId(PersistenceManager pm, String idReserva) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaAlojamiento() + " WHERE id Like ?");
		q.setResultClass(Alojamiento.class);
		q.setParameters(idReserva);
		return (Alojamiento) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS CLIENTES
	 * de la base de datos de AlohAndes
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos CLIENTE
	 */
	public List<Alojamiento> darAlojamientosPorUserId(PersistenceManager pm, String idUsuario) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaAlojamiento() + " WHERE operador_id = ?");
		q.setParameters(idUsuario);
		q.setResultClass(Alojamiento.class);
		return q.executeList();
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
		return q.executeList();
	}

	public List<Alojamiento> darAlojamientoPorDotacion(PersistenceManager pm, List<String> dotacion, String inicio,
			String fin, int size) {
		String peticion = "SELECT AL.* FROM OFERTA OFE JOIN ALOJAMIENTO AL ON OFE.ALOJAMIENTO_ID=AL.ID JOIN (  SELECT AL.ID	FROM ALOJAMIENTO AL JOIN SERVICIOS_ALOJAMIENTOS SA ON AL.ID= sa.alojamiento_id JOIN SERVICIO SER ON 		sa.servicio_id=SER.ID ";
		if (dotacion.size() > 0) {
			peticion += "WHERE SER.DESCRIPCION LIKE \'" + dotacion.get(0) + "\'";
			if (dotacion.size() != 1) {
				for (int i = 1; i < dotacion.size(); i++) {
					peticion += " OR SER.DESCRIPCION LIKE \'" + dotacion.get(i) + "\'";
				}
			}
			peticion += "GROUP BY AL.ID HAVING COUNT(*) =" + size + ") INFO ON INFO.ID=AL.ID WHERE OFE.DIA<TO_DATE(\'"
					+ fin + "\',\'YYYY-MM-DD\') AND OFE.DIA>TO_DATE(\'" + inicio + "\','YYYY-MM-DD') ";
		}
		System.out.println(peticion);
		Query q = pm.newQuery(SQL, peticion);
		q.setResultClass(Alojamiento.class);
		return q.executeList();

	}

	public void deshabilitarAlojamientoPorId(PersistenceManager pm, Long alojamientoId) {
		Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaAlojamiento() + " SET HABILITADO = 0 WHERE ID = ?");
		q.setParameters(alojamientoId);
		q.executeUnique();
	}

	public void habilitarAlojamientoPorId(PersistenceManager pm, Long alojamientoId) {
		Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaAlojamiento() + " SET HABILITADO = 1 WHERE ID = ?");
		q.setParameters(alojamientoId);
		q.executeUnique();
	}

	public List<Alojamiento> buscarAlojamientosPorCapacidad(PersistenceManager pm, Integer capacidad) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaAlojamiento() + " WHERE capacidad = ?");
		q.setParameters(capacidad);
		q.setResultClass(Alojamiento.class);
		return q.executeList();
	}

	public List<Alojamiento> darAlojamientosPorUserIdNoHabilitados(PersistenceManager pm, String idUsuario) {
		Query q = pm.newQuery(SQL,
				"SELECT * FROM " + pa.darTablaAlojamiento() + " WHERE operador_id = ? AND habilitado = 0");
		q.setParameters(idUsuario);
		q.setResultClass(Alojamiento.class);
		return q.executeList();
	}

	public Alojamiento darAlojamientoMayorPorSemana(PersistenceManager pm, String semana, int anio) {
		Query q = pm.newQuery(SQL,
				"SELECT * FROM ALOJAMIENTO AL WHERE AL.ID = ( " + "SELECT * FROM("
						+ "SELECT OFER.ALOJAMIENTO_ID AS ALOJAMIENTO " + "FROM ALOJAMIENTO ALO "
						+ "FULL OUTER JOIN OFERTA OFER ON OFER.ALOJAMIENTO_ID = ALO.ID "
						+ "FULL OUTER JOIN RESERVA RES ON RES.ID = OFER.RESERVA_ID "
						+ "WHERE TO_CHAR(OFER.DIA,'YYYY') LIKE '" + anio + "' " + "AND TO_CHAR(DIA - 7/24,'IW') LIKE '"
						+ semana + "' " + "GROUP BY OFER.ALOJAMIENTO_ID, TO_CHAR(DIA - 7/24,'IW') "
						+ "ORDER BY COUNT(RES.ID) DESC) " + "WHERE ROWNUM = 1)");
		q.setResultClass(Alojamiento.class);
		Object resp = q.executeUnique();
		if (resp == null) {
			return null;
		}
		return (Alojamiento) resp;
	}

	public Alojamiento darAlojamientoMenorPorSemana(PersistenceManager pm, String semana, int anio) {
		Query q = pm.newQuery(SQL,
				"SELECT * FROM ALOJAMIENTO AL WHERE AL.ID = ( " + "SELECT * FROM("
						+ "SELECT OFER.ALOJAMIENTO_ID AS ALOJAMIENTO " + "FROM ALOJAMIENTO ALO "
						+ "FULL OUTER JOIN OFERTA OFER ON OFER.ALOJAMIENTO_ID = ALO.ID "
						+ "FULL OUTER JOIN RESERVA RES ON RES.ID = OFER.RESERVA_ID "
						+ "WHERE TO_CHAR(OFER.DIA,'YYYY') LIKE '" + anio + "' " + "AND TO_CHAR(DIA - 7/24,'IW') LIKE '"
						+ semana + "' " + "GROUP BY OFER.ALOJAMIENTO_ID, TO_CHAR(DIA - 7/24,'IW') "
						+ "ORDER BY COUNT(RES.ID) ASC) " + "WHERE ROWNUM = 1)");
		q.setResultClass(Alojamiento.class);
		Object resp = q.executeUnique();
		if (resp == null) {
			return null;
		}
		return (Alojamiento) resp;
	}

}
