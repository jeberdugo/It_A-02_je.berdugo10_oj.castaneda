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

package uniandes.isis2304.alohandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.alohandes.negocio.AlohAndes;
import uniandes.isis2304.alohandes.negocio.Alojamiento;
import uniandes.isis2304.alohandes.negocio.Oferta;
import uniandes.isis2304.alohandes.negocio.Reserva;
import uniandes.isis2304.alohandes.negocio.Usuario;
import uniandes.isis2304.alohandes.persistencia.SQLAlojamiento;

/**
 * Clase principal de la interfaz
 * 
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfazAlohAndes extends JFrame implements ActionListener {
	/*
	 * **************************************************************** Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazAlohAndes.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json";

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json";

	/*
	 * **************************************************************** Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren
	 * utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private AlohAndes alohandes;

	/*
	 * **************************************************************** Atributos de
	 * interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	private long usuarioActivo;
	private int tipoActivo;

	/*
	 * **************************************************************** Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazAlohAndes() {
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame();
		if (guiConfig != null) {
			crearMenu(guiConfig.getAsJsonArray("menuBar"));
		}

		tableConfig = openConfig("Tablas BD", CONFIG_TABLAS);
		alohandes = new AlohAndes(tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos();

		setLayout(new BorderLayout());
		add(new JLabel(new ImageIcon(path)), BorderLayout.NORTH);
		add(panelDatos, BorderLayout.CENTER);
		usuarioActivo = -1;
		tipoActivo = -1;
	}

	/*
	 * **************************************************************** Métodos de
	 * configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o
	 * con valores por defecto si hay errores.
	 * 
	 * @param tipo       - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado NULL si hay
	 *         un error en el archivo.
	 */
	private JsonObject openConfig(String tipo, String archConfig) {
		JsonObject config = null;
		try {
			Gson gson = new Gson();
			FileReader file = new FileReader(archConfig);
			JsonReader reader = new JsonReader(file);
			config = gson.fromJson(reader, JsonObject.class);
			log.info("Se encontró un archivo de configuración válido: " + tipo);
		} catch (Exception e) {
//			e.printStackTrace ();
			log.info("NO se encontró un archivo de configuración válido");
			JOptionPane.showMessageDialog(null,
					"No se encontró un archivo de configuración de interfaz válido: " + tipo, "AlohAndes App",
					JOptionPane.ERROR_MESSAGE);
		}
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame() {
		int alto = 0;
		int ancho = 0;
		String titulo = "";

		if (guiConfig == null) {
			log.info("Se aplica configuración por defecto");
			titulo = "AlohAndes APP Default";
			alto = 300;
			ancho = 500;
		} else {
			log.info("Se aplica configuración indicada en el archivo de configuración");
			titulo = guiConfig.get("title").getAsString();
			alto = guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(50, 50);
		setResizable(true);
		setBackground(Color.WHITE);

		setTitle(titulo);
		setSize(ancho, alto);
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * 
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(JsonArray jsonMenu) {
		// Creación de la barra de menús
		menuBar = new JMenuBar();
		for (JsonElement men : jsonMenu) {
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject();

			String menuTitle = jom.get("menuTitle").getAsString();
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu(menuTitle);

			for (JsonElement op : opciones) {
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject();
				String lb = jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem(lb);
				mItem.addActionListener(this);
				mItem.setActionCommand(event);

				menu.add(mItem);
			}
			menuBar.add(menu);
		}
		setJMenuBar(menuBar);
	}

	/*
	 * ****************************************************************
	 * Requerimientos funcionales de modificacion
	 *****************************************************************/

	public void registrarOperador() {
		try {
			String nombreUsuario = JOptionPane.showInputDialog(this, "Nombre de usuario", "Siguiente",
					JOptionPane.QUESTION_MESSAGE);
			if (nombreUsuario != null) {
				Usuario usuario = alohandes.buscarUsuarioPorUsuario(nombreUsuario);
				if (usuario == null) {
					String correo = JOptionPane.showInputDialog(this, "Correo", "Siguiente",
							JOptionPane.QUESTION_MESSAGE);
					String contrasena = JOptionPane.showInputDialog(this, "Contraseña", "Siguiente",
							JOptionPane.QUESTION_MESSAGE);
					String[] opciones = { "CC", "NIT", "CE" };
					int tipoDocumento = JOptionPane.showOptionDialog(this, "Tipo de documento", "",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
					int numeroDocumento = Integer.parseInt(JOptionPane.showInputDialog(this, "Numero de documento",
							"Siguiente", JOptionPane.QUESTION_MESSAGE));
					usuario = alohandes.adicionarUsuario(nombreUsuario, correo, contrasena, numeroDocumento,
							tipoDocumento);
				}
				String[] tipos = { "Empresa", "cliente" };
				int tipo = JOptionPane.showOptionDialog(this, "Tipo de operador", "", JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
				String tb = alohandes.adicionarOperador(tipo, usuario);
				if (tb == null) {
					throw new Exception("No se pudo crear un operador con nombre: " + nombreUsuario);
				}
				String resultado = "En adicionarTipoBebida\n\n";
				resultado += "Tipo de bebida adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			} else {
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} catch (Exception e) {
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarOferta() {
		try {
			if (usuarioActivo != -1) {
				if (tipoActivo == 1) {
					List<Alojamiento> lista = alohandes.darAlojamientosPorUserId("" + usuarioActivo);
					List<String> listaId = new ArrayList<String>();
					if (lista != null) {
						for (Alojamiento a : lista) {
							listaId.add("" + a.getId());
						}

						String alooid = (String) JOptionPane.showInputDialog(null, "Seleccione Un Alojamiento",
								"Alojamientos", JOptionPane.QUESTION_MESSAGE, null, listaId.toArray(), "Seleccione");
						long alojamientoId = Long.parseLong(alooid);
						System.out.println("" + alojamientoId);

						Properties p = new Properties();
						p.put("text.today", "Today");
						p.put("text.month", "Month");
						p.put("text.year", "Year");
						UtilDateModel model = new UtilDateModel();
						JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
						String message = "Choose start date:\n";
						Object[] params = { message, datePanel };
						JOptionPane.showInputDialog(this, params, "Start date", JOptionPane.PLAIN_MESSAGE);
						String fecha = model.getDay() + "-" + model.getMonth() + "-" + model.getYear();
						System.out.print(fecha);
						String precio = JOptionPane.showInputDialog(this, "Precio", "Final",
								JOptionPane.QUESTION_MESSAGE);

						System.out.print(precio);

						System.out.print(alohandes.adicionarOferta(fecha, Integer.parseInt(precio), alojamientoId));
					}

				} else
					JOptionPane.showMessageDialog(this, "Debe loguearse como operador", "Debe loguearse",
							JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(this, "No esta logueado", "Debe loguearse", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	}

	public void registrarAlojamiento() {
		try {
			if (usuarioActivo != -1) {
				if (tipoActivo == 1) {

					int capacidad = 0;
					int tipo = 0;
					long idOperador = 0;
					long registrocam = 0;
					long registrosup = 0;
					String ubicacion = "";
					String descripcion = "";

					idOperador = usuarioActivo;

					String cap = JOptionPane.showInputDialog(this, "Capacidad", "Siguiente",
							JOptionPane.QUESTION_MESSAGE);
					capacidad += Integer.parseInt(cap);

					String tip = JOptionPane.showInputDialog(this, "Tipo", "Siguiente", JOptionPane.QUESTION_MESSAGE);
					tipo += Integer.parseInt(tip);

					if (tipo == 0 || tipo == 1) {

						String registroc = JOptionPane.showInputDialog(this, "Registro de camara", "Siguiente",
								JOptionPane.QUESTION_MESSAGE);
						registrocam += Long.parseLong(registroc);

						String registros = JOptionPane.showInputDialog(this, "Registro de Super Intendencia",
								"Siguiente", JOptionPane.QUESTION_MESSAGE);
						registrosup += Long.parseLong(registros);

					}

					String ub = JOptionPane.showInputDialog(this, "Ubicacion", "Siguiente",
							JOptionPane.QUESTION_MESSAGE);
					ubicacion += ub;

					String des = JOptionPane.showInputDialog(this, "Descripcion", "Final",
							JOptionPane.QUESTION_MESSAGE);
					descripcion += des;

					String resultado = "";

					resultado += alohandes.adicionaAlojamiento(capacidad, tipo, idOperador, registrocam, registrosup,
							ubicacion, descripcion);
					panelDatos.actualizarInterfaz(resultado);
				} else
					JOptionPane.showMessageDialog(this, "Debe loguearse como operador", "Debe loguearse",
							JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(this, "No esta logueado", "Debe loguearse", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void registrarCliente() {
		try {
			String nombreUsuario = JOptionPane.showInputDialog(this, "Nombre de usuario", "Siguiente",
					JOptionPane.QUESTION_MESSAGE);
			if (nombreUsuario != null) {
				Usuario usuario = alohandes.buscarUsuarioPorUsuario(nombreUsuario);
				if (usuario == null) {
					String correo = JOptionPane.showInputDialog(this, "Correo", "Siguiente",
							JOptionPane.QUESTION_MESSAGE);
					String contrasena = JOptionPane.showInputDialog(this, "Contraseña", "Siguiente",
							JOptionPane.QUESTION_MESSAGE);
					String[] opciones = { "CC", "NIT", "CE" };
					int tipoDocumento = JOptionPane.showOptionDialog(this, "Tipo de documento", "",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
					int numeroDocumento = Integer.parseInt(JOptionPane.showInputDialog(this, "Numero de documento",
							"Siguiente", JOptionPane.QUESTION_MESSAGE));
					usuario = alohandes.adicionarUsuario(nombreUsuario, correo, contrasena, numeroDocumento,
							tipoDocumento);
				}
				String nombre = JOptionPane.showInputDialog(this, "Nombre", "Siguiente", JOptionPane.QUESTION_MESSAGE);
				String[] roles = { "Profesor", "Empleado", "Egresado", "Estudiante", "Padre" };
				int rol = JOptionPane.showOptionDialog(this, "Rol", "", JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, roles, roles[0]);
				String tb = alohandes.adicionarCliente(nombre, rol, usuario);
				if (tb == null) {
					throw new Exception("No se pudo crear un cliente con nombre: " + nombreUsuario);
				}
				String resultado = "En adicionarTipoBebida\n\n";
				resultado += "Tipo de bebida adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			} else {
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} catch (Exception e) {
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarReserva() {
		try {
			if (usuarioActivo != -1) {
				if (tipoActivo == 2) {
					List<Alojamiento> listaA = alohandes.darAlojamientos();
					List<String> listaAlojamientos = new ArrayList<String>();
					List<Oferta> listaOfertasFinal = new ArrayList<Oferta>();

					if (listaA != null) {
						for (Alojamiento a : listaA) {
							listaAlojamientos.add("" + a.getId());
						}
					}

					String alooid = (String) JOptionPane.showInputDialog(null, "Seleccione Un Alojamiento",
							"Alojamientos", JOptionPane.QUESTION_MESSAGE, null, listaAlojamientos.toArray(),
							"Seleccione");
					long alojamientoId = Long.parseLong(alooid);
					if(alohandes.darAlojamientoPorId(alojamientoId).getHabilitado()==1) {
						String nDias = JOptionPane.showInputDialog(this, "Dias a reservar", "Siguiente",
								JOptionPane.QUESTION_MESSAGE);
						int dias = Integer.parseInt(nDias);

						List<String> listaO = alohandes.darOfertasPorAlojamiento(alojamientoId);
						List<String> listaOfertas = new ArrayList<String>();

						for (int i = 1; i <= dias; i++) {

							BigDecimal ofid = (BigDecimal) JOptionPane.showInputDialog(null, "Seleccione Oferta Dia " + i,
									"Ofertas", JOptionPane.QUESTION_MESSAGE, null, listaO.toArray(), "Seleccione");
							long ofertaId = ofid.longValue();
							Oferta of = alohandes.darOferta(ofertaId);
							System.out.println(of);
							listaOfertasFinal.add(of);
							listaOfertas.remove(ofid);

						}

						alohandes.adicionarReserva(usuarioActivo, listaOfertasFinal);
					}
					else {
						JOptionPane.showMessageDialog(this, "Alojamiento deshabilitado", "Alojamiento deshabilitado",
								JOptionPane.ERROR_MESSAGE);		
					}

				} else
					JOptionPane.showMessageDialog(this, "Debe loguearse como cliente", "Debe loguearse como cliente",
							JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(this, "No esta logueado", "Debe loguearse", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	public void cancelarReserva() {
		if (usuarioActivo != -1) {
			if (tipoActivo == 2) {
				List listaA = alohandes.darReservasPorCliente(usuarioActivo);
				List<String> listaAlojamientos = new ArrayList<String>();

				if (listaA != null) {
					for (int i = 0; i < listaA.size(); i++) {
						String tmp = "";
						tmp += listaA.get(i);
						listaAlojamientos.add("" + tmp);
					}
				}

				BigDecimal alooid = (BigDecimal) JOptionPane.showInputDialog(null, "Seleccione una Reserva",
						"Eliminar Reserva", JOptionPane.QUESTION_MESSAGE, null, listaA.toArray(), "Seleccione");
				if (alooid != null) {
					long resId = alooid.longValue();

					alohandes.eliminarReserva(resId);

					panelDatos.actualizarInterfaz("Se elimino la reserva con id: " + resId);
				} else
					panelDatos.actualizarInterfaz("No se pudo cancelar la reserva");
			} else
				JOptionPane.showMessageDialog(this, "Debe loguearse como cliente", "Debe loguearse como cliente",
						JOptionPane.ERROR_MESSAGE);
		} else
			JOptionPane.showMessageDialog(this, "No esta logueado", "Debe loguearse", JOptionPane.ERROR_MESSAGE);

	}

	public void retirarAlojamiento() {
		try {
			if (usuarioActivo != -1) {
				List<Alojamiento> lista = alohandes.darAlojamientosPorUserId("" + usuarioActivo);
				List<String> listaId = new ArrayList<String>();
				if (lista != null) {
					for (Alojamiento a : lista) {
						listaId.add("" + a.getId());
					}

					String alooid = (String) JOptionPane.showInputDialog(null, "Seleccione Un Alojamiento",
							"Alojamientos", JOptionPane.QUESTION_MESSAGE, null, listaId.toArray(), "Seleccione");
					long alojamientoId = Long.parseLong(alooid);

					if (alohandes.eliminarAlojamientoPorId(alojamientoId) != -1) {
						panelDatos.actualizarInterfaz("Eliminado alojamiento con id " + alojamientoId);
					} else
						JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
				}

			} else
				JOptionPane.showMessageDialog(this, "No esta logueado", "Debe loguearse", JOptionPane.ERROR_MESSAGE);
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	}

	public void infoUsuario() {
		if (usuarioActivo != -1) {

			String alojamientos = "";
			String tipo = "";
			if (tipoActivo == 1) {
				tipo = "OPERADOR";

			}
			if (tipoActivo == 2) {
				tipo = "CLIENTE";
				List listaA = alohandes.darReservasPorCliente(usuarioActivo);
				List<String> listaAlojamientos = new ArrayList<String>();

				if (listaA != null) {
					for (int i = 0; i < listaA.size(); i++) {
						String tmp = "";
						alojamientos += listaA.get(i) + "\n";

					}
				}
			}

			else
				alojamientos = "no hay";

			Usuario user = alohandes.buscarUsuarioPorId("" + usuarioActivo);
			if (user != null) {
				if (tipoActivo == 1) {
					JOptionPane.showMessageDialog(this,
							"ID: " + user.getId() + "\nUsuario: " + user.getUsuario() + "\nTipo: " + tipo + "\nCorreo: "
									+ user.getCorreo() + "\nDocumento: " + user.getTipo_Documento() + " "
									+ user.getNumero_Documento(),
							"Informacion Usuario", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(this,
							"ID: " + user.getId() + "\nUsuario: " + user.getUsuario() + "\nTipo: " + tipo + "\nCorreo: "
									+ user.getCorreo() + "\nDocumento: " + user.getTipo_Documento() + " "
									+ user.getNumero_Documento() + "\nReservas: \n" + alojamientos,
							"Informacion Usuario", JOptionPane.INFORMATION_MESSAGE);

			}

		} else
			JOptionPane.showMessageDialog(this, "No esta logueado", "Debe loguearse", JOptionPane.ERROR_MESSAGE);

	}
	
	public void registrarReservaColectiva() {
		   
		   
		 if(usuarioActivo!=-1)
		 {
			 if(tipoActivo==2) {
			 				 
			 Properties p = new Properties();
			 p.put("text.today", "Today");
			 p.put("text.month", "Month");
			 p.put("text.year", "Year");
			 UtilDateModel model = new UtilDateModel();
			 JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
			 String message ="Choose start date:\n";
			 Object[] params = {message,datePanel};
			 JOptionPane.showInputDialog(this,params,"Start date", JOptionPane.PLAIN_MESSAGE);
			 String fecha =model.getDay()+"-"+model.getMonth()+"-"+model.getYear();
			 System.out.print(fecha);
			 String cap = JOptionPane.showInputDialog(this, "Capacidad Solicitada", "Capacidad",
						JOptionPane.QUESTION_MESSAGE);
			 
			 int capacidad = Integer.parseInt(cap);
			 
			 	try {
					
					panelDatos.actualizarInterfaz(alohandes.adicionarReservaColectiva(usuarioActivo, capacidad, fecha));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					 panelDatos.actualizarInterfaz(e.getMessage());
				}
			 
			
				
			

			 }else
				 JOptionPane.showMessageDialog(this,"Debe loguearse como cliente","Debe loguearse",JOptionPane.ERROR_MESSAGE);
		 }
		 else
			 JOptionPane.showMessageDialog(this,"No esta logueado","Debe loguearse",JOptionPane.ERROR_MESSAGE);
			
			 
  
  
}

public void cancelarReservaColectiva() {
  
}

	public void logout() {
		if (usuarioActivo != -1) {
			usuarioActivo = -1;
			tipoActivo = -1;

			panelDatos.actualizarInterfaz("Se ha cerrado sesion");
		} else
			JOptionPane.showMessageDialog(this, "No esta logueado", "Debe loguearse", JOptionPane.ERROR_MESSAGE);

	}

	public void login() {
		JPasswordField pf = new JPasswordField();
		String user = JOptionPane.showInputDialog(this, "Ingresa el usuario", "Login", JOptionPane.QUESTION_MESSAGE);
		JOptionPane.showConfirmDialog(this, pf, "Enter Password", JOptionPane.CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (user != null) {
			if (pf.getPassword() != null) {
				String invalido = "";
				String password = new String(pf.getPassword());
				Usuario usuario = alohandes.login(user, password);
				if (usuario == null)
					invalido = " No es posible porque no existe el usuario o la clave es incorrecta";
				else {
					usuarioActivo = usuario.getId();
					invalido = "correcto id:" + usuario.getId();
					if (alohandes.buscarOperadorPorId("" + usuarioActivo) != null) {
						tipoActivo = 1;
					}
					if (alohandes.buscarClientePorId("" + usuarioActivo) != null) {
						tipoActivo = 2;
					}
				}

				String resultado = " Login de usuario: " + user + " es " + invalido;
				panelDatos.actualizarInterfaz(resultado);
			} else
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
		} else
			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");

	}

	

	public void deshabilitarAlojamiento() {
		if (usuarioActivo != -1) {
			if (tipoActivo == 1) {
				List<Alojamiento> listaA = alohandes.darAlojamientosPorUserId(usuarioActivo + "");
				Alojamiento alojamiento = (Alojamiento) JOptionPane.showInputDialog(null, "Seleccione un alojamiento",
						"Deshabilitar alojamiento", JOptionPane.QUESTION_MESSAGE, null, listaA.toArray(), "Seleccione");
				panelDatos.actualizarInterfaz(alohandes.deshabilitarAlojamiento(alojamiento)+"Se deshabilito el alojamiento con id: " + alojamiento.getId());
			} else
				JOptionPane.showMessageDialog(this, "Debe loguearse como cliente", "Debe loguearse como cliente",
						JOptionPane.ERROR_MESSAGE);
		} else
			JOptionPane.showMessageDialog(this, "No esta logueado", "Debe loguearse", JOptionPane.ERROR_MESSAGE);
	}

	public void habilitarAlojamiento() {
		if (usuarioActivo != -1) {
			if (tipoActivo == 1) {
				List<Alojamiento> listaA = alohandes.darAlojamientosDeshabilitadosPorUserId(usuarioActivo);
				Alojamiento alojamiento = (Alojamiento) JOptionPane.showInputDialog(null, "Seleccione un alojamiento",
						"Habilitar alojamiento", JOptionPane.QUESTION_MESSAGE, null, listaA.toArray(), "Seleccione");
				alohandes.habilitarAlojamiento(alojamiento.getId());
				panelDatos.actualizarInterfaz("Se habilito el alojamiento con id: " + alojamiento.getId());
			} else
				JOptionPane.showMessageDialog(this, "Debe loguearse como cliente", "Debe loguearse como cliente",
						JOptionPane.ERROR_MESSAGE);
		} else
			JOptionPane.showMessageDialog(this, "No esta logueado", "Debe loguearse", JOptionPane.ERROR_MESSAGE);
	}

	

	public void dineroPorProveedor() {

		panelDatos.actualizarInterfaz(alohandes.darIngresosPorOperador());
	}

	public void ofertasPopulares() {
		panelDatos.actualizarInterfaz(alohandes.dar20());
	}
	
	

	public void indiceOcupacion() {

		panelDatos.actualizarInterfaz(alohandes.darIndiceOcupacion());
	}

	public void alojamientosPorDotacion() {
		String ai = JOptionPane.showInputDialog(this, "Ingrese el año YYYY de la fecha inicial", "Ok",
				JOptionPane.QUESTION_MESSAGE);
		String mi = JOptionPane.showInputDialog(this, "Ingrese el mes MM de la fecha inicial", "Ok",
				JOptionPane.QUESTION_MESSAGE);
		String di = JOptionPane.showInputDialog(this, "Ingrese el dia DD de la fecha inicial", "Ok",
				JOptionPane.QUESTION_MESSAGE);
		String inicio = ai + "-" + mi + "-" + di;
		String af = JOptionPane.showInputDialog(this, "Ingrese el año YYYY de la fecha final", "Ok",
				JOptionPane.QUESTION_MESSAGE);
		String mf = JOptionPane.showInputDialog(this, "Ingrese el mes MM fecha final", "Ok",
				JOptionPane.QUESTION_MESSAGE);
		String df = JOptionPane.showInputDialog(this, "Ingrese el dia DD fecha final", "Ok",
				JOptionPane.QUESTION_MESSAGE);
		String fin = af + "-" + mf + "-" + df;
		int limite = Integer.parseInt(JOptionPane.showInputDialog(this,
				"Ingrese la cantidad de elementos para dotacion", "Ok", JOptionPane.QUESTION_MESSAGE));
		List<String> dotacion = new ArrayList<String>();
		for (int i = 0; i < limite; i++) {
			dotacion.add(JOptionPane.showInputDialog(this, "Ingrese la descripcion de la dotacion", "Ok",
					JOptionPane.QUESTION_MESSAGE));
		}
		panelDatos.actualizarInterfaz(alohandes.darAlojamientosPorDotacion(dotacion, inicio, fin, limite).toString());

	}

	public void usoPorTipo() {

	}

	public void usoPorUsuario() {

	}

	public void analisisOperacion() {
		
		String[] tipos=new String[5];
		tipos[0]="1";
		tipos[1]="2";
		tipos[2]="3";
		tipos[3]="4";
		tipos[4]="5";
		
		String alooid = (String) JOptionPane.showInputDialog(null, "Tipo",
				"Alojamientos", JOptionPane.QUESTION_MESSAGE, null,tipos, "Seleccione");
		
		panelDatos.actualizarInterfaz(alohandes.analisisOp(alooid));

	}

	public void clientesFrecuentes() {
		List<Alojamiento> lista = alohandes.darAlojamientosPorUserId("" + usuarioActivo);
		List<String> listaId = new ArrayList<String>();
		if (lista != null) {
			for (Alojamiento a : lista) {
				listaId.add("" + a.getId());
			}

			String alooid = (String) JOptionPane.showInputDialog(null, "Seleccione Un Alojamiento",
					"Alojamientos", JOptionPane.QUESTION_MESSAGE, null, listaId.toArray(), "Seleccione");
		
			
			panelDatos.actualizarInterfaz(alohandes.dar20(alooid));
		}
		

	}
	
	public void consultarFuncionamiento() {
		panelDatos.actualizarInterfaz(alohandes.consultarFuncionamiento());
	}
	
	public void consultarLosBuenosClientes() {
		panelDatos.actualizarInterfaz(alohandes.consultarLosBuenosClientes());
	}

	public void ofertasPocaDemanda() {
		
		panelDatos.actualizarInterfaz(alohandes.pocaOferta());

	}

	/*
	 * **************************************************************** Métodos
	 * administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de AlohAndes
	 */
	public void mostrarLogAlohAndes() {
		mostrarArchivo("alohandes.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus() {
		mostrarArchivo("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de alohandes Muestra en el panel de datos la
	 * traza de la ejecución
	 */
	public void limpiarLogAlohAndes() {
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo("alohandes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de alohandes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus Muestra en el panel de datos la
	 * traza de la ejecución
	 */
	public void limpiarLogDatanucleus() {
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de alohandes
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD() {
		try {
			// Ejecución de la demo y recolección de los resultados
			long eliminados[] = alohandes.limpiarAlohAndes();

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados[0] + " Gustan eliminados\n";
			resultado += eliminados[1] + " Sirven eliminados\n";
			resultado += eliminados[2] + " Visitan eliminados\n";
			resultado += eliminados[3] + " Bebidas eliminadas\n";
			resultado += eliminados[4] + " Tipos de bebida eliminados\n";
			resultado += eliminados[5] + " Bebedores eliminados\n";
			resultado += eliminados[6] + " Bares eliminados\n";
			resultado += "\nLimpieza terminada";

			panelDatos.actualizarInterfaz(resultado);
		} catch (Exception e) {
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral() {
		mostrarArchivo("data/00-ST-AlohAndesJDO.pdf");
	}

	/**
	 * Muestra el modelo conceptual de AlohAndes
	 */
	public void mostrarModeloConceptual() {
		mostrarArchivo("data/Modelo Conceptual AlohAndes.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de AlohAndes
	 */
	public void mostrarEsquemaBD() {
		mostrarArchivo("data/Esquema BD AlohAndes.pdf");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD() {
		mostrarArchivo("data/EsquemaAlohAndes.sql");
	}

	/**
	 * Muestra la arquitectura de referencia para AlohAndes
	 */
	public void mostrarArqRef() {
		mostrarArchivo("data/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc() {
		mostrarArchivo("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe() {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: AlohAndes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e,
	 * haciendo énfasis en las excepcionsde JDO
	 * 
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es
	 *         javax.jdo.JDODataStoreException, "" de lo contrario
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
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * 
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) {
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y alohandes.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * 
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(new File(nombreArchivo)));
			bw.write("");
			bw.close();
			return true;
		} catch (IOException e) {
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * 
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo(String nombreArchivo) {
		try {
			Desktop.getDesktop().open(new File(nombreArchivo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * **************************************************************** Métodos de
	 * la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos
	 * de negocio Invoca al método correspondiente según el evento recibido
	 * 
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento) {
		String evento = pEvento.getActionCommand();
		try {
			Method req = InterfazAlohAndes.class.getMethod(evento);
			req.invoke(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int diferenciaDias(String fechaF, String fechaI) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int dias = 0;

		try {
			Date fechaInicial = dateFormat.parse(fechaI);
			Date fechaFinal = dateFormat.parse(fechaF);
			dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dias;
	}

	/*
	 * **************************************************************** Programa
	 * principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * 
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main(String[] args) {
		try {

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			InterfazAlohAndes interfaz = new InterfazAlohAndes();
			interfaz.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
