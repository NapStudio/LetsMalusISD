package es.udc.ws.app.model.reserva;

import java.sql.Connection;
import java.util.List;

import es.udc.ws.util.exceptions.InstanceNotFoundException;

public interface ReservaDAO {

    public Reserva create(Connection connection, Reserva reserva);

    public Reserva find(Connection connection, Long reservaId)
            throws InstanceNotFoundException;
    
    public List<Reserva> findbyOferta(Connection connection, Long ofertaId)
            throws InstanceNotFoundException;
    
    public List<Reserva> findbyUsuario(Connection connection, String emailUsuarioReserva)
            throws InstanceNotFoundException;

    public void update(Connection connection, Reserva reserva)
            throws InstanceNotFoundException;

    public void remove(Connection connection, Long reservaId)
            throws InstanceNotFoundException;
}
