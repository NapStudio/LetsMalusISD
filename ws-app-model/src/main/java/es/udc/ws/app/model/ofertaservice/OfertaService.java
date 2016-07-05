package es.udc.ws.app.model.ofertaservice;

import java.util.Calendar;
import java.util.List;

import es.udc.ws.app.exceptions.BadStateReservaException;
import es.udc.ws.app.exceptions.OfertaReservadaException;
import es.udc.ws.app.exceptions.TimeExpirationException;
import es.udc.ws.app.model.oferta.Oferta;
import es.udc.ws.app.model.reserva.Reserva;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public interface OfertaService {
	
	public Oferta addOferta(Oferta oferta) throws InputValidationException;

    public void updateOferta(Oferta oferta) throws InputValidationException,
            InstanceNotFoundException;

    public void removeOferta(Long ofertaId) throws InstanceNotFoundException, OfertaReservadaException;
    
    public void invalidarOferta(Long ofertaId) throws InstanceNotFoundException, InputValidationException;

    public Oferta findOferta(Long ofertaId) throws InstanceNotFoundException;

    public List<Oferta> findOfertas(String keywords, String estadoBusqueda, Calendar fechaBusqueda);

    public Long reservarOferta(Long ofertaId, String emailUsuarioReserva, String tarjetaCreditoReserva)
            throws InstanceNotFoundException, InputValidationException, OfertaReservadaException, TimeExpirationException;
    
    public Reserva findReserva(Long reservaId) throws InstanceNotFoundException;

    public List<Reserva> findReservasByOferta(Long ofertaId) throws InstanceNotFoundException,
            TimeExpirationException;
    
    public List<Reserva> findReservasByUsuario(String emailUsuarioReserva, String estado) throws InstanceNotFoundException,
    TimeExpirationException;
    
    public Long reclamarOferta(Long reservaId) throws InstanceNotFoundException, BadStateReservaException, TimeExpirationException ;
}
