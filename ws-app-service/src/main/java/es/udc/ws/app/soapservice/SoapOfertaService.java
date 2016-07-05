package es.udc.ws.app.soapservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.http.client.ClientProtocolException;

import es.udc.ws.app.dto.OfertaDto;
import es.udc.ws.app.dto.ReservaDto;
import es.udc.ws.app.exceptions.BadStateReservaException;
import es.udc.ws.app.exceptions.OfertaReservadaException;
import es.udc.ws.app.exceptions.TimeExpirationException;
import es.udc.ws.app.model.oferta.Oferta;
import es.udc.ws.app.model.ofertaservice.OfertaService;
import es.udc.ws.app.model.ofertaservice.OfertaServiceFactory;
import es.udc.ws.app.model.reserva.Reserva;
import es.udc.ws.app.service.facebook.FacebookService;
import es.udc.ws.app.serviceutil.OfertaToOfertaDtoConversor;
import es.udc.ws.app.serviceutil.ReservaToReservaDtoConversor;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

@WebService(name = "OfertasProvider", serviceName = "OfertasProviderService", targetNamespace = "http://soap.ws.udc.es/")
public class SoapOfertaService {
	private FacebookService facebookService=new FacebookService();

	@WebMethod(operationName = "addOferta")
	public Long addOferta(@WebParam(name = "ofertaDto") OfertaDto ofertaDto)
			throws SoapInputValidationException {
		Oferta oferta = OfertaToOfertaDtoConversor.toOferta(ofertaDto);
		
			try {
				oferta.setFacebookId(facebookService.publicarOferta(oferta));
			} catch (ClientProtocolException e) {
				System.out.println("Errorfacebook 1"+e);
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Errorfacebook 1"+e);
				e.printStackTrace();
			}
	
		try {
			return OfertaServiceFactory.getService().addOferta(oferta)
					.getOfertaId();
		} catch (InputValidationException ex) {
			throw new SoapInputValidationException(ex.getMessage());
		}
	}

