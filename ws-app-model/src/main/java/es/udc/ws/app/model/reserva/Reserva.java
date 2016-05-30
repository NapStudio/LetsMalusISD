package es.udc.ws.app.model.reserva;

import java.util.Date;

public class Reserva {
	
    private Long reservaId;
    private Long ofertaId;
    private String emailUsuarioReserva;
    private String tarjetaCreditoReserva;
    //estados reserva: válida, inválida, anulada.
    private String estadoReserva;
    private Date fechaCreacionReserva;
    
    public Reserva(){
    	
    }
    
	public Reserva(Long ofertaId, String emailUsuarioReserva,
			String tarjetaCreditoReserva, String estadoReserva,
			Date fechaCreacionReserva) {
		super();
		this.ofertaId = ofertaId;
		this.emailUsuarioReserva = emailUsuarioReserva;
		this.tarjetaCreditoReserva = tarjetaCreditoReserva;
		this.estadoReserva = estadoReserva;
		this.fechaCreacionReserva = fechaCreacionReserva;
	}

	public Reserva(Long reservaId, Long ofertaId, String emailUsuarioReserva,
			String tarjetaCreditoReserva, String estadoReserva,
			Date fechaCreacionReserva) {
		super();
		this.reservaId = reservaId;
		this.ofertaId = ofertaId;
		this.emailUsuarioReserva = emailUsuarioReserva;
		this.tarjetaCreditoReserva = tarjetaCreditoReserva;
		this.estadoReserva = estadoReserva;
		this.fechaCreacionReserva = fechaCreacionReserva;
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

	public Date getFechaCreacionReserva() {
		return fechaCreacionReserva;
	}

	public void setFechaCreacionReserva(Date fechaCreacionReserva) {
		this.fechaCreacionReserva = fechaCreacionReserva;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((emailUsuarioReserva == null) ? 0 : emailUsuarioReserva
						.hashCode());
		result = prime * result
				+ ((estadoReserva == null) ? 0 : estadoReserva.hashCode());
		result = prime
				* result
				+ ((fechaCreacionReserva == null) ? 0 : fechaCreacionReserva
						.hashCode());
		result = prime * result
				+ ((ofertaId == null) ? 0 : ofertaId.hashCode());
		result = prime * result
				+ ((reservaId == null) ? 0 : reservaId.hashCode());
		result = prime
				* result
				+ ((tarjetaCreditoReserva == null) ? 0 : tarjetaCreditoReserva
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		if (emailUsuarioReserva == null) {
			if (other.emailUsuarioReserva != null)
				return false;
		} else if (!emailUsuarioReserva.equals(other.emailUsuarioReserva))
			return false;
		if (estadoReserva == null) {
			if (other.estadoReserva != null)
				return false;
		} else if (!estadoReserva.equals(other.estadoReserva))
			return false;
		if (fechaCreacionReserva == null) {
			if (other.fechaCreacionReserva != null)
				return false;
		} else if (!fechaCreacionReserva.equals(other.fechaCreacionReserva))
			return false;
		if (ofertaId == null) {
			if (other.ofertaId != null)
				return false;
		} else if (!ofertaId.equals(other.ofertaId))
			return false;
		if (reservaId == null) {
			if (other.reservaId != null)
				return false;
		} else if (!reservaId.equals(other.reservaId))
			return false;
		if (tarjetaCreditoReserva == null) {
			if (other.tarjetaCreditoReserva != null)
				return false;
		} else if (!tarjetaCreditoReserva.equals(other.tarjetaCreditoReserva))
			return false;
		return true;
	}

    

    
}
