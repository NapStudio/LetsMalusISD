package es.udc.ws.app.test.model;

import static es.udc.ws.app.model.util.ModelConstants.OFERTA_DATA_SOURCE;
import static es.udc.ws.app.model.util.ModelConstants.MAX_PRICE;
import static es.udc.ws.app.model.util.ModelConstants.RESERVA_EXPIRATION_DAYS;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import java.sql.Connection;

import org.junit.BeforeClass;
import org.junit.Test;

import es.udc.ws.app.exceptions.OfertaReservadaException;
import es.udc.ws.app.exceptions.TimeExpirationException;
import es.udc.ws.app.model.oferta.Oferta;
import es.udc.ws.app.model.ofertaservice.OfertaService;
import es.udc.ws.app.model.ofertaservice.OfertaServiceFactory;
import es.udc.ws.app.model.reserva.Reserva;
import es.udc.ws.app.model.reserva.ReservaDAO;
import es.udc.ws.app.model.reserva.ReservaDAOFactory;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;
import es.udc.ws.util.sql.DataSourceLocator;
import es.udc.ws.util.sql.SimpleDataSource;

public class OfertaServiceTest {

	private final long NON_EXISTENT_OFERTA_ID = -1;
	private final long NON_EXISTENT_RESERVA_ID = -1;
	private final String USER_ID = "ws-user";

	private final String VALID_CREDIT_CARD_NUMBER = "1234567890123456";
	private final String INVALID_CREDIT_CARD_NUMBER = "";

	private static OfertaService ofertaService = null;

	private static ReservaDAO reservaDAO = null;

	@BeforeClass
	public static void init() {

		/*
		 * Create a simple data source and add it to "DataSourceLocator" (this
		 * is needed to test "es.udc.ws.app.model.ofertaservice.OfertaService"
		 */
		DataSource dataSource = new SimpleDataSource();

		/* Add "dataSource" to "DataSourceLocator". */
		DataSourceLocator.addDataSource(OFERTA_DATA_SOURCE, dataSource);

		ofertaService = OfertaServiceFactory.getService();

		reservaDAO = ReservaDAOFactory.getDao();

	}

	private Oferta getValidOferta(String title) {
		return new Oferta(title, "esto es una descripcion", "válida", 19.95F,
				17.50F, 1.75F, Calendar.getInstance(), Calendar.getInstance());
	}

	private Oferta getValidOferta(String title, String descripcion) {
		Calendar calendar= Calendar.getInstance();
		calendar.add(Calendar.MINUTE, RESERVA_EXPIRATION_DAYS);
		return new Oferta(title, descripcion, "válida", 19.95F, 17.50F, 1.75F,
				calendar, calendar);
	}

	private Oferta getValidOferta() {
		return getValidOferta("Oferta title");
	}

	private Oferta createOferta(Oferta oferta) {

		Oferta addedOferta = null;
		try {
			addedOferta = ofertaService.addOferta(oferta);
		} catch (InputValidationException e) {
			throw new RuntimeException(e);
		}
		return addedOferta;

	}

	private void removeOferta(Long ofertaId) throws InstanceNotFoundException,
			OfertaReservadaException {
		try {
			ofertaService.removeOferta(ofertaId);
		} catch (InstanceNotFoundException e) {
			throw new RuntimeException(e);
		} catch (OfertaReservadaException e) {
			throw new OfertaReservadaException(ofertaId);
		}

	}

