package es.udc.ws.app.client.service.soap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import es.udc.ws.app.dto.OfertaDto;

public class OfertaDtoToSoapOfertaDtoConversor {
	public static es.udc.ws.app.client.service.soap.wsdl.OfertaDto toSoapOfertaDto(
			OfertaDto oferta) {
		es.udc.ws.app.client.service.soap.wsdl.OfertaDto soapOfertaDto = new es.udc.ws.app.client.service.soap.wsdl.OfertaDto();
		soapOfertaDto.setComisionOferta(oferta.getComisionOferta());
		soapOfertaDto.setDescripcionOferta(oferta.getDescripcionOferta());
		soapOfertaDto.setEstadoOferta(oferta.getEstadoOferta());
		try {
			soapOfertaDto.setFechaLimiteOferta(GregorianCalendarConversor.toXMLGregorianCalendar(oferta
					.getFechaLimiteOferta()));
		} catch (DatatypeConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			soapOfertaDto.setFechaLimiteReserva(GregorianCalendarConversor.toXMLGregorianCalendar(oferta
					.getFechaLimiteOferta()));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		soapOfertaDto.setNombreOferta(oferta.getNombreOferta());
		soapOfertaDto.setOfertaId(oferta.getOfertaId());
		soapOfertaDto.setPrecioDescontadoOferta(oferta
				.getPrecioDescontadoOferta());
		soapOfertaDto.setPrecioRealOferta(oferta.getPrecioRealOferta());
		return soapOfertaDto;
	}

	public static OfertaDto toOfertaDto(
			es.udc.ws.app.client.service.soap.wsdl.OfertaDto oferta) throws DatatypeConfigurationException {
		try {
			return new OfertaDto(oferta.getOfertaId(), oferta.getNombreOferta(),
					oferta.getDescripcionOferta(), oferta.getEstadoOferta(),
					oferta.getPrecioRealOferta(),
					oferta.getPrecioDescontadoOferta(), oferta.getComisionOferta(),
					GregorianCalendarConversor.toCalendar(oferta.getFechaLimiteOferta()), GregorianCalendarConversor.toCalendar(oferta.getFechaLimiteReserva()));
		} catch (DatatypeConfigurationException e) {
			throw new DatatypeConfigurationException();
		}
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return ofertaDtos;
	}



}
