package es.udc.ws.app.exceptions;

import java.util.Calendar;


@SuppressWarnings("serial")
public class ReservaExpirationException extends Exception{
	
    private Long reservaId;
    private Calendar fechaExpiracion;

    public ReservaExpirationException(Long reservaId, Calendar fechalimite) {
        super("Reserva with id=\"" + reservaId + 
              "\" has expired (expirationDate = \"" + 
              fechalimite.toString() + "\")");
        this.reservaId = reservaId;
        this.fechaExpiracion = fechalimite;
    }

	public Long getReservaId() {
		return reservaId;
	}

	public Calendar getFechaExpiracion() {
		return fechaExpiracion;
	}
    
}
