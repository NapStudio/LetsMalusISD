package es.udc.ws.app.soapservice;

import java.util.Calendar;

public class SoapTimeExpirationExceptionInfo {

	private String message;
	private Long Id;
	private Calendar fechaExpiracion;

	public SoapTimeExpirationExceptionInfo() {
	}

	public SoapTimeExpirationExceptionInfo(String message, Long reservaId,
			Calendar fechaExpiracion) {
		this.message = message;
		this.Id = reservaId;
		this.fechaExpiracion = fechaExpiracion;
	}

	public Long getId() {
		return Id;
	}

	public Calendar getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(Calendar fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public void setId(Long reservaId) {
		this.Id = reservaId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
