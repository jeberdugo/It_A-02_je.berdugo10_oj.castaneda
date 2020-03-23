package uniandes.isis2304.alohandes.negocio;

import java.util.Date;

public class Reserva implements VOReserva{

	private long id;
	private int estado;
	private double valorTotal;
	
	public Reserva() {
		this.id=0;
		this.estado=0;
		valorTotal=0;
	}
	public Reserva(long id, int estado, double valorTotal) {
		this.id=id;
		this.estado=estado;
		this.valorTotal=valorTotal;
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
	public double getValorTotal() {
		// TODO Auto-generated method stub
		return this.valorTotal;
	}

	@Override
	public void setValorTotal(double valorTotal) {
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

	

}
