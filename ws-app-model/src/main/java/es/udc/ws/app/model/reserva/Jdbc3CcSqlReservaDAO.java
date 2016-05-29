package es.udc.ws.app.model.reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;


public class Jdbc3CcSqlReservaDAO extends MySQLReservaDAO{

	@Override
	public Reserva create(Connection connection, Reserva reserva) {
		String queryString = "INSERT INTO Reserva"
                + " (ofertaId, emailUsuarioReserva, fechaExpiracionReserva, tarjetaCreditoReserva, precioReserva, fechaCreacionReserva)"
                + " VALUES (?, ?, ?, ?, ?)";
		
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                        queryString, Statement.RETURN_GENERATED_KEYS)) {


			Timestamp sqlTimestampExpiracion = new Timestamp(reserva.getFechaExpiracionReserva().getTime());
			Timestamp sqlTimestampCreacion = new Timestamp(reserva.getFechaCreacionReserva().getTime());
        	
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setLong(i++, reserva.getOfertaId());
            preparedStatement.setString(i++, reserva.getEmailUsuarioReserva());
            preparedStatement.setTimestamp(i++, sqlTimestampExpiracion);
            preparedStatement.setString(i++, reserva.getTarjetaCreditoReserva());
            preparedStatement.setFloat(i++, reserva.getPrecioReserva());
            preparedStatement.setTimestamp(i++, sqlTimestampCreacion);
            /* Execute query. */
            preparedStatement.executeUpdate();

            /* Get generated identifier. */
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (!resultSet.next()) {
                throw new SQLException(
                        "JDBC driver did not return generated key.");
            }
            Long reservaId = resultSet.getLong(1);

            /* Return reserva. */
            return new Reserva(reservaId, reserva.getOfertaId(), reserva.getEmailUsuarioReserva(),
                    reserva.getFechaExpiracionReserva(), reserva.getTarjetaCreditoReserva(),
                    reserva.getPrecioReserva(), reserva.getFechaCreacionReserva());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

}
