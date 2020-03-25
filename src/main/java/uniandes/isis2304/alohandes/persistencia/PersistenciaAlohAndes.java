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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	/*
	 * **************************************************************** Métodos del
	 * MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaAlohAndes() {
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
		}
	}

	public Usuario adicionarUsuario(String nombreUsuario, String correo, String contrasena, int numeroDocumento,
			int tipoDocumento) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idUsuario = nextval();
			long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, idUsuario, nombreUsuario, correo, contrasena,
					numeroDocumento, tipoDocumento);
			tx.commit();
			log.trace("Inserción de usuario: " + nombreUsuario + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Usuario(idUsuario, nombreUsuario, correo, contrasena, numeroDocumento, tipoDocumento);
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public Cliente adicionarCliente(long idCliente, String nombre, int rol) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlCliente.adicionarCliente(pm, idCliente, nombre, rol);
			tx.commit();
			log.trace("Inserción de cliente: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Cliente(idCliente, nombre, rol,null);
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public Oferta adicionarOferta(String dia, int precio, long alojamientoid) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idOferta = nextval();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fecha=sdf.parse(dia);
			Timestamp fechasql=new Timestamp(fecha.getTime());;
			long tuplasInsertadas = sqlOferta.adicionarOferta(pm, ""+idOferta, fechasql, precio,""+alojamientoid);
			tx.commit();
			
			log.trace("Inserción de cliente: " + idOferta + ": " + tuplasInsertadas + " tuplas insertadas");
			
			return new Oferta(idOferta, fecha, precio,null,darAlojamientoPorId(""+alojamientoid));
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	public List<Oferta> darOfertas(){
		
		PersistenceManager pm = pmf.getPersistenceManager();
		
			List<Oferta> lista=sqlOferta.darOfertas(pmf.getPersistenceManager());
			

			return lista;
		
		
	}
	
public Oferta darOferta(){
		
		PersistenceManager pm = pmf.getPersistenceManager();
		long id=1;
		
			Oferta lista=sqlOferta.darOfertaPorId(pmf.getPersistenceManager(),id);
			

			return lista;
		
		
	}
	
	public Reserva adicionarReserva( boolean estado, long clienteid, List<Oferta>ofertasid) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idOferta = nextval();
			int valorTotal=0;
			for(Oferta o:ofertasid) {
				valorTotal+=o.getPrecio();
			}
			int estado2=1;
			if(estado==true) {
				estado2=0;
			}
				
			long tuplasInsertadas = sqlReserva.adicionarReserva(pm, ""+idOferta, estado2, valorTotal,clienteid);
			
			
					for(Oferta o:ofertasid) {
						tuplasInsertadas+=sqlOferta.actualizarReserva(pm, ""+o.getId(),""+ idOferta);
					}
			tx.commit();
			
			log.trace("Inserción de cliente: " + idOferta + ": " + tuplasInsertadas + " tuplas insertadas");
			
			return new Reserva(idOferta, estado,valorTotal,darClientePorId(""+clienteid),ofertasid);
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public Alojamiento adicionarAlojamiento(int capacidad, int  tipo, long idOperador, long registrocam, long registrosup, String ubicacion, String descripcion) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long idAlojamiento = nextval();
			long tuplasInsertadas = sqlAlojamiento.adicionarAlojamiento(pm, ""+idAlojamiento, ""+capacidad, ""+tipo, ""+idOperador, ""+registrocam, ""+registrosup, ubicacion, descripcion);
			tx.commit();
			log.trace("Inserción de alojamiento: " + idAlojamiento + ": " + tuplasInsertadas + " tuplas insertadas");
			List<Habitacion> habitaciones= new ArrayList<Habitacion>();
			Seguro seguro=new Seguro();
			List<Servicio> servicios= new ArrayList<Servicio>();
			List<Regla> reglas= new ArrayList<Regla>();
			List<Menaje> menaje= new ArrayList<Menaje>();
			List<Oferta> ofertas= new ArrayList<Oferta>();
			
			return new Alojamiento(idAlojamiento, capacidad,  ubicacion,  descripcion,  tipo, ""+registrocam, ""+ registrosup,  darOperadorPorId(""+idOperador));
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public Operador adicionarOperador(long idOperador, int tipo) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasInsertadas = sqlOperador.adicionarOperador(pm, idOperador, tipo);
			tx.commit();
			log.trace("Inserción de operador: " + tipo + ": " + tuplasInsertadas + " tuplas insertadas");
			List<Alojamiento> lista=new ArrayList<Alojamiento>();
			return new Operador(idOperador, tipo,lista);
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BEBEDOR, dado el identificador del bebedor
	 * Adiciona entradas al log de la aplicación
	 * @param idBebedor - El identificador del bebedor
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAlojamientoPorId (long Alojamiento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlAlojamiento.eliminarAlojamientoPorId(pm, Alojamiento);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BEBEDOR, dado el identificador del bebedor
	 * Adiciona entradas al log de la aplicación
	 * @param idBebedor - El identificador del bebedor
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarReservaPorId (long idBebedor) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReserva.eliminarReservaPorId(pm, idBebedor);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Usuario login (String idUsuario,String contra)
	{
		boolean exito=false;
		PersistenceManager pm = pmf.getPersistenceManager();
		Usuario user= sqlUsuario.darUsuarioPorUsuario(pm, idUsuario);
		
		
		return user;
		
	}
	
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Alojamiento> darAlojamientosPorUserId (String idUsuario)
	{
		return sqlAlojamiento.darAlojamientosPorUserId(pmf.getPersistenceManager(), idUsuario);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public Alojamiento darAlojamientoPorId (String idUsuario)
	{
		return sqlAlojamiento.buscarAlojamientoPorId(pmf.getPersistenceManager(), idUsuario);
	}
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Usuario darUsuarioPorId (String idUsuario)
	{
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Usuario user= sqlUsuario.darUsuarioPorId(pm, idUsuario);
		
		
		return user;
		
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Cliente darClientePorId (String idUsuario)
	{
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Cliente user= sqlCliente.darClientePorId(pm, idUsuario);
		
		
		return user;
		
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Operador darOperadorPorId (String idUsuario)
	{
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Operador user= sqlOperador.darOperadorPorId(pm, idUsuario);
		
		
		return user;
		
	}

}
