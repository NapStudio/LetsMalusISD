package es.udc.ws.app.model.ofertaservice;

import static es.udc.ws.app.model.util.ModelConstants.OFERTA_DATA_SOURCE;
import static es.udc.ws.app.model.util.ModelConstants.MAX_PRICE;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.apache.http.client.ClientProtocolException;

import es.udc.ws.app.exceptions.BadStateReservaException;
import es.udc.ws.app.exceptions.OfertaReservadaException;
import es.udc.ws.app.exceptions.TimeExpirationException;
import es.udc.ws.app.model.facebook.FacebookService;
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

public class OfertaServiceImpl implements OfertaService {
	private DataSource dataSource;
	private OfertaDAO ofertaDAO = null;
	private ReservaDAO reservaDAO = null;
	private FacebookService facebookService=new FacebookService();

	public OfertaServiceImpl() {
		System.out.println("ofertaserviceImpl created OK!");
		dataSource = DataSourceLocator.getDataSource(OFERTA_DATA_SOURCE);
		ofertaDAO = OfertaDAOFactory.getDao();
		reservaDAO = ReservaDAOFactory.getDao();
	}

	private void validateOferta(Oferta oferta) throws InputValidationException {

		PropertyValidator.validateMandatoryString("nombreOferta",
				oferta.getNombreOferta());
		PropertyValidator.validateMandatoryString("descripcionOferta",
				oferta.getDescripcionOferta());
		PropertyValidator.validateMandatoryString("estadoOferta",
				oferta.getEstadoOferta());
		PropertyValidator.validateDouble("precioRealOferta",
				oferta.getPrecioRealOferta(), 0, MAX_PRICE);
		PropertyValidator.validateDouble("precioDescontadoOferta",
				oferta.getPrecioDescontadoOferta(), 0, MAX_PRICE);
		PropertyValidator.validateDouble("comisionOferta",
				oferta.getComisionOferta(), 0, MAX_PRICE);
	}

