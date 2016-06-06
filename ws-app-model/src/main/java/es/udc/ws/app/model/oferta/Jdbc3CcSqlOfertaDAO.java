package es.udc.ws.app.model.oferta;

import static es.udc.ws.app.model.util.ModelConstants.RESERVA_EXPIRATION_DAYS;
import static es.udc.ws.app.model.util.ModelConstants.OFERTA_EXPIRATION_DAYS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


public class Jdbc3CcSqlOfertaDAO extends MySQLOfertaDAO{

	@Override
    public Oferta create(Connection connection, Oferta oferta) {
		
        String queryString = "INSERT INTO Oferta"
                + " (nombreOferta, descripcionOferta, estadoOferta, precioRealOferta, precioDescontadoOferta, comisionOferta, fechaLimiteOferta, fechaLimiteReserva)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                        queryString, Statement.RETURN_GENERATED_KEYS)) {

        	 Calendar expirationDateReserva = Calendar.getInstance();
        	 expirationDateReserva.add(Calendar.DAY_OF_MONTH, RESERVA_EXPIRATION_DAYS);
             
             Calendar expirationDateOferta = Calendar.getInstance();
             expirationDateOferta.add(Calendar.DAY_OF_MONTH, OFERTA_EXPIRATION_DAYS);
             


             
			Timestamp sqlTimestampOferta = new Timestamp(expirationDateOferta.getTime().getTime());
			Timestamp sqlTimestampReserva = new Timestamp(expirationDateReserva.getTime().getTime());

            Date ofertaDate=sqlTimestampOferta;
            Date reservaDate=sqlTimestampReserva;

            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setString(i++, oferta.getNombreOferta());
            preparedStatement.setString(i++, oferta.getDescripcionOferta());
            preparedStatement.setString(i++, oferta.getEstadoOferta());
            preparedStatement.setFloat(i++, oferta.getPrecioRealOferta());
            preparedStatement.setFloat(i++, oferta.getPrecioDescontadoOferta());
            preparedStatement.setFloat(i++, oferta.getComisionOferta());
            preparedStatement.setTimestamp(i++, sqlTimestampOferta);
            preparedStatement.setTimestamp(i++, sqlTimestampReserva);
            /* Execute query. */
            preparedStatement.executeUpdate();

            /* Get generated identifier. */
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (!resultSet.next()) {
                throw new SQLException(
                        "JDBC driver did not return generated key.");
            }
            Long ofertaId = resultSet.getLong(1);

            /* Return oferta. */
            return new Oferta(ofertaId, oferta.getNombreOferta(), oferta.getDescripcionOferta(),
                    oferta.getEstadoOferta(), oferta.getPrecioRealOferta(),
                    oferta.getPrecioDescontadoOferta(), oferta.getComisionOferta(), ofertaDate,reservaDate);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
	
}
