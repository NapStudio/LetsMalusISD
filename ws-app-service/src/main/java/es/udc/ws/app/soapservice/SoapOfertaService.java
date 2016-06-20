package es.udc.ws.app.soapservice;

import java.util.Calendar;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import es.udc.ws.app.dto.OfertaDto;
import es.udc.ws.app.dto.ReservaDto;
import es.udc.ws.app.exceptions.BadStateReservaException;
import es.udc.ws.app.exceptions.OfertaReservadaException;
import es.udc.ws.app.exceptions.ReservaExpirationException;
import es.udc.ws.app.model.oferta.Oferta;
import es.udc.ws.app.model.ofertaservice.OfertaServiceFactory;
import es.udc.ws.app.model.reserva.Reserva;
import es.udc.ws.app.serviceutil.OfertaToOfertaDtoConversor;
import es.udc.ws.app.serviceutil.ReservaToReservaDtoConversor;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

@WebService(name = "OfertasProvider", serviceName = "OfertasProviderService", targetNamespace = "http://soap.ws.udc.es/")
public class SoapOfertaService {

	@WebMethod(operationName = "addOferta")
	public Long addOferta(@WebParam(name = "ofertaDto") OfertaDto ofertaDto)
			throws SoapInputValidationException {
		Oferta oferta = OfertaToOfertaDtoConversor.toOferta(ofertaDto);
		try {
			return OfertaServiceFactory.getService().addOferta(oferta)
					.getOfertaId();
		} catch (InputValidationException ex) {
			// TODO implementar esta excepcion
			throw new SoapInputValidationException(ex.getMessage());
		}
	}

	@WebMethod(operationName = "updateOferta")
	public void updateOferta(@WebParam(name = "ofertaDto") OfertaDto ofertaDto)
			throws SoapInputValidationException, SoapInstanceNotFoundException {
		Oferta oferta = OfertaToOfertaDtoConversor.toOferta(ofertaDto);
		try {
			OfertaServiceFactory.getService().updateOferta(oferta);
		} catch (InputValidationException ex) {
			throw new SoapInputValidationException(ex.getMessage());
		} catch (InstanceNotFoundException ex) {
			throw new SoapInstanceNotFoundException(
					new SoapInstanceNotFoundExceptionInfo(ex.getInstanceId(),
							ex.getInstanceType()));
		}
	}

	@WebMethod(operationName = "invalidarOferta")
	public void invalidarOferta(@WebParam(name = "ofertaId") Long ofertaId)
			throws SoapInstanceNotFoundException {
		try {
			OfertaServiceFactory.getService().invalidarOferta(ofertaId);
		} catch (InstanceNotFoundException ex) {
			throw new SoapInstanceNotFoundException(
					new SoapInstanceNotFoundExceptionInfo(ex.getInstanceId(),
							ex.getInstanceType()));
		}
	}

	@WebMethod(operationName = "removeOferta")
	public void removeOferta(@WebParam(name = "ofertaId") Long ofertaId)
			throws SoapInstanceNotFoundException, SoapOfertaReservadaException {
		try {
			OfertaServiceFactory.getService().removeOferta(ofertaId);
		} catch (InstanceNotFoundException ex) {
			throw new SoapInstanceNotFoundException(
					new SoapInstanceNotFoundExceptionInfo(ex.getInstanceId(),
							ex.getInstanceType()));
		} catch (OfertaReservadaException e) {
			throw new SoapOfertaReservadaException(
					new SoapOfertaReservadaExceptionInfo(e.getOfertaId()));
		}
	}

	@WebMethod(operationName = "findOfertas")
	public List<OfertaDto> findOfertas(
			@WebParam(name = "keywords") String keywords,
			@WebParam(name = "estadoBusqueda") String estadoBusqueda,
			@WebParam(name = "fechaBusqueda") Calendar fechaBusqueda) {
		List<Oferta> ofertas = OfertaServiceFactory.getService().findOfertas(
				keywords, estadoBusqueda, fechaBusqueda);
		return OfertaToOfertaDtoConversor.toOfertaDtos(ofertas);
	}

