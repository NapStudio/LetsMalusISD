package es.udc.ws.app.exceptions;

@SuppressWarnings("serial")
public class BadStateReservaException extends Exception {
	private Long reservaId;
	private String estadoReserva;

	public BadStateReservaException(Long reservaId, String estadoReserva) {
		super("The reservation id: " + reservaId + " has state: "
				+ estadoReserva);
		this.reservaId = reservaId;
		this.estadoReserva = estadoReserva;

	}

	public Long getReservaId() {
		return reservaId;
	}

	public String getEstadoReserva() {
		return estadoReserva;
	}

	public void setReservaId(Long reservaId) {
		this.reservaId = reservaId;
	}

	public void setEstadoReserva(String estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

}
