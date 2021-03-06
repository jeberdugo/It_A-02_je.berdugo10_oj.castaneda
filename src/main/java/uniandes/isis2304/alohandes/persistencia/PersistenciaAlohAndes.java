/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: AlohAndes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.alohandes.persistencia;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.alohandes.negocio.Alojamiento;
import uniandes.isis2304.alohandes.negocio.Cliente;
import uniandes.isis2304.alohandes.negocio.Habitacion;
import uniandes.isis2304.alohandes.negocio.Horario;
import uniandes.isis2304.alohandes.negocio.Menaje;
import uniandes.isis2304.alohandes.negocio.Oferta;
import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.negocio.Regla;
import uniandes.isis2304.alohandes.negocio.Reserva;
import uniandes.isis2304.alohandes.negocio.Seguro;
import uniandes.isis2304.alohandes.negocio.Servicio;
import uniandes.isis2304.alohandes.negocio.Usuario;

/**
 * Clase para el manejador de persistencia del proyecto AlohAndes Traduce la
 * información entre objetos Java y tuplas de la base de datos, en ambos
 * sentidos Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase)
 * para comunicarse de manera correcta con la base de datos Se apoya en las
 * clases SQLBar, SQLBebedor, SQLBebida, SQLGustan, SQLSirven, SQLTipoBebida y
 * SQLVisitan, que son las que realizan el acceso a la base de datos
 * 
 * @author Germán Bravo
 */
public class PersistenciaAlohAndes {
	/*
	 * **************************************************************** Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaAlohAndes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una
	 * consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/*
	 * **************************************************************** Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaAlohAndes instance;

	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las
	 * transacciones
	 */
	private PersistenceManagerFactory pmf;

	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su
	 * orden: Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y
	 * visitan
	 */
	private List<String> tablas;

	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaAlohAndes
	 */
	private SQLUtil sqlUtil;

