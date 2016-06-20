package es.udc.ws.app.soapservice;

import javax.xml.ws.WebFault;

@SuppressWarnings("serial")
@WebFault(
    name="SoapBadStateReservaException",
    targetNamespace="http://soap.ws.udc.es/"
)
public class SoapBadStateReservaException extends Exception {

    private SoapBadStateReservaExceptionInfo faultInfo;  
    
    protected SoapBadStateReservaException(
    		SoapBadStateReservaExceptionInfo faultInfo) {
        this.faultInfo = faultInfo;
    }

    public SoapBadStateReservaExceptionInfo getFaultInfo() {
        return faultInfo;
    }
}
