package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Usuario;

public class SQLUsuario {
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
	public SQLUsuario(PersistenciaAlohAndes pa) {
		this.pa = pa;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un USUARIO a la base de datos
	 * de AlohAndes
	 * 
	 * @param pm              - El manejador de persistencia
	 * @param idUsuario       - El id del usuario
	 * @param nombreUsuario   - El nommbre de usuario del usuario
	 * @param correo          - El correo del usuario
	 * @param contrasena      - La contraseña del usuario
	 * @param numeroDocumento - El numero de documento del usuario
	 * @return El número de tuplas insertadas
	 */
	public long adicionarUsuario(PersistenceManager pm, long idUsuario, String usuario, String correo,
			String contrasena, int numeroDocumento, int tipoDocumento) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaUsuario()
				+ "(id, usuario, correo, contrasena, numero_documento, tipo_documento) values (?, ?, ?, ?, ?, ?)");
		q.setParameters(idUsuario, usuario, correo, contrasena, numeroDocumento, tipoDocumento);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar USUARIOS de la base de datos de
	 * AlohAndes, por su id
	 * 
	 * @param pm        - El manejador de persistencia
	 * @param idUsuario - El id del usuario
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarUsuarioPorId(PersistenceManager pm, long idUsuario) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaUsuario() + " WHERE id = ?");
		q.setParameters(idUsuario);
		return (long) q.executeUnique();
	}
	
	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS USUARIOS
	 * de la base de datos de AlohAndes
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos USUARIO
	 */
	public List<Usuario> darUsuarios(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaUsuario());
		q.setResultClass(Usuario.class);
		return (List<Usuario>) q.executeList();
	}
	
	
	public Usuario darUsuarioPorUsuario (PersistenceManager pm, String idUsuario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaUsuario() + " WHERE USUARIO Like ?");
		q.setResultClass(Usuario.class);
		q.setParameters(idUsuario);
		return (Usuario) q.executeUnique();
	}

}
