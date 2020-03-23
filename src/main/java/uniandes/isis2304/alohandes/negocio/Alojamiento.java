package uniandes.isis2304.alohandes.negocio;

public class Alojamiento implements VOAlojamiento {

	private long id;
	
	private int capacidad;
	
	private String ubicacion;
	
	private String descripcion;
	
	private int tipo;
	
	private long registroCam;
	
	private long registroSup;
	
	private long idOperador;
	
	
	public Alojamiento() {
		
		 this.id=0;
		
		 this.capacidad=0;
		
		 this.ubicacion="";
		
		 this.descripcion="";
		
		 this.tipo=0;
		
		 this.registroCam=0;
		
		 this.registroSup=0;
		 
		 this.setIdOperador(0);
		 
		 
	}
	public Alojamiento(long id, int capacidad, String ubicacion, String descripcion, int tipo, long regC, long regS, long idOperador) {
		
		 this.id=id;
		
		 this.capacidad=capacidad;
		
		 this.ubicacion=ubicacion;
		
		 this.descripcion=descripcion;
		
		 this.tipo=tipo;
		
		 this.registroCam=regC;
		
		 this.registroSup=regS;
	}
	
	
	
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		this.id=id;
	}

	@Override
	public int getCapacidad() {
		// TODO Auto-generated method stub
		return this.capacidad;
	}

	@Override
	public void setCapacidad(int capacidad) {
		// TODO Auto-generated method stub
		this.capacidad=capacidad;
		
	}

	@Override
	public String getUbicacion() {
		// TODO Auto-generated method stub
		return this.ubicacion;
	}

	@Override
	public void setUbicacion(String ubicacion) {
		// TODO Auto-generated method stub
		this.ubicacion=ubicacion;
	}

	@Override
	public String getDescripcion() {
		// TODO Auto-generated method stub
		return this.descripcion;
	}

	@Override
	public void setDescripcion(String descripcion) {
		// TODO Auto-generated method stub
		this.descripcion=descripcion;
	}

	@Override
	public int getTipo() {
		// TODO Auto-generated method stub
		return this.tipo;
	}

	@Override
	public void setTipo(int tipo) {
		// TODO Auto-generated method stub
		this.tipo=tipo;
	}

	
	public long getIdOperador() {
		return idOperador;
	}
	public void setIdOperador(long idOperador) {
		this.idOperador = idOperador;
	}
	public long getRegistroCam() {
		return registroCam;
	}
	public void setRegistroCam(long registroCam) {
		this.registroCam = registroCam;
	}
	public long getRegistroSup() {
		return registroSup;
	}
	public void setRegistroSup(long registroSup) {
		this.registroSup = registroSup;
	}

}