	private SQLOperador sqlOperador;
	private SQLUsuario sqlUsuario;
	private SQLCliente sqlCliente;
	private SQLReserva sqlReserva;
	private SQLOferta sqlOferta;
	private SQLAlojamiento sqlAlojamiento;
	private SQLHorario sqlHorario;
	private SQLMenaje sqlMenaje;
	private SQLRegla sqlRegla;
	private SQLServicio sqlServicio;
	private SQLHabitacion sqlHabitacion;
	private SQLSeguro sqlSeguro;
	private SQLReservaColectiva sqlReservaColectiva;
	/*
	 * **************************************************************** Métodos del
	 * MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	public PersistenciaAlohAndes() {
		pmf = JDOHelper.getPersistenceManagerFactory("AlohAndes");
		crearClasesSQL();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String>();
		tablas.add("AlohAndes_sequence");
		tablas.add("USUARIO");
		tablas.add("OPERADOR");
		tablas.add("CLIENTE");
		tablas.add("RESERVA");
		tablas.add("OFERTA");
		tablas.add("ALOJAMIENTO");
		tablas.add("HORARIO");
		tablas.add("MENAJE");
		tablas.add("REGLA");
		tablas.add("SERVICIO");
		tablas.add("HABITACION");
		tablas.add("SERVICIOS_ALOJAMIENTOS");
		tablas.add("SEGURO");
		tablas.add("RESERVA_COLECTIVA");

	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json -
	 * Patrón SINGLETON
	 * 
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de
	 *                    la unidad de persistencia a manejar
	 */
	private PersistenciaAlohAndes(JsonObject tableConfig) {
		crearClasesSQL();
		tablas = leerNombresTablas(tableConfig);
		String unidadPersistencia = tableConfig.get("unidadPersistencia").getAsString();
		log.info("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory(unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaAlohAndes existente - Patrón
	 *         SINGLETON
	 */
	public static PersistenciaAlohAndes getInstance() {
		if (instance == null) {
			instance = new PersistenciaAlohAndes();
		}
		return instance;
	}

	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto
	 * tableConfig
	 * 
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaAlohAndes existente - Patrón
	 *         SINGLETON
	 */
	public static PersistenciaAlohAndes getInstance(JsonObject tableConfig) {
		if (instance == null) {
			instance = new PersistenciaAlohAndes(tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia() {
		pmf.close();
		instance = null;
	}

	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * 
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List<String> leerNombresTablas(JsonObject tableConfig) {
		JsonArray nombres = tableConfig.getAsJsonArray("tablas");

		List<String> resp = new LinkedList<String>();
		for (JsonElement nom : nombres) {
			resp.add(nom.getAsString());
		}

		return resp;
	}

	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL() {

		sqlUtil = new SQLUtil(this);
		sqlOperador = new SQLOperador(this);
		sqlUsuario = new SQLUsuario(this);
		sqlCliente = new SQLCliente(this);
		sqlReserva = new SQLReserva(this);
		sqlOferta = new SQLOferta(this);
		sqlAlojamiento = new SQLAlojamiento(this);
		sqlHorario = new SQLHorario(this);
		sqlMenaje = new SQLMenaje(this);
		sqlRegla = new SQLRegla(this);
		sqlServicio = new SQLServicio(this);
		sqlHabitacion = new SQLHabitacion(this);
		sqlSeguro = new SQLSeguro(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de AlohAndes
	 */
	public String darSeqAlohAndes() {
		return tablas.get(0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Usuario de
	 *         AlohAndes
	 */
	public String darTablaUsuario() {
		return tablas.get(1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Operador de
	 *         AlohAndes
	 */
	public String darTablaOperador() {
		return tablas.get(2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Cliente de
	 *         AlohAndes
	 */
	public String darTablaCliente() {
		return tablas.get(3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Reserva de
	 *         AlohAndes
	 */
	public String darTablaReserva() {
		return tablas.get(4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Oferta de
	 *         AlohAndes
	 */
	public String darTablaOferta() {
		return tablas.get(5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Alojamiento de
	 *         AlohAndes
	 */
	public String darTablaAlojamiento() {
		return tablas.get(6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Horario de
	 *         AlohAndes
	 */
	public String darTablaHorario() {
		return tablas.get(7);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Menaje de
	 *         AlohAndes
	 */
	public String darTablaMenaje() {
		return tablas.get(8);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Regla de
	 *         AlohAndes
	 */
	public String darTablaRegla() {
		return tablas.get(9);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Servicio de
	 *         AlohAndes
	 */
	public String darTablaServicio() {
		return tablas.get(10);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Habitacion de
	 *         AlohAndes
	 */
	public String darTablaHabitacion() {
		return tablas.get(11);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de
	 *         Servicios_Alojamientos de AlohAndes
	 */
	public String darTablaServicios_Alojamientos() {
		return tablas.get(12);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Seguro de
	 *         AlohAndes
	 */
	public String darTablaSeguro() {
		return tablas.get(13);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Seguro de
	 *         AlohAndes
	 */
	public String darTablaReservaColectiva() {
		return tablas.get(14);
	}

	/**
	 * Transacción para el generador de secuencia de AlohAndes Adiciona entradas al
	 * log de la aplicación
	 * 
	 * @return El siguiente número del secuenciador de AlohAndes
	 */
	private long nextval() {
		long resp = sqlUtil.nextval(pmf.getPersistenceManager());
		log.trace("Generando secuencia: " + resp);
		return resp;
	}

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la
	 * Exception e, que da el detalle específico del problema encontrado
	 * 
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) {
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException")) {
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions()[0].getMessage();
		}
		return resp;
	}

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de AlohAndes
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL
	 * ORDEN ES IMPORTANTE
	 * 
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en
	 *         las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA, TIPOBEBIDA, BEBEDOR y
	 *         BAR, respectivamente
	 */
	public long[] limpiarAlohAndes() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long[] resp = sqlUtil.limpiarAlohAndes(pm);
			tx.commit();
			log.info("Borrada la base de datos");
			return resp;
		} catch (Exception e) {
//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] { -1, -1, -1, -1, -1, -1, -1 };
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

	}

	public Usuario buscarUsuarioPorUsuario(String usuario) {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Usuario usuarioEncontrado = sqlUsuario.darUsuarioPorUsuario(pm, usuario);
			tx.commit();
			log.trace("Usuario encontrado: " + usuarioEncontrado.toString());
			return usuarioEncontrado;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public Usuario adicionarUsuario(String nombreUsuario, String correo, String contrasena, int numeroDocumento,
			int tipoDocumento) throws Exception {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idUsuario = nextval();
			long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, idUsuario, nombreUsuario, correo, contrasena,
					numeroDocumento, tipoDocumento);
			tx.commit();
			Usuario usuario = new Usuario(idUsuario, nombreUsuario, correo, contrasena, numeroDocumento, tipoDocumento);
			log.trace("Inserción de usuario: " + usuario.toString() + " : " + tuplasInsertadas + " tuplas insertadas");
			return usuario;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw new Exception(darDetalleException(e));
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public Cliente adicionarCliente(long idCliente, String nombre, int rol) throws Exception {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlCliente.adicionarCliente(pm, idCliente, nombre, rol);
			tx.commit();
			Cliente cliente = new Cliente(idCliente, nombre, rol);
			log.trace("Inserción de cliente: " + cliente.toString() + " : " + tuplasInsertadas + " tuplas insertadas");
			return cliente;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw new Exception(darDetalleException(e));
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public Oferta adicionarOferta(String dia, int precio, long alojamientoid) throws Exception {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idOferta = nextval();
			System.out.print("Empieza");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fecha = sdf.parse(dia);
			Timestamp fechasql = new Timestamp(fecha.getTime());
			long tuplasInsertadas = sqlOferta.adicionarOferta(pm, "" + idOferta, fechasql, precio, alojamientoid);
			tx.commit();
			Oferta oferta = new Oferta(idOferta, fecha, precio, -1, alojamientoid);
			;
			log.trace("Inserción de oferta: " + oferta.toString() + " : " + tuplasInsertadas + " tuplas insertadas");
			return oferta;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw new Exception(darDetalleException(e));
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public Alojamiento adicionarAlojamiento(int capacidad, int tipo, long idOperador, long registrocam,
			long registrosup, String ubicacion, String descripcion) throws Exception {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idAlojamiento = nextval();
			long tuplasInsertadas = sqlAlojamiento.adicionarAlojamiento(pm, "" + idAlojamiento, "" + capacidad,
					"" + tipo, "" + idOperador, "" + registrocam, "" + registrosup, ubicacion, descripcion, "", 1);
			tx.commit();
			Alojamiento alojamiento = new Alojamiento(idAlojamiento, capacidad, ubicacion, descripcion, tipo,
					"" + registrocam, "" + registrosup, 1, null, idOperador);
			log.trace("Inserción de alojamiento: " + alojamiento.toString() + " : " + tuplasInsertadas
					+ " tuplas insertadas");
			return alojamiento;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw new Exception(darDetalleException(e));
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public Reserva adicionarReserva(boolean estado, long clienteid, List<Oferta> ofertasid, String idResColectiva)
			throws Exception {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idOferta = nextval();
			int valorTotal = 0;
			for (Oferta o : ofertasid) {
				valorTotal += o.getPrecio();
			}
			int estado2 = 1;
			if (estado == false) {
				estado2 = 0;
			}

			Date hoy = new Date();

			Timestamp fechasql = new Timestamp(hoy.getTime());

			long tuplasInsertadas = sqlReserva.adicionarReserva(pm, "" + idOferta, estado2, valorTotal, clienteid,
					fechasql, null);

			for (Oferta o : ofertasid) {
				tuplasInsertadas += sqlOferta.actualizarReserva(pm, "" + o.getId(), "" + idOferta);
			}
			tx.commit();
			Reserva reserva = new Reserva(idOferta, estado2, valorTotal,
					new java.sql.Date(Calendar.getInstance().getTime().getTime()), null, clienteid);
			log.trace("Inserción de reserva: " + reserva.toString() + " : " + tuplasInsertadas + " tuplas insertadas");
			return reserva;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw new Exception(darDetalleException(e));
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public Operador adicionarOperador(long idOperador, int tipo) throws Exception {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlOperador.adicionarOperador(pm, idOperador, tipo);
			tx.commit();
			Operador operador = new Operador(idOperador, tipo);
			log.trace(
					"Inserción de operador: " + operador.toString() + " : " + tuplasInsertadas + " tuplas insertadas");
			return operador;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw new Exception(darDetalleException(e));

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public Habitacion adicionarHabitacion(long idHabitacion, int capacidad, int tipo, long alojamientoId)
			throws Exception {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlHabitacion.adicionarHorario(pm, idHabitacion, capacidad, tipo, alojamientoId);
			tx.commit();
			Habitacion habitacion = new Habitacion(idHabitacion, capacidad, tipo, alojamientoId);
			log.trace("Inserción de habitacion: " + habitacion.toString() + " : " + tuplasInsertadas
					+ " tuplas insertadas");
			return habitacion;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw new Exception(darDetalleException(e));

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public Horario adicionarHorario(long idHorario, java.sql.Date horaInicio, java.sql.Date horaFin, String diasSemana)
			throws Exception {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlHorario.adicionarHorario(pm, idHorario,
					"TO_DATE(\'" + horaInicio.getTime() + ", \'hh24:mi:ss\')",
					"TO_DATE(\'" + horaFin.getTime() + ", \'hh24:mi:ss\')", diasSemana);
			tx.commit();
			Horario horario = new Horario(idHorario, horaInicio, horaFin, diasSemana);
			log.trace("Inserción de horario: " + horario.toString() + " : " + tuplasInsertadas + " tuplas insertadas");
			return horario;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw new Exception(darDetalleException(e));

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public Menaje adicionarMenaje(long idMenaje, String descripcion, int disponibilidad, long alojamientoId)
			throws Exception {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlMenaje.adicionarMenaje(pm, idMenaje, descripcion, disponibilidad, alojamientoId);
			tx.commit();
			Menaje menaje;
			if (disponibilidad == 1) {
				menaje = new Menaje(idMenaje, descripcion, true, alojamientoId);
			}

			else {
				menaje = new Menaje(idMenaje, descripcion, false, alojamientoId);
			}
			log.trace("Inserción de menaje: " + menaje.toString() + " : " + tuplasInsertadas + " tuplas insertadas");
			return menaje;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw new Exception(darDetalleException(e));

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public Regla adicionarRegla(long idRegla, String descripcion, long alojamientoId) throws Exception {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlRegla.adicionarRegla(pm, idRegla, descripcion, alojamientoId);
			tx.commit();
			Regla regla = new Regla(idRegla, descripcion, alojamientoId);
			log.trace("Inserción de regla: " + regla.toString() + " : " + tuplasInsertadas + " tuplas insertadas");
			return regla;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw new Exception(darDetalleException(e));

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public Servicio adicionarServicio(long idServicio, String descripcion, double costo, long idHorario,
			long alojamientoId) throws Exception {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlServicio.adicionarServicio(pm, idServicio, descripcion, costo, idHorario,
					alojamientoId);
			tx.commit();
			Servicio servicio = new Servicio(idServicio, descripcion, costo, idHorario);
			log.trace(
					"Inserción de servicio: " + servicio.toString() + " : " + tuplasInsertadas + " tuplas insertadas");
			return servicio;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw new Exception(darDetalleException(e));

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public Seguro adicionarSeguro(long idSeguro, String caracteristicas, double costo, java.sql.Date vigencia,
			long alojamientoId) throws Exception {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlSeguro.adicionarSeguro(pm, idSeguro, caracteristicas, costo,
					"TO_DATE(\'" + vigencia + "\', \'YYYY-MM-DD\')", alojamientoId);
			tx.commit();
			Seguro seguro = new Seguro(idSeguro, caracteristicas, costo, vigencia, alojamientoId);
			log.trace("Inserción de seguro: " + seguro.toString() + " : " + tuplasInsertadas + " tuplas insertadas");
			return seguro;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw new Exception(darDetalleException(e));

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * 
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de
	 *         la tabla TIPOBEBIDA
	 */
	public List<Alojamiento> darAlojamientosPorUserId(String idUsuario) {
		Long duration = System.currentTimeMillis();
		List<Alojamiento> resp = sqlAlojamiento.darAlojamientosPorUserId(pmf.getPersistenceManager(), idUsuario);
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * 
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de
	 *         la tabla TIPOBEBIDA
	 */
	public List darReservasPorCliente(long idUsuario) {
		Long duration = System.currentTimeMillis();
		List resp = sqlReserva.darReservasPorCliente(pmf.getPersistenceManager(), idUsuario);
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * 
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de
	 *         la tabla TIPOBEBIDA
	 */
	public Alojamiento darAlojamientoPorId(String idUsuario) {
		Long duration = System.currentTimeMillis();
		Alojamiento resp = sqlAlojamiento.buscarAlojamientoPorId(pmf.getPersistenceManager(), idUsuario);
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un
	 * identificador dado
	 * 
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla
	 *         TIPOBEBIDA con el identificador dado
	 */
	public Usuario darUsuarioPorId(String idUsuario) {
		Long duration = System.currentTimeMillis();
		Usuario resp = sqlUsuario.darUsuarioPorId(pmf.getPersistenceManager(), idUsuario);
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un
	 * identificador dado
	 * 
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla
	 *         TIPOBEBIDA con el identificador dado
	 */
	public Cliente darClientePorId(String idUsuario) {
		Long duration = System.currentTimeMillis();
		Cliente resp = sqlCliente.darClientePorId(pmf.getPersistenceManager(), idUsuario);
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un
	 * identificador dado
	 * 
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla
	 *         TIPOBEBIDA con el identificador dado
	 */
	public Operador darOperadorPorId(String idUsuario) {
		Long duration = System.currentTimeMillis();
		Operador resp = sqlOperador.darOperadorPorId(pmf.getPersistenceManager(), idUsuario);
		System.out.println("Tiempo darOpid: "+(System.currentTimeMillis() - duration));
		return resp;
	}

	public Oferta darOferta(long idOferta) {
		Long duration = System.currentTimeMillis();
		Oferta resp = sqlOferta.darOfertaPorId(pmf.getPersistenceManager(), idOferta);
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Alojamiento> darAlojamientos() {
		Long duration = System.currentTimeMillis();
		List<Alojamiento> resp = sqlAlojamiento.darAlojamientos(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Oferta> darOfertas() {
		Long duration = System.currentTimeMillis();
		List<Oferta> resp = sqlOferta.darOfertas(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Operador> darOperadores() {
		Long duration = System.currentTimeMillis();
		List<Operador> resp = sqlOperador.darOperadores(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Cliente> darClientes() {
		Long duration = System.currentTimeMillis();
		List<Cliente> resp = sqlCliente.darClientes(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Usuario> darUsuarios() {
		Long duration = System.currentTimeMillis();
		List<Usuario> resp = sqlUsuario.darUsuarios(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Reserva> darReservas() {
		Long duration = System.currentTimeMillis();
		List<Reserva> resp = sqlReserva.darReservas(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Habitacion> darHabitaciones() {
		Long duration = System.currentTimeMillis();
		List<Habitacion> resp = sqlHabitacion.darHabitaciones(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Regla> darReglas() {
		Long duration = System.currentTimeMillis();
		List<Regla> resp = sqlRegla.darReglas(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Menaje> darMenajeList() {
		Long duration = System.currentTimeMillis();
		List<Menaje> resp = sqlMenaje.darMenajeList(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Horario> darHorarios() {
		Long duration = System.currentTimeMillis();
		List<Horario> resp = sqlHorario.darHorarios(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Servicio> darServicios() {
		Long duration = System.currentTimeMillis();
		List<Servicio> resp = sqlServicio.darServicios(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Seguro> darSeguros() {
		Long duration = System.currentTimeMillis();
		List<Seguro> resp = sqlSeguro.darSeguros(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List darOfertasPorAlojamiento(long idUsuario) {
		Long duration = System.currentTimeMillis();
		List resp = sqlOferta.darOfertasPorAlojamiento(pmf.getPersistenceManager(), idUsuario);
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Oferta> darOfertasPorFecha(String fecha) {
		Long duration = System.currentTimeMillis();
		List<Oferta> resp = sqlOferta.darOfertasPorFecha(pmf.getPersistenceManager(), fecha);
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	public List<Oferta> darOfertasCapacidad(int capacidad, String dia) throws ParseException {
		Long duration = System.currentTimeMillis();
		List<Oferta> ofertas = darOfertasPorFecha(dia);
		ArrayList<Oferta> ofCap = new ArrayList<Oferta>();
		int capAsignada = 0;

		for (int i = 0; i < ofertas.size() || capAsignada > capacidad; i++) {
			Oferta of = ofertas.get(i);
			ofCap.add(ofertas.get(i));
			capAsignada += darAlojamientoPorId("" + of.getAlojamiento_id()).getCapacidad();
		}

		if (capAsignada <= capacidad) {
			ofCap = null;
		}
		System.out.println(System.currentTimeMillis() - duration);
		return ofCap;
	}

	public String registrarReservaColectiva(long clienteid, int capacidad, String dia) {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		String resp = " Error";
		try {
			tx.begin();
			System.out.println("incio2");
			List<Oferta> ofertas = darOfertasCapacidad(capacidad, dia);
			System.out.println("" + ofertas.size());

			if (ofertas.isEmpty() == true) {
				System.out.println("No se pudo agregar reserva colectiva");
				resp = "No hay capacidad";

			} else {
				System.out.println("Agregar reserva colectiva");
				sqlReservaColectiva.adicionarReserva(pm, "" + sqlUtil.nextval(pm), 2);
				adicionarReserva(false, clienteid, ofertas, "" + sqlUtil.nextval(pm));

			}

			tx.commit();

			return "Se agrego la reserva colectiva";

		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			resp = "Exception : " + e.getMessage() + "\n" + darDetalleException(e);
			return resp;

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}

	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BEBEDOR,
	 * dado el identificador del bebedor Adiciona entradas al log de la aplicación
	 * 
	 * @param idBebedor - El identificador del bebedor
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarReservaPorId(long idBebedor) {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long resp = 0;
			resp += sqlOferta.actualizarReservaNull(pm, "" + idBebedor);
			resp += sqlReserva.eliminarReservaPorId(pm, idBebedor);
			tx.commit();
			return resp;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public String dar20mas() {
		Long duration = System.currentTimeMillis();
		String veinti = sqlOferta.dar20AlojamientosMasPopulares(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return veinti;
	}

	public String indiceOcupacion() {
		Long duration = System.currentTimeMillis();
		String veinti = sqlOferta.indiceOcupacion(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return veinti;
	}

	public String analisisOperacion() {
		Long duration = System.currentTimeMillis();
		String veinti = sqlOferta.indiceOcupacion(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return veinti;
	}

	public String pocaOferta() {
		Long duration = System.currentTimeMillis();
		String veinti = sqlOferta.pocaOferta(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return veinti;
	}

	public String clientesFrecuentes(String idAlojamiento) {
		Long duration = System.currentTimeMillis();
		String veinti = sqlOferta.clientesFrecuentes(pmf.getPersistenceManager(), idAlojamiento);
		System.out.println(System.currentTimeMillis() - duration);
		return veinti;
	}

	public String analisisOperacion(String tipo) {
		Long duration = System.currentTimeMillis();
		String veinti = sqlOferta.analisisOperacion(pmf.getPersistenceManager(), tipo);
		System.out.println(System.currentTimeMillis() - duration);
		return veinti;
	}

	public String ingresosPorOperador() {
		Long duration = System.currentTimeMillis();
		String veinti = sqlOferta.ingresosPorOperador(pmf.getPersistenceManager());
		System.out.println(System.currentTimeMillis() - duration);
		return veinti;
	}

	public List<Alojamiento> darAlojamientosPorDotacion(List<String> dotacion, String inicio, String fin, int size) {
		Long duration = System.currentTimeMillis();
		List<Alojamiento> resp = sqlAlojamiento.darAlojamientoPorDotacion(pmf.getPersistenceManager(), dotacion, inicio,
				fin, size);
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BEBEDOR,
	 * dado el identificador del bebedor Adiciona entradas al log de la aplicación
	 * 
	 * @param idBebedor - El identificador del bebedor
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 * @throws Exception
	 */
	public long eliminarAlojamientoPorId(long Alojamiento) throws Exception {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			long resp = sqlAlojamiento.eliminarAlojamientoPorId(pm, Alojamiento);
			tx.commit();
			return resp;
		} catch (Exception e) {
//        	e.printStackTrace();
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw new Exception(darDetalleException(e));
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}
	}

	public String deshabilitarAlojamiento(Alojamiento alojamiento) {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			String resp = "";
			sqlAlojamiento.deshabilitarAlojamientoPorId(pmf.getPersistenceManager(), alojamiento.getId());
			List<Oferta> ofertas = sqlOferta.darOfertasConReservaPorAlojamiento(pmf.getPersistenceManager(),
					alojamiento.getId());
			sqlOferta.eliminarReservasAlojamiento(pmf.getPersistenceManager(), alojamiento.getId());
			List<Alojamiento> alojamientos = sqlAlojamiento.buscarAlojamientosPorCapacidad(pmf.getPersistenceManager(),
					alojamiento.getCapacidad());
			if (alojamientos.isEmpty()) {
				resp = "No se pueden reubicar las reservas";
				return resp;
			}
			for (int i = 0; i < ofertas.size(); i++) {
				boolean encontro = false;
				for (int j = 0; j < alojamientos.size() && !encontro; j++) {
					try {
						Oferta oferta = sqlOferta.darOfertasSinReservaPorAlojamientoYFecha(pmf.getPersistenceManager(),
								alojamiento.getId(), ofertas.get(i).getDia() + "").get(0);
						encontro = true;
						sqlOferta.actualizarReserva(pmf.getPersistenceManager(), oferta.getId() + "",
								ofertas.get(i).getReserva_id() + "");
						resp += "Se cambio asigno la reserva " + ofertas.get(i).getReserva_id() + " al alojamiento "
								+ ofertas.get(i).getAlojamiento_id() + " con el indice de oferta " + oferta.getId()
								+ "\n";
						alojamientos.remove(j);
					} catch (IndexOutOfBoundsException e) {

					}

				}
				if (!encontro) {
					sqlReserva.eliminarReservaPorId(pmf.getPersistenceManager(), ofertas.get(i).getReserva_id());
					resp += "No se encontro alojamiento para la reserva " + ofertas.get(i).getId() + "\n";
				}
			}
			log.trace(resp);
			tx.commit();
			return resp;
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return "";
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}

	}

	public void habilitarAlojamiento(Long alojamientoId) {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			sqlAlojamiento.habilitarAlojamientoPorId(pmf.getPersistenceManager(), alojamientoId);
			tx.commit();
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
			System.out.println(System.currentTimeMillis() - duration);
		}

	}

	public List<Alojamiento> darAlojamientosPorUserIdNoHabilitados(Long userId) {
		Long duration = System.currentTimeMillis();
		List<Alojamiento> resp = sqlAlojamiento.darAlojamientosPorUserIdNoHabilitados(pmf.getPersistenceManager(),
				userId + "");
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un
	 * identificador dado
	 * 
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla
	 *         TIPOBEBIDA con el identificador dado
	 */
	public Usuario login(String idUsuario, String contra) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Usuario user = sqlUsuario.darUsuarioPorUsuario(pm, idUsuario);
		return user;

	}

	public String darAlojamientoMenorPorSemana(String semana, int anio) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Alojamiento consulta = sqlAlojamiento.darAlojamientoMenorPorSemana(pm, semana, anio);
		if (consulta != null) {
			return "Peor alojamiento: " + consulta.toString() + "\n";
		}
		return "No hay peor alojamiento\n";
	}

	public String darAlojamientoMayorPorSemana(String semana, int anio) {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		Alojamiento consulta = sqlAlojamiento.darAlojamientoMayorPorSemana(pm, semana, anio);
		if (consulta != null) {
			return "Mejor alojamiento: " + consulta.toString() + "\n";
		}
		return "No hay mejor alojamiento\n";
	}

	public String darOperadorMayorPorSemana(String semana, int anio) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Operador consulta = sqlOperador.darOperadorMayorPorSemana(pm, semana, anio);
		if (consulta != null) {
			return "Mejor operador: " + consulta.toString() + "\n";
		}
		return "No hay mejor operador\n";
	}

	public String darOperadorMenorPorSemana(String semana, int anio) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Operador consulta = sqlOperador.darOperadorMenorPorSemana(pm, semana, anio);
		if (consulta != null) {
			return "Mejor operador: " + consulta.toString() + "\n";
		}
		return "No hay mejor operador\n";
	}

	public ArrayList<List<Cliente>> darBuenosClientes(int mes, int anio) {
		Long duration = System.currentTimeMillis();
		PersistenceManager pm = pmf.getPersistenceManager();
		ArrayList<List<Cliente>> resp = new ArrayList<List<Cliente>>();
		resp.add( sqlCliente.darBuenClienteMes(pm, mes, anio));
		resp.add( sqlCliente.darBuenClientesCosto(pm));
		resp.add(sqlCliente.darBuenClienteSuite(pm));
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}
	
	public String consultarConsumo2(long alid,Date fi, Date ff) {
		Long duration = System.currentTimeMillis();
		String resp = "Consumo de alojamiento con id: " + alid + " entre el "+ fi +" y el "+ ff +" \n";
		PersistenceManager pm = pmf.getPersistenceManager();
		Timestamp fechasqlI = new Timestamp(fi.getTime());
		Timestamp fechasqlF = new Timestamp(ff.getTime());
		List<Usuario> listaUsuarios=sqlOferta.consultarConsumo2(pm, alid,fechasqlI,fechasqlF);
		for(Usuario us:listaUsuarios) {
			resp += us.toString() + "\n";
		}
		System.out.println(System.currentTimeMillis() - duration);
		
		return resp;
		
	}

	public String consultarConsumo1(long alid,Date fi, Date ff) {
		Long duration = System.currentTimeMillis();
		String resp = "Consumo de alojamiento con id: " + alid + " entre el "+ fi.toString()+" y el "+ ff.toString() +" \n";
		PersistenceManager pm = pmf.getPersistenceManager();
		Timestamp fechasqlI = new Timestamp(fi.getTime());
		Timestamp fechasqlF = new Timestamp(ff.getTime());
		List<Usuario> listaUsuarios=sqlOferta.consultarConsumo1(pm, alid,fechasqlI,fechasqlF);
		for(Usuario us:listaUsuarios) {
			resp += us.toString() + "\n";
		}
		System.out.println(System.currentTimeMillis() - duration);
		return resp;
	}

}