	@WebMethod(operationName = "reservarOferta")
	public Long reservarOferta(
			@WebParam(name = "ofertaId") Long ofertaId,
			@WebParam(name = "emailUsuarioReserva") String emailUsuarioReserva,
			@WebParam(name = "tarjetaCreditoReserva") String tarjetaCreditoReserva)
			throws SoapInstanceNotFoundException, SoapInputValidationException {
		try {
			Long reservaId = OfertaServiceFactory.getService().reservarOferta(
					ofertaId, emailUsuarioReserva, tarjetaCreditoReserva);
			return reservaId;
		} catch (InstanceNotFoundException ex) {
			throw new SoapInstanceNotFoundException(
					new SoapInstanceNotFoundExceptionInfo(ex.getInstanceId(),
							ex.getInstanceType()));
		} catch (InputValidationException ex) {
			throw new SoapInputValidationException(ex.getMessage());
		}
	}

	@WebMethod(operationName = "findReserva")
	public ReservaDto findReserva(@WebParam(name = "reservaId") Long reservaId)
			throws SoapInstanceNotFoundException {
		try {
			Reserva reserva = OfertaServiceFactory.getService().findReserva(
					reservaId);
			return ReservaToReservaDtoConversor.toReservaDto(reserva);
		} catch (InstanceNotFoundException ex) {
			throw new SoapInstanceNotFoundException(
					new SoapInstanceNotFoundExceptionInfo(ex.getInstanceId(),
							ex.getInstanceType()));
		}
	}

	// TODO cambiar paramentros entrada reclamarOferta
	@WebMethod(operationName = "reclamarOferta")
	public Long reclamarOferta(@WebParam(name = "reservaId") Long reservaId)
			throws SoapInstanceNotFoundException, SoapBadStateReservaException,
			SoapReservaExpirationException {
		try {
			reservaId = OfertaServiceFactory.getService().reclamarOferta(
					reservaId);
			return reservaId;
		} catch (InstanceNotFoundException ex) {
			throw new SoapInstanceNotFoundException(
					new SoapInstanceNotFoundExceptionInfo(ex.getInstanceId(),
							ex.getInstanceType()));
		} catch (BadStateReservaException e) {
			throw new SoapBadStateReservaException(
					new SoapBadStateReservaExceptionInfo(e.getReservaId(),
							e.getEstadoReserva()));
		} catch (ReservaExpirationException e) {
			throw new SoapReservaExpirationException(
					new SoapReservaExpirationExceptionInfo(e.getReservaId(),
							e.getFechaExpiracion()));
		}
	}

	@WebMethod(operationName = "findReservasByOferta")
	public List<ReservaDto> findReservasByOferta(
			@WebParam(name = "ofertaId") Long ofertaId)	throws SoapInstanceNotFoundException,
			SoapReservaExpirationException {
		
		try{
		List<Reserva> reservas = OfertaServiceFactory.getService()
				.findReservasByOferta(ofertaId);

		return ReservaToReservaDtoConversor.toReservaDtos(reservas);
		}catch(InstanceNotFoundException ex) {
			throw new SoapInstanceNotFoundException(
					new SoapInstanceNotFoundExceptionInfo(ex.getInstanceId(),
							ex.getInstanceType()));			
		}catch(ReservaExpirationException ex) {
			throw new SoapReservaExpirationException(
					new SoapReservaExpirationExceptionInfo(ex.getReservaId(),
							ex.getFechaExpiracion()));	
		}
	}

	@WebMethod(operationName = "findReservasByUsuario")
	public List<ReservaDto> findReservasByUsuario(
			@WebParam(name = "emailUsuarioReserva") String emailUsuarioReserva)
			throws SoapInstanceNotFoundException,
			SoapReservaExpirationException {
		try{
			List<Reserva> reservas = OfertaServiceFactory.getService()
					.findReservasByUsuario(emailUsuarioReserva);

			return ReservaToReservaDtoConversor.toReservaDtos(reservas);
			}catch(InstanceNotFoundException ex) {
				throw new SoapInstanceNotFoundException(
						new SoapInstanceNotFoundExceptionInfo(ex.getInstanceId(),
								ex.getInstanceType()));			
			}catch(ReservaExpirationException ex) {
				throw new SoapReservaExpirationException(
						new SoapReservaExpirationExceptionInfo(ex.getReservaId(),
								ex.getFechaExpiracion()));	
			}
	}

}
