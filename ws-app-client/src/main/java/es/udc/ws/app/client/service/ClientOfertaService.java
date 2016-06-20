package es.udc.ws.app.client.service;

import java.util.Calendar;
import java.util.List;

import es.udc.ws.app.dto.OfertaDto;
import es.udc.ws.app.dto.ReservaDto;
import es.udc.ws.app.exceptions.BadStateReservaException;
import es.udc.ws.app.exceptions.OfertaReservadaException;
import es.udc.ws.app.exceptions.ReservaExpirationException;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public interface ClientOfertaService {
	
	//TODO porque devuelve el Id y no un objeto

	public OfertaDto addOferta(OfertaDto oferta) throws InputValidationException;

    public void updateOferta(OfertaDto oferta) throws InputValidationException,
            InstanceNotFoundException;

    public void removeOferta(Long ofertaId) throws InstanceNotFoundException, OfertaReservadaException;
    
    public void invalidarOferta(Long ofertaId) throws InstanceNotFoundException;

    public OfertaDto findOferta(Long ofertaId) throws InstanceNotFoundException;

    public List<OfertaDto> findOfertas(String keywords, String estadoBusqueda, Calendar fechaBusqueda);

    public Long reservarOferta(Long ofertaId, String emailUsuarioReserva, String tarjetaCreditoReserva)
            throws InstanceNotFoundException, InputValidationException;
    
    public ReservaDto findReserva(Long reservaId) throws InstanceNotFoundException;

    public List<ReservaDto> findReservasByOferta(Long ofertaId) throws InstanceNotFoundException,
            ReservaExpirationException;
    
    public List<ReservaDto> findReservasByUsuario(String emailUsuarioReserva) throws InstanceNotFoundException,
    ReservaExpirationException;
    
    public Long reclamarOferta(Long reservaId) throws InstanceNotFoundException, BadStateReservaException, ReservaExpirationException ;
}
