package es.udc.ws.app.client.service.soap;

import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.BindingProvider;

import es.udc.ws.app.client.service.ClientOfertaService;
import es.udc.ws.app.client.service.soap.wsdl.*;
import es.udc.ws.util.configuration.ConfigurationParametersManager;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;
import es.udc.ws.app.dto.OfertaDto;
import es.udc.ws.app.dto.ReservaDto;
import es.udc.ws.app.exceptions.BadStateReservaException;
import es.udc.ws.app.exceptions.ReservaExpirationException;

public class SoapClientOfertaService implements ClientOfertaService {
	private final static String ENDPOINT_ADDRESS_PARAMETER = "SoapClientOfertaService.endpointAddress";

	private String endpointAddress;

	private OfertasProvider ofertasProvider;

	public SoapClientOfertaService() {
		init(getEndpointAddress());
	}

	private void init(String ofertasProviderURL) {
		OfertasProviderService moviesProviderService = new OfertasProviderService();
		ofertasProvider = moviesProviderService.getOfertasProviderPort();
		((BindingProvider) ofertasProvider).getRequestContext().put(
				BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ofertasProviderURL);
	}

	@Override
	public Long addOferta(OfertaDto oferta) throws InputValidationException {
		try {
			return ofertasProvider.addOferta(OfertaDtoToSoapOfertaDtoConversor
					.toSoapOfertaDto(oferta));
		} catch (SoapInputValidationException ex) {
			throw new InputValidationException(ex.getMessage());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void updateOferta(OfertaDto oferta) throws InputValidationException,
			InstanceNotFoundException {
		try {
			ofertasProvider.updateOferta(OfertaDtoToSoapOfertaDtoConversor
					.toSoapOfertaDto(oferta));
		} catch (SoapInputValidationException ex) {
			throw new InputValidationException(ex.getMessage());
		} catch (SoapInstanceNotFoundException ex) {
			throw new InstanceNotFoundException(ex.getFaultInfo()
					.getInstanceId(), ex.getFaultInfo().getInstanceType());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void removeOferta(Long ofertaId) throws InstanceNotFoundException,
			SoapOfertaReservadaException {
		try {
			ofertasProvider.removeOferta(ofertaId);
		} catch (SoapInstanceNotFoundException ex) {
			throw new InstanceNotFoundException(ex.getFaultInfo()
					.getInstanceId(), ex.getFaultInfo().getInstanceType());
		}
	}

	@Override
	public List<OfertaDto> findOfertas(String keywords, String estado,
			Calendar date) throws DatatypeConfigurationException {
		try {
			return OfertaDtoToSoapOfertaDtoConversor
					.toOfertaDtos(ofertasProvider.findOfertas(keywords, estado,
							(GregorianCalendarConversor
									.toXMLGregorianCalendar(date))));
		} catch (DatatypeConfigurationException e) {
			throw new DatatypeConfigurationException(e);
		}
	}

	@Override
	public void invalidarOferta(Long ofertaId) throws InstanceNotFoundException {
		try {
			ofertasProvider.invalidarOferta(ofertaId);
		} catch (SoapInstanceNotFoundException ex) {
			throw new InstanceNotFoundException(ex.getFaultInfo()
					.getInstanceId(), ex.getFaultInfo().getInstanceType());
		}
	}

	private String getEndpointAddress() {

		if (endpointAddress == null) {
			endpointAddress = ConfigurationParametersManager
					.getParameter(ENDPOINT_ADDRESS_PARAMETER);
		}

		return endpointAddress;
	}

	// @Override
	// public OfertaDto findOferta(Long ofertaId) throws
	// InstanceNotFoundException {
	// try {
	// ofertasProvider.findOferta(ofertaId);
	// } catch (SoapInstanceNotFoundException ex) {
	// throw new InstanceNotFoundException(ex.getFaultInfo()
	// .getInstanceId(), ex.getFaultInfo().getInstanceType());
	// }
	// }

	@Override
	public Long reservarOferta(Long ofertaId, String emailUsuarioReserva,
			String tarjetaCreditoReserva) throws InstanceNotFoundException,
			InputValidationException {
		try {
			return ofertasProvider.reservarOferta(ofertaId,
					emailUsuarioReserva, tarjetaCreditoReserva);
		} catch (SoapInputValidationException ex) {
			throw new InputValidationException(ex.getMessage());
		} catch (SoapInstanceNotFoundException ex) {
			throw new InstanceNotFoundException(ex.getFaultInfo()
					.getInstanceId(), ex.getFaultInfo().getInstanceType());
		}
	}

	@Override
	public ReservaDto findReserva(Long reservaId)
			throws InstanceNotFoundException {
		try {
			return ReservaDtoToSoapReservaDtoConversor
					.toReservaDto(ofertasProvider.findReserva(reservaId));
		} catch (SoapInstanceNotFoundException ex) {
			throw new InstanceNotFoundException(ex.getFaultInfo()
					.getInstanceId(), ex.getFaultInfo().getInstanceType());
		}
	}

	@Override
	public List<ReservaDto> findReservasByOferta(Long ofertaId)
			throws InstanceNotFoundException, ReservaExpirationException {
		try {
			return ReservaDtoToSoapReservaDtoConversor
					.toReservaDtos(ofertasProvider
							.findReservasByOferta(ofertaId));
		} catch (SoapInstanceNotFoundException ex) {
			throw new InstanceNotFoundException(ex.getFaultInfo()
					.getInstanceId(), ex.getFaultInfo().getInstanceType());
		} catch (SoapReservaExpirationException e) {
			// TODO es una lista, si esta excepcion es de un reserva en concreto
			// como lanzas la excepcion?

			throw new ReservaExpirationException(ofertaId, null);
		}
	}

	@Override
	public List<ReservaDto> findReservasByUsuario(String emailUsuarioReserva)
			throws InstanceNotFoundException, ReservaExpirationException {
		try {
			return ReservaDtoToSoapReservaDtoConversor
					.toReservaDtos(ofertasProvider
							.findReservasByUsuario(emailUsuarioReserva));
		} catch (SoapInstanceNotFoundException ex) {
			throw new InstanceNotFoundException(ex.getFaultInfo()
					.getInstanceId(), ex.getFaultInfo().getInstanceType());
		} catch (SoapReservaExpirationException e) {
			// TODO es una lista, si esta excepcion es de un reserva en concreto
			// como lanzas la excepcion?

			throw new ReservaExpirationException((long) 0, null);
		}
	}

	@Override
	public Long reclamarOferta(Long reservaId)
			throws InstanceNotFoundException, BadStateReservaException,
			ReservaExpirationException {
		try {
			return ofertasProvider.reclamarOferta(reservaId);
		} catch (SoapBadStateReservaException e) {
			throw new BadStateReservaException(reservaId, null);
		} catch (SoapInstanceNotFoundException e) {
			throw new InstanceNotFoundException(e.getFaultInfo()
					.getInstanceId(), e.getFaultInfo().getInstanceType());
		} catch (SoapReservaExpirationException e) {
			// TODO Auto-generated catch block
			throw new ReservaExpirationException((long) 0, null);
		}
	}
}
