
package es.udc.ws.app.client.service.soap.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.udc.ws.app.client.service.soap.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindOfertas_QNAME = new QName("http://soap.ws.udc.es/", "findOfertas");
    private final static QName _SoapOfertaReservadaException_QNAME = new QName("http://soap.ws.udc.es/", "SoapOfertaReservadaException");
    private final static QName _RemoveOferta_QNAME = new QName("http://soap.ws.udc.es/", "removeOferta");
    private final static QName _FindReservasByOfertaResponse_QNAME = new QName("http://soap.ws.udc.es/", "findReservasByOfertaResponse");
    private final static QName _InvalidarOfertaResponse_QNAME = new QName("http://soap.ws.udc.es/", "invalidarOfertaResponse");
    private final static QName _AddOferta_QNAME = new QName("http://soap.ws.udc.es/", "addOferta");
    private final static QName _FindOfertasResponse_QNAME = new QName("http://soap.ws.udc.es/", "findOfertasResponse");
    private final static QName _FindReservasByUsuarioResponse_QNAME = new QName("http://soap.ws.udc.es/", "findReservasByUsuarioResponse");
    private final static QName _SoapInstanceNotFoundException_QNAME = new QName("http://soap.ws.udc.es/", "SoapInstanceNotFoundException");
    private final static QName _RemoveOfertaResponse_QNAME = new QName("http://soap.ws.udc.es/", "removeOfertaResponse");
    private final static QName _ReclamarOferta_QNAME = new QName("http://soap.ws.udc.es/", "reclamarOferta");
    private final static QName _ReservarOfertaResponse_QNAME = new QName("http://soap.ws.udc.es/", "reservarOfertaResponse");
    private final static QName _FindReserva_QNAME = new QName("http://soap.ws.udc.es/", "findReserva");
    private final static QName _SoapBadStateReservaException_QNAME = new QName("http://soap.ws.udc.es/", "SoapBadStateReservaException");
    private final static QName _FindReservasByOferta_QNAME = new QName("http://soap.ws.udc.es/", "findReservasByOferta");
    private final static QName _UpdateOferta_QNAME = new QName("http://soap.ws.udc.es/", "updateOferta");
    private final static QName _InvalidarOferta_QNAME = new QName("http://soap.ws.udc.es/", "invalidarOferta");
    private final static QName _AddOfertaResponse_QNAME = new QName("http://soap.ws.udc.es/", "addOfertaResponse");
    private final static QName _ReservarOferta_QNAME = new QName("http://soap.ws.udc.es/", "reservarOferta");
    private final static QName _FindReservaResponse_QNAME = new QName("http://soap.ws.udc.es/", "findReservaResponse");
    private final static QName _FindOfertaResponse_QNAME = new QName("http://soap.ws.udc.es/", "findOfertaResponse");
    private final static QName _UpdateOfertaResponse_QNAME = new QName("http://soap.ws.udc.es/", "updateOfertaResponse");
    private final static QName _ReclamarOfertaResponse_QNAME = new QName("http://soap.ws.udc.es/", "reclamarOfertaResponse");
    private final static QName _SoapTimeExpirationException_QNAME = new QName("http://soap.ws.udc.es/", "SoapTimeExpirationException");
    private final static QName _SoapInputValidationException_QNAME = new QName("http://soap.ws.udc.es/", "SoapInputValidationException");
    private final static QName _FindOferta_QNAME = new QName("http://soap.ws.udc.es/", "findOferta");
    private final static QName _FindReservasByUsuario_QNAME = new QName("http://soap.ws.udc.es/", "findReservasByUsuario");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.udc.ws.app.client.service.soap.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RemoveOfertaResponse }
     * 
     */
    public RemoveOfertaResponse createRemoveOfertaResponse() {
        return new RemoveOfertaResponse();
    }

    /**
     * Create an instance of {@link SoapInstanceNotFoundExceptionInfo }
     * 
     */
    public SoapInstanceNotFoundExceptionInfo createSoapInstanceNotFoundExceptionInfo() {
        return new SoapInstanceNotFoundExceptionInfo();
    }

    /**
     * Create an instance of {@link ReclamarOferta }
     * 
     */
    public ReclamarOferta createReclamarOferta() {
        return new ReclamarOferta();
    }

    /**
     * Create an instance of {@link ReservarOfertaResponse }
     * 
     */
    public ReservarOfertaResponse createReservarOfertaResponse() {
        return new ReservarOfertaResponse();
    }

    /**
     * Create an instance of {@link SoapBadStateReservaExceptionInfo }
     * 
     */
    public SoapBadStateReservaExceptionInfo createSoapBadStateReservaExceptionInfo() {
        return new SoapBadStateReservaExceptionInfo();
    }

    /**
     * Create an instance of {@link FindReserva }
     * 
     */
    public FindReserva createFindReserva() {
        return new FindReserva();
    }

    /**
     * Create an instance of {@link FindReservasByOferta }
     * 
     */
    public FindReservasByOferta createFindReservasByOferta() {
        return new FindReservasByOferta();
    }

    /**
     * Create an instance of {@link UpdateOferta }
     * 
     */
    public UpdateOferta createUpdateOferta() {
        return new UpdateOferta();
    }

    /**
     * Create an instance of {@link FindOfertas }
     * 
     */
    public FindOfertas createFindOfertas() {
        return new FindOfertas();
    }

    /**
     * Create an instance of {@link SoapOfertaReservadaExceptionInfo }
     * 
     */
    public SoapOfertaReservadaExceptionInfo createSoapOfertaReservadaExceptionInfo() {
        return new SoapOfertaReservadaExceptionInfo();
    }

    /**
     * Create an instance of {@link RemoveOferta }
     * 
     */
    public RemoveOferta createRemoveOferta() {
        return new RemoveOferta();
    }

    /**
     * Create an instance of {@link AddOferta }
     * 
     */
    public AddOferta createAddOferta() {
        return new AddOferta();
    }

    /**
     * Create an instance of {@link FindOfertasResponse }
     * 
     */
    public FindOfertasResponse createFindOfertasResponse() {
        return new FindOfertasResponse();
    }

    /**
     * Create an instance of {@link FindReservasByUsuarioResponse }
     * 
     */
    public FindReservasByUsuarioResponse createFindReservasByUsuarioResponse() {
        return new FindReservasByUsuarioResponse();
    }

    /**
     * Create an instance of {@link FindReservasByOfertaResponse }
     * 
     */
    public FindReservasByOfertaResponse createFindReservasByOfertaResponse() {
        return new FindReservasByOfertaResponse();
    }

    /**
     * Create an instance of {@link InvalidarOfertaResponse }
     * 
     */
    public InvalidarOfertaResponse createInvalidarOfertaResponse() {
        return new InvalidarOfertaResponse();
    }

    /**
     * Create an instance of {@link FindOfertaResponse }
     * 
     */
    public FindOfertaResponse createFindOfertaResponse() {
        return new FindOfertaResponse();
    }

    /**
     * Create an instance of {@link UpdateOfertaResponse }
     * 
     */
    public UpdateOfertaResponse createUpdateOfertaResponse() {
        return new UpdateOfertaResponse();
    }

    /**
     * Create an instance of {@link ReclamarOfertaResponse }
     * 
     */
    public ReclamarOfertaResponse createReclamarOfertaResponse() {
        return new ReclamarOfertaResponse();
    }

    /**
     * Create an instance of {@link SoapTimeExpirationExceptionInfo }
     * 
     */
    public SoapTimeExpirationExceptionInfo createSoapTimeExpirationExceptionInfo() {
        return new SoapTimeExpirationExceptionInfo();
    }

    /**
     * Create an instance of {@link FindOferta }
     * 
     */
    public FindOferta createFindOferta() {
        return new FindOferta();
    }

    /**
     * Create an instance of {@link FindReservasByUsuario }
     * 
     */
    public FindReservasByUsuario createFindReservasByUsuario() {
        return new FindReservasByUsuario();
    }

    /**
     * Create an instance of {@link InvalidarOferta }
     * 
     */
    public InvalidarOferta createInvalidarOferta() {
        return new InvalidarOferta();
    }

    /**
     * Create an instance of {@link AddOfertaResponse }
     * 
     */
    public AddOfertaResponse createAddOfertaResponse() {
        return new AddOfertaResponse();
    }

    /**
     * Create an instance of {@link ReservarOferta }
     * 
     */
    public ReservarOferta createReservarOferta() {
        return new ReservarOferta();
    }

    /**
     * Create an instance of {@link FindReservaResponse }
     * 
     */
    public FindReservaResponse createFindReservaResponse() {
        return new FindReservaResponse();
    }

    /**
     * Create an instance of {@link ReservaDto }
     * 
     */
    public ReservaDto createReservaDto() {
        return new ReservaDto();
    }

    /**
     * Create an instance of {@link OfertaDto }
     * 
     */
    public OfertaDto createOfertaDto() {
        return new OfertaDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOfertas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findOfertas")
    public JAXBElement<FindOfertas> createFindOfertas(FindOfertas value) {
        return new JAXBElement<FindOfertas>(_FindOfertas_QNAME, FindOfertas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoapOfertaReservadaExceptionInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "SoapOfertaReservadaException")
    public JAXBElement<SoapOfertaReservadaExceptionInfo> createSoapOfertaReservadaException(SoapOfertaReservadaExceptionInfo value) {
        return new JAXBElement<SoapOfertaReservadaExceptionInfo>(_SoapOfertaReservadaException_QNAME, SoapOfertaReservadaExceptionInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveOferta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "removeOferta")
    public JAXBElement<RemoveOferta> createRemoveOferta(RemoveOferta value) {
        return new JAXBElement<RemoveOferta>(_RemoveOferta_QNAME, RemoveOferta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindReservasByOfertaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findReservasByOfertaResponse")
    public JAXBElement<FindReservasByOfertaResponse> createFindReservasByOfertaResponse(FindReservasByOfertaResponse value) {
        return new JAXBElement<FindReservasByOfertaResponse>(_FindReservasByOfertaResponse_QNAME, FindReservasByOfertaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidarOfertaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "invalidarOfertaResponse")
    public JAXBElement<InvalidarOfertaResponse> createInvalidarOfertaResponse(InvalidarOfertaResponse value) {
        return new JAXBElement<InvalidarOfertaResponse>(_InvalidarOfertaResponse_QNAME, InvalidarOfertaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddOferta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "addOferta")
    public JAXBElement<AddOferta> createAddOferta(AddOferta value) {
        return new JAXBElement<AddOferta>(_AddOferta_QNAME, AddOferta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOfertasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findOfertasResponse")
    public JAXBElement<FindOfertasResponse> createFindOfertasResponse(FindOfertasResponse value) {
        return new JAXBElement<FindOfertasResponse>(_FindOfertasResponse_QNAME, FindOfertasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindReservasByUsuarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findReservasByUsuarioResponse")
    public JAXBElement<FindReservasByUsuarioResponse> createFindReservasByUsuarioResponse(FindReservasByUsuarioResponse value) {
        return new JAXBElement<FindReservasByUsuarioResponse>(_FindReservasByUsuarioResponse_QNAME, FindReservasByUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoapInstanceNotFoundExceptionInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "SoapInstanceNotFoundException")
    public JAXBElement<SoapInstanceNotFoundExceptionInfo> createSoapInstanceNotFoundException(SoapInstanceNotFoundExceptionInfo value) {
        return new JAXBElement<SoapInstanceNotFoundExceptionInfo>(_SoapInstanceNotFoundException_QNAME, SoapInstanceNotFoundExceptionInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveOfertaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "removeOfertaResponse")
    public JAXBElement<RemoveOfertaResponse> createRemoveOfertaResponse(RemoveOfertaResponse value) {
        return new JAXBElement<RemoveOfertaResponse>(_RemoveOfertaResponse_QNAME, RemoveOfertaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReclamarOferta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "reclamarOferta")
    public JAXBElement<ReclamarOferta> createReclamarOferta(ReclamarOferta value) {
        return new JAXBElement<ReclamarOferta>(_ReclamarOferta_QNAME, ReclamarOferta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReservarOfertaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "reservarOfertaResponse")
    public JAXBElement<ReservarOfertaResponse> createReservarOfertaResponse(ReservarOfertaResponse value) {
        return new JAXBElement<ReservarOfertaResponse>(_ReservarOfertaResponse_QNAME, ReservarOfertaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindReserva }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findReserva")
    public JAXBElement<FindReserva> createFindReserva(FindReserva value) {
        return new JAXBElement<FindReserva>(_FindReserva_QNAME, FindReserva.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoapBadStateReservaExceptionInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "SoapBadStateReservaException")
    public JAXBElement<SoapBadStateReservaExceptionInfo> createSoapBadStateReservaException(SoapBadStateReservaExceptionInfo value) {
        return new JAXBElement<SoapBadStateReservaExceptionInfo>(_SoapBadStateReservaException_QNAME, SoapBadStateReservaExceptionInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindReservasByOferta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findReservasByOferta")
    public JAXBElement<FindReservasByOferta> createFindReservasByOferta(FindReservasByOferta value) {
        return new JAXBElement<FindReservasByOferta>(_FindReservasByOferta_QNAME, FindReservasByOferta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateOferta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "updateOferta")
    public JAXBElement<UpdateOferta> createUpdateOferta(UpdateOferta value) {
        return new JAXBElement<UpdateOferta>(_UpdateOferta_QNAME, UpdateOferta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidarOferta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "invalidarOferta")
    public JAXBElement<InvalidarOferta> createInvalidarOferta(InvalidarOferta value) {
        return new JAXBElement<InvalidarOferta>(_InvalidarOferta_QNAME, InvalidarOferta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddOfertaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "addOfertaResponse")
    public JAXBElement<AddOfertaResponse> createAddOfertaResponse(AddOfertaResponse value) {
        return new JAXBElement<AddOfertaResponse>(_AddOfertaResponse_QNAME, AddOfertaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReservarOferta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "reservarOferta")
    public JAXBElement<ReservarOferta> createReservarOferta(ReservarOferta value) {
        return new JAXBElement<ReservarOferta>(_ReservarOferta_QNAME, ReservarOferta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindReservaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findReservaResponse")
    public JAXBElement<FindReservaResponse> createFindReservaResponse(FindReservaResponse value) {
        return new JAXBElement<FindReservaResponse>(_FindReservaResponse_QNAME, FindReservaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOfertaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findOfertaResponse")
    public JAXBElement<FindOfertaResponse> createFindOfertaResponse(FindOfertaResponse value) {
        return new JAXBElement<FindOfertaResponse>(_FindOfertaResponse_QNAME, FindOfertaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateOfertaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "updateOfertaResponse")
    public JAXBElement<UpdateOfertaResponse> createUpdateOfertaResponse(UpdateOfertaResponse value) {
        return new JAXBElement<UpdateOfertaResponse>(_UpdateOfertaResponse_QNAME, UpdateOfertaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReclamarOfertaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "reclamarOfertaResponse")
    public JAXBElement<ReclamarOfertaResponse> createReclamarOfertaResponse(ReclamarOfertaResponse value) {
        return new JAXBElement<ReclamarOfertaResponse>(_ReclamarOfertaResponse_QNAME, ReclamarOfertaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoapTimeExpirationExceptionInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "SoapTimeExpirationException")
    public JAXBElement<SoapTimeExpirationExceptionInfo> createSoapTimeExpirationException(SoapTimeExpirationExceptionInfo value) {
        return new JAXBElement<SoapTimeExpirationExceptionInfo>(_SoapTimeExpirationException_QNAME, SoapTimeExpirationExceptionInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "SoapInputValidationException")
    public JAXBElement<String> createSoapInputValidationException(String value) {
        return new JAXBElement<String>(_SoapInputValidationException_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOferta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findOferta")
    public JAXBElement<FindOferta> createFindOferta(FindOferta value) {
        return new JAXBElement<FindOferta>(_FindOferta_QNAME, FindOferta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindReservasByUsuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ws.udc.es/", name = "findReservasByUsuario")
    public JAXBElement<FindReservasByUsuario> createFindReservasByUsuario(FindReservasByUsuario value) {
        return new JAXBElement<FindReservasByUsuario>(_FindReservasByUsuario_QNAME, FindReservasByUsuario.class, null, value);
    }

}
