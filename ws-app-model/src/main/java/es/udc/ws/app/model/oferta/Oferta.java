package es.udc.ws.app.model.oferta;

import java.util.Date;

public class Oferta {
	
    private Long ofertaId;
    private String nombreOferta;
    private String descripcionOferta;
    private String estadoOferta;
    private float precioRealOferta;
    private float precioDescontadoOferta;
    private float comisionOferta;
    private Date fechaLimiteOferta;
    private Date fechaLimiteReserva;
    
    public Oferta(String nombreOferta, String descripcionOferta,
			String estadoOferta, float precioRealOferta,
			float precioDescontadoOferta, float comisionOferta,
			Date fechaLimiteOferta, Date fechaLimiteReserva) {
		super();
		this.nombreOferta = nombreOferta;
		this.descripcionOferta = descripcionOferta;
		this.estadoOferta = estadoOferta;
		this.precioRealOferta = precioRealOferta;
		this.precioDescontadoOferta = precioDescontadoOferta;
		this.comisionOferta = comisionOferta;
		this.fechaLimiteOferta = fechaLimiteOferta;
		this.fechaLimiteReserva = fechaLimiteReserva;
	}
    
    
	public Oferta(Long ofertaId, String nombreOferta, String descripcionOferta,
			String estadoOferta, float precioRealOferta,
			float precioDescontadoOferta, float comisionOferta,
			Date fechaLimiteOferta, Date fechaLimiteReserva) {
		super();
		this.ofertaId = ofertaId;
		this.nombreOferta = nombreOferta;
		this.descripcionOferta = descripcionOferta;
		this.estadoOferta = estadoOferta;
		this.precioRealOferta = precioRealOferta;
		this.precioDescontadoOferta = precioDescontadoOferta;
		this.comisionOferta = comisionOferta;
		this.fechaLimiteOferta = fechaLimiteOferta;
		this.fechaLimiteReserva = fechaLimiteReserva;
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
	public float getComisionOferta() {
		return comisionOferta;
	}
	public void setComisionOferta(float comisionOferta) {
		this.comisionOferta = comisionOferta;
	}


	public Date getFechaLimiteReserva() {
		return fechaLimiteReserva;
	}


	public void setFechaLimiteReserva(Date fechaLimiteReserva) {
		this.fechaLimiteReserva = fechaLimiteReserva;
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
