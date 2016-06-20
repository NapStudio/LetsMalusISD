package es.udc.ws.app.client.service.soap;

import java.util.ArrayList;
import java.util.List;

import es.udc.ws.app.dto.ReservaDto;

public class ReservaDtoToSoapReservaDtoConversor {
	public static es.udc.ws.app.client.service.soap.wsdl.ReservaDto toSoapReservaDto(
			ReservaDto reserva) {
		es.udc.ws.app.client.service.soap.wsdl.ReservaDto soapReservaDto = new es.udc.ws.app.client.service.soap.wsdl.ReservaDto();
		soapReservaDto.setReservaId(reserva.getReservaId());
		soapReservaDto.setOfertaId(reserva.getOfertaId());
		soapReservaDto.setEstadoReserva(reserva.getEstadoReserva());
		soapReservaDto.setEmailUsuarioReserva(reserva.getEmailUsuarioReserva());
		soapReservaDto.setTarjetaCreditoReserva(reserva
				.getTarjetaCreditoReserva());
		return soapReservaDto;
	}

	public static ReservaDto toReservaDto(
			es.udc.ws.app.client.service.soap.wsdl.ReservaDto reserva) {
		return new ReservaDto(reserva.getReservaId(), reserva.getOfertaId(),
				reserva.getEmailUsuarioReserva(),
				reserva.getTarjetaCreditoReserva(), reserva.getEstadoReserva());
	}

	public static List<ReservaDto> toReservaDtos(
			List<es.udc.ws.app.client.service.soap.wsdl.ReservaDto> reservas) {
		List<ReservaDto> reservaDtos = new ArrayList<>(reservas.size());
		for (int i = 0; i < reservas.size(); i++) {
			es.udc.ws.app.client.service.soap.wsdl.ReservaDto reserva = reservas
					.get(i);
			reservaDtos.add(toReservaDto(reserva));

		}
		return reservaDtos;
	}
}
