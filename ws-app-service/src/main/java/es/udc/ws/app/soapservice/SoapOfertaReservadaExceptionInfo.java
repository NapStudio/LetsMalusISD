package es.udc.ws.app.soapservice;

public class SoapOfertaReservadaExceptionInfo {
	private Long ofertaId;

	public SoapOfertaReservadaExceptionInfo() {
	}

	public SoapOfertaReservadaExceptionInfo(Long ofertaId) {
		this.ofertaId = ofertaId;

	}

	public Long getOfertaId() {
		return ofertaId;
	}

	public void setOfertaId(Long ofertaId) {
		this.ofertaId = ofertaId;
	}

}
