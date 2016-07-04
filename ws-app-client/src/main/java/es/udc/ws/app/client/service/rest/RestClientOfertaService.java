package es.udc.ws.app.client.service.rest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import es.udc.ws.app.client.service.ClientOfertaService;
import es.udc.ws.app.client.service.soap.wsdl.SoapOfertaReservadaException;
import es.udc.ws.app.dto.OfertaDto;
import es.udc.ws.app.dto.ReservaDto;
import es.udc.ws.app.exceptions.BadStateReservaException;
import es.udc.ws.app.exceptions.OfertaReservadaException;
import es.udc.ws.app.exceptions.ReservaExpirationException;
import es.udc.ws.app.xml.ParsingException;
import es.udc.ws.app.xml.XmlExceptionConversor;
import es.udc.ws.app.xml.XmlOfertaDtoConversor;
import es.udc.ws.app.xml.XmlReservaDtoConversor;
import es.udc.ws.util.configuration.ConfigurationParametersManager;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class RestClientOfertaService implements ClientOfertaService {

	private final static String ENDPOINT_ADDRESS_PARAMETER = "RestClientMovieService.endpointAddress";
	private String endpointAddress;

	@Override
	public Long addOferta(OfertaDto oferta) throws InputValidationException {

		try {

			HttpResponse response = Request
					.Post(getEndpointAddress() + "ofertas")
					.bodyStream(toInputStream(oferta),
							ContentType.create("application/xml")).execute()
					.returnResponse();

			validateStatusCode(HttpStatus.SC_CREATED, response);

			return XmlOfertaDtoConversor.toOferta(
					response.getEntity().getContent()).getOfertaId();

		} catch (InputValidationException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateOferta(OfertaDto oferta) throws InputValidationException,
			InstanceNotFoundException {
		try {

			HttpResponse response = Request
					.Put(getEndpointAddress() + "ofertas/"
							+ +oferta.getOfertaId())
					.bodyStream(toInputStream(oferta),
							ContentType.create("application/xml")).execute()
					.returnResponse();

			validateStatusCode(HttpStatus.SC_NO_CONTENT, response);

		} catch (InputValidationException | InstanceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void removeOferta(Long ofertaId) throws InstanceNotFoundException,
			OfertaReservadaException, SoapOfertaReservadaException {
		try {

			HttpResponse response = Request
					.Delete(getEndpointAddress() + "ofertas/" + ofertaId)
					.execute().returnResponse();

			validateStatusCode(HttpStatus.SC_NO_CONTENT, response);

		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public OfertaDto findOferta(Long ofertaId) throws InstanceNotFoundException {
		try {

			HttpResponse response = Request
					.Get(getEndpointAddress() + "movies?OfertaId="
							+ URLEncoder.encode(String.valueOf(ofertaId), "UTF-8")).execute()
					.returnResponse();

			validateStatusCode(HttpStatus.SC_OK, response);

			return XmlOfertaDtoConversor.toOferta(response.getEntity()
					.getContent());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void invalidarOferta(Long ofertaId) throws InstanceNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OfertaDto> findOfertas(String keywords, String estadoBusqueda,
			Calendar fechaBusqueda) throws DatatypeConfigurationException {

		try {

			HttpResponse response = Request
					.Get(getEndpointAddress()
							+ "ofertas?keywords="
							+ URLEncoder.encode(keywords, "UTF-8")
							+ "?estado="
							+ URLEncoder.encode(estadoBusqueda, "UTF-8")
							+ "?fecha="
							+ URLEncoder.encode(fechaBusqueda.toString(),
									"UTF-8")).execute().returnResponse();

			validateStatusCode(HttpStatus.SC_OK, response);

			return XmlOfertaDtoConversor.toOfertas(response.getEntity()
					.getContent());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Long reservarOferta(Long ofertaId, String emailUsuarioReserva,
			String tarjetaCreditoReserva) throws InstanceNotFoundException,
			InputValidationException {
		try {

			HttpResponse response = Request
					.Post(getEndpointAddress() + "reservas")
					.bodyForm(
							Form.form()
									.add("ofertaId", Long.toString(ofertaId))
									.add("emailUsuarioReserva",
											emailUsuarioReserva)
									.add("tarjetaCreditoReserva",
											tarjetaCreditoReserva).build())
					.execute().returnResponse();

			validateStatusCode(HttpStatus.SC_CREATED, response);

			return XmlReservaDtoConversor.toReserva(
					response.getEntity().getContent()).getReservaId();

		} catch (InputValidationException | InstanceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public ReservaDto findReserva(Long reservaId)
			throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservaDto> findReservasByOferta(Long ofertaId)
			throws InstanceNotFoundException, ReservaExpirationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservaDto> findReservasByUsuario(String emailUsuarioReserva, String estado)
			throws InstanceNotFoundException, ReservaExpirationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long reclamarOferta(Long reservaId)
			throws InstanceNotFoundException, BadStateReservaException,
			ReservaExpirationException {
		try {
			// TODO pues algo con badstatereserva que no es lanzada o algo
			HttpResponse response = Request
					.Get(getEndpointAddress() + "reservas/" + reservaId)
					.execute().returnResponse();

			validateStatusCode(HttpStatus.SC_OK, response);

			return XmlReservaDtoConversor.toReserva(
					response.getEntity().getContent()).getReservaId();

		} catch (InstanceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private synchronized String getEndpointAddress() {
		if (endpointAddress == null) {
			endpointAddress = ConfigurationParametersManager
					.getParameter(ENDPOINT_ADDRESS_PARAMETER);
		}
		return endpointAddress;
	}

	private InputStream toInputStream(OfertaDto oferta) {

		try {

			ByteArrayOutputStream xmlOutputStream = new ByteArrayOutputStream();
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

			outputter.output(XmlOfertaDtoConversor.toXml(oferta),
					xmlOutputStream);

			return new ByteArrayInputStream(xmlOutputStream.toByteArray());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private void validateStatusCode(int successCode, HttpResponse response)
			throws InstanceNotFoundException, ReservaExpirationException,
			InputValidationException, ParsingException {

		try {

			int statusCode = response.getStatusLine().getStatusCode();

			/* Success? */
			if (statusCode == successCode) {
				return;
			}

			/* Handler error. */
			switch (statusCode) {

			case HttpStatus.SC_NOT_FOUND:
				throw XmlExceptionConversor
						.fromInstanceNotFoundExceptionXml(response.getEntity()
								.getContent());

			case HttpStatus.SC_BAD_REQUEST:
				throw XmlExceptionConversor
						.fromInputValidationExceptionXml(response.getEntity()
								.getContent());

			case HttpStatus.SC_GONE:
				throw XmlExceptionConversor
						.fromReservaExpirationExceptionXml(response.getEntity()
								.getContent());

			default:
				throw new RuntimeException("HTTP error; status code = "
						+ statusCode);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
