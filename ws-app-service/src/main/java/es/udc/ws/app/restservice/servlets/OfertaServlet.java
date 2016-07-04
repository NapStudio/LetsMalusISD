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
import es.udc.ws.app.exceptions.OfertaReservadaException;
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
		OfertaDto xmlOferta;
		try {
			xmlOferta = XmlOfertaDtoConversor.toOferta(req.getInputStream());
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
		OfertaDto ofertaDto = OfertaToOfertaDtoConversor.toOfertaDto(oferta);

		String movieURL = ServletUtils.normalizePath(req.getRequestURL()
				.toString()) + "/" + oferta.getOfertaId();
		Map<String, String> headers = new HashMap<>(1);
		headers.put("Location", movieURL);

		ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_CREATED,
				XmlOfertaDtoConversor.toXml(ofertaDto), headers);
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
													+ "invalid oferta id")),
							null);
			return;
		}
		String movieIdAsString = path.substring(1);
		Long movieId;
		try {
			movieId = Long.valueOf(movieIdAsString);
		} catch (NumberFormatException ex) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "invalid oferta id '"
													+ movieIdAsString + "'")),
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
		if (!movieId.equals(ofertaDto.getOfertaId())) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "invalid movieId")), null);
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
		ServletUtils.writeServiceResponse(resp,
				HttpServletResponse.SC_NO_CONTENT, null, null);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
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
													+ "invalid oferta id")),
							null);
			return;
		}
		String movieIdAsString = path.substring(1);
		Long ofertaId;
		try {
			ofertaId = Long.valueOf(movieIdAsString);
		} catch (NumberFormatException ex) {
			ServletUtils
					.writeServiceResponse(
							resp,
							HttpServletResponse.SC_BAD_REQUEST,
							XmlExceptionConversor
									.toInputValidationExceptionXml(new InputValidationException(
											"Invalid Request: "
													+ "invalid oferta id '"
													+ movieIdAsString + "'")),
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletUtils.writeServiceResponse(resp,
				HttpServletResponse.SC_NO_CONTENT, null, null);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = ServletUtils.normalizePath(req.getPathInfo());
		if (path == null || path.length() == 0) {
			String keyWords = req.getParameter("keywords");
			List<Oferta> movies = OfertaServiceFactory.getService().findOfertas(
					keyWords, null, null);
			List<OfertaDto> movieDtos = OfertaToOfertaDtoConversor
					.toOfertaDtos(movies);
			ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_OK,
					XmlOfertaDtoConversor.toXml(movieDtos), null);
		} else {
			String movieIdAsString = path.substring(1);
			Long movieId;
			try {
				movieId = Long.valueOf(movieIdAsString);
			} catch (NumberFormatException ex) {
				ServletUtils
						.writeServiceResponse(
								resp,
								HttpServletResponse.SC_BAD_REQUEST,
								XmlExceptionConversor
										.toInputValidationExceptionXml(new InputValidationException(
												"Invalid Request: "
														+ "invalid oferta id'"
														+ movieIdAsString + "'")),
								null);

				return;
			}
			Oferta oferta;
			try {
				oferta = OfertaServiceFactory.getService().findOferta(movieId);
			} catch (InstanceNotFoundException ex) {
				ServletUtils.writeServiceResponse(resp,
						HttpServletResponse.SC_NOT_FOUND,
						XmlExceptionConversor.toInstanceNotFoundException(ex),
						null);
				return;
			}
			OfertaDto ofertaDto = OfertaToOfertaDtoConversor.toOfertaDto(oferta);
			ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_OK,
					XmlOfertaDtoConversor.toXml(ofertaDto), null);
		}
	}

}
