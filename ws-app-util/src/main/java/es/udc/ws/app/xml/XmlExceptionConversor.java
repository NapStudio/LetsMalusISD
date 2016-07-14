package es.udc.ws.app.xml;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import es.udc.ws.app.exceptions.BadStateReservaException;
import es.udc.ws.app.exceptions.OfertaReservadaException;
import es.udc.ws.app.exceptions.TimeExpirationException;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class XmlExceptionConversor {

	public final static String CONVERSION_PATTERN = "EEE, d MMM yyyy HH:mm:ss Z";

	public final static Namespace XML_NS = XmlOfertaDtoConversor.XML_NS;

	public static InputValidationException fromInputValidationExceptionXml(
			InputStream ex) throws ParsingException {
		try {

			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(ex);
			Element rootElement = document.getRootElement();

			Element message = rootElement.getChild("message", XML_NS);

			return new InputValidationException(message.getText());
		} catch (JDOMException | IOException e) {
			throw new ParsingException(e);
		} catch (Exception e) {
			throw new ParsingException(e);
		}
	}

	public static InstanceNotFoundException fromInstanceNotFoundExceptionXml(
			InputStream ex) throws ParsingException {
		try {

			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(ex);
			Element rootElement = document.getRootElement();

			Element instanceId = rootElement.getChild("instanceId", XML_NS);
			Element instanceType = rootElement.getChild("instanceType", XML_NS);

			return new InstanceNotFoundException(instanceId.getText(),
					instanceType.getText());
		} catch (JDOMException | IOException e) {
			throw new ParsingException(e);
		} catch (Exception e) {
			throw new ParsingException(e);
		}
	}


	public static BadStateReservaException fromBadStateReservaExceptionXml(
			InputStream ex) throws ParsingException {
		try {

			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(ex);
			Element rootElement = document.getRootElement();

			Element reservaID = rootElement.getChild("reservaId", XML_NS);
			Element estado = rootElement.getChild("estadoReserva", XML_NS);

			return new BadStateReservaException(Long.parseLong(reservaID
					.getTextTrim()), estado.getText());
		} catch (JDOMException | IOException | NumberFormatException e) {
			throw new ParsingException(e);
		} catch (Exception e) {
			throw new ParsingException(e);
		}
	}

	public static OfertaReservadaException fromOfertaReservadaExceptionXml(
			InputStream ex) throws ParsingException {
		try {

			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(ex);
			Element rootElement = document.getRootElement();

			Element ofertaId = rootElement.getChild("ofertaId", XML_NS);

			return new OfertaReservadaException(Long.parseLong(ofertaId
					.getTextTrim()));
		} catch (JDOMException | IOException | NumberFormatException e) {
			throw new ParsingException(e);
		} catch (Exception e) {
			throw new ParsingException(e);
		}
	}
	
	public static TimeExpirationException fromTimeExpirationExceptionXml(
			InputStream ex) throws ParsingException {
		try {

			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(ex);
			Element rootElement = document.getRootElement();

			Element message = rootElement.getChild("message", XML_NS);
			Element instanceId = rootElement.getChild("Id", XML_NS);
			Element expirationDate = rootElement
					.getChild("expirationDate", XML_NS);
			
			System.out.println();

			Calendar calendar = null;
			if (expirationDate != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(CONVERSION_PATTERN,
						Locale.ENGLISH);
				calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(expirationDate.getText()));
			}
			
			System.out.println("toxml timeExpiration "+message.toString()+Long.parseLong(instanceId
					.getTextTrim())+ calendar);

			return new TimeExpirationException(message.getText(), Long.parseLong(instanceId
					.getTextTrim()), calendar);
		} catch (JDOMException | IOException | ParseException
				| NumberFormatException e) {
			throw new ParsingException(e);
		} catch (Exception e) {
			throw new ParsingException(e);
		}
	}

	public static Document toInputValidationExceptionXml(
			InputValidationException ex) throws IOException {

		Element exceptionElement = new Element("InputValidationException",
				XML_NS);

		Element messageElement = new Element("message", XML_NS);
		messageElement.setText(ex.getMessage());
		exceptionElement.addContent(messageElement);

		return new Document(exceptionElement);
	}

	public static Document toInstanceNotFoundException(
			InstanceNotFoundException ex) throws IOException {

		Element exceptionElement = new Element("InstanceNotFoundException",
				XML_NS);

		if (ex.getInstanceId() != null) {
			Element instanceIdElement = new Element("instanceId", XML_NS);
			instanceIdElement.setText(ex.getInstanceId().toString());

			exceptionElement.addContent(instanceIdElement);
		}

		if (ex.getInstanceType() != null) {
			Element instanceTypeElement = new Element("instanceType", XML_NS);
			instanceTypeElement.setText(ex.getInstanceType());

			exceptionElement.addContent(instanceTypeElement);
		}
		return new Document(exceptionElement);
	}

	public static Document toTimeExpirationException(
			TimeExpirationException ex) throws IOException {

		Element exceptionElement = new Element("TimeExpirationException",
				XML_NS);
		
		if (ex.getMessage() != null) {
			Element messageElement = new Element("message", XML_NS);
			System.out.println("message totime: "+ex.getMessage());
			messageElement.setText(ex.getMessage());
			exceptionElement.addContent(messageElement);
		}

		if (ex.getId() != null) {
			Element IdElement = new Element("Id", XML_NS);
			System.out.println("id totime: "+ex.getId());
			IdElement.setText(ex.getId().toString());
			exceptionElement.addContent(IdElement);
		}

		if (ex.getFechaExpiracion() != null) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					CONVERSION_PATTERN, Locale.ENGLISH);

			Element expirationDateElement = new Element("expirationDate",
					XML_NS);

			System.out.println("fecha totime: "+ex.getFechaExpiracion().getTime());
			expirationDateElement.setText(dateFormatter.format(ex
					.getFechaExpiracion().getTime()));

			exceptionElement.addContent(expirationDateElement);
		}

		return new Document(exceptionElement);
	}

	public static Document toBadStateReservaExceptionXml(
			BadStateReservaException ex) throws IOException {

		Element exceptionElement = new Element("BadStateReservaException",
				XML_NS);

		if (ex.getReservaId() != null) {
			Element reservaIdElement = new Element("reservaId", XML_NS);
			reservaIdElement.setText(ex.getReservaId().toString());
			exceptionElement.addContent(reservaIdElement);
		}

		if (ex.getEstadoReserva() != null) {

			Element reservaIdElement = new Element("estadoReserva", XML_NS);
			reservaIdElement.setText(ex.getEstadoReserva().toString());
			exceptionElement.addContent(reservaIdElement);
		}

		return new Document(exceptionElement);
	}

	public static Document toOfertaReservadaExceptionXml(
			OfertaReservadaException ex) throws IOException {

		Element exceptionElement = new Element("OfertaReservadaException", XML_NS);
		System.out.println(ex.getOfertaId());

		if (ex.getOfertaId() != null) {
			Element reservaIdElement = new Element("ofertaId", XML_NS);
			reservaIdElement.setText(ex.getOfertaId().toString());
			exceptionElement.addContent(reservaIdElement);
		}
		return new Document(exceptionElement);
	}
}