	@WebMethod(operationName = "updateOferta")
	public void updateOferta(@WebParam(name = "ofertaDto") OfertaDto ofertaDto)
			throws SoapInputValidationException, SoapInstanceNotFoundException {
		Oferta oferta = OfertaToOfertaDtoConversor.toOferta(ofertaDto);
		try {
			Oferta ofertaFace=OfertaServiceFactory.getService().findOferta(oferta.getOfertaId());
			System.out.println("ofertaFace"+ofertaFace);
			try {
				oferta.setFacebookId(facebookService.actualizarOferta(ofertaFace));
			} catch (Exception e) {
				System.out.println("problema fb");
			}
		} catch (InstanceNotFoundException e) {
			System.out.println("problema fb");
		}
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
			throws SoapInstanceNotFoundException, SoapInputValidationException {
		try {
			OfertaServiceFactory.getService().invalidarOferta(ofertaId);
			try {
				facebookService.borrarOferta(OfertaServiceFactory.getService().findOferta(ofertaId).getFacebookId());
			} catch (ClientProtocolException e) {
				System.out.println("Error remove 1"+e);
			} catch (IOException e) {
				System.out.println("Error remove 2"+e);
			}
		} catch (InstanceNotFoundException ex) {
			throw new SoapInstanceNotFoundException(
					new SoapInstanceNotFoundExceptionInfo(ex.getInstanceId(),
							ex.getInstanceType()));
		} catch (InputValidationException e) {
			throw new SoapInputValidationException(e.getMessage());
		}
	}

	@WebMethod(operationName = "removeOferta")
	public void removeOferta(@WebParam(name = "ofertaId") Long ofertaId)
			throws SoapInstanceNotFoundException, SoapOfertaReservadaException {
		try {
			String faceId=OfertaServiceFactory.getService().findOferta(ofertaId).getFacebookId();
			try {
				facebookService.borrarOferta(faceId);
			} catch (ClientProtocolException e) {
				System.out.println("Error remove 1"+e);
			} catch (IOException e) {
				System.out.println("Error remove 2"+e);
			}
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

	@WebMethod(operationName = "findOferta")
	public OfertaDto findOferta(
			@WebParam(name = "ofertaId") Long ofertaId) throws SoapInstanceNotFoundException {
		Oferta oferta;
		try {
			oferta = OfertaServiceFactory.getService().findOferta(
					ofertaId);
		} catch (InstanceNotFoundException ex) {
			throw new SoapInstanceNotFoundException(
					new SoapInstanceNotFoundExceptionInfo(ex.getInstanceId(),
							ex.getInstanceType()));
		}
		int facebookLikes = 0;
		try {
			facebookLikes = facebookService.getOfertaLikes(oferta.getFacebookId());
		}  catch (ClientProtocolException e) {
			System.out.println("Error finds 1"+e);
		} catch (IOException e) {
			System.out.println("Error finds 2"+e);
		}
		return OfertaToOfertaDtoConversor.toOfertaDto(oferta,facebookLikes);
	}

	@WebMethod(operationName = "findOfertas")
	public List<OfertaDto> findOfertas(
			@WebParam(name = "keywords") String keywords,
			@WebParam(name = "estadoBusqueda") String estadoBusqueda,
			@WebParam(name = "fechaBusqueda") Calendar fechaBusqueda) {
		List<Oferta> ofertas = OfertaServiceFactory.getService().findOfertas(
				keywords, estadoBusqueda, fechaBusqueda);
		List<Integer> facebookLikes= new ArrayList<Integer>();
		for(Oferta oferta:ofertas){
				try {
					facebookLikes.add(facebookService.getOfertaLikes(oferta.getFacebookId()));
				} catch (ClientProtocolException e) {
					System.out.println("Error find 1"+e);
				} catch (IOException e) {
					System.out.println("Error find 2"+e);
				}
			
		}
		return OfertaToOfertaDtoConversor.toOfertaDtos(ofertas,facebookLikes);
	}

	@WebMethod(operationName = "reservarOferta")
	public Long reservarOferta(
			@WebParam(name = "ofertaId") Long ofertaId,
			@WebParam(name = "emailUsuarioReserva") String emailUsuarioReserva,
			@WebParam(name = "tarjetaCreditoReserva") String tarjetaCreditoReserva)
			throws SoapInstanceNotFoundException, SoapInputValidationException, SoapOfertaReservadaException, SoapTimeExpirationException {
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
		} catch (OfertaReservadaException ex) {
			throw new SoapOfertaReservadaException(new SoapOfertaReservadaExceptionInfo(ex.getOfertaId()));
		} catch (TimeExpirationException e) {
			throw new SoapTimeExpirationException(new SoapTimeExpirationExceptionInfo(e.getMessage(),e.getId(),e.getFechaExpiracion()));
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

	@WebMethod(operationName = "reclamarOferta")
	public Long reclamarOferta(@WebParam(name = "reservaId") Long reservaId)
			throws SoapInstanceNotFoundException, SoapBadStateReservaException,
			SoapTimeExpirationException {
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
		} catch (TimeExpirationException e) {
			throw new SoapTimeExpirationException(
					new SoapTimeExpirationExceptionInfo(e.getMessage(), e.getId(),
							e.getFechaExpiracion()));
		}
	}

	@WebMethod(operationName = "findReservasByOferta")
	public List<ReservaDto> findReservasByOferta(
			@WebParam(name = "ofertaId") Long ofertaId)	throws SoapInstanceNotFoundException,
			SoapTimeExpirationException {
		
		try{
			System.out.println(Long.valueOf(ofertaId));
		List<Reserva> reservas = OfertaServiceFactory.getService()
				.findReservasByOferta(ofertaId);

		return ReservaToReservaDtoConversor.toReservaDtos(reservas);
		}catch(InstanceNotFoundException ex) {
			throw new SoapInstanceNotFoundException(
					new SoapInstanceNotFoundExceptionInfo(ex.getInstanceId(),
							ex.getInstanceType()));			
		}catch(TimeExpirationException ex) {
			throw new SoapTimeExpirationException(
					new SoapTimeExpirationExceptionInfo(ex.getMessage(), ex.getId(),
							ex.getFechaExpiracion()));	
		}
	}

	@WebMethod(operationName = "findReservasByUsuario")
	public List<ReservaDto> findReservasByUsuario(
			@WebParam(name = "emailUsuarioReserva") String emailUsuarioReserva,
			@WebParam(name = "estadoReserva") String estadoReserva)
			throws SoapInstanceNotFoundException,
			SoapTimeExpirationException {
		try{
			System.out.println("emailUsuario "+emailUsuarioReserva+" estado: "+estadoReserva);
			List<Reserva> reservas = OfertaServiceFactory.getService()
					.findReservasByUsuario(emailUsuarioReserva, estadoReserva);

			return ReservaToReservaDtoConversor.toReservaDtos(reservas);
			}catch(InstanceNotFoundException ex) {
				throw new SoapInstanceNotFoundException(
						new SoapInstanceNotFoundExceptionInfo(ex.getInstanceId(),
								ex.getInstanceType()));			
			}catch(TimeExpirationException ex) {
				throw new SoapTimeExpirationException(
						new SoapTimeExpirationExceptionInfo(ex.getMessage(), ex.getId(),
								ex.getFechaExpiracion()));	
			}
	}

}
