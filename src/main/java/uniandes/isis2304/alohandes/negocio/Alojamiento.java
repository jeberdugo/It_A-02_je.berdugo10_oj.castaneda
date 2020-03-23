package uniandes.isis2304.alohandes.negocio;

public class Alojamiento implements VOAlojamiento {

	private long id;
	
	private int capacidad;
	
	private String ubicacion;
	
	private String descripcion;
	
	private int tipo;
	
	private long registroCamara;
	
	private long registroSuperI;
	
	private long idOperador;
	
	
	public Alojamiento() {
		
		 this.id=0;
		
		 this.capacidad=0;
		
		 this.ubicacion="";
		
		 this.descripcion="";
		
		 this.tipo=0;
		
		 this.registroCamara=0;
		
		 this.registroSuperI=0;
		 
		 this.setIdOperador(0);
		 
		 
	}
	public Alojamiento(long id, int capacidad, String ubicacion, String descripcion, int tipo, long regC, long regS, long idOperador) {
		
		 this.id=id;
		
		 this.capacidad=capacidad;
		
		 this.ubicacion=ubicacion;
		
		 this.descripcion=descripcion;
		
		 this.tipo=tipo;
		
		 this.registroCamara=regC;
		
		 this.registroSuperI=regS;
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

	@Override
	public long getRegistroCamara() {
		// TODO Auto-generated method stub
		return this.registroCamara;
	}

	@Override
	public void setRegistroCamara(long registroCamara) {
		// TODO Auto-generated method stub
		this.registroCamara=registroCamara;
	}

	@Override
	public long getRegistroSuperI() {
		// TODO Auto-generated method stub
		return this.registroSuperI;
	}

	@Override
	public void setRegistroSuperI(long registroSuperI) {
		// TODO Auto-generated method stub
		this.registroSuperI=registroSuperI;
	}
	public long getIdOperador() {
		return idOperador;
	}
	public void setIdOperador(long idOperador) {
		this.idOperador = idOperador;
	}

}