	private void removeReserva(Long reservaId) {

		DataSource dataSource = DataSourceLocator
				.getDataSource(OFERTA_DATA_SOURCE);

		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				reservaDAO.remove(connection, reservaId);

				/* Commit. */
				connection.commit();

			} catch (InstanceNotFoundException e) {
				connection.commit();
				throw new RuntimeException(e);
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

	private void updateReserva(Reserva reserva) {

		DataSource dataSource = DataSourceLocator
				.getDataSource(OFERTA_DATA_SOURCE);

		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				reservaDAO.update(connection, reserva);

				/* Commit. */
				connection.commit();

			} catch (InstanceNotFoundException e) {
				connection.commit();
				throw new RuntimeException(e);
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

	@Test
	public void testAddOfertaAndFindOferta() throws InputValidationException,
			InstanceNotFoundException, OfertaReservadaException {

		Oferta oferta = getValidOferta();
		Oferta addedOferta = null;

		addedOferta = ofertaService.addOferta(oferta);
		/*
		 * oferta.getNombreOferta(),oferta.getDescripcionOferta(),oferta.
		 * getEstadoOferta(),
		 * oferta.getPrecioRealOferta(),oferta.getPrecioDescontadoOferta
		 * (),oferta.getComisionOferta(),
		 * oferta.getFechaLimiteOferta(),oferta.getFechaLimiteReserva()); Oferta
		 * foundOferta = ofertaService.findOferta(addedOferta.getOfertaId()
		 */

		Oferta foundOferta = ofertaService
				.findOferta(addedOferta.getOfertaId());

		assertEquals(addedOferta.getOfertaId(), foundOferta.getOfertaId());

		// Clear Database
		removeOferta(addedOferta.getOfertaId());

	}

	@Test
	public void testAddInvalidOferta() throws OfertaReservadaException,
			InstanceNotFoundException {

		Oferta oferta = getValidOferta();
		Oferta addedOferta = null;
		boolean exceptionCatched = false;

		try {
			// Check oferta title not null
			oferta.setNombreOferta(null);
			try {
				addedOferta = ofertaService.addOferta(oferta);
			} catch (InputValidationException e) {
				exceptionCatched = true;
			}
			assertTrue(exceptionCatched);

			// Check oferta title not empty
			exceptionCatched = false;
			oferta = getValidOferta();
			oferta.setNombreOferta("");
			try {
				addedOferta = ofertaService.addOferta(oferta);
			} catch (InputValidationException e) {
				exceptionCatched = true;
			}
			assertTrue(exceptionCatched);

			// Check oferta description not null
			exceptionCatched = false;
			oferta = getValidOferta();
			oferta.setDescripcionOferta(null);
			try {
				addedOferta = ofertaService.addOferta(oferta);
			} catch (InputValidationException e) {
				exceptionCatched = true;
			}
			assertTrue(exceptionCatched);

			// Check oferta price >= 0
			exceptionCatched = false;
			oferta = getValidOferta();
			oferta.setPrecioRealOferta((float) -1.0f);
			try {
				addedOferta = ofertaService.addOferta(oferta);
			} catch (InputValidationException e) {
				exceptionCatched = true;
			}
			assertTrue(exceptionCatched);

			// Check oferta price <= MAX_PRICE
			exceptionCatched = false;
			oferta = getValidOferta();
			oferta.setPrecioRealOferta((float) (MAX_PRICE + 1.0f));
			try {
				addedOferta = ofertaService.addOferta(oferta);
			} catch (InputValidationException e) {
				exceptionCatched = true;
			}
			assertTrue(exceptionCatched);
			// Check valid discount
			exceptionCatched = false;
			oferta = getValidOferta();
			oferta.setPrecioDescontadoOferta((float) -1.0f);
			try {
				addedOferta = ofertaService.addOferta(oferta);
			} catch (InputValidationException e) {
				exceptionCatched = true;
			}
			assertTrue(exceptionCatched);

			// TODO estado Oferta??

		} finally {
			if (!exceptionCatched) {
				// Clear Database
				removeOferta(addedOferta.getOfertaId());
			}
		}

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testFindNonExistentOferta() throws InstanceNotFoundException {
		ofertaService.findOferta(NON_EXISTENT_OFERTA_ID);

	}

	@Test
	public void testUpdateOferta() throws InputValidationException,
			InstanceNotFoundException, OfertaReservadaException {

		Oferta oferta = createOferta(getValidOferta());
		try {

			oferta.setNombreOferta("nome da oferta");
			oferta.setPrecioRealOferta((float) 100.0f);
			oferta.setPrecioDescontadoOferta((float) 40.0f);
			oferta.setDescripcionOferta("ofertiña");

			ofertaService.updateOferta(oferta);

			Oferta updatedOferta = ofertaService.findOferta(oferta
					.getOfertaId());

			assertEquals(oferta.getOfertaId(), updatedOferta.getOfertaId());

		} finally {
			// Clear Database
			removeOferta(oferta.getOfertaId());
		}

	}

	@Test(expected = InputValidationException.class)
	public void testUpdateInvalidOferta() throws InputValidationException,
			InstanceNotFoundException, OfertaReservadaException {

		Oferta oferta = createOferta(getValidOferta());
		try {
			// Check oferta max not null
			oferta = ofertaService.findOferta(oferta.getOfertaId());
			oferta.setNombreOferta(null);
			ofertaService.updateOferta(oferta);
		} finally {
			// Clear Database
			removeOferta(oferta.getOfertaId());
		}

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testUpdateNonExistentOferta() throws InputValidationException,
			InstanceNotFoundException {

		Oferta oferta = getValidOferta();
		oferta.setOfertaId(NON_EXISTENT_OFERTA_ID);
		ofertaService.updateOferta(oferta);

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testRemoveOferta() throws InstanceNotFoundException,
			OfertaReservadaException {

		Oferta oferta = createOferta(getValidOferta());
		boolean exceptionCatched = false;
		try {
			ofertaService.removeOferta(oferta.getOfertaId());
		} catch (InstanceNotFoundException e) {
			exceptionCatched = true;
		}
		assertTrue(!exceptionCatched);

		ofertaService.findOferta(oferta.getOfertaId());

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testRemoveNonExistentMovie() throws InstanceNotFoundException,
			OfertaReservadaException {

		ofertaService.removeOferta(NON_EXISTENT_OFERTA_ID);

	}

	@Test
	public void testFindOfertas() throws InstanceNotFoundException,
			OfertaReservadaException {

		// Add ofertas
		List<Oferta> ofertas = new LinkedList<Oferta>();
		Oferta oferta1 = createOferta(getValidOferta("oferta 1",
				"palabra clave 1"));
		ofertas.add(oferta1);
		Oferta oferta2 = createOferta(getValidOferta("oferta 2",
				"palabra eee clave 2"));
		ofertas.add(oferta2);
		Oferta oferta3 = createOferta(getValidOferta("oferta 3",
				"palabra asdfasd clave 1"));
		ofertas.add(oferta3);
		List<Oferta> foundOfertas = new ArrayList<Oferta>();

		try {
//			// Proba devolucion todo
//			foundOfertas = ofertaService.findOfertas("palabra", null, null);
////			assertEquals(ofertas, foundOfertas);
//TODO hacer este test
//			foundOfertas = ofertaService.findOfertas("clave", null, null);
//
//			assertEquals(ofertas, foundOfertas);
//
//			// Solo una
//			foundOfertas = ofertaService.findOfertas("eee", null, null);
//			assertEquals(1, foundOfertas.size());
//			assertEquals(ofertas.get(1), foundOfertas.get(0));
//
//			foundOfertas = ofertaService.findOfertas("1", null, null);
//			assertEquals(2, foundOfertas.size());
//
//			foundOfertas = ofertaService.findOfertas("palabra", "non", null);
//			assertEquals(0, foundOfertas.size());
		} finally {

			// Clear Database
			for (Oferta oferta : ofertas) {
				removeOferta(oferta.getOfertaId());
			}
		}

	}

	@Test
	public void testReservarOfertaAndFindReserva()
			throws InstanceNotFoundException, InputValidationException,
			OfertaReservadaException, TimeExpirationException {

		Oferta oferta = createOferta(getValidOferta());
		Reserva reserva = null;

		try {

			/* Buy reserva. */
//TODO este test
//			Calendar beforeExpirationCalendar = Calendar.getInstance();
//			beforeExpirationCalendar.add(Calendar.DAY_OF_MONTH,
//					RESERVA_EXPIRATION_DAYS);
//			beforeExpirationCalendar.set(Calendar.MILLISECOND, 0);
//
//			Long reservaId = (ofertaService.reservarOferta(
//					oferta.getOfertaId(), USER_ID, VALID_CREDIT_CARD_NUMBER));
//			reserva = ofertaService.findReserva(reservaId);
//
//			Calendar afterExpirationCalendar = Calendar.getInstance();
//			afterExpirationCalendar.add(Calendar.DAY_OF_MONTH,
//					RESERVA_EXPIRATION_DAYS);
//
//			afterExpirationCalendar.set(Calendar.MILLISECOND, 0);
//
//			Calendar fechaLimiteReserva = Calendar.getInstance();
//			fechaLimiteReserva=(oferta.getFechaLimiteReserva());
//			fechaLimiteReserva.set(Calendar.MILLISECOND, 0);
//
//			/* Find reserva. */
//			Reserva foundReserva = ofertaService.findReserva(reserva
//					.getReservaId());
//			Calendar fechaCreacion = Calendar.getInstance();
//			fechaCreacion=(foundReserva.getFechaCreacionReserva());
//			/* Check reserva. */
//			assertEquals(reserva, foundReserva);
//			assertEquals(VALID_CREDIT_CARD_NUMBER,
//					foundReserva.getTarjetaCreditoReserva());
//			assertEquals(USER_ID, foundReserva.getEmailUsuarioReserva());
//			assertEquals(oferta.getOfertaId(), foundReserva.getOfertaId());
//			assertEquals(reserva.getReservaId(), foundReserva.getReservaId());
//			System.out.println(fechaLimiteReserva);
//			System.out.println(beforeExpirationCalendar);
//			System.out.println(afterExpirationCalendar);
//			
			//TODO mirar lo de las fechas y tal
//			assertTrue((fechaLimiteReserva.compareTo(beforeExpirationCalendar) >= 0)
//					&& (fechaLimiteReserva.compareTo(afterExpirationCalendar) <= 0));

//			assertTrue(Calendar.getInstance().after(fechaCreacion));

		} finally {
			/* Clear database: remove reserva (if created) and movie. */
			if (reserva != null) {
				removeReserva(reserva.getReservaId());
			}
			removeOferta(oferta.getOfertaId());
		}

	}

	// TODO comprobar cuando se reserva una oferta que esta sea valida

	@Test(expected = InputValidationException.class)
	public void testReservarOfertaWithInvalidCreditCard()
			throws InputValidationException, InstanceNotFoundException,
			OfertaReservadaException, TimeExpirationException {

		Oferta oferta = createOferta(getValidOferta());
		try {
			ofertaService.reservarOferta(oferta.getOfertaId(), USER_ID,
					INVALID_CREDIT_CARD_NUMBER);

		} finally {
			/* Clear database. */
			removeOferta(oferta.getOfertaId());
		}

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testBuyNonExistentOferta() throws InputValidationException,
			InstanceNotFoundException, OfertaReservadaException, TimeExpirationException {

		Long reservaId = ofertaService.reservarOferta(NON_EXISTENT_OFERTA_ID,
				USER_ID, VALID_CREDIT_CARD_NUMBER);

		removeReserva(reservaId);

	}

}
