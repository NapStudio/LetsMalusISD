package es.udc.ws.app.model.oferta;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import es.udc.ws.util.exceptions.InstanceNotFoundException;

public interface OfertaDAO {

	public Oferta create(Connection connection, Oferta oferta);

	public Oferta find(Connection connection, Long ofertaId)
			throws InstanceNotFoundException;

	public List<Oferta> findByParameters(Connection connection,
			String keywords, String estadoBusqueda, Calendar fechaBusqueda);

	public List<Oferta> findAll(Connection connection);

	public void update(Connection connection, Oferta oferta)
			throws InstanceNotFoundException;

	public void remove(Connection connection, Long ofertaId)
			throws InstanceNotFoundException;
}
