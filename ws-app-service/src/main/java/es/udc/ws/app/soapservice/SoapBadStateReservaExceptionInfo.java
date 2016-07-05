package es.udc.ws.app.soapservice;



public class SoapBadStateReservaExceptionInfo {

	private Long reservaId;
	private String estadoReserva;

	public SoapBadStateReservaExceptionInfo() {
	}

	public SoapBadStateReservaExceptionInfo(Long reservaId, String estadoReserva) {
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