	@Override
	public Oferta addOferta(Oferta oferta) throws InputValidationException {

		validateOferta(oferta);

		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				try {
					oferta.setFacebookId(facebookService.publicarOferta(oferta));
				} catch (ClientProtocolException e) {
					System.out.println("Hubo un problema publicando. La petición a facebook no se ha podido completar. \n"+e);
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Hubo un problema publicando. La petición a facebook no se ha podido completar. \n"+e);
					e.printStackTrace();
				}
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
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				if(oferta.getEstadoOferta()==null||oferta.getEstadoOferta().equals("nocambiar")){
					oferta.setEstadoOferta(ofertaDAO.find(connection, oferta.getOfertaId()).getEstadoOferta());				
				}
				if ((reservaDAO.findbyOferta(connection, oferta.getOfertaId()))
						.isEmpty()) {
						try {
							Oferta ofertaFace=oferta;
							ofertaFace.setFacebookId(ofertaDAO.find(connection, oferta.getOfertaId()).getFacebookId());
							oferta.setFacebookId(facebookService
									.actualizarOferta(ofertaFace));
						} catch (Exception e) {
							System.out
									.println("Hubo un problema actualizando. La petición a facebook no se ha podido completar. \n"
											+ e);
						}
					ofertaDAO.update(connection, oferta);
				} else {
					Oferta ofertaOriginal = ofertaDAO.find(connection,
							oferta.getOfertaId());
					if (ofertaOriginal.getFechaLimiteOferta().before(
							oferta.getFechaLimiteOferta())) {
						if (ofertaOriginal.getPrecioDescontadoOferta() >= oferta
								.getPrecioDescontadoOferta()) {
							try {
								oferta.setFacebookId(facebookService
										.actualizarOferta(oferta));
							} catch (Exception e) {
								System.out
										.println("Hubo un problema actualizando. La petición a facebook no se ha podido completar. \n"
												+ e);
							}
							ofertaDAO.update(connection, oferta);
						} else {
							throw new InputValidationException(
									"Invalid price value (it must be smaller than "
											+ ofertaOriginal
													.getPrecioDescontadoOferta()
											+ "): "
											+ oferta.getPrecioDescontadoOferta());
						}
					} else {
						throw new InputValidationException(
								"Invalid date value (it must be greater than "
										+ ofertaOriginal.getFechaLimiteOferta()
												.getTime()
										+ "): "
										+ oferta.getFechaLimiteOferta()
												.getTime());
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
	public void removeOferta(Long ofertaId) throws InstanceNotFoundException,
			OfertaReservadaException {
		System.out.println("removeofertadao");
		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				try {
					facebookService.borrarOferta(OfertaServiceFactory.getService()
							.findOferta(ofertaId).getFacebookId());
				} catch (ClientProtocolException e) {
					System.out
							.println("Hubo un problema borrando. La petición a facebook no se ha podido completar. \n"
									+ e);
				} catch (IOException e) {
					System.out
							.println("Hubo un problema borrando. La petición a facebook no se ha podido completar. \n"
									+ e);
				}
				if ((reservaDAO.findbyOferta(connection, ofertaId)).isEmpty()) {
					System.out.println("no tiene reservas");
					ofertaDAO.remove(connection, ofertaId);
				} else {
					System.out.println("tiene reservas lanza excepcion");
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
	public void invalidarOferta(Long ofertaId)
			throws InstanceNotFoundException, InputValidationException {
		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);
				/* Do work. */
				Oferta oferta = ofertaDAO.find(connection, ofertaId);
				try {
					facebookService.borrarOferta(oferta.getFacebookId());
				} catch (ClientProtocolException e) {
					System.out
							.println("Hubo un problema actualizando. La petición a facebook no se ha podido completar. \n"
									+ e);
				} catch (IOException e) {
					System.out
							.println("Hubo un problema actualizando. La petición a facebook no se ha podido completar. \n"
									+ e);
				}
				if (oferta.getEstadoOferta().equals("inválida")) {
					throw new InputValidationException("inválida");
				} else {
					oferta.setEstadoOferta("inválida");
					List<Reserva> reservas = new ArrayList<Reserva>();
					reservas = reservaDAO.findbyOferta(connection, ofertaId);
					if (!reservas.isEmpty()) {
						for (Reserva reserva : reservas) {
							if (!reserva.getEstadoReserva().equals("reclamada")) {
								reserva.setEstadoReserva("anulada");
								reservaDAO.update(connection, reserva);
							}
						}
					}
					ofertaDAO.update(connection, oferta);
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
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				Oferta oferta = ofertaDAO.find(connection, ofertaId);
				System.out.println("service fbId "+oferta.getFacebookId());
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
	public List<Oferta> findOfertas(String keywords, String estadoBusqueda,
			Calendar fechaBusqueda) {
		try (Connection connection = dataSource.getConnection()) {

			try {
				System.out.println("ofertaserviceimpl keywords: " + keywords);
				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				List<Oferta> ofertas = new ArrayList<Oferta>();
				if (keywords == null && estadoBusqueda == null
						&& fechaBusqueda == null) {
					ofertas = ofertaDAO.findAll(connection);
				} else {
					ofertas = ofertaDAO.findByParameters(connection, keywords,
							estadoBusqueda, fechaBusqueda);
				}
				List<Oferta> nuevaOfertas=new ArrayList<>();
				for(Oferta oferta:ofertas){
					System.out.println(oferta.getFechaLimiteOferta().getTime());
					System.out.println(Calendar.getInstance().getTime());
					System.out.println((oferta.getFechaLimiteOferta().after(Calendar.getInstance())));
					System.out.println(oferta.getEstadoOferta());
					System.out.println(oferta.getEstadoOferta().equals("válida")+"\n");
					if((oferta.getFechaLimiteOferta().after(Calendar.getInstance()))&&(oferta.getEstadoOferta().equals("válida"))){
						nuevaOfertas.add(oferta);
					}
				}

				/* Commit. */
				connection.commit();

				return nuevaOfertas;
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
			InputValidationException, OfertaReservadaException, TimeExpirationException {

		PropertyValidator.validateCreditCard(tarjetaCreditoReserva);

		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				boolean canReserve = true;
				Oferta oferta = ofertaDAO.find(connection, ofertaId);
				List<Reserva> reservas = reservaDAO.findbyOferta(connection,
						ofertaId);
				for (Reserva reservaFound : reservas) {
					if (reservaFound.getEmailUsuarioReserva().equals(
							emailUsuarioReserva)) {
						canReserve = false;
					}
				}
				if((oferta.getFechaLimiteOferta().before(Calendar.getInstance()))){
					throw new TimeExpirationException("oferta", ofertaId, oferta.getFechaLimiteOferta());
				}
				Reserva reserva = new Reserva();
				if (canReserve) {
					reserva.setEmailUsuarioReserva(emailUsuarioReserva);
					reserva.setOfertaId(oferta.getOfertaId());
					reserva.setTarjetaCreditoReserva(tarjetaCreditoReserva);
					reserva.setFechaCreacionReserva(Calendar.getInstance());
					reserva.setEstadoReserva("válida");

					reserva = reservaDAO.create(connection, reserva);
				} else {
					throw new OfertaReservadaException(Long.valueOf(ofertaId));
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
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Reserva> findReservasByOferta(Long ofertaId)
			throws InstanceNotFoundException, TimeExpirationException {
		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				List<Reserva> reservas = new ArrayList<Reserva>();
				reservas = reservaDAO.findbyOferta(connection, ofertaId);

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
	public List<Reserva> findReservasByUsuario(String emailUsuarioReserva,
			String estado) throws InstanceNotFoundException,
			TimeExpirationException {
		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);
				System.out.println("emailUsuario " + emailUsuarioReserva
						+ " estado: " + estado);
				/* Do work. */
				List<Reserva> reservas = new ArrayList<Reserva>();
				reservas = reservaDAO.findbyUsuario(connection,
						emailUsuarioReserva, estado);

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
	public Long reclamarOferta(Long reservaId)
			throws InstanceNotFoundException, BadStateReservaException,
			TimeExpirationException {
		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				Reserva reserva = reservaDAO.find(connection, reservaId);
				if (reserva.getEstadoReserva().equals("anulada")) {
					throw new BadStateReservaException(reservaId,
							"anulada");
				} else if (reserva.getEstadoReserva().equals("inválida")) {
					throw new BadStateReservaException(reservaId,
							"inválida");
				} else {
					Calendar fechalimite = Calendar.getInstance();
					fechalimite = ((ofertaDAO.find(connection,
							reserva.getOfertaId())).getFechaLimiteReserva());
					if (fechalimite.before(Calendar.getInstance())) {
						reserva.setEstadoReserva("inválida");
						reservaDAO.update(connection, reserva);
						throw new TimeExpirationException("reserva", reservaId,
								fechalimite);
					}
				}
				reserva.setEstadoReserva("reclamada");
				reservaDAO.update(connection, reserva);
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
	public Reserva findReserva(Long reservaId) throws InstanceNotFoundException {
		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
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
	
	@Override
	public int getLikes(String ofertaFbId){

		int facebookLikes = 0;
		try {
			facebookLikes = facebookService.getOfertaLikes(ofertaFbId);
		} catch (ClientProtocolException e) {
			System.out
					.println("Hubo un problema al buscar la oferta. La petición a facebook no se ha podido completar. \n"
							+ e);
		} catch (IOException e) {
			System.out
					.println("Hubo un problema al buscar la oferta. La petición a facebook no se ha podido completar. \n"
							+ e);
		}
		return facebookLikes;
	}
	
	@Override
	public List<Integer> getLikesList(List<Oferta> ofertas){
		List<Integer> facebookLikes = new ArrayList<Integer>();
		for (Oferta oferta : ofertas) {
			try {
				facebookLikes.add(facebookService.getOfertaLikes(oferta
						.getFacebookId()));
			} catch (ClientProtocolException e) {
				System.out
						.println("No se han podido encontrar los likes. La petición a facebook no se ha podido completar. \n"
								+ e);
			} catch (IOException e) {
				System.out
						.println("No se han podido encontrar los likes. La petición a facebook no se ha podido completar. \n"
								+ e);
			}

		}
		return facebookLikes;
	}

}
