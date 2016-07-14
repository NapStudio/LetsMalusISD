
package es.udc.ws.app.client.service.soap.wsdl;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "OfertasProvider", targetNamespace = "http://soap.ws.udc.es/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface OfertasProvider {


    /**
     * 
     * @param emailUsuarioReserva
     * @param estadoReserva
     * @return
     *     returns java.util.List<es.udc.ws.app.client.service.soap.wsdl.ReservaDto>
     * @throws SoapInstanceNotFoundException
     * @throws SoapTimeExpirationException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findReservasByUsuario", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.FindReservasByUsuario")
    @ResponseWrapper(localName = "findReservasByUsuarioResponse", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.FindReservasByUsuarioResponse")
    @Action(input = "http://soap.ws.udc.es/OfertasProvider/findReservasByUsuarioRequest", output = "http://soap.ws.udc.es/OfertasProvider/findReservasByUsuarioResponse", fault = {
        @FaultAction(className = SoapInstanceNotFoundException.class, value = "http://soap.ws.udc.es/OfertasProvider/findReservasByUsuario/Fault/SoapInstanceNotFoundException"),
        @FaultAction(className = SoapTimeExpirationException.class, value = "http://soap.ws.udc.es/OfertasProvider/findReservasByUsuario/Fault/SoapTimeExpirationException")
    })
    public List<ReservaDto> findReservasByUsuario(
        @WebParam(name = "emailUsuarioReserva", targetNamespace = "")
        String emailUsuarioReserva,
        @WebParam(name = "estadoReserva", targetNamespace = "")
        String estadoReserva)
        throws SoapInstanceNotFoundException, SoapTimeExpirationException
    ;

    /**
     * 
     * @param ofertaId
     * @return
     *     returns java.util.List<es.udc.ws.app.client.service.soap.wsdl.ReservaDto>
     * @throws SoapInstanceNotFoundException
     * @throws SoapTimeExpirationException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findReservasByOferta", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.FindReservasByOferta")
    @ResponseWrapper(localName = "findReservasByOfertaResponse", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.FindReservasByOfertaResponse")
    @Action(input = "http://soap.ws.udc.es/OfertasProvider/findReservasByOfertaRequest", output = "http://soap.ws.udc.es/OfertasProvider/findReservasByOfertaResponse", fault = {
        @FaultAction(className = SoapInstanceNotFoundException.class, value = "http://soap.ws.udc.es/OfertasProvider/findReservasByOferta/Fault/SoapInstanceNotFoundException"),
        @FaultAction(className = SoapTimeExpirationException.class, value = "http://soap.ws.udc.es/OfertasProvider/findReservasByOferta/Fault/SoapTimeExpirationException")
    })
    public List<ReservaDto> findReservasByOferta(
        @WebParam(name = "ofertaId", targetNamespace = "")
        Long ofertaId)
        throws SoapInstanceNotFoundException, SoapTimeExpirationException
    ;

    /**
     * 
     * @param ofertaId
     * @throws SoapInstanceNotFoundException
     * @throws SoapOfertaReservadaException
     */
    @WebMethod
    @RequestWrapper(localName = "removeOferta", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.RemoveOferta")
    @ResponseWrapper(localName = "removeOfertaResponse", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.RemoveOfertaResponse")
    @Action(input = "http://soap.ws.udc.es/OfertasProvider/removeOfertaRequest", output = "http://soap.ws.udc.es/OfertasProvider/removeOfertaResponse", fault = {
        @FaultAction(className = SoapInstanceNotFoundException.class, value = "http://soap.ws.udc.es/OfertasProvider/removeOferta/Fault/SoapInstanceNotFoundException"),
        @FaultAction(className = SoapOfertaReservadaException.class, value = "http://soap.ws.udc.es/OfertasProvider/removeOferta/Fault/SoapOfertaReservadaException")
    })
    public void removeOferta(
        @WebParam(name = "ofertaId", targetNamespace = "")
        Long ofertaId)
        throws SoapInstanceNotFoundException, SoapOfertaReservadaException
    ;

    /**
     * 
     * @param reservaId
     * @return
     *     returns es.udc.ws.app.client.service.soap.wsdl.ReservaDto
     * @throws SoapInstanceNotFoundException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findReserva", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.FindReserva")
    @ResponseWrapper(localName = "findReservaResponse", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.FindReservaResponse")
    @Action(input = "http://soap.ws.udc.es/OfertasProvider/findReservaRequest", output = "http://soap.ws.udc.es/OfertasProvider/findReservaResponse", fault = {
        @FaultAction(className = SoapInstanceNotFoundException.class, value = "http://soap.ws.udc.es/OfertasProvider/findReserva/Fault/SoapInstanceNotFoundException")
    })
    public ReservaDto findReserva(
        @WebParam(name = "reservaId", targetNamespace = "")
        Long reservaId)
        throws SoapInstanceNotFoundException
    ;

    /**
     * 
     * @param reservaId
     * @return
     *     returns java.lang.Long
     * @throws SoapInstanceNotFoundException
     * @throws SoapTimeExpirationException
     * @throws SoapBadStateReservaException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "reclamarOferta", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.ReclamarOferta")
    @ResponseWrapper(localName = "reclamarOfertaResponse", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.ReclamarOfertaResponse")
    @Action(input = "http://soap.ws.udc.es/OfertasProvider/reclamarOfertaRequest", output = "http://soap.ws.udc.es/OfertasProvider/reclamarOfertaResponse", fault = {
        @FaultAction(className = SoapInstanceNotFoundException.class, value = "http://soap.ws.udc.es/OfertasProvider/reclamarOferta/Fault/SoapInstanceNotFoundException"),
        @FaultAction(className = SoapBadStateReservaException.class, value = "http://soap.ws.udc.es/OfertasProvider/reclamarOferta/Fault/SoapBadStateReservaException"),
        @FaultAction(className = SoapTimeExpirationException.class, value = "http://soap.ws.udc.es/OfertasProvider/reclamarOferta/Fault/SoapTimeExpirationException")
    })
    public Long reclamarOferta(
        @WebParam(name = "reservaId", targetNamespace = "")
        Long reservaId)
        throws SoapBadStateReservaException, SoapInstanceNotFoundException, SoapTimeExpirationException
    ;

    /**
     * 
     * @param ofertaDto
     * @return
     *     returns java.lang.Long
     * @throws SoapInputValidationException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addOferta", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.AddOferta")
    @ResponseWrapper(localName = "addOfertaResponse", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.AddOfertaResponse")
    @Action(input = "http://soap.ws.udc.es/OfertasProvider/addOfertaRequest", output = "http://soap.ws.udc.es/OfertasProvider/addOfertaResponse", fault = {
        @FaultAction(className = SoapInputValidationException.class, value = "http://soap.ws.udc.es/OfertasProvider/addOferta/Fault/SoapInputValidationException")
    })
    public Long addOferta(
        @WebParam(name = "ofertaDto", targetNamespace = "")
        OfertaDto ofertaDto)
        throws SoapInputValidationException
    ;

    /**
     * 
     * @param keywords
     * @return
     *     returns java.util.List<es.udc.ws.app.client.service.soap.wsdl.OfertaDto>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findOfertas", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.FindOfertas")
    @ResponseWrapper(localName = "findOfertasResponse", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.FindOfertasResponse")
    @Action(input = "http://soap.ws.udc.es/OfertasProvider/findOfertasRequest", output = "http://soap.ws.udc.es/OfertasProvider/findOfertasResponse")
    public List<OfertaDto> findOfertas(
        @WebParam(name = "keywords", targetNamespace = "")
        String keywords);

    /**
     * 
     * @param ofertaId
     * @throws SoapInstanceNotFoundException
     * @throws SoapInputValidationException
     */
    @WebMethod
    @RequestWrapper(localName = "invalidarOferta", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.InvalidarOferta")
    @ResponseWrapper(localName = "invalidarOfertaResponse", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.InvalidarOfertaResponse")
    @Action(input = "http://soap.ws.udc.es/OfertasProvider/invalidarOfertaRequest", output = "http://soap.ws.udc.es/OfertasProvider/invalidarOfertaResponse", fault = {
        @FaultAction(className = SoapInstanceNotFoundException.class, value = "http://soap.ws.udc.es/OfertasProvider/invalidarOferta/Fault/SoapInstanceNotFoundException"),
        @FaultAction(className = SoapInputValidationException.class, value = "http://soap.ws.udc.es/OfertasProvider/invalidarOferta/Fault/SoapInputValidationException")
    })
    public void invalidarOferta(
        @WebParam(name = "ofertaId", targetNamespace = "")
        Long ofertaId)
        throws SoapInputValidationException, SoapInstanceNotFoundException
    ;

    /**
     * 
     * @param ofertaId
     * @return
     *     returns es.udc.ws.app.client.service.soap.wsdl.OfertaDto
     * @throws SoapInstanceNotFoundException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findOferta", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.FindOferta")
    @ResponseWrapper(localName = "findOfertaResponse", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.FindOfertaResponse")
    @Action(input = "http://soap.ws.udc.es/OfertasProvider/findOfertaRequest", output = "http://soap.ws.udc.es/OfertasProvider/findOfertaResponse", fault = {
        @FaultAction(className = SoapInstanceNotFoundException.class, value = "http://soap.ws.udc.es/OfertasProvider/findOferta/Fault/SoapInstanceNotFoundException")
    })
    public OfertaDto findOferta(
        @WebParam(name = "ofertaId", targetNamespace = "")
        Long ofertaId)
        throws SoapInstanceNotFoundException
    ;

    /**
     * 
     * @param emailUsuarioReserva
     * @param tarjetaCreditoReserva
     * @param ofertaId
     * @return
     *     returns java.lang.Long
     * @throws SoapInstanceNotFoundException
     * @throws SoapOfertaReservadaException
     * @throws SoapInputValidationException
     * @throws SoapTimeExpirationException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "reservarOferta", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.ReservarOferta")
    @ResponseWrapper(localName = "reservarOfertaResponse", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.ReservarOfertaResponse")
    @Action(input = "http://soap.ws.udc.es/OfertasProvider/reservarOfertaRequest", output = "http://soap.ws.udc.es/OfertasProvider/reservarOfertaResponse", fault = {
        @FaultAction(className = SoapInstanceNotFoundException.class, value = "http://soap.ws.udc.es/OfertasProvider/reservarOferta/Fault/SoapInstanceNotFoundException"),
        @FaultAction(className = SoapInputValidationException.class, value = "http://soap.ws.udc.es/OfertasProvider/reservarOferta/Fault/SoapInputValidationException"),
        @FaultAction(className = SoapOfertaReservadaException.class, value = "http://soap.ws.udc.es/OfertasProvider/reservarOferta/Fault/SoapOfertaReservadaException"),
        @FaultAction(className = SoapTimeExpirationException.class, value = "http://soap.ws.udc.es/OfertasProvider/reservarOferta/Fault/SoapTimeExpirationException")
    })
    public Long reservarOferta(
        @WebParam(name = "ofertaId", targetNamespace = "")
        Long ofertaId,
        @WebParam(name = "emailUsuarioReserva", targetNamespace = "")
        String emailUsuarioReserva,
        @WebParam(name = "tarjetaCreditoReserva", targetNamespace = "")
        String tarjetaCreditoReserva)
        throws SoapInputValidationException, SoapInstanceNotFoundException, SoapOfertaReservadaException, SoapTimeExpirationException
    ;

    /**
     * 
     * @param ofertaDto
     * @throws SoapInstanceNotFoundException
     * @throws SoapInputValidationException
     */
    @WebMethod
    @RequestWrapper(localName = "updateOferta", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.UpdateOferta")
    @ResponseWrapper(localName = "updateOfertaResponse", targetNamespace = "http://soap.ws.udc.es/", className = "es.udc.ws.app.client.service.soap.wsdl.UpdateOfertaResponse")
    @Action(input = "http://soap.ws.udc.es/OfertasProvider/updateOfertaRequest", output = "http://soap.ws.udc.es/OfertasProvider/updateOfertaResponse", fault = {
        @FaultAction(className = SoapInputValidationException.class, value = "http://soap.ws.udc.es/OfertasProvider/updateOferta/Fault/SoapInputValidationException"),
        @FaultAction(className = SoapInstanceNotFoundException.class, value = "http://soap.ws.udc.es/OfertasProvider/updateOferta/Fault/SoapInstanceNotFoundException")
    })
    public void updateOferta(
        @WebParam(name = "ofertaDto", targetNamespace = "")
        OfertaDto ofertaDto)
        throws SoapInputValidationException, SoapInstanceNotFoundException
    ;

}
