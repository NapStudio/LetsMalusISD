package es.udc.ws.app.client.service.soap;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import es.udc.ws.app.dto.OfertaDto;

public class OfertaDtoToSoapOfertaDtoConversor {
	public static es.udc.ws.app.client.service.soap.wsdl.OfertaDto toSoapOfertaDto(
			OfertaDto oferta) throws DatatypeConfigurationException {
		es.udc.ws.app.client.service.soap.wsdl.OfertaDto soapOfertaDto = new es.udc.ws.app.client.service.soap.wsdl.OfertaDto();
		soapOfertaDto.setDescripcionOferta(oferta.getDescripcionOferta());
		soapOfertaDto.setEstadoOferta(oferta.getEstadoOferta());
		Date cDateini = oferta.getFechaLimiteOferta().getTime();
		GregorianCalendar cini = new GregorianCalendar();
		cini.setTime(cDateini);
		XMLGregorianCalendar date2 = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(cini);
		soapOfertaDto.setFechaLimiteOferta(date2);
		Date cDatefin = oferta.getFechaLimiteReserva().getTime();
		GregorianCalendar cfin = new GregorianCalendar();
		cfin.setTime(cDatefin);
		XMLGregorianCalendar date3 = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(cfin);
		soapOfertaDto.setFechaLimiteReserva(date3);
		soapOfertaDto.setNombreOferta(oferta.getNombreOferta());
		soapOfertaDto.setOfertaId(oferta.getOfertaId());
		soapOfertaDto.setPrecioDescontadoOferta(oferta
				.getPrecioDescontadoOferta());
		soapOfertaDto.setPrecioRealOferta(oferta.getPrecioRealOferta());
		return soapOfertaDto;
	}

	public static OfertaDto toOfertaDto(
			es.udc.ws.app.client.service.soap.wsdl.OfertaDto oferta)
			throws DatatypeConfigurationException {
		return new OfertaDto(oferta.getOfertaId(), oferta.getNombreOferta(),
				oferta.getDescripcionOferta(), oferta.getEstadoOferta(),
				oferta.getPrecioRealOferta(),
				oferta.getPrecioDescontadoOferta(), oferta
						.getFechaLimiteOferta().toGregorianCalendar(), oferta
						.getFechaLimiteReserva().toGregorianCalendar(),
				oferta.getFacebookLikes());
	}

	public static List<OfertaDto> toOfertaDtos(
			List<es.udc.ws.app.client.service.soap.wsdl.OfertaDto> ofertas) {
		List<OfertaDto> ofertaDtos = new ArrayList<>(ofertas.size());
		for (int i = 0; i < ofertas.size(); i++) {
			es.udc.ws.app.client.service.soap.wsdl.OfertaDto oferta = ofertas
					.get(i);
			try {
				ofertaDtos.add(toOfertaDto(oferta));
			} catch (DatatypeConfigurationException e) {
				return ofertaDtos;
			}

		}
		return ofertaDtos;
	}

}
