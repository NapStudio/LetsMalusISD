package es.udc.ws.app.dto;

public class ReservaDto {

	private Long reservaId;
	private Long ofertaId;
	private String emailUsuarioReserva;
	private String tarjetaCreditoReserva;
	// estados reserva: válida, inválida, anulada.
	private String estadoReserva;

	public ReservaDto() {

	}

	public ReservaDto(Long reservaId, Long ofertaId,
			String emailUsuarioReserva, String tarjetaCreditoReserva,
			String estadoReserva) {
		super();
		this.reservaId = reservaId;
		this.ofertaId = ofertaId;
		this.emailUsuarioReserva = emailUsuarioReserva;
		this.tarjetaCreditoReserva = tarjetaCreditoReserva;
		this.estadoReserva = estadoReserva;
	}

	public Long getReservaId() {
		return reservaId;
	}

	public void setReservaId(Long reservaId) {
		this.reservaId = reservaId;
	}

	public Long getOfertaId() {
		return ofertaId;
	}

	public void setOfertaId(Long ofertaId) {
		this.ofertaId = ofertaId;
	}

	public String getEmailUsuarioReserva() {
		return emailUsuarioReserva;
	}

	public void setEmailUsuarioReserva(String emailUsuarioReserva) {
		this.emailUsuarioReserva = emailUsuarioReserva;
	}

	public String getTarjetaCreditoReserva() {
		return tarjetaCreditoReserva;
	}

	public void setTarjetaCreditoReserva(String tarjetaCreditoReserva) {
		this.tarjetaCreditoReserva = tarjetaCreditoReserva;
	}

	public String getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(String estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	@Override
	public String toString() {
		return "ReservaDto [reservaId=" + reservaId + ", ofertaId=" + ofertaId
				+ ", emailUsuarioReserva=" + emailUsuarioReserva
				+ ", tarjetaCreditoReserva=" + tarjetaCreditoReserva
				+ ", estadoReserva=" + estadoReserva + "]";
	}

}
