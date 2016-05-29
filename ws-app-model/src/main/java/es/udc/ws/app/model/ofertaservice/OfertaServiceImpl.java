package es.udc.ws.app.model.ofertaservice;


import static es.udc.ws.app.model.util.ModelConstants.OFERTA_DATA_SOURCE;
import static es.udc.ws.app.model.util.ModelConstants.MAX_PRICE;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import es.udc.ws.app.exceptions.ReservaExpirationException;
import es.udc.ws.app.model.oferta.Oferta;
import es.udc.ws.app.model.oferta.OfertaDAO;
import es.udc.ws.app.model.oferta.OfertaDAOFactory;
import es.udc.ws.app.model.reserva.Reserva;
import es.udc.ws.app.model.reserva.ReservaDAO;
import es.udc.ws.app.model.reserva.ReservaDAOFactory;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;
import es.udc.ws.util.sql.DataSourceLocator;
import es.udc.ws.util.validation.PropertyValidator;

public class OfertaServiceImpl implements OfertaService{
    private DataSource dataSource;
    private OfertaDAO ofertaDAO = null;
    private ReservaDAO reservaDAO = null;

    public OfertaServiceImpl() {
        dataSource = DataSourceLocator.getDataSource(OFERTA_DATA_SOURCE);
        ofertaDAO = OfertaDAOFactory.getDao();
        reservaDAO = ReservaDAOFactory.getDao();
    }
    
    private void validateOferta(Oferta oferta) throws InputValidationException {

        PropertyValidator.validateMandatoryString("nombreOferta", oferta.getNombreOferta());
        PropertyValidator.validateMandatoryString("descripcionOferta", oferta.getDescripcionOferta());
        PropertyValidator.validateMandatoryString("estadoOferta", oferta.getEstadoOferta());
        PropertyValidator.validateDouble("precioRealOferta", oferta.getPrecioRealOferta(), 0,
                MAX_PRICE);
        PropertyValidator.validateDouble("precioDescontadoOferta", oferta.getPrecioDescontadoOferta(), 0,
                MAX_PRICE);
        //TODO: como cambiar calendar a date o al reves.
       /* PropertyValidator.validatePastDate("creation date",
                oferta.getFechaLimiteOferta());*/
    }

	@Override
	public Oferta addOferta(Oferta oferta) throws InputValidationException {
		
		validateOferta(oferta);
		
		try (Connection connection = dataSource.getConnection()) {

	            try {

	                /* Prepare connection. */
	                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
	                connection.setAutoCommit(false);

	                /* Do work. */
	                Oferta createdOferta = ofertaDAO.create(connection, oferta);

	                /* Commit. */
	                connection.commit();

	                return createdOferta;

	            } catch (SQLException e) {
	                connection.rollback();
	                throw new RuntimeException(e);
	            } catch (RuntimeException | Error e) {
	                connection.rollback();
	                throw e;
	            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void updateOferta(Oferta oferta) throws InputValidationException,
			InstanceNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOferta(Long ofertaId) throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Oferta findOferta(Long ofertaId) throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Oferta> findOfertas(String keywords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reserva reservarOferta(Long ofertaId, String emailUsuarioReserva,
			String tarjetaCreditoReserva) throws InstanceNotFoundException,
			InputValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reserva findSale(Long reservaId) throws InstanceNotFoundException,
			ReservaExpirationException {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
