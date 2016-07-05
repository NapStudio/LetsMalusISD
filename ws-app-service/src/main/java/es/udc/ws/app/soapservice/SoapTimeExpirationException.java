package es.udc.ws.app.soapservice;

import javax.xml.ws.WebFault;

@SuppressWarnings("serial")
@WebFault(
    name="SoapTimeExpirationException",
    targetNamespace="http://soap.ws.udc.es/"
)
public class SoapTimeExpirationException extends Exception {

    private SoapTimeExpirationExceptionInfo faultInfo;

    protected SoapTimeExpirationException(
    		SoapTimeExpirationExceptionInfo faultInfo) {
        this.faultInfo = faultInfo;
    }

    public SoapTimeExpirationExceptionInfo getFaultInfo() {
        return faultInfo;
    }
}
