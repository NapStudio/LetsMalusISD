package es.udc.ws.app.model.oferta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;


public class Jdbc3CcSqlOfertaDAO extends MySQLOfertaDAO{

	@Override
    public Oferta create(Connection connection, Oferta oferta) {
		
        String queryString = "INSERT INTO Oferta"
                + " (nombreOferta, descripcionOferta, estadoOferta, precioRealOferta, precioDescontadoOferta, comisionOferta, fechaLimiteOferta, fechaLimiteReserva)"
                + " VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                        queryString, Statement.RETURN_GENERATED_KEYS)) {


			Timestamp sqlTimestamp = new Timestamp(oferta.getFechaLimiteOferta().getTime());
			Timestamp sqlTimestamp2 = new Timestamp(oferta.getFechaLimiteReserva().getTime());
        	
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setString(i++, oferta.getNombreOferta());
            preparedStatement.setString(i++, oferta.getDescripcionOferta());
            preparedStatement.setString(i++, oferta.getEstadoOferta());
            preparedStatement.setFloat(i++, oferta.getPrecioRealOferta());
            preparedStatement.setFloat(i++, oferta.getPrecioDescontadoOferta());
            preparedStatement.setFloat(i++, oferta.getComisionOferta());
            preparedStatement.setTimestamp(i++, sqlTimestamp);
            preparedStatement.setTimestamp(i++, sqlTimestamp2);
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
                    oferta.getPrecioDescontadoOferta(), oferta.getComisionOferta(), oferta.getFechaLimiteOferta(), oferta.getFechaLimiteReserva());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
	
}
