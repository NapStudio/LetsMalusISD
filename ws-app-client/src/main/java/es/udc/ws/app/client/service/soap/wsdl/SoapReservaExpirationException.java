
package es.udc.ws.app.client.service.soap.wsdl;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "SoapReservaExpirationException", targetNamespace = "http://soap.ws.udc.es/")
public class SoapReservaExpirationException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private SoapReservaExpirationExceptionInfo faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public SoapReservaExpirationException(String message, SoapReservaExpirationExceptionInfo faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public SoapReservaExpirationException(String message, SoapReservaExpirationExceptionInfo faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: es.udc.ws.app.client.service.soap.wsdl.SoapReservaExpirationExceptionInfo
     */
    public SoapReservaExpirationExceptionInfo getFaultInfo() {
        return faultInfo;
    }

}