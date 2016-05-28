package es.udc.ws.app.model.oferta;

import java.util.Date;

public class Oferta {
	
    private Long ofertaId;
    private String nombreOferta;
    private String descripcionOferta;
    private String estadoOferta;
    private float precioRealOferta;
    private float precioDescontadoOferta;
    private Date fechaLimiteOferta;
    
    
    
	public Oferta(String nombreOferta, String descripcionOferta,
			String estadoOferta, float precioRealOferta,
			float precioDescontadoOferta, Date fechaLimiteOferta) {
		super();
		this.nombreOferta = nombreOferta;
		this.descripcionOferta = descripcionOferta;
		this.estadoOferta = estadoOferta;
		this.precioRealOferta = precioRealOferta;
		this.precioDescontadoOferta = precioDescontadoOferta;
		this.fechaLimiteOferta = fechaLimiteOferta;
	}

	public Oferta(Long ofertaId, String nombreOferta, String descripcionOferta,
			String estadoOferta, float precioRealOferta,
			float precioDescontadoOferta, Date fechaLimiteOferta) {
		super();
		this.ofertaId = ofertaId;
		this.nombreOferta = nombreOferta;
		this.descripcionOferta = descripcionOferta;
		this.estadoOferta = estadoOferta;
		this.precioRealOferta = precioRealOferta;
		this.precioDescontadoOferta = precioDescontadoOferta;
		this.fechaLimiteOferta = fechaLimiteOferta;
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
	public Date getFechaLimiteOferta() {
		return fechaLimiteOferta;
	}
	public void setFechaLimiteOferta(Date fechaLimiteOferta) {
		this.fechaLimiteOferta = fechaLimiteOferta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((descripcionOferta == null) ? 0 : descripcionOferta
						.hashCode());
		result = prime * result
				+ ((estadoOferta == null) ? 0 : estadoOferta.hashCode());
		result = prime
				* result
				+ ((fechaLimiteOferta == null) ? 0 : fechaLimiteOferta
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
		if (fechaLimiteOferta == null) {
			if (other.fechaLimiteOferta != null)
				return false;
		} else if (!fechaLimiteOferta.equals(other.fechaLimiteOferta))
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
	
	
    

}
