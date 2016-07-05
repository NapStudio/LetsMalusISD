package es.udc.ws.app.exceptions;

@SuppressWarnings("serial")
public class OfertaReservadaException extends Exception{
	private Long ofertaId;
	
	public OfertaReservadaException(Long ofertaId){
		super("The offer with id: "+ofertaId+" is booked");
		this.ofertaId=ofertaId;
		
	}
	public Long getOfertaId() {
		return ofertaId;
	}
}
