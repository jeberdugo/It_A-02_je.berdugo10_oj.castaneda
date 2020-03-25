package uniandes.isis2304.alohandes.negocio;

import java.util.List;

public class Reserva implements VOReserva {
	/**
	 * El identificador ÚNICO de las reservas
	 */
	private long id;
	
	/**
	 * El estado de la reserva
	 */
	private boolean estado;
	
	/**
	 * El valor total de la reserva
	 */
	private int valor_Total;
	
	/**
	 * El cliente propietario de la reserva
	 */
	private Cliente cliente_Id;
	
	
	
	/**
	 * Constructor por defecto
	 */
	public Reserva() {
		this.id = 0;
		this.estado = false;
		this.setValor_Total(0);
		this.setCliente_Id(null);
		
	}
	/**
	 * Constructor con valores
	 * 
	 * @param id          - El id de la reserva
	 * @param estado      - El estado de la reserva
	 * @param valorTotal - El valor total de la reserva
	 * @param cliente    - El cliente propietario de la reserva
	 * @param ofertas - Las ofertas que componen la reserva
	 */
	public Reserva(long id, boolean estado, int valorTotal, Cliente cliente) {
		this.id = id;
		this.estado = estado;
		this.setValor_Total(valorTotal);
		this.setCliente_Id(cliente);
		
	}

	/**
	 * @return El id del horario
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id de la reserva
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El estado de la reserva
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * @param estado - El nuevo estado de la reserva
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	

	/**
	 * @return Una cadena de caracteres con todos los atributos de la reserva
	 */
	public String toString() {
		return "Reserva [id=" + id + ", estado=" + estado + ", valorTotal=" + valor_Total + ", cliente=" 
				+ ", ofertas="  + "]";
	}
	public int getValor_Total() {
		return valor_Total;
	}
	public void setValor_Total(int valor_Total) {
		this.valor_Total = valor_Total;
	}
	public Cliente getCliente_Id() {
		return cliente_Id;
	}
	public void setCliente_Id(Cliente cliente_Id) {
		this.cliente_Id = cliente_Id;
	}
	
}
