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

	public Oferta(String nombreOferta, String descripcionOferta,
			String estadoOferta, float precioRealOferta,
			float precioDescontadoOferta, float comisionOferta,
			Calendar fechaLimiteOferta, Calendar fechaLimiteReserva) {
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
	}

	public Oferta(Long ofertaId, String nombreOferta, String descripcionOferta,
			String estadoOferta, float precioRealOferta,
			float precioDescontadoOferta, float comisionOferta,
			Calendar fechaLimiteOferta, Calendar fechaLimiteReserva) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ofertaId == null) ? 0 : ofertaId.hashCode());
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
		if (ofertaId == null) {
			if (other.ofertaId != null)
				return false;
		} else if (!ofertaId.equals(other.ofertaId))
			return false;
		return true;
	}

}
