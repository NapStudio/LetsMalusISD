package es.udc.ws.app.exceptions;

import java.util.Date;


@SuppressWarnings("serial")
public class ReservaExpirationException extends Exception{
	
    private Long reservaId;
    private Date fechaExpiracion;

    public ReservaExpirationException(Long reservaId, Date fechaExpiracion) {
        super("Reserva with id=\"" + reservaId + 
              "\" has expired (expirationDate = \"" + 
              fechaExpiracion.toString() + "\")");
        this.reservaId = reservaId;
        this.fechaExpiracion = fechaExpiracion;
    }

	public Long getReservaId() {
		return reservaId;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}
    
}
