package es.udc.ws.app.model.reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import es.udc.ws.util.exceptions.InstanceNotFoundException;

public abstract class MySQLReservaDAO implements ReservaDAO{

	protected MySQLReservaDAO() {
		
	}

	@Override
	public Reserva find(Connection connection, Long reservaId)
			throws InstanceNotFoundException {
		 /* Create "queryString". */
        String queryString = "SELECT ofertaId, emailUsuarioReserva, fechaExpiracionReserva,"
                + " tarjetaCreditoReserva, precioReserva, fechaCreacionReserva FROM Reserva WHERE reservaId = ?";

        
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setLong(i++, reservaId.longValue());

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new InstanceNotFoundException(reservaId,
                        Reserva.class.getName());
            }

            /* Get results. */
            i = 1;
            Long ofertaId = resultSet.getLong(i++);
            String emailUsuarioReserva = resultSet.getString(i++);
            Date fechaExpiracionReserva = resultSet.getTimestamp(i++);
            String tarjetaCreditoReserva = resultSet.getString(i++);
            float precioReserva = resultSet.getFloat(i++);
            Date fechaCreacionReserva = resultSet.getTimestamp(i++);
            /* Return sale. */
            return new Reserva(reservaId, ofertaId, emailUsuarioReserva, fechaExpiracionReserva,
            		tarjetaCreditoReserva, precioReserva, fechaCreacionReserva);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void update(Connection connection, Reserva reserva)
			throws InstanceNotFoundException {
        /* Create "queryString". */
        String queryString = "UPDATE Reserva"
                + " SET ofertaId = ?, emailUsuarioReserva = ?, fechaExpiracionReserva = ?, "
                + " tarjetaCreditoReserva = ?, precioReserva = ? WHERE reservaId = ?";
        
        /* (ofertaId, emailUsuarioReserva, fechaExpiracionReserva, tarjetaCreditoReserva, precioReserva, fechaCreacionReserva)"
        + " VALUES (?, ?, ?, ?, ?)";*/

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {
        	
        	Timestamp sqlTimestampExpiracion = new Timestamp(reserva.getFechaExpiracionReserva().getTime());

            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setLong(i++, reserva.getOfertaId());
            preparedStatement.setString(i++, reserva.getEmailUsuarioReserva());
            preparedStatement.setTimestamp(i++, sqlTimestampExpiracion);
            preparedStatement.setString(i++, reserva.getTarjetaCreditoReserva());
            preparedStatement.setFloat(i++, reserva.getPrecioReserva());
            preparedStatement.setLong(i++, reserva.getReservaId());
            

            /* Execute query. */
            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows == 0) {
                throw new InstanceNotFoundException(reserva.getOfertaId(),
                        Reserva.class.getName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void remove(Connection connection, Long reservaId)
			throws InstanceNotFoundException {
        /* Create "queryString". */
        String queryString = "DELETE FROM Reserva WHERE" + " reservaId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setLong(i++, reservaId);

            /* Execute query. */
            int removedRows = preparedStatement.executeUpdate();

            if (removedRows == 0) {
                throw new InstanceNotFoundException(reservaId,
                        Reserva.class.getName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
		

}
