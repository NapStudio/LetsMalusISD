package es.udc.ws.app.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import es.udc.ws.app.dto.OfertaDto;
import es.udc.ws.app.dto.ReservaDto;

public class XmlReservaDtoConversor {

	public final static Namespace XML_NS = Namespace
			.getNamespace("http://ws.udc.es/reservas/xml");
	
    public static Document toResponse(ReservaDto reserva)
            throws IOException {

        Element reservaElement = toJDOMElement(reserva);

        return new Document(reservaElement);
    }



	public static Document toXml(List<ReservaDto> reserva) throws IOException {

		Element reservasElement = new Element("reservas", XML_NS);
		for (int i = 0; i < reserva.size(); i++) {
			ReservaDto xmlReservaDto = reserva.get(i);
			Element reservaElement = toJDOMElement(xmlReservaDto);
			reservasElement.addContent(reservaElement);
		}

		return new Document(reservasElement);
	}

	public static ReservaDto toReserva(InputStream reservaXml)
			throws ParsingException {
		try {

			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(reservaXml);
			Element rootElement = document.getRootElement();

			return toReserva(rootElement);
		} catch (ParsingException ex) {
			throw ex;
		} catch (Exception e) {
			throw new ParsingException(e);
		}
	}

	public static List<ReservaDto> toReservas(InputStream reservaXml)
			throws ParsingException {
		try {

			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(reservaXml);
			Element rootElement = document.getRootElement();

			if (!"reservas".equalsIgnoreCase(rootElement.getName())) {
				throw new ParsingException("Unrecognized element '"
						+ rootElement.getName() + "' ('reservas' expected)");
			}
			@SuppressWarnings("unchecked")
			List<Element> children = rootElement.getChildren();
			List<ReservaDto> reservaDtos = new ArrayList<>(children.size());
			for (int i = 0; i < children.size(); i++) {
				Element element = children.get(i);
				reservaDtos.add(toReserva(element));
			}

			return reservaDtos;
		} catch (ParsingException ex) {
			throw ex;
		} catch (Exception e) {
			throw new ParsingException(e);
		}
	}

	public static Element toJDOMElement(ReservaDto reserva) {

		Element reservaElement = new Element("reserva", XML_NS);

		if (reserva.getReservaId() != null) {
			Element identifierElement = new Element("reservaId", XML_NS);
			identifierElement.setText(reserva.getReservaId().toString());
			reservaElement.addContent(identifierElement);
		}

		Element ofertaElement = new Element("ofertaId", XML_NS);
		ofertaElement.setText((reserva.getOfertaId().toString()));
		reservaElement.addContent(ofertaElement);

		Element userElement = new Element("emailUsuarioReserva", XML_NS);
		userElement.setText((reserva.getEmailUsuarioReserva()));
		reservaElement.addContent(userElement);

		Element cardElement = new Element("tarjetaCreditoReserva", XML_NS);
		cardElement.setText((reserva.getTarjetaCreditoReserva()));
		reservaElement.addContent(cardElement);

		Element stateElement = new Element("estadoReserva", XML_NS);
		stateElement.setText((reserva.getEstadoReserva()));
		reservaElement.addContent(stateElement);

		return reservaElement;
	}

	private static ReservaDto toReserva(Element reservaElement)
			throws ParsingException, DataConversionException {
		if (!"reserva".equals(reservaElement.getName())) {
			throw new ParsingException("Unrecognized element '"
					+ reservaElement.getName() + "' ('reserva' expected)");
		}
		Element identifierElement = reservaElement.getChild("reservaId", XML_NS);
		Long identifier = null;

		if (identifierElement != null) {
			identifier = Long.valueOf(identifierElement.getTextTrim());
		}

		Long oferta = Long.valueOf(reservaElement.getChild("ofertaId",
				XML_NS).getTextTrim());

		String user = reservaElement.getChildTextNormalize(
				"emailUsuarioReserva", XML_NS);

		String card = reservaElement.getChildTextNormalize(
				"tarjetaCreditoReserva", XML_NS);

		String state = reservaElement.getChildTextNormalize("estadoReserva",
				XML_NS);

		return new ReservaDto(identifier, oferta, user, card, state);
	}

}
