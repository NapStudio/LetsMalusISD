package es.udc.ws.app.model.oferta;

import java.util.Calendar;

public class Oferta {

	private Long ofertaId;
	private String nombreOferta;
	private String descripcionOferta;
	private String estadoOferta;
	private float precioRealOferta;
	private float precioDescontadoOferta;
	private float comisionOferta;
	private Calendar fechaLimiteOferta;
	private Calendar fechaLimiteReserva;
	private String facebookId;

	public Oferta(String nombreOferta, String descripcionOferta,
			String estadoOferta, float precioRealOferta,
			float precioDescontadoOferta, float comisionOferta,
			Calendar fechaLimiteOferta, Calendar fechaLimiteReserva,
			String facebookId) {
		super();
		this.nombreOferta = nombreOferta;
		this.descripcionOferta = descripcionOferta;
		this.estadoOferta = estadoOferta;
		this.precioRealOferta = precioRealOferta;
		this.precioDescontadoOferta = precioDescontadoOferta;
		this.comisionOferta = comisionOferta;
		this.fechaLimiteOferta = fechaLimiteOferta;
		if (fechaLimiteOferta != null) {
			this.fechaLimiteOferta.set(Calendar.MILLISECOND, 0);
		}
		this.fechaLimiteReserva = fechaLimiteReserva;
		if (fechaLimiteReserva != null) {
			this.fechaLimiteReserva.set(Calendar.MILLISECOND, 0);
		}
		this.facebookId = facebookId;
	}

	public Oferta(Long ofertaId, String nombreOferta, String descripcionOferta,
			String estadoOferta, float precioRealOferta,
			float precioDescontadoOferta, float comisionOferta,
			Calendar fechaLimiteOferta, Calendar fechaLimiteReserva,
			String facebookId) {
		super();
		this.ofertaId = ofertaId;
		this.nombreOferta = nombreOferta;
		this.descripcionOferta = descripcionOferta;
		this.estadoOferta = estadoOferta;
		this.precioRealOferta = precioRealOferta;
		this.precioDescontadoOferta = precioDescontadoOferta;
		this.comisionOferta = comisionOferta;
		this.fechaLimiteOferta = fechaLimiteOferta;
		if (fechaLimiteOferta != null) {
			this.fechaLimiteOferta.set(Calendar.MILLISECOND, 0);
		}
		this.fechaLimiteReserva = fechaLimiteReserva;
		if (fechaLimiteReserva != null) {
			this.fechaLimiteReserva.set(Calendar.MILLISECOND, 0);
		}
		this.facebookId = facebookId;
	}

	public Long getOfertaId() {
		return ofertaId;
	}

	public void setOfertaId(Long ofertaId) {
		this.ofertaId = ofertaId;
	}

	public String getNombreOferta() {
		return nombreOferta;
	}

	public void setNombreOferta(String nombreOferta) {
		this.nombreOferta = nombreOferta;
	}

	public String getDescripcionOferta() {
		return descripcionOferta;
	}

	public void setDescripcionOferta(String descripcionOferta) {
		this.descripcionOferta = descripcionOferta;
	}

	public String getEstadoOferta() {
		return estadoOferta;
	}

	public void setEstadoOferta(String estadoOferta) {
		this.estadoOferta = estadoOferta;
	}

	public float getPrecioRealOferta() {
		return precioRealOferta;
	}

	public void setPrecioRealOferta(float precioRealOferta) {
		this.precioRealOferta = precioRealOferta;
	}

	public float getPrecioDescontadoOferta() {
		return precioDescontadoOferta;
	}

	public void setPrecioDescontadoOferta(float precioDescontadoOferta) {
		this.precioDescontadoOferta = precioDescontadoOferta;
	}

	public Calendar getFechaLimiteOferta() {
		return fechaLimiteOferta;
	}

	public void setFechaLimiteOferta(Calendar fechaLimiteOferta) {
		this.fechaLimiteOferta = fechaLimiteOferta;

		if (fechaLimiteOferta != null) {
			this.fechaLimiteOferta.set(Calendar.MILLISECOND, 0);
		}
	}

	public float getComisionOferta() {
		return comisionOferta;
	}

