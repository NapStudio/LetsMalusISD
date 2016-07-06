package es.udc.ws.app.serviceutil;

import java.util.ArrayList;
import java.util.List;

import es.udc.ws.app.dto.OfertaDto;
import es.udc.ws.app.model.oferta.Oferta;


public class OfertaToOfertaDtoConversor {
	
	 public static List<OfertaDto> toOfertaDtos(List<Oferta> ofertas, List<Integer> facebookLikes) {
	        List<OfertaDto> ofertaDtos = new ArrayList<>(ofertas.size());
	        for (int i = 0; i < ofertas.size(); i++) {
	        	Oferta oferta = ofertas.get(i);
	        	int like=facebookLikes.get(i);
	            ofertaDtos.add(toOfertaDto(oferta, like));
	        }
	        return ofertaDtos;
	    }

	    public static OfertaDto toOfertaDto(Oferta oferta, int facebookLikes) {
	        return new OfertaDto(oferta.getOfertaId(), oferta.getNombreOferta(), oferta
	                .getDescripcionOferta(), oferta.getEstadoOferta(), oferta.getPrecioRealOferta(),
	                oferta.getPrecioDescontadoOferta(),oferta.getFechaLimiteOferta(),
	                oferta.getFechaLimiteReserva(), facebookLikes);
	    }
	    
	    
	    public static Oferta toOferta(OfertaDto oferta) {
	        return new Oferta(oferta.getOfertaId(), oferta.getNombreOferta(), oferta
	                .getDescripcionOferta(), oferta.getEstadoOferta(), oferta.getPrecioRealOferta(),
	                oferta.getPrecioDescontadoOferta(),0,oferta.getFechaLimiteOferta(),
	                oferta.getFechaLimiteReserva(), "");
	    }    

}
