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
	

	
	/* 	ofertaId BIGINT NOT NULL AUTO_INCREMENT,
    nombreOferta VARCHAR(255) COLLATE latin1_bin NOT NULL,
    descripcionOferta VARCHAR(1024) COLLATE latin1_bin ,
    precioRealOferta FLOAT NOT NULL,
    precioDescontadoOferta FLOAT NOT NULL,
    fechaLimiteOferta TIMESTAMP DEFAULT 0 NOT NULL,
    estadoOferta VARCHAR(255) NOT NULL,
    CONSTRAINT OfertaId PRIMARY KEY(ofertaId), 
    CONSTRAINT validPrice CHECK ( price >= 0) 
    ) ENGINE = InnoDB;
	
    
   private Long ofertaId;
    private String nombreOferta;
    private String descripcionOferta;
    private String estadoOferta;
    private float precioRealOferta;
    private float precioDescontadoOferta;
    private Date fechaLimiteOferta;*/
	

    /* Create "queryString". */

	@Override
	public Oferta find(Connection connection, Long ofertaId)
			throws InstanceNotFoundException {
		 /* Create "queryString". */
        String queryString = "SELECT nombreOferta, descripcionOferta, "
                + " estadoOferta, precioRealOferta, precioDescontadoOferta, fechaLimiteOferta FROM Oferta WHERE ofertaId = ?";

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
            Date fechaLimiteOferta = resultSet.getTimestamp(i++);

            /* Return movie. */
            return new Oferta(ofertaId, nombreOferta, descripcionOferta, estadoOferta, precioRealOferta,
            		precioDescontadoOferta, fechaLimiteOferta);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<Oferta> findByKeywords(Connection connection, String keywords) {
        /* Create "queryString". */
        String[] words = keywords != null ? keywords.split(" ") : null;
        String queryString = "SELECT ofertaId, nombreOferta, descripcionOferta, "
                + " estadoOferta, precioRealOferta, precioDescontadoOferta, fechaLimiteOferta FROM Oferta";
        if (words != null && words.length > 0) {
            queryString += " WHERE";
            for (int i = 0; i < words.length; i++) {
                if (i > 0) {
                    queryString += " AND";
                }
                queryString += " LOWER(nombreOferta) LIKE LOWER(?)";
            }
        }
        queryString += " ORDER BY nombreOferta";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

            if (words != null) {
                /* Fill "preparedStatement". */
                for (int i = 0; i < words.length; i++) {
                    preparedStatement.setString(i + 1, "%" + words[i] + "%");
                }
            }

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
                Date fechaLimiteOferta = resultSet.getTimestamp(i++);

                ofertas.add(new Oferta(ofertaId, nombreOferta, descripcionOferta, estadoOferta,
                		precioRealOferta, precioDescontadoOferta, fechaLimiteOferta));

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
                + "precioRealOferta = ?, precioDescontadoOferta = ?, fechaLimiteOferta = ? WHERE ofertaId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {
        	
        	Timestamp sqlTimestamp = new Timestamp(oferta.getFechaLimiteOferta().getTime());
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setString(i++, oferta.getNombreOferta());
            preparedStatement.setString(i++, oferta.getDescripcionOferta());
            preparedStatement.setString(i++, oferta.getEstadoOferta());
            preparedStatement.setFloat(i++, oferta.getPrecioRealOferta());
            preparedStatement.setFloat(i++, oferta.getPrecioDescontadoOferta());
            preparedStatement.setTimestamp(i++, sqlTimestamp);

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
