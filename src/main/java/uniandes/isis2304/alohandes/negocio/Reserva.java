package uniandes.isis2304.alohandes.negocio;

import java.util.Date;

public class Reserva implements VOReserva{

	private long id;
	private int estado;
	private int valorTotal;
	private long clienteId;
	
	public Reserva() {
		this.id=0;
		this.estado=0;
		valorTotal=0;
		clienteId=0;
	}
	public Reserva(long id, int estado, int valorTotal,long clienteid) {
		this.id=id;
		this.estado=estado;
		this.valorTotal=valorTotal;
		this.clienteId=clienteid;
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
	public int getValorTotal() {
		// TODO Auto-generated method stub
		return this.valorTotal;
	}

	@Override
	public void setValorTotal(int valorTotal) {
		// TODO Auto-generated method stub
		this.valorTotal=valorTotal;
	}

	@Override
	public int getEstado() {
		// TODO Auto-generated method stub
		return this.estado;
	}

	@Override
	public void setEstado(int estado) {
		// TODO Auto-generated method stub
		this.estado=estado;
		
	}
	public long getClienteId() {
		return clienteId;
	}
	public void setClienteId(long clienteId) {
		this.clienteId = clienteId;
	}

	

}
