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
	private int valorTotal;
	
	/**
	 * El cliente propietario de la reserva
	 */
	private Cliente cliente;
	
	/**
	 * Las ofertas que componen la reserva
	 */
	private List<Oferta> ofertas;
	
	/**
	 * Constructor por defecto
	 */
	public Reserva() {
		this.id = 0;
		this.estado = false;
		this.valorTotal = 0;
		this.cliente = null;
		this.ofertas=null;
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
	public Reserva(long id, boolean estado, int valorTotal, Cliente cliente, List<Oferta> ofertas) {
		this.id = id;
		this.estado = estado;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
		this.ofertas=ofertas;
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
	 * @return El valor total de la reserva
	 */
	public int getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal - El nuevo valor total de la reserva
	 */
	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * @return El cliente propietario de la reserva
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente - El nuevo cliente propietario de la reserva
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * @return Las ofertas que componen la reserva
	 */
	public List<Oferta> getOfertas() {
		return ofertas;
	}
	
	/**
	 * @param ofertas - Las nuevas ofertas que componen la reserva
	 */
	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos de la reserva
	 */
	public String toString() {
		return "Reserva [id=" + id + ", estado=" + estado + ", valorTotal=" + valorTotal + ", cliente=" + cliente
				+ ", ofertas=" + ofertas + "]";
	}
	
}
