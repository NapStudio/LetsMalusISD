package es.udc.ws.app.restservice.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.udc.ws.app.dto.OfertaDto;
import es.udc.ws.app.dto.ReservaDto;
import es.udc.ws.app.exceptions.BadStateReservaException;
import es.udc.ws.app.exceptions.OfertaReservadaException;
import es.udc.ws.app.exceptions.TimeExpirationException;
import es.udc.ws.app.model.oferta.Oferta;
import es.udc.ws.app.model.ofertaservice.OfertaServiceFactory;
import es.udc.ws.app.model.reserva.Reserva;
import es.udc.ws.app.serviceutil.OfertaToOfertaDtoConversor;
import es.udc.ws.app.serviceutil.ReservaToReservaDtoConversor;
import es.udc.ws.app.xml.ParsingException;
import es.udc.ws.app.xml.XmlExceptionConversor;
import es.udc.ws.app.xml.XmlOfertaDtoConversor;
import es.udc.ws.app.xml.XmlReservaDtoConversor;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;
import es.udc.ws.util.servlet.ServletUtils;

@SuppressWarnings("serial")
public class ReservaServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String ofertaIdParameter = req.getParameter("ofertaId");
		if (ofertaIdParameter == null) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "parameter 'ofertaId' is mandatory")),
							null);
			return;
		}
		Long ofertaId;
		try {
			ofertaId = Long.valueOf(ofertaIdParameter);
		} catch (NumberFormatException ex) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "parameter 'ofertaId' is invalid '"
													+ ofertaIdParameter + "'")),
							null);

			return;
		}
		String emailUsuarioReserva = req.getParameter("emailUsuarioReserva");
		if (emailUsuarioReserva == null) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "parameter 'emailUsuarioReserva' is mandatory")),
							null);
			return;
		}
		String tarjetaCreditoReserva = req
				.getParameter("tarjetaCreditoReserva");
		if (tarjetaCreditoReserva == null) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "parameter 'tarjetaCreditoReserva' is mandatory")),
							null);

			return;
		}
		Long reserva = (long) 0;
		try {
			reserva = OfertaServiceFactory.getService().reservarOferta(
					ofertaId, emailUsuarioReserva, tarjetaCreditoReserva);
		} catch (InstanceNotFoundException ex) {
			ServletUtils
					.writeServiceResponse(resp,
							HttpServletResponse.SC_NOT_FOUND,
							XmlExceptionConversor
									.toInstanceNotFoundException(ex), null);
			return;
		} catch (InputValidationException ex) {
			ServletUtils.writeServiceResponse(resp,
					HttpServletResponse.SC_BAD_REQUEST,
					XmlExceptionConversor.toInputValidationExceptionXml(ex),
					null);
			return;
		} catch (OfertaReservadaException e) {
			System.out.println("ofertaReservadaException 1  " + e.toString());
			ServletUtils.writeServiceResponse(resp,
					HttpServletResponse.SC_FORBIDDEN,
					XmlExceptionConversor.toOfertaReservadaExceptionXml(e),
					null);
		} catch (TimeExpirationException e) {
			System.out.println("ofertaReservadaException 1  " + e.toString());
			ServletUtils.writeServiceResponse(resp,
					HttpServletResponse.SC_GONE,
					XmlExceptionConversor.toTimeExpirationException(e),
					null);
		}
		ReservaDto reservaDto;
		try {
			reservaDto = ReservaToReservaDtoConversor
					.toReservaDto(OfertaServiceFactory.getService()
							.findReserva(reserva));
		} catch (InstanceNotFoundException e) {
			ServletUtils.writeServiceResponse(resp,
					HttpServletResponse.SC_NOT_FOUND,
					XmlExceptionConversor.toInstanceNotFoundException(e), null);
			return;
		}

		String reservaURL = ServletUtils.normalizePath(req.getRequestURL()
				.toString()) + "/" + reserva;

		Map<String, String> headers = new HashMap<>(1);
		headers.put("Location", reservaURL);

		ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_CREATED,
				XmlReservaDtoConversor.toResponse(reservaDto), headers);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String path = ServletUtils.normalizePath(req.getPathInfo());
		if (path == null || path.length() == 0) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "invalid reserva id")),
							null);
			return;
		}
		String reservaIdAsString = path.substring(1);
		System.out.println(reservaIdAsString);
		Long reservaId;
		try {
			reservaId = Long.valueOf(reservaIdAsString);
		} catch (NumberFormatException ex) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "invalid reserva id '"
													+ reservaIdAsString + "'")),
							null);
			return;
		}
		try {
			OfertaServiceFactory.getService().reclamarOferta(reservaId);
		} catch (InstanceNotFoundException ex) {
			ServletUtils
					.writeServiceResponse(resp,
							HttpServletResponse.SC_NOT_FOUND,
							XmlExceptionConversor
									.toInstanceNotFoundException(ex), null);
			return;
		} catch (BadStateReservaException e) {
			ServletUtils.writeServiceResponse(resp,
					HttpServletResponse.SC_NOT_FOUND,
					XmlExceptionConversor.toBadStateReservaExceptionXml(e),
					null);
		} catch (TimeExpirationException e) {
			ServletUtils
					.writeServiceResponse(resp, HttpServletResponse.SC_GONE,
							XmlExceptionConversor
									.toTimeExpirationException(e), null);
		}
		ServletUtils.writeServiceResponse(resp,
				HttpServletResponse.SC_NO_CONTENT, null, null);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = ServletUtils.normalizePath(req.getPathInfo());
		if (path == null || path.length() == 0) {
			String ofertaIdParameter = req.getParameter("ofertaId");
			if (!(ofertaIdParameter == null)) {
				Long ofertaId;
				try {
					ofertaId = Long.valueOf(ofertaIdParameter);
				} catch (NumberFormatException ex) {
					ServletUtils
							.writeServiceResponse(
									resp,
									HttpServletResponse.SC_BAD_REQUEST,
									XmlExceptionConversor
											.toInputValidationExceptionXml(new InputValidationException(
													"Invalid Request: "
															+ "parameter 'ofertaId' is invalid '"
															+ ofertaIdParameter
															+ "'")), null);

					return;
				}
				try {
					List<Reserva> reservas = OfertaServiceFactory.getService()
							.findReservasByOferta(ofertaId);
					List<ReservaDto> reservaDtos = ReservaToReservaDtoConversor.toReservaDtos(reservas);
					ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_OK,
							XmlReservaDtoConversor.toXml(reservaDtos), null);
				} catch (InstanceNotFoundException e) {
					ServletUtils.writeServiceResponse(resp,
							HttpServletResponse.SC_NOT_FOUND,
							XmlExceptionConversor
									.toInstanceNotFoundException(e), null);
					return;
				} catch (TimeExpirationException e) {
					ServletUtils.writeServiceResponse(resp,
							HttpServletResponse.SC_GONE, XmlExceptionConversor
									.toTimeExpirationException(e), null);
				}
			} else {
				String emailUsuarioReserva = req.getParameter("emailUsuarioReserva");
				String estado = req.getParameter("estado");
				try {
					List<Reserva> reservas = OfertaServiceFactory.getService()
							.findReservasByUsuario(emailUsuarioReserva, estado);
					List<ReservaDto> reservaDtos = ReservaToReservaDtoConversor.toReservaDtos(reservas);
					ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_OK,
							XmlReservaDtoConversor.toXml(reservaDtos), null);
				} catch (InstanceNotFoundException e) {
					ServletUtils.writeServiceResponse(resp,
							HttpServletResponse.SC_NOT_FOUND,
							XmlExceptionConversor
									.toInstanceNotFoundException(e), null);
				} catch (TimeExpirationException e) {
					ServletUtils.writeServiceResponse(resp,
							HttpServletResponse.SC_GONE, XmlExceptionConversor
									.toTimeExpirationException(e), null);
				}
			}

			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "invalid reserva id")),
							null);
			return;
		}
		String reservaIdAsString = path.substring(1);
		Long reservaId;
		try {
			reservaId = Long.valueOf(reservaIdAsString);
		} catch (NumberFormatException ex) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "invalid reserva id '"
													+ reservaIdAsString)), null);
			return;
		}
		Reserva reserva;
		try {
			reserva = OfertaServiceFactory.getService().findReserva(reservaId);
		} catch (InstanceNotFoundException ex) {
			ServletUtils
					.writeServiceResponse(resp,
							HttpServletResponse.SC_NOT_FOUND,
							XmlExceptionConversor
									.toInstanceNotFoundException(ex), null);
			return;
			// } catch (TimeExpirationException ex) {
			// ServletUtils.writeServiceResponse(resp,
			// HttpServletResponse.SC_GONE,
			// XmlExceptionConversor.toReservaExpirationException(ex), null);
			//
			// return;
			// TODO mirar esta excepcion si se necesita soltar
		}

		ReservaDto reservaDto = ReservaToReservaDtoConversor
				.toReservaDto(reserva);

		ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_OK,
				XmlReservaDtoConversor.toResponse(reservaDto), null);

	}

}
