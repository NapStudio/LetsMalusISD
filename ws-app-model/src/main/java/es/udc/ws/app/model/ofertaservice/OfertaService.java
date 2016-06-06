package es.udc.ws.app.model.ofertaservice;

import java.util.Date;
import java.util.List;

import es.udc.ws.app.exceptions.BadStateReserva;
import es.udc.ws.app.exceptions.OfertaReservadaException;
import es.udc.ws.app.exceptions.ReservaExpirationException;
import es.udc.ws.app.model.oferta.Oferta;
import es.udc.ws.app.model.reserva.Reserva;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public interface OfertaService {
	
	public Oferta addOferta(Oferta oferta) throws InputValidationException;

    public void updateOferta(Oferta oferta) throws InputValidationException,
            InstanceNotFoundException;

    public void removeOferta(Long ofertaId) throws InstanceNotFoundException, OfertaReservadaException;
    
    public void invalidarOferta(Long ofertaId) throws InstanceNotFoundException;

    public Oferta findOferta(Long ofertaId) throws InstanceNotFoundException;

    public List<Oferta> findOfertas(String keywords, String estadoBusqueda, Date fechaBusqueda);

    public Long reservarOferta(Long ofertaId, String emailUsuarioReserva, String tarjetaCreditoReserva)
            throws InstanceNotFoundException, InputValidationException;
    
    public Reserva findReserva(Long reservaId) throws InstanceNotFoundException;

    public List<Reserva> findReservasByOferta(Long ofertaId) throws InstanceNotFoundException,
            ReservaExpirationException;
    
    public List<Reserva> findReservasByUsuario(String emailUsuarioReserva) throws InstanceNotFoundException,
    ReservaExpirationException;
    
    public Long reclamarOferta(Long reservaId) throws InstanceNotFoundException, BadStateReserva, ReservaExpirationException ;
}
