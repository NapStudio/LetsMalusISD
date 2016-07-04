package es.udc.ws.app.restservice.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.udc.ws.app.dto.ReservaDto;
import es.udc.ws.app.exceptions.ReservaExpirationException;
import es.udc.ws.app.model.ofertaservice.OfertaServiceFactory;
import es.udc.ws.app.model.reserva.Reserva;
import es.udc.ws.app.serviceutil.ReservaToReservaDtoConversor;
import es.udc.ws.app.xml.XmlExceptionConversor;
import es.udc.ws.app.xml.XmlReservaDtoConversor;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;
import es.udc.ws.util.servlet.ServletUtils;


@SuppressWarnings("serial")
public class ReservaServlet extends HttpServlet {
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String movieIdParameter = req.getParameter("movieId");
        if (movieIdParameter == null) {
            ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_BAD_REQUEST,
                    XmlExceptionConversor.toInputValidationExceptionXml(
                    new InputValidationException("Invalid Request: " +
                        "parameter 'movieId' is mandatory")), null);
            return;
        }
        Long movieId;
        try {
            movieId = Long.valueOf(movieIdParameter);
        } catch (NumberFormatException ex) {
            ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_BAD_REQUEST,
                    XmlExceptionConversor.toInputValidationExceptionXml(
                    new InputValidationException("Invalid Request: " +
                        "parameter 'movieId' is invalid '" +
                        movieIdParameter + "'")),
                    null);

            return;
        }
        String userId = req.getParameter("userId");
        if (userId == null) {
            ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_BAD_REQUEST,
                    XmlExceptionConversor.toInputValidationExceptionXml(
                    new InputValidationException("Invalid Request: " +
                        "parameter 'userId' is mandatory")), null);
            return;
        }
        String creditCardNumber = req.getParameter("creditCardNumber");
        if (creditCardNumber == null) {
            ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_BAD_REQUEST,
                    XmlExceptionConversor.toInputValidationExceptionXml(
                    new InputValidationException("Invalid Request: "+
                        "parameter 'creditCardNumber' is mandatory")), null);

            return;
        }
        Long reserva;
        try {
            reserva = OfertaServiceFactory.getService()
                    .reservarOferta(movieId, userId, creditCardNumber);
        } catch (InstanceNotFoundException ex) {
            ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_NOT_FOUND,
                    XmlExceptionConversor.toInstanceNotFoundException(ex), null);
            return;
        } catch (InputValidationException ex) {
            ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_BAD_REQUEST,
                    XmlExceptionConversor.toInputValidationExceptionXml(ex), null);
            return;
        }
        ReservaDto reservaDto;
		try {
			reservaDto = ReservaToReservaDtoConversor.toReservaDto(OfertaServiceFactory.getService()
			        .findReserva(reserva));
		} catch (InstanceNotFoundException e) {
			ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_NOT_FOUND,
                    XmlExceptionConversor.toInstanceNotFoundException(e), null);
            return;
		}

        String reservaURL = ServletUtils.normalizePath(req.getRequestURL().toString()) + "/" +
                reserva;

        Map<String, String> headers = new HashMap<>(1);
        headers.put("Location", reservaURL);
        ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_CREATED,
                XmlReservaDtoConversor.toResponse(reservaDto), headers);
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
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
													+ "invalid sale id")), null);
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
//		} catch (ReservaExpirationException ex) {
//			ServletUtils.writeServiceResponse(resp,
//					HttpServletResponse.SC_GONE,
//					XmlExceptionConversor.toReservaExpirationException(ex), null);
//
//			return;
			//TODO mirar esta excepcion si se necesita soltar
		}

		ReservaDto reservaDto = ReservaToReservaDtoConversor.toReservaDto(reserva);

		ServletUtils.writeServiceResponse(resp, HttpServletResponse.SC_OK,
				XmlReservaDtoConversor.toResponse(reservaDto), null);

	}

}
