package es.udc.ws.app.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import es.udc.ws.app.dto.OfertaDto;

public class XmlOfertaDtoConversor {

	public final static Namespace XML_NS = Namespace
			.getNamespace("http://ws.udc.es/ofertas/xml");

	public static Document toXml(OfertaDto oferta) throws IOException {

		Element ofertaElement = toJDOMElement(oferta);

		return new Document(ofertaElement);
	}

	public static Document toXml(List<OfertaDto> oferta) throws IOException {

		Element ofertasElement = new Element("ofertas", XML_NS);
		for (int i = 0; i < oferta.size(); i++) {
			OfertaDto xmlOfertaDto = oferta.get(i);
			Element ofertaElement = toJDOMElement(xmlOfertaDto);
			ofertasElement.addContent(ofertaElement);
		}

		return new Document(ofertasElement);
	}

	public static OfertaDto toOferta(InputStream ofertaXml)
			throws ParsingException {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(ofertaXml);
			Element rootElement = document.getRootElement();

			return toOferta(rootElement);
		} catch (ParsingException ex) {
			throw ex;
		} catch (Exception e) {
			throw new ParsingException(e);
		}
	}

	public static List<OfertaDto> toOfertas(InputStream ofertaXml)
			throws ParsingException {
		try {

			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(ofertaXml);
			Element rootElement = document.getRootElement();

			if (!"ofertas".equalsIgnoreCase(rootElement.getName())) {
				throw new ParsingException("Unrecognized element '"
						+ rootElement.getName() + "' ('ofertas' expected)");
			}
			@SuppressWarnings("unchecked")
			List<Element> children = rootElement.getChildren();
			List<OfertaDto> ofertaDtos = new ArrayList<>(children.size());
			for (int i = 0; i < children.size(); i++) {
				Element element = children.get(i);
				ofertaDtos.add(toOferta(element));
			}

			return ofertaDtos;
		} catch (ParsingException ex) {
			throw ex;
		} catch (Exception e) {
			throw new ParsingException(e);
		}
	}

	public static Element toJDOMElement(OfertaDto oferta) {

		Element ofertaElement = new Element("oferta", XML_NS);

		if (oferta.getOfertaId() != null) {
			Element identifierElement = new Element("ofertaId", XML_NS);
			identifierElement.setText(oferta.getOfertaId().toString());
			ofertaElement.addContent(identifierElement);
		}

		Element nombreElement = new Element("nombreOferta", XML_NS);
		nombreElement.setText((oferta.getNombreOferta()));
		ofertaElement.addContent(nombreElement);

		Element descripcionElement = new Element("descripcionOferta", XML_NS);
		descripcionElement.setText((oferta.getDescripcionOferta()));
		ofertaElement.addContent(descripcionElement);

		System.out.println("xmlOferta toJDOM fbli: "
				+ oferta.getFacebookLikes());
		Element facebookLikes = new Element("facebookLikes", XML_NS);
		facebookLikes.setText(String.valueOf(oferta.getFacebookLikes()));
		ofertaElement.addContent(facebookLikes);

		Element estadoElement = new Element("estadoOferta", XML_NS);
		estadoElement.setText((oferta.getEstadoOferta()));
		ofertaElement.addContent(estadoElement);

		Element precioRealElement = new Element("precioRealOferta", XML_NS);
		precioRealElement
				.setText(Double.toString(oferta.getPrecioRealOferta()));
		ofertaElement.addContent(precioRealElement);

		Element precioDescontadoElement = new Element("precioDescontadoOferta",
				XML_NS);
		precioDescontadoElement.setText(Double.toString(oferta
				.getPrecioDescontadoOferta()));
		ofertaElement.addContent(precioDescontadoElement);

		if (oferta.getFechaLimiteOferta() != null) {
			System.out.println("converting jdom fechaOferta");
			Element fechaOfertaElement = dataToJDOM(
					oferta.getFechaLimiteOferta(), "fechaLimiteOferta");
			ofertaElement.addContent(fechaOfertaElement);
		}

		if (oferta.getFechaLimiteReserva() != null) {
			System.out.println("converting jdom fechaOferta");
			Element fechaReservaElement = dataToJDOM(
					oferta.getFechaLimiteReserva(), "fechaLimiteReserva");
			ofertaElement.addContent(fechaReservaElement);
		}

		return ofertaElement;
	}

	private static OfertaDto toOferta(Element ofertaElement)
			throws ParsingException, DataConversionException {
		if (!"oferta".equals(ofertaElement.getName())) {
			throw new ParsingException("Unrecognized element '"
					+ ofertaElement.getName() + "' ('oferta' expected)");
		}
		Element identifierElement = ofertaElement.getChild("ofertaId", XML_NS);
		Long identifier = null;

		if (identifierElement != null) {
			identifier = Long.valueOf(identifierElement.getTextTrim());
		}

		String name = ofertaElement.getChildTextNormalize("nombreOferta",
				XML_NS);

		String description = ofertaElement.getChildTextNormalize(
				"descripcionOferta", XML_NS);

		System.out.println("facebookLikes dto: "
				+ ofertaElement.getChildTextTrim("facebookLikes", XML_NS));

		Element facebookLikesElement = ofertaElement.getChild("facebookLikes",
				XML_NS);
		int facebookLikes = 0;

		if (facebookLikesElement != null) {
			facebookLikes = Integer.valueOf(facebookLikesElement.getTextTrim());
			System.out.println("xml toOferta fblik: " + facebookLikes);
		}

		String state = ofertaElement.getChildTextNormalize("estadoOferta",
				XML_NS);

		float realPrice = Float.valueOf(ofertaElement.getChildTextTrim(
				"precioRealOferta", XML_NS));

		float discountPrice = Float.valueOf(ofertaElement.getChildTextTrim(
				"precioDescontadoOferta", XML_NS));

		Calendar limitOfertaDate = null;
		if (getExpirationOfertaDate(ofertaElement.getChild("fechaLimiteOferta",
				XML_NS)) == null) {
			limitOfertaDate = null;
		} else {
			limitOfertaDate = getExpirationOfertaDate(ofertaElement.getChild(
					"fechaLimiteOferta", XML_NS));

		}
		Calendar limitReservaDate = null;
		if (getExpirationReservaDate(ofertaElement.getChild(
				"fechaLimiteReserva", XML_NS)) == null) {
			limitReservaDate = null;
		} else {
			limitReservaDate = getExpirationReservaDate(ofertaElement.getChild(
					"fechaLimiteReserva", XML_NS));

		}

		return new OfertaDto(identifier, name, description, state, realPrice,
				discountPrice, limitOfertaDate, limitReservaDate, facebookLikes);
	}

	private static Calendar getExpirationReservaDate(
			Element expirationReservaDateElement)
			throws DataConversionException {
		if (expirationReservaDateElement == null) {
			return null;
		}
		System.out.println(expirationReservaDateElement);
		int day = expirationReservaDateElement.getAttribute("day")
				.getIntValue();
		int month = expirationReservaDateElement.getAttribute("month")
				.getIntValue();
		int year = expirationReservaDateElement.getAttribute("year")
				.getIntValue();
		int hour = expirationReservaDateElement.getAttribute("hour")
				.getIntValue();
		int minute = expirationReservaDateElement.getAttribute("minute")
				.getIntValue();
		int sec = expirationReservaDateElement.getAttribute("second")
				.getIntValue();

		Calendar releaseDate = Calendar.getInstance();

		releaseDate.set(Calendar.DAY_OF_MONTH, day);
		releaseDate.set(Calendar.MONTH, Calendar.JANUARY + month - 1);
		releaseDate.set(Calendar.YEAR, year);
		releaseDate.set(Calendar.HOUR_OF_DAY, hour);
		releaseDate.set(Calendar.MINUTE, minute);
		releaseDate.set(Calendar.SECOND, sec);

		return releaseDate;
	}

	private static Calendar getExpirationOfertaDate(
			Element expirationOfertaDateElement) throws DataConversionException {
		if (expirationOfertaDateElement == null) {
			return null;
		}
		System.out.println(expirationOfertaDateElement);
		int day = expirationOfertaDateElement.getAttribute("day").getIntValue();
		int month = expirationOfertaDateElement.getAttribute("month")
				.getIntValue();
		int year = expirationOfertaDateElement.getAttribute("year")
				.getIntValue();
		int hour = expirationOfertaDateElement.getAttribute("hour")
				.getIntValue();
		int minute = expirationOfertaDateElement.getAttribute("minute")
				.getIntValue();
		int sec = expirationOfertaDateElement.getAttribute("second")
				.getIntValue();

		Calendar releaseDate = Calendar.getInstance();

		releaseDate.set(Calendar.DAY_OF_MONTH, day);
		releaseDate.set(Calendar.MONTH, Calendar.JANUARY + month - 1);
		releaseDate.set(Calendar.YEAR, year);
		releaseDate.set(Calendar.HOUR_OF_DAY, hour);
		releaseDate.set(Calendar.MINUTE, minute);
		releaseDate.set(Calendar.SECOND, sec);

		return releaseDate;
	}

	private static Element dataToJDOM(Calendar expirationDate,
			String nombreElemento) {

		Element releaseDateElement = new Element(nombreElemento, XML_NS);
		int day = expirationDate.get(Calendar.DAY_OF_MONTH);
		int month = expirationDate.get(Calendar.MONTH) - Calendar.JANUARY + 1;
		int year = expirationDate.get(Calendar.YEAR);
		int hour = expirationDate.get(Calendar.HOUR_OF_DAY);
		int min = expirationDate.get(Calendar.MINUTE);
		int sec = expirationDate.get(Calendar.SECOND);

		releaseDateElement.setAttribute("day", Integer.toString(day));
		releaseDateElement.setAttribute("month", Integer.toString(month));
		releaseDateElement.setAttribute("year", Integer.toString(year));
		releaseDateElement.setAttribute("hour", Integer.toString(hour));
		releaseDateElement.setAttribute("minute", Integer.toString(min));
		releaseDateElement.setAttribute("second", Integer.toString(sec));

		return releaseDateElement;

	}

}
