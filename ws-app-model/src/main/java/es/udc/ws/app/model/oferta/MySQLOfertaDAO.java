package es.udc.ws.app.model.oferta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.udc.ws.util.exceptions.InstanceNotFoundException;


public abstract class MySQLOfertaDAO implements OfertaDAO {
	
	protected MySQLOfertaDAO(){
		
	}

	@Override
	public Oferta find(Connection connection, Long ofertaId)
			throws InstanceNotFoundException {
		 /* Create "queryString". */
        String queryString = "SELECT nombreOferta, descripcionOferta, "
                + " estadoOferta, precioRealOferta, precioDescontadoOferta, comisionOferta, fechaLimiteOferta, fechaLimiteReserva FROM Oferta WHERE ofertaId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setLong(i++, ofertaId.longValue());

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new InstanceNotFoundException(ofertaId,
                        Oferta.class.getName());
            }

            /* Get results. */
            i = 1;
            String nombreOferta = resultSet.getString(i++);
            String descripcionOferta = resultSet.getString(i++);
            String estadoOferta = resultSet.getString(i++);
            float precioRealOferta = resultSet.getFloat(i++);
            float precioDescontadoOferta = resultSet.getFloat(i++);
            float comisionOferta = resultSet.getFloat(i++);
            Date fechaLimiteOferta = resultSet.getTimestamp(i++);
            Date fechaLimiteReserva = resultSet.getTimestamp(i++);

            /* Return oferta. */
            return new Oferta(ofertaId, nombreOferta, descripcionOferta, estadoOferta, precioRealOferta,
            		precioDescontadoOferta,comisionOferta, fechaLimiteOferta, fechaLimiteReserva);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<Oferta> findByParameters(Connection connection, String keywords, String estadoBusqueda, Date fechaBusqueda) {
        /* Create "queryString". */
        String[] words = keywords != null ? keywords.split(" ") : null;
        String queryString = "SELECT ofertaId, nombreOferta, descripcionOferta, "
                + " estadoOferta, precioRealOferta, precioDescontadoOferta, comisionOferta, fechaLimiteOferta, fechaLimiteReserva FROM Oferta";
        
        if ((words != null && words.length > 0) || (estadoBusqueda != null)
        		|| (fechaBusqueda != null)) {
        	queryString += " WHERE";
        }
        boolean isFirst=true;
        if (words != null && words.length > 0) {
        	isFirst=false;
            for (int i = 0; i < words.length; i++) {
                if (i > 0) {
                    queryString += " AND";
                }
                queryString += " LOWER (descripcionOferta) LIKE LOWER (?)";
            }
        }
        if(estadoBusqueda!=null){
        	if(!isFirst){
        		queryString+= " AND";
        	}else{
        		isFirst=false;
        	}
        	queryString+=" estadoOferta = ?";
        }
        if(fechaBusqueda!=null){
        	if(!isFirst){
        		queryString+= " AND";
        	}else{
        		isFirst=false;
        	}
        	queryString+=" fechaLimiteOferta = ?";
        }
        
        queryString += " ORDER BY nombreOferta";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

        	int j=1;
            if (words != null) {
                /* Fill "preparedStatement". */
                for (int i = 0; i < words.length; i++) {
                    preparedStatement.setString(j++, "%" + words[i] + "%");
                }
            }
            if(estadoBusqueda!=null) preparedStatement.setString(j++, estadoBusqueda);
            if(fechaBusqueda!=null) preparedStatement.setTimestamp(j++, new Timestamp(fechaBusqueda.getTime()));
           

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            /* Read ofertas. */
            List<Oferta> ofertas = new ArrayList<Oferta>();

            while (resultSet.next()) {

                int i = 1;
                Long ofertaId = new Long(resultSet.getLong(i++));
                String nombreOferta = resultSet.getString(i++);
                String descripcionOferta = resultSet.getString(i++);
                String estadoOferta = resultSet.getString(i++);
                float precioRealOferta = resultSet.getFloat(i++);
                float precioDescontadoOferta = resultSet.getFloat(i++);
                float comisionOferta = resultSet.getFloat(i++);
                Date fechaLimiteOferta = resultSet.getTimestamp(i++);
                Date fechaLimiteReserva = resultSet.getTimestamp(i++);

                ofertas.add(new Oferta(ofertaId, nombreOferta, descripcionOferta, estadoOferta,
                		precioRealOferta, precioDescontadoOferta, comisionOferta, fechaLimiteOferta, fechaLimiteReserva));

            }

            /* Return ofertas. */
            return ofertas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	@Override
	public List<Oferta> findAll(Connection connection){
		 /* Create "queryString". */
        String queryString = "SELECT ofertaId, nombreOferta, descripcionOferta, "
                + " estadoOferta, precioRealOferta, precioDescontadoOferta, comisionOferta, fechaLimiteOferta, fechaLimiteReserva FROM Oferta";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

            /* Fill "preparedStatement". */

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Oferta> ofertas = new ArrayList<Oferta>();

            while (resultSet.next()) {

                int i = 1;
                Long ofertaId = new Long(resultSet.getLong(i++));
                String nombreOferta = resultSet.getString(i++);
                String descripcionOferta = resultSet.getString(i++);
                String estadoOferta = resultSet.getString(i++);
                float precioRealOferta = resultSet.getFloat(i++);
                float precioDescontadoOferta = resultSet.getFloat(i++);
                float comisionOferta = resultSet.getFloat(i++);
                Date fechaLimiteOferta = resultSet.getTimestamp(i++);
                Date fechaLimiteReserva = resultSet.getTimestamp(i++);

                ofertas.add(new Oferta(ofertaId, nombreOferta, descripcionOferta, estadoOferta,
                		precioRealOferta, precioDescontadoOferta, comisionOferta, fechaLimiteOferta, fechaLimiteReserva));

            }

            /* Return ofertas. */
            return ofertas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void update(Connection connection, Oferta oferta)
			throws InstanceNotFoundException {

        /* Create "queryString". */
        String queryString = "UPDATE Oferta"
                + " SET nombreOferta = ?, descripcionOferta = ?, estadoOferta = ?, "
                + "precioRealOferta = ?, precioDescontadoOferta = ?, comisionOferta = ?, fechaLimiteOferta = ?, fechaLimiteReserva = ? "
                + "WHERE ofertaId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

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
            
            
            preparedStatement.setLong(i++, oferta.getOfertaId());

            /* Execute query. */
            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows == 0) {
                throw new InstanceNotFoundException(oferta.getOfertaId(),
                        Oferta.class.getName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void remove(Connection connection, Long ofertaId)
			throws InstanceNotFoundException {
		/* Create "queryString". */
        String queryString = "DELETE FROM Oferta WHERE" + " ofertaId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setLong(i++, ofertaId);

            /* Execute query. */
            int removedRows = preparedStatement.executeUpdate();

            if (removedRows == 0) {
                throw new InstanceNotFoundException(ofertaId,
                        Oferta.class.getName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
	}

}
