package es.udc.ws.app.client.service.soap;

import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.BindingProvider;

import es.udc.ws.app.client.service.ClientOfertaService;
import es.udc.ws.app.client.service.soap.wsdl.*;
import es.udc.ws.app.client.service.soap.ReservaDtoToSoapReservaDtoConversor;
import es.udc.ws.util.configuration.ConfigurationParametersManager;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;
import es.udc.ws.app.dto.OfertaDto;
import es.udc.ws.app.dto.ReservaDto;
import es.udc.ws.app.exceptions.BadStateReservaException;
import es.udc.ws.app.exceptions.OfertaReservadaException;
import es.udc.ws.app.exceptions.TimeExpirationException;

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
	public List<OfertaDto> findOfertas(String keywords) throws DatatypeConfigurationException {
		return OfertaDtoToSoapOfertaDtoConversor
				.toOfertaDtos(ofertasProvider.findOfertas(keywords));
	}

	@Override
	public void invalidarOferta(Long ofertaId)
			throws InstanceNotFoundException, InputValidationException {
		try {
			ofertasProvider.invalidarOferta(ofertaId);
		} catch (SoapInstanceNotFoundException ex) {
			throw new InstanceNotFoundException(ex.getFaultInfo()
					.getInstanceId(), ex.getFaultInfo().getInstanceType());
		} catch (SoapInputValidationException e) {
			throw new InputValidationException(e.getMessage());
		}
	}

	private String getEndpointAddress() {

		if (endpointAddress == null) {
			endpointAddress = ConfigurationParametersManager
					.getParameter(ENDPOINT_ADDRESS_PARAMETER);
		}

		return endpointAddress;
	}

	@Override
	public OfertaDto findOferta(Long ofertaId) throws InstanceNotFoundException {
		try {
			return OfertaDtoToSoapOfertaDtoConversor
					.toOfertaDto(ofertasProvider.findOferta(ofertaId));
		} catch (SoapInstanceNotFoundException ex) {
			throw new InstanceNotFoundException(ex.getFaultInfo()
					.getInstanceId(), ex.getFaultInfo().getInstanceType());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Long reservarOferta(Long ofertaId, String emailUsuarioReserva,
			String tarjetaCreditoReserva) throws InstanceNotFoundException,
			InputValidationException, NumberFormatException,
			OfertaReservadaException, TimeExpirationException {
		try {
			return ofertasProvider.reservarOferta(ofertaId,
					emailUsuarioReserva, tarjetaCreditoReserva);
		} catch (SoapInputValidationException ex) {
			throw new InputValidationException(ex.getMessage());
		} catch (SoapInstanceNotFoundException ex) {
			throw new InstanceNotFoundException(ex.getFaultInfo()
					.getInstanceId(), ex.getFaultInfo().getInstanceType());
		} catch (SoapOfertaReservadaException e) {
			throw new OfertaReservadaException(e.getFaultInfo().getOfertaId());
		} catch (SoapTimeExpirationException e) {
			throw new TimeExpirationException(e.getFaultInfo().getMessage(), e.getFaultInfo().getId(), GregorianCalendarConversor.toCalendar(e.getFaultInfo().getFechaExpiracion()));
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
			throws InstanceNotFoundException, TimeExpirationException {
		try {
			return ReservaDtoToSoapReservaDtoConversor
					.toReservaDtos(ofertasProvider
							.findReservasByOferta(ofertaId));
		} catch (SoapInstanceNotFoundException ex) {
			throw new InstanceNotFoundException(ex.getFaultInfo()
					.getInstanceId(), ex.getFaultInfo().getInstanceType());
		} catch (SoapTimeExpirationException e) {
			throw new TimeExpirationException(e.getFaultInfo().getMessage(),
					e.getFaultInfo().getId(),
					GregorianCalendarConversor.toCalendar(e.getFaultInfo()
							.getFechaExpiracion()));
		}
	}

	@Override
	public List<ReservaDto> findReservasByUsuario(String emailUsuarioReserva,
			String estado) throws InstanceNotFoundException,
			TimeExpirationException {
		try {

			return ReservaDtoToSoapReservaDtoConversor
					.toReservaDtos(ofertasProvider.findReservasByUsuario(
							emailUsuarioReserva, estado));
		} catch (SoapInstanceNotFoundException ex) {
			throw new InstanceNotFoundException(ex.getFaultInfo()
					.getInstanceId(), ex.getFaultInfo().getInstanceType());
		} catch (SoapTimeExpirationException e) {
			throw new TimeExpirationException(e.getFaultInfo().getMessage(), e.getFaultInfo().getId(), GregorianCalendarConversor.toCalendar(e.getFaultInfo().getFechaExpiracion()));
		}
	}

	@Override
	public Long reclamarOferta(Long reservaId)
			throws InstanceNotFoundException, BadStateReservaException,
			TimeExpirationException {
		try {
			return ofertasProvider.reclamarOferta(reservaId);
		} catch (SoapBadStateReservaException e) {
			throw new BadStateReservaException(e.getFaultInfo().getReservaId(),
					e.getFaultInfo().getEstadoReserva());
		} catch (SoapInstanceNotFoundException e) {
			throw new InstanceNotFoundException(e.getFaultInfo()
					.getInstanceId(), e.getFaultInfo().getInstanceType());
		} catch (SoapTimeExpirationException e) {
			throw new TimeExpirationException(e.getFaultInfo().getMessage(), e.getFaultInfo().getId(), GregorianCalendarConversor.toCalendar(e.getFaultInfo().getFechaExpiracion()));
		}
	}
}