	public void setComisionOferta(float comisionOferta) {
		this.comisionOferta = comisionOferta;
	}

	public Calendar getFechaLimiteReserva() {
		return fechaLimiteReserva;
	}

	public void setFechaLimiteReserva(Calendar fechaLimiteReserva) {
		this.fechaLimiteReserva = fechaLimiteReserva;
		if (fechaLimiteReserva != null) {
			this.fechaLimiteReserva.set(Calendar.MILLISECOND, 0);
		}
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(comisionOferta);
		result = prime
				* result
				+ ((descripcionOferta == null) ? 0 : descripcionOferta
						.hashCode());
		result = prime * result
				+ ((estadoOferta == null) ? 0 : estadoOferta.hashCode());
		result = prime * result
				+ ((facebookId == null) ? 0 : facebookId.hashCode());
		result = prime
				* result
				+ ((fechaLimiteOferta == null) ? 0 : fechaLimiteOferta
						.hashCode());
		result = prime
				* result
				+ ((fechaLimiteReserva == null) ? 0 : fechaLimiteReserva
						.hashCode());
		result = prime * result
				+ ((nombreOferta == null) ? 0 : nombreOferta.hashCode());
		result = prime * result
				+ ((ofertaId == null) ? 0 : ofertaId.hashCode());
		result = prime * result + Float.floatToIntBits(precioDescontadoOferta);
		result = prime * result + Float.floatToIntBits(precioRealOferta);
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
		Oferta other = (Oferta) obj;
		if (Float.floatToIntBits(comisionOferta) != Float
				.floatToIntBits(other.comisionOferta))
			return false;
		if (descripcionOferta == null) {
			if (other.descripcionOferta != null)
				return false;
		} else if (!descripcionOferta.equals(other.descripcionOferta))
			return false;
		if (estadoOferta == null) {
			if (other.estadoOferta != null)
				return false;
		} else if (!estadoOferta.equals(other.estadoOferta))
			return false;
		if (facebookId == null) {
			if (other.facebookId != null)
				return false;
		} else if (!facebookId.equals(other.facebookId))
			return false;
		if (fechaLimiteOferta == null) {
			if (other.fechaLimiteOferta != null)
				return false;
		} else if (!fechaLimiteOferta.equals(other.fechaLimiteOferta))
			return false;
		if (fechaLimiteReserva == null) {
			if (other.fechaLimiteReserva != null)
				return false;
		} else if (!fechaLimiteReserva.equals(other.fechaLimiteReserva))
			return false;
		if (nombreOferta == null) {
			if (other.nombreOferta != null)
				return false;
		} else if (!nombreOferta.equals(other.nombreOferta))
			return false;
		if (ofertaId == null) {
			if (other.ofertaId != null)
				return false;
		} else if (!ofertaId.equals(other.ofertaId))
			return false;
		if (Float.floatToIntBits(precioDescontadoOferta) != Float
				.floatToIntBits(other.precioDescontadoOferta))
			return false;
		if (Float.floatToIntBits(precioRealOferta) != Float
				.floatToIntBits(other.precioRealOferta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Oferta [ofertaId=" + ofertaId + ", nombreOferta="
				+ nombreOferta + ", descripcionOferta=" + descripcionOferta
				+ ", estadoOferta=" + estadoOferta + ", precioRealOferta="
				+ precioRealOferta + ", precioDescontadoOferta="
				+ precioDescontadoOferta + ", comisionOferta=" + comisionOferta
				+ ", fechaLimiteOferta=" + fechaLimiteOferta
				+ ", fechaLimiteReserva=" + fechaLimiteReserva
				+ ", facebookId=" + facebookId + "]";
	}

	public String toStringSin() {
		return "Oferta [nombreOferta=" + nombreOferta + ", descripcionOferta="
				+ descripcionOferta + ", estadoOferta=" + estadoOferta
				+ ", precioRealOferta=" + precioRealOferta
				+ ", precioDescontadoOferta=" + precioDescontadoOferta
				+ ", fechaLimiteOferta=" + fechaLimiteOferta
				+ ", fechaLimiteReserva=" + fechaLimiteReserva
				+ ", facebookId=" + facebookId + "]";
	}

}
