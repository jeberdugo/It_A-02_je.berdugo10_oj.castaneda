package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Reserva;

public class SQLReservaColectiva {

	private final static String SQL = PersistenciaAlohAndes.SQL;

	private PersistenciaAlohAndes pa;

	public SQLReservaColectiva(PersistenciaAlohAndes pa) {
		this.pa = pa;
	}

	public long adicionarReserva(PersistenceManager pm, String idReserva, double valorTotal) {
		Query q = pm.newQuery(SQL,
				"INSERT INTO " + pa.darTablaReserva() + "(id, valor_total) values (?, ?)");
		q.setParameters(idReserva, valorTotal);
		return (long) q.executeUnique();
	}

	public long eliminarReservaPorId(PersistenceManager pm, long idReserva) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaReserva() + " WHERE id = ?");
		q.setParameters(idReserva);
		return (long) q.executeUnique();
	}

	public List<Reserva> darReservasColectivas(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva());
		q.setResultClass(Reserva.class);
		return q.executeList();
	}
}
