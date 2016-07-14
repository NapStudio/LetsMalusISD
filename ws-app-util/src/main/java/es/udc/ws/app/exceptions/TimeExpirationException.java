package es.udc.ws.app.exceptions;

import java.util.Calendar;


@SuppressWarnings("serial")
public class TimeExpirationException extends Exception{
	
    private Long Id;
    private Calendar fechaExpiracion;
    private String message;

    public TimeExpirationException(String message, Long Id, Calendar fechalimite) {
        super(message+" with id=\"" + Id + 
              "\" has expired (expirationDate = \"" + 
              fechalimite.toString() + "\")");
        this.message=message;
        this.Id = Id;
        this.fechaExpiracion = fechalimite;
    }

	public Long getId() {
		return Id;
	}

	public Calendar getFechaExpiracion() {
		return fechaExpiracion;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "TimeExpirationException [Id=" + Id + ", fechaExpiracion="
				+ fechaExpiracion.getTime() + ", in=" + message + "]";
	}
	
	
    
}
