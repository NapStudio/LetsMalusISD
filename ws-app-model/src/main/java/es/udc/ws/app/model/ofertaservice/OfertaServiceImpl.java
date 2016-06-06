package es.udc.ws.app.model.ofertaservice;


import static es.udc.ws.app.model.util.ModelConstants.OFERTA_DATA_SOURCE;
import static es.udc.ws.app.model.util.ModelConstants.MAX_PRICE;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import es.udc.ws.app.exceptions.BadStateReserva;
import es.udc.ws.app.exceptions.OfertaReservadaException;
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
        PropertyValidator.validateDouble("precioRealOferta", oferta.getPrecioRealOferta(), 0, MAX_PRICE);
        PropertyValidator.validateDouble("precioDescontadoOferta", oferta.getPrecioDescontadoOferta(), 0, MAX_PRICE);
        //TODO: check max_price y que hacer con comision(si es un porcentaje o que)
        PropertyValidator.validateDouble("comisionOferta", oferta.getComisionOferta(), 0, MAX_PRICE);
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
		
		validateOferta(oferta);
        try (Connection connection = dataSource.getConnection()) {

            try {

                /* Prepare connection. */
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);

                /* Do work. */
                if((reservaDAO.findbyOferta(connection, oferta.getOfertaId())).isEmpty()){
                	ofertaDAO.update(connection, oferta);
                }else{
                	Oferta ofertaOriginal=ofertaDAO.find(connection, oferta.getOfertaId());
                	if(ofertaOriginal.getFechaLimiteOferta().before(oferta.getFechaLimiteOferta())){                		
                		if(ofertaOriginal.getPrecioDescontadoOferta()>=oferta.getPrecioDescontadoOferta()){
                    		ofertaDAO.update(connection, oferta); 
                		}else{
                			throw new InputValidationException("Invalid price value (it must be smaller than "+ofertaOriginal.getPrecioDescontadoOferta()
                    				+"): " +oferta.getPrecioDescontadoOferta());
                		}
                	}else{
                		throw new InputValidationException("Invalid date value (it must be greater than "+ofertaOriginal.getFechaLimiteOferta()
                				+"): " +oferta.getFechaLimiteOferta());
                	}
                }
                /* Commit. */
                connection.commit();

            } catch (InstanceNotFoundException e) {
                connection.commit();
                throw e;
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
	public void removeOferta(Long ofertaId) throws InstanceNotFoundException, OfertaReservadaException {
        try (Connection connection = dataSource.getConnection()) {

            try {

                /* Prepare connection. */
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);

                /* Do work. */
                if((reservaDAO.findbyOferta(connection, ofertaId)).isEmpty()){
                    ofertaDAO.remove(connection, ofertaId);
                }else{
                	throw new OfertaReservadaException(ofertaId);
                }

                /* Commit. */
                connection.commit();

            } catch (InstanceNotFoundException e) {
                connection.commit();
                throw e;
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
	public void invalidarOferta(Long ofertaId) throws InstanceNotFoundException {
        try (Connection connection = dataSource.getConnection()) {

            try {

                /* Prepare connection. */
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);

                /* Do work. */
                Oferta oferta = ofertaDAO.find(connection, ofertaId);
                oferta.setEstadoOferta("inválida");                
                List<Reserva> reservas = new ArrayList<Reserva>();
                reservas= reservaDAO.findbyOferta(connection, ofertaId);
                if(!reservas.isEmpty()){
                   for(Reserva reserva:reservas){
                	   reserva.setEstadoReserva("anulada");
                	   reservaDAO.update(connection, reserva);
                   }
                }

                /* Commit. */
                connection.commit();

            } catch (InstanceNotFoundException e) {
                connection.commit();
                throw e;
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
	public Oferta findOferta(Long ofertaId) throws InstanceNotFoundException {
		 try (Connection connection = dataSource.getConnection()) {

	            try {

	                /* Prepare connection. */
	                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
	                connection.setAutoCommit(false);

	                /* Do work. */
	                Oferta oferta = ofertaDAO.find(connection, ofertaId);
	                

	                /* Commit. */
	                connection.commit();
	                return oferta;
	            } catch (InstanceNotFoundException e) {
	                connection.commit();
	                throw e;
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
	public List<Oferta> findOfertas(String keywords, String estadoBusqueda, Date fechaBusqueda) {
		 try (Connection connection = dataSource.getConnection()) {

	            try {

	                /* Prepare connection. */
	                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
	                connection.setAutoCommit(false);

	                /* Do work. */
	                List<Oferta> ofertas = new ArrayList<Oferta>();
	                if(keywords==null&&estadoBusqueda==null&&fechaBusqueda==null){
	                	ofertas = ofertaDAO.findAll(connection);
	                }else{
		                ofertas = ofertaDAO.findByParameters(connection, keywords, estadoBusqueda, fechaBusqueda);
	                }
	                
	                

	                /* Commit. */
	                connection.commit();
	                
	                
	                return ofertas;
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
	public Long reservarOferta(Long ofertaId, String emailUsuarioReserva,
			String tarjetaCreditoReserva) throws InstanceNotFoundException,
			InputValidationException {
		
		PropertyValidator.validateCreditCard(tarjetaCreditoReserva);
		
		try (Connection connection = dataSource.getConnection()) {

            try {

                /* Prepare connection. */
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);

                /* Do work. */
                Oferta oferta=ofertaDAO.find(connection, ofertaId);
                Reserva reserva= new Reserva();
                reserva.setEmailUsuarioReserva(emailUsuarioReserva);
                reserva.setOfertaId(oferta.getOfertaId());
                reserva.setTarjetaCreditoReserva(tarjetaCreditoReserva);
                reserva.setFechaCreacionReserva(new Date());
                reserva.setEstadoReserva("válida");
                		
                		
                reserva=reservaDAO.create(connection, reserva);

                /* Commit. */
                connection.commit();

                return reserva.getReservaId();

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
	public List<Reserva> findReservasByOferta(Long ofertaId) throws InstanceNotFoundException, ReservaExpirationException{
		try (Connection connection = dataSource.getConnection()) {

            try {

                /* Prepare connection. */
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);

                /* Do work. */
                List<Reserva> reservas = new ArrayList<Reserva>();
                reservas= reservaDAO.findbyOferta(connection, ofertaId);

                /* Commit. */
                connection.commit();

                return reservas;

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
	public List<Reserva> findReservasByUsuario(String emailUsuarioReserva)
			throws InstanceNotFoundException, ReservaExpirationException {
		try (Connection connection = dataSource.getConnection()) {

            try {

                /* Prepare connection. */
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);

                /* Do work. */
                List<Reserva> reservas = new ArrayList<Reserva>();
                reservas= reservaDAO.findbyUsuario(connection, emailUsuarioReserva);

                /* Commit. */
                connection.commit();

                return reservas;

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
	public Long reclamarOferta(Long reservaId) throws InstanceNotFoundException, BadStateReserva, ReservaExpirationException {
		try (Connection connection = dataSource.getConnection()) {

            try {

                /* Prepare connection. */
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);

                /* Do work. */
                Reserva reserva= reservaDAO.find(connection, reservaId);
                if(reserva.getEstadoReserva().equals("anulada")){
                	throw new BadStateReserva(reserva.getEstadoReserva(),reservaId);
                }else if(reserva.getEstadoReserva().equals("inválida")){
                	throw new BadStateReserva(reserva.getEstadoReserva(),reservaId);
            	}else{
                	Date fechalimite = (ofertaDAO.find(connection, reserva.getOfertaId())).getFechaLimiteReserva();
                	if(fechalimite.before(new Date())){
                		reserva.setEstadoReserva("inválida");
                		reservaDAO.update(connection, reserva);
                    	throw new ReservaExpirationException(reservaId,fechalimite);
                	}
                }
                /* Commit. */
                connection.commit();

        		return reserva.getReservaId();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            } catch (RuntimeException | Error e) {
                connection.rollback();
                throw e;
            }//TODO catch something

	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

	@Override
	public Reserva findReserva(Long reservaId) throws InstanceNotFoundException {
		 try (Connection connection = dataSource.getConnection()) {

	            try {

	                /* Prepare connection. */
	                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
	                connection.setAutoCommit(false);

	                /* Do work. */
	                Reserva reserva = reservaDAO.find(connection, reservaId);
	                

	                /* Commit. */
	                connection.commit();
	                return reserva;
	            } catch (InstanceNotFoundException e) {
	                connection.commit();
	                throw e;
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
    
    
}
