package es.udc.ws.app.dto;

import java.util.Calendar;

public class OfertaDto {

	private Long ofertaId;
	private String nombreOferta;
	private String descripcionOferta;
	private String estadoOferta;
	private float precioRealOferta;
	private float precioDescontadoOferta;
	private Calendar fechaLimiteOferta;
	private Calendar fechaLimiteReserva;
	private int facebookLikes;

	public OfertaDto() {

	}

	public OfertaDto(Long ofertaId, String nombreOferta,
			String descripcionOferta, String estadoOferta,
			float precioRealOferta, float precioDescontadoOferta,
			Calendar fechaLimiteOferta, Calendar fechaLimiteReserva,
			int facebookLikes) {
		super();
		this.ofertaId = ofertaId;
		this.nombreOferta = nombreOferta;
		this.descripcionOferta = descripcionOferta;
		this.estadoOferta = estadoOferta;
		this.precioRealOferta = precioRealOferta;
		this.precioDescontadoOferta = precioDescontadoOferta;
		this.fechaLimiteOferta = fechaLimiteOferta;
		this.fechaLimiteReserva = fechaLimiteReserva;
		this.facebookLikes = facebookLikes;
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
	}

	public Calendar getFechaLimiteReserva() {
		return fechaLimiteReserva;
	}

	public void setFechaLimiteReserva(Calendar fechaLimiteReserva) {
		this.fechaLimiteReserva = fechaLimiteReserva;
	}

	public int getFacebookLikes() {
		return facebookLikes;
	}

	public void setFacebookLikes(int facebookLikes) {
		this.facebookLikes = facebookLikes;
	}

	@Override
	public String toString() {
		return "OfertaDto [ofertaId=" + ofertaId + ", nombreOferta="
				+ nombreOferta + ", descripcionOferta=" + descripcionOferta
				+ ", estadoOferta=" + estadoOferta + ", precioRealOferta="
				+ precioRealOferta + ", precioDescontadoOferta="
				+ precioDescontadoOferta + ", fechaLimiteOferta="
				+ fechaLimiteOferta.getTime() + ", fechaLimiteReserva="
				+ fechaLimiteReserva.getTime() + ", facebookLikes="
				+ facebookLikes + "]";
	}

}
