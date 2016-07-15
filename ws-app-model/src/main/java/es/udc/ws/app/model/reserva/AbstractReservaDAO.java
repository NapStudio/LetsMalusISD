package es.udc.ws.app.model.reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.udc.ws.util.exceptions.InstanceNotFoundException;

public abstract class AbstractReservaDAO implements ReservaDAO {

	protected AbstractReservaDAO() {

	}

	@Override
	public Reserva find(Connection connection, Long reservaId)
			throws InstanceNotFoundException {
		/* Create "queryString". */
		String queryString = "SELECT ofertaId, emailUsuarioReserva,"
				+ " tarjetaCreditoReserva, estadoReserva, fechaCreacionReserva FROM Reserva WHERE reservaId = ?";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

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
			String tarjetaCreditoReserva = resultSet.getString(i++);
			String estadoReserva = resultSet.getString(i++);

			Calendar fechaCreacionReserva = Calendar.getInstance();
			fechaCreacionReserva.setTime(resultSet.getTimestamp(i++));
			/* Return reserva. */
			return new Reserva(reservaId, ofertaId, emailUsuarioReserva,
					tarjetaCreditoReserva, estadoReserva, fechaCreacionReserva);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Connection connection, Reserva reserva)
			throws InstanceNotFoundException {
		/* Create "queryString". */
		String queryString = "UPDATE Reserva"
				+ " SET ofertaId = ?, emailUsuarioReserva = ?,  "
				+ " tarjetaCreditoReserva = ?, estadoReserva = ? WHERE reservaId = ?";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, reserva.getOfertaId());
			preparedStatement.setString(i++, reserva.getEmailUsuarioReserva());
			preparedStatement
					.setString(i++, reserva.getTarjetaCreditoReserva());
			preparedStatement.setString(i++, reserva.getEstadoReserva());
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

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

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

	@Override
	public List<Reserva> findbyOferta(Connection connection, Long ofertaId)
			throws InstanceNotFoundException {
		String queryString = "SELECT reservaId, emailUsuarioReserva,"
				+ " tarjetaCreditoReserva, estadoReserva, fechaCreacionReserva FROM Reserva WHERE ofertaId = ?";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, ofertaId.longValue());

			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Reserva> reserva = new ArrayList<Reserva>();

			while (resultSet.next()) {
				i = 1;
				Long reservaId = resultSet.getLong(i++);
				String emailUsuarioReserva = resultSet.getString(i++);
				String tarjetaCreditoReserva = resultSet.getString(i++);
				String estadoReserva = resultSet.getString(i++);
				Calendar fechaCreacionReserva = Calendar.getInstance();
				fechaCreacionReserva.setTime(resultSet.getTimestamp(i++));
				/* Return reserva. */

				reserva.add(new Reserva(reservaId, ofertaId,
						emailUsuarioReserva, tarjetaCreditoReserva,
						estadoReserva, fechaCreacionReserva));
			}
			return reserva;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Reserva> findbyUsuario(Connection connection,
			String emailUsuarioReserva, String estado)
			throws InstanceNotFoundException {
		String queryString = "SELECT reservaId, ofertaId,"
				+ " tarjetaCreditoReserva, estadoReserva, fechaCreacionReserva FROM Reserva WHERE emailUsuarioReserva = ?";

		if (estado != null) {
			queryString += " AND estadoReserva = ?";
		}

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */

			int i = 1;

			if (estado != null) {
				preparedStatement.setString(i++, emailUsuarioReserva);
				preparedStatement.setString(i++, estado);
			} else {
				preparedStatement.setString(i++, emailUsuarioReserva);
			}

			System.out.println(queryString);
			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Reserva> reserva = new ArrayList<Reserva>();

			while (resultSet.next()) {
				i = 1;
				Long reservaId = resultSet.getLong(i++);
				Long ofertaId = resultSet.getLong(i++);
				String tarjetaCreditoReserva = resultSet.getString(i++);
				String estadoReserva = resultSet.getString(i++);
				Calendar fechaCreacionReserva = Calendar.getInstance();
				fechaCreacionReserva.setTime(resultSet.getTimestamp(i++));
				/* Return reserva. */

				reserva.add(new Reserva(reservaId, ofertaId,
						emailUsuarioReserva, tarjetaCreditoReserva,
						estadoReserva, fechaCreacionReserva));
			}
			return reserva;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
