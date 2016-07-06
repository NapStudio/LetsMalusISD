package es.udc.ws.app.restservice.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;

import es.udc.ws.app.dto.OfertaDto;
import es.udc.ws.app.exceptions.OfertaReservadaException;
import es.udc.ws.app.model.facebook.FacebookService;
import es.udc.ws.app.model.oferta.Oferta;
import es.udc.ws.app.model.ofertaservice.OfertaServiceFactory;
import es.udc.ws.app.serviceutil.OfertaToOfertaDtoConversor;
import es.udc.ws.app.xml.ParsingException;
import es.udc.ws.app.xml.XmlExceptionConversor;
import es.udc.ws.app.xml.XmlOfertaDtoConversor;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;
import es.udc.ws.util.servlet.ServletUtils;

@SuppressWarnings("serial")
public class OfertaServlet extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doPost");
		OfertaDto xmlOferta;
		try {
			xmlOferta = XmlOfertaDtoConversor.toOferta(req.getInputStream());
		} catch (ParsingException ex) {
			System.out.println("servlet erro bad request");
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											ex.getMessage())), null);

			return;

		}
		Oferta oferta = OfertaToOfertaDtoConversor.toOferta(xmlOferta);
		try {
			oferta = OfertaServiceFactory.getService().addOferta(oferta);
		} catch (InputValidationException ex) {
			ServletUtils.writeServiceResponse(resp,
					HttpServletResponse.SC_BAD_REQUEST,
					XmlExceptionConversor.toInputValidationExceptionXml(ex),
					null);
			return;
		}
		OfertaDto ofertaDto = OfertaToOfertaDtoConversor.toOfertaDto(oferta, 0);

		String ofertaURL = ServletUtils.normalizePath(req.getRequestURL()
				.toString()) + "/" + oferta.getOfertaId();
		Map<String, String> headers = new HashMap<>(1);
		headers.put("Location", ofertaURL);

		ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_CREATED,
				XmlOfertaDtoConversor.toXml(ofertaDto), headers);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doPut");
		String path = ServletUtils.normalizePath(req.getPathInfo());
		if (path == null || path.length() == 0) {

			String ofertaIdAsString = req.getParameter("ofertaId");
			System.out.println(ofertaIdAsString);
			Long ofertaId;
			try {
				ofertaId = Long.valueOf(ofertaIdAsString);
			} catch (NumberFormatException ex) {
				ServletUtils
						.writeServiceResponse(
								resp,
								HttpServletResponse.SC_BAD_REQUEST,
								XmlExceptionConversor
										.toInputValidationExceptionXml(new InputValidationException(
												"Invalid Request: "
														+ "invalid oferta id '"
														+ ofertaIdAsString
														+ "'")), null);
				return;
			}
			try {
				OfertaServiceFactory.getService().invalidarOferta(ofertaId);

			} catch (InstanceNotFoundException e) {
				ServletUtils.writeServiceResponse(resp,
						HttpServletResponse.SC_NOT_FOUND,
						XmlExceptionConversor.toInstanceNotFoundException(e),
						null);
				return;
			} catch (InputValidationException e) {
				ServletUtils.writeServiceResponse(resp,
						HttpServletResponse.SC_BAD_REQUEST,
						XmlExceptionConversor.toInputValidationExceptionXml(e),
						null);
				return;
			}

			System.out.println(req.getInputStream().available());
			ServletUtils.writeServiceResponse(resp,
					HttpServletResponse.SC_NO_CONTENT, null, null);
		}
		String ofertaIdAsString = path.substring(1);
		Long ofertaId;
		try {
			ofertaId = Long.valueOf(ofertaIdAsString);
		} catch (NumberFormatException ex) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "invalid oferta id '"
													+ ofertaIdAsString + "'")),
							null);
			return;
		}

		OfertaDto ofertaDto;
		try {
			ofertaDto = XmlOfertaDtoConversor.toOferta(req.getInputStream());
		} catch (ParsingException ex) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											ex.getMessage())), null);
			return;

		}
		if (!ofertaId.equals(ofertaDto.getOfertaId())) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "invalid ofertaId")),
							null);
			return;
		}
		Oferta oferta = OfertaToOfertaDtoConversor.toOferta(ofertaDto);
		try {
			OfertaServiceFactory.getService().updateOferta(oferta);
		} catch (InputValidationException ex) {
			ServletUtils.writeServiceResponse(resp,
					HttpServletResponse.SC_BAD_REQUEST,
					XmlExceptionConversor.toInputValidationExceptionXml(ex),
					null);
			return;
		} catch (InstanceNotFoundException ex) {
			ServletUtils
					.writeServiceResponse(resp,
							HttpServletResponse.SC_NOT_FOUND,
							XmlExceptionConversor
									.toInstanceNotFoundException(ex), null);
			return;
		}
		System.out.println(req.getInputStream().available());
		ServletUtils.writeServiceResponse(resp,
				HttpServletResponse.SC_NO_CONTENT, null, null);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doDelete");
		String path = ServletUtils.normalizePath(req.getPathInfo());
		if (path == null || path.length() == 0) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "invalid oferta id")),
							null);
			return;
		}
		String ofertaIdAsString = path.substring(1);
		Long ofertaId;
		try {
			ofertaId = Long.valueOf(ofertaIdAsString);
		} catch (NumberFormatException ex) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "invalid oferta id '"
													+ ofertaIdAsString + "'")),
							null);

			return;
		}
		try {
			OfertaServiceFactory.getService().removeOferta(ofertaId);
		} catch (InstanceNotFoundException ex) {
			ServletUtils
					.writeServiceResponse(resp,
							HttpServletResponse.SC_NOT_FOUND,
							XmlExceptionConversor
									.toInstanceNotFoundException(ex), null);
			return;
		} catch (OfertaReservadaException e) {
			ServletUtils.writeServiceResponse(resp,
					HttpServletResponse.SC_FORBIDDEN,
					XmlExceptionConversor.toOfertaReservadaExceptionXml(e),
					null);
			return;
		}
		ServletUtils.writeServiceResponse(resp,
				HttpServletResponse.SC_NO_CONTENT, null, null);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doGet");
		String path = ServletUtils.normalizePath(req.getPathInfo());
		if (path == null || path.length() == 0) {
			String keywords = req.getParameter("keywords");
			List<Oferta> ofertas = OfertaServiceFactory.getService()
					.findOfertas(keywords, null, Calendar.getInstance());
			List<OfertaDto> ofertaDtos = OfertaToOfertaDtoConversor
					.toOfertaDtos(ofertas, OfertaServiceFactory.getService().getLikesList(ofertas));
			ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_OK,
					XmlOfertaDtoConversor.toXml(ofertaDtos), null);
		} else {
			String ofertaIdAsString = path.substring(1);
			Long ofertaId;
			try {
				ofertaId = Long.valueOf(ofertaIdAsString);
			} catch (NumberFormatException ex) {
				ServletUtils
						.writeServiceResponse(
								resp,
								HttpServletResponse.SC_BAD_REQUEST,
								XmlExceptionConversor
										.toInputValidationExceptionXml(new InputValidationException(
												"Invalid Request: "
														+ "invalid oferta id'"
														+ ofertaIdAsString
														+ "'")), null);

				return;
			}
			Oferta oferta;
			try {
				oferta = OfertaServiceFactory.getService().findOferta(ofertaId);
			} catch (InstanceNotFoundException ex) {
				ServletUtils.writeServiceResponse(resp,
						HttpServletResponse.SC_NOT_FOUND,
						XmlExceptionConversor.toInstanceNotFoundException(ex),
						null);
				return;
			}
			OfertaDto ofertaDto = OfertaToOfertaDtoConversor.toOfertaDto(
					oferta, OfertaServiceFactory.getService().getLikes(oferta.getFacebookId()));
			ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_OK,
					XmlOfertaDtoConversor.toXml(ofertaDto), null);
		}
	}

}
