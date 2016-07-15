package es.udc.ws.app.soapservice;

import javax.xml.ws.WebFault;

@SuppressWarnings("serial")
@WebFault(name = "SoapOfertaReservadaException", targetNamespace = "http://soap.ws.udc.es/")
public class SoapOfertaReservadaException extends Exception {

	private SoapOfertaReservadaExceptionInfo faultInfo;

	protected SoapOfertaReservadaException(
			SoapOfertaReservadaExceptionInfo faultInfo) {
		this.faultInfo = faultInfo;
	}

	public SoapOfertaReservadaExceptionInfo getFaultInfo() {
		return faultInfo;
	}
}
