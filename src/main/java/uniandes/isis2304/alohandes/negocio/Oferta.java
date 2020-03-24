package uniandes.isis2304.alohandes.negocio;

import java.util.Date;

public class Oferta implements VOOferta {
	private long id;
	private Date dia;
	private int precio;
	private long idReserva;
	private long alojamientoid;
	
	public Oferta() {
		this.id=0;
		this.dia=null;
		precio=0;
	}
	public Oferta(long id, Date dia, int precio,long alojamientoid) {
		this.id=id;
		this.dia=dia;
		this.precio=precio;
		this.alojamientoid=alojamientoid;
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
	public int getPrecio() {
		// TODO Auto-generated method stub
		return this.precio;
	}

	@Override
	public void setPrecio(int precio) {
		// TODO Auto-generated method stub
		this.precio=precio;
	}

	@Override
	public Date getDia() {
		// TODO Auto-generated method stub
		return this.dia;
	}

	@Override
	public void setDia(Date dia) {
		// TODO Auto-generated method stub
		this.dia=dia;
		
	}

	
	public long getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(long idReserva) {
		this.idReserva = idReserva;
	}
	public long getAlojamientoid() {
		return alojamientoid;
	}
	public void setAlojamientoid(long alojamientoid) {
		this.alojamientoid = alojamientoid;
	}

}
