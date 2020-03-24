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

package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohAndes;


/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author Germán Bravo
 */
public class AlohAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(AlohAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaAlohAndes pp;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public AlohAndes ()
	{
		pp = PersistenciaAlohAndes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public AlohAndes (JsonObject tableConfig)
	{
		pp = PersistenciaAlohAndes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}

	public String adicionarCliente(String nombre, int rol, Usuario usuario) {
		String respuesta = usuario.toString();
		respuesta += pp.adicionarCliente(usuario.getId(), nombre, rol).toString();
		return respuesta;
	}
	
	public Usuario buscarUsuarioPorUsuario(String usuario) {
		return pp.buscarUsuarioPorUsuario(usuario);
	}
	public Usuario buscarUsuarioPorId(String idUsuario) {
		return pp.darUsuarioPorId(idUsuario);
	}
	
	public Cliente buscarClientePorId(String idUsuario) {
		return pp.darClientePorId(idUsuario);
	}
	
	public Operador buscarOperadorPorId(String idUsuario) {
		return pp.darOperadorPorId(idUsuario);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Alojamiento> darAlojamientosPorUserId (String idUsuario)
	{
		return pp.darAlojamientosPorUserId( idUsuario);
	}

	public Usuario adicionarUsuario(String nombreUsuario, String correo, String contrasena,
			int numeroDocumento, int tipoDocumento) {
		return pp.adicionarUsuario(nombreUsuario, correo, contrasena, numeroDocumento, tipoDocumento);
	}
	
	public String adicionarOperador(int tipo, Usuario usuario) {
		String respuesta = usuario.toString();
		respuesta += pp.adicionarOperador(usuario.getId(), tipo).toString();
		return respuesta;
	}
	
	public String adicionarOferta(String dia, int precio, long alojamientoid) {
		String respuesta = "";
		respuesta += pp.adicionarOferta(dia, precio, alojamientoid);
		return respuesta;
	}
	
	
	public String adicionarReserva( long clienteid, List<Oferta>ofertasid) {
		String respuesta = "";
		respuesta += pp.adicionarReserva(1, clienteid, ofertasid);
		return respuesta;
	}
	
	public List<Oferta> darOfertas(){
		return pp.darOfertas();
	}
	
	public Oferta darOferta(){
		return pp.darOferta();
	}
	
	
	public String adicionaAlojamiento(int capacidad, int  tipo, long idOperador, long registrocam, long registrosup, String ubicacion, String descripcion) {
		String respuesta = "";
		respuesta += "Capacidad: "+capacidad+"Tipo: " +  tipo+ "Idop: " + idOperador+ "RegCam: " + registrocam+ "RegSup: " + registrosup+ "Ubicacion: " +ubicacion+ "Descripcion: " +descripcion;
		pp.adicionarAlojamiento(capacidad,   tipo, idOperador,  registrocam, registrosup, ubicacion, descripcion);
		return respuesta;
	}
	
	/**
	 * RF1 - Registra un rol en la plataforma
	 */
	public Usuario login(String idUsuario, String contra) 
	{
		
		log.info("Intento de login de usuario: " + idUsuario);
 
		Usuario exito=null;
		Usuario user=pp.login(idUsuario, contra);
		
             if(user.getContrasena().equals(contra))
            	 exito=user;
		
		
		

		return exito;
	}
	
	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de AlohAndes
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarAlohAndes ()
	{
        log.info ("Limpiando la BD de AlohAndes");
        long [] borrrados = pp.limpiarAlohAndes();	
        log.info ("Limpiando la BD de AlohAndes: Listo!");
        return borrrados;
	}
}
