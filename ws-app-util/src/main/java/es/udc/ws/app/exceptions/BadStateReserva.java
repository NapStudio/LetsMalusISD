package es.udc.ws.app.exceptions;

@SuppressWarnings("serial")
public class BadStateReserva extends Exception{
	//TODO
	private Long reservaId;
	private String estadoReserva;
	public BadStateReserva(String estadoReserva, Long reservaId){
		super("The reservation id: "+reservaId+" has state: "+estadoReserva);
		this.reservaId=reservaId;
		this.estadoReserva=estadoReserva;
		
	}
	public Long getReservaId() {
		return reservaId;
	}
	public String getEstadoReserva() {
		return estadoReserva;
	}
	
}
