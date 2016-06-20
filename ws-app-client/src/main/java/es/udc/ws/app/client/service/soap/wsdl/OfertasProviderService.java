
package es.udc.ws.app.client.service.soap.wsdl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "OfertasProviderService", targetNamespace = "http://soap.ws.udc.es/", wsdlLocation = "file:/C:/software/ws-app-1.0.0/ws-app-service/target/generated-sources/wsdl/OfertasProviderService.wsdl")
public class OfertasProviderService
    extends Service
{

    private final static URL OFERTASPROVIDERSERVICE_WSDL_LOCATION;
    private final static WebServiceException OFERTASPROVIDERSERVICE_EXCEPTION;
    private final static QName OFERTASPROVIDERSERVICE_QNAME = new QName("http://soap.ws.udc.es/", "OfertasProviderService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/software/ws-app-1.0.0/ws-app-service/target/generated-sources/wsdl/OfertasProviderService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        OFERTASPROVIDERSERVICE_WSDL_LOCATION = url;
        OFERTASPROVIDERSERVICE_EXCEPTION = e;
    }

    public OfertasProviderService() {
        super(__getWsdlLocation(), OFERTASPROVIDERSERVICE_QNAME);
    }

    public OfertasProviderService(WebServiceFeature... features) {
        super(__getWsdlLocation(), OFERTASPROVIDERSERVICE_QNAME, features);
    }

    public OfertasProviderService(URL wsdlLocation) {
        super(wsdlLocation, OFERTASPROVIDERSERVICE_QNAME);
    }

    public OfertasProviderService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, OFERTASPROVIDERSERVICE_QNAME, features);
    }

    public OfertasProviderService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OfertasProviderService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns OfertasProvider
     */
    @WebEndpoint(name = "OfertasProviderPort")
    public OfertasProvider getOfertasProviderPort() {
        return super.getPort(new QName("http://soap.ws.udc.es/", "OfertasProviderPort"), OfertasProvider.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OfertasProvider
     */
    @WebEndpoint(name = "OfertasProviderPort")
    public OfertasProvider getOfertasProviderPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soap.ws.udc.es/", "OfertasProviderPort"), OfertasProvider.class, features);
    }

    private static URL __getWsdlLocation() {
        if (OFERTASPROVIDERSERVICE_EXCEPTION!= null) {
            throw OFERTASPROVIDERSERVICE_EXCEPTION;
        }
        return OFERTASPROVIDERSERVICE_WSDL_LOCATION;
    }

}
