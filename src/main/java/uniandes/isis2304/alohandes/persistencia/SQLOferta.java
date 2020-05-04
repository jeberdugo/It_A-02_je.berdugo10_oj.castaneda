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
	 * @param idOferta    - El id del oferta
	 * @param nombre      - El nombre del oferta
	 * @param presupuesto - El rol del oferta (0: Profesor, 1: Empleado, 2:
	 *                    Egresado, 3: Estudiante, 4: Padre)
	 * @return El número de tuplas insertadas
	 */
	public long adicionarOferta(PersistenceManager pm, String idOferta, Timestamp dia, int precio, long alojamientoid) {
		Query q = pm.newQuery(SQL,
				"INSERT INTO " + pa.darTablaOferta() + "(id, dia, precio, alojamiento_id) values (?, ?, ?, ?)");
		q.setParameters(idOferta, dia, precio, alojamientoid);

		return (long) q.executeUnique();

	}

	public long actualizarReserva(PersistenceManager pm, String idOferta, String reservaid) {
		Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaOferta() + " SET reserva_id = ? WHERE id = ?");
		q.setParameters(reservaid, idOferta);

		return (long) q.executeUnique();

	}

	public long actualizarReservaNull(PersistenceManager pm, String reservaid) {

		Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaOferta() + " SET reserva_id = null WHERE reserva_id = ?");
		q.setParameters(reservaid);

		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar OFERTAS de la base de datos de
	 * AlohAndes, por su id
	 * 
	 * @param pm       - El manejador de persistencia
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

	public Oferta darOfertaPorId(PersistenceManager pm, long idReserva) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta() + " WHERE id = ?");
		q.setParameters(idReserva);
		q.setResultClass(Oferta.class);
		
		return (Oferta) q.executeUnique();
	}

	public String dar20AlojamientosMasPopulares(PersistenceManager pm) {
		String veinte = "";
		Query q = pm.newQuery(SQL,
				"SELECT * FROM (select CONCAT(alojamiento_id,CONCAT('-', count(*))) from oferta where Reserva_id is not null group by alojamiento_id order by Count(*) DESC) WHERE rownum <= 20");

		List tipoServicio = (List) q.executeList();

		for (int i = 0; i < tipoServicio.size(); i++) {
			String temp = "";
			temp += "" + tipoServicio.get(i);
			String[] datos = temp.split("-");
			veinte += "ID Alojamiento: " + datos[0] + " Numero de reservas: " + datos[1] + "\n";
		}

		return veinte;

	}
	
	public String pocaOferta(PersistenceManager pm) {
		String veinte = "";
		Query q = pm.newQuery(SQL,
				"SELECT CONCAT(CONCAT(id,'-'),ultima_reserva) FROM ALOJAMIENTO al WHERE al.ultima_reserva<(SYSDATE-30)");

		List tipoServicio = (List) q.executeList();

		if(tipoServicio.isEmpty()) {
			veinte="No hay ningun alojamiento con poca demanda";
		}
		for (int i = 0; i < tipoServicio.size(); i++) {
			String temp = "";
			temp += "" + tipoServicio.get(i);
			String[] datos = temp.split("-");
			veinte += "Alojamiento: " + datos[0] + " Fecha ultima reserva: " + datos[1] + "\n";
		}

		return veinte;

	}
	
	public String clientesFrecuentes(PersistenceManager pm, String idAlojamiento) {
		String veinte = "";
		Query q = pm.newQuery(SQL,
				"SELECT CONCAT(CONCAT(res.cliente_id,'-'),count(res.cliente_id))\r\n" + 
				"FROM OFERTA o \r\n" + 
				"JOIN  RESERVA res ON o.reserva_id=res.id\r\n" + 
				"WHERE o.alojamiento_id= ? \r\n" + 
				"GROUP BY res.cliente_id\r\n" + 
				"HAVING count(res.cliente_id)>=15");
		q.setParameters(idAlojamiento);

		List tipoServicio = (List) q.executeList();

		if(tipoServicio.isEmpty()) {
			veinte="No hay ningun cliente frecuente";
		}
		for (int i = 0; i < tipoServicio.size(); i++) {
			String temp = "";
			temp += "" + tipoServicio.get(i);
			String[] datos = temp.split("-");
			veinte += "ID Cliente Frecuente: " + datos[0] + " Alojamiento: " + datos[1] + "\n";
		}

		return veinte;

	}
	
	
	public String analisisOperacion(PersistenceManager pm, String tipo) {
		String veinte = "";
		Query mayorDemanda = pm.newQuery(SQL,
				"SELECT * FROM(\r\n" + 
				"SELECT\r\n" + 
				"    CONCAT(CONCAT(o.dia,'-'), COUNT(o.dia))\r\n" + 
				"FROM ALOJAMIENTO al\r\n" + 
				"JOIN OFERTA o ON o.alojamiento_id=al.id\r\n" + 
				" WHERE al.tipo = ? \r\n" + 
				"AND o.reserva_id IS NOT NULL\r\n" + 
				" GROUP BY o.dia\r\n" + 
				" ORDER BY COUNT(o.dia) DESC) WHERE ROWNUM<=5");
		mayorDemanda.setParameters(tipo);
		
		Query mayorIngreso = pm.newQuery(SQL,
				" SELECT * FROM(\r\n" + 
				" SELECT  CONCAT(CONCAT(o.dia,'-'), SUM(o.precio))\r\n" + 
				"FROM ALOJAMIENTO al\r\n" + 
				"JOIN OFERTA o ON o.alojamiento_id=al.id\r\n" + 
				" WHERE al.tipo =  ? \r\n" + 
				"AND o.reserva_id IS NOT NULL\r\n" + 
				" GROUP BY o.dia)WHERE  ROWNUM<=5");
		mayorIngreso.setParameters(tipo);
		
		Query menorOcu = pm.newQuery(SQL,
				" SELECT * FROM(\r\n" + 
				"SELECT\r\n" + 
				"    CONCAT(CONCAT(o.dia,'-'), COUNT(o.dia))\r\n" + 
				"FROM ALOJAMIENTO al\r\n" + 
				"JOIN OFERTA o ON o.alojamiento_id=al.id\r\n" + 
				" WHERE al.tipo =  ? \r\n" + 
				"AND o.reserva_id IS NOT NULL\r\n" + 
				" GROUP BY o.dia\r\n" + 
				" ORDER BY COUNT(o.dia) ASC) WHERE ROWNUM<=5");
		menorOcu.setParameters(tipo);

		List listaMayorDemanda = (List) mayorDemanda.executeList();
		List listaMayorIngreso = (List) mayorIngreso.executeList();
		List listaMenorOcu = (List) menorOcu.executeList();

		veinte += "Fechas con mayor demanda: \n";
		if(listaMayorDemanda.isEmpty()) {
			veinte="No hay datos";
		}
		for (int i = 0; i < listaMayorDemanda.size(); i++) {
			String temp = "";
			temp += "" + listaMayorDemanda.get(i);
			String[] datos = temp.split("-");
			veinte += "Fecha: " + datos[0] + " Numero de clientes: " + datos[1] + "\n";
		}
		
		veinte += "Fechas con mayor ingreso: \n";
		if(listaMayorIngreso.isEmpty()) {
			veinte="No hay datos";
		}
		for (int i = 0; i < listaMayorIngreso.size(); i++) {
			String temp = "";
			temp += "" + listaMayorIngreso.get(i);
			String[] datos = temp.split("-");
			veinte += "Fecha: " + datos[0] + " Recaudo: " + datos[1] + "\n";
		}
		
		veinte += "Fechas con menor ocupación: \n";
		if(listaMenorOcu.isEmpty()) {
			veinte="No hay datos";
		}
		for (int i = 0; i < listaMenorOcu.size(); i++) {
			String temp = "";
			temp += "" + listaMenorOcu.get(i);
			String[] datos = temp.split("-");
			veinte += "Fecha: " + datos[0] + " Numero de clientes: " + datos[1] + "\n";
		}

		return veinte;

	}

	public String ingresosPorOperador(PersistenceManager pm) {
		String peticion = "SELECT CONCAT(operador_id,CONCAT('-',suma)) FROM(SELECT "
				+ "Operador_ID,SUM(valor_total)AS SUMA FROM reserva ofe JOIN (SELECT reserva_id,alojamiento_id  "
				+ "FROM oferta where reserva_id is not null group by reserva_id, alojamiento_id) temp on temp.reserva_id=ofe.id "
				+ "JOIN alojamiento on temp.alojamiento_id=alojamiento.id GROUP BY operador_id)";
		String respuesta = "";

		Query q = pm.newQuery(SQL, peticion);

		List tipoServicio = (List) q.executeList();

		for (int i = 0; i < tipoServicio.size(); i++) {
			String temp = "";
			temp += "" + tipoServicio.get(i);
			String[] datos = temp.split("-");
			respuesta += "ID Operador: " + datos[0] + " Ingresos totales: " + datos[1] + "\n";
		}
		return respuesta;

	}

	public String indiceOcupacion(PersistenceManager pm) {
		String peticion = "SELECT CONCAT(alojamiento_id,CONCAT('-',CONCAT(numOfertas,CONCAT('-',numOcupacion)))) "
				+ "from( Select alojamiento_id,count(alojamiento_id) AS numOfertas from oferta "
				+ "group by alojamiento_id) natural join( SELECT alojamiento_id,count(alojamiento_id) AS numOcupacion "
				+ "FROM oferta where reserva_id is not null group by alojamiento_id)";
		String respuesta = "";

		Query q = pm.newQuery(SQL, peticion);

		List tipoServicio = (List) q.executeList();

		for (int i = 0; i < tipoServicio.size(); i++) {
			String temp = "";
			temp += "" + tipoServicio.get(i);
			String[] datos = temp.split("-");
			double d1 = Double.valueOf("" + Integer.parseInt(datos[2]));
			double d2 = Double.valueOf("" + Integer.parseInt(datos[1]));
			double indice = d1 / d2;

			String ind = String.format("%.2f", indice);

			respuesta += "ID Alojamiento: " + datos[0] + " Idice de ocupacion: " + ind + "\n";
		}
		return respuesta;

	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS OFERTAS
	 * de la base de datos de AlohAndes
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos OFERTA
	 */
	public List darOfertasPorAlojamiento(PersistenceManager pm, long idAlojamiento) {
		Query q = pm.newQuery(SQL, "SELECT id FROM " + pa.darTablaOferta() + " WHERE alojamiento_id = ?");
		q.setParameters(idAlojamiento);
		
		return (List) q.executeList();
	}
	
	public List<Oferta> darOfertasConReservaPorAlojamiento(PersistenceManager pm, long idAlojamiento) {
		Query q = pm.newQuery(SQL,
				"SELECT * FROM " + pa.darTablaOferta() + " WHERE alojamiento_id = ? AND reserva_id IS NOT NULL");
		q.setResultClass(Oferta.class);
		q.setParameters(idAlojamiento);
		return (List<Oferta>) q.executeList();
	}

	public List<Oferta> darOfertasSinReservaPorAlojamientoYFecha(PersistenceManager pm, long idAlojamiento, String fecha) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta()
				+ " WHERE alojamiento_id = ? AND reserva_id IS NULL AND DIA=TO_DATE(\'"+fecha.split(" ")[0]+"\',\'YYYY-MM-DD\')");
		q.setResultClass(Oferta.class);
		q.setParameters(idAlojamiento);
		return (List<Oferta>) q.executeList();
	}
	
	public void eliminarReservasAlojamiento(PersistenceManager pm, long alojamientoId) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta() + " WHERE alojamiento_id = ?");
		q.setParameters(alojamientoId);
		q.executeUnique();
	}

	public List darOfertasPorFecha(PersistenceManager pm, String fecha) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta() + " WHERE DIA = ?");
		q.setParameters(fecha);
		q.setResultClass(Oferta.class);
		
		return (List<Oferta>) q.executeList();
		// TODO Auto-generated method stub
		
	}

}
