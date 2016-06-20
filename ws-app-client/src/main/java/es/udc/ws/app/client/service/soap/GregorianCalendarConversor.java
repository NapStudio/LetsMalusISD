package es.udc.ws.app.client.service.soap;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class GregorianCalendarConversor {
	
	
	public static XMLGregorianCalendar toXMLGregorianCalendar(Calendar c)
			throws DatatypeConfigurationException {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(c.getTimeInMillis());
		XMLGregorianCalendar xc = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(gc);
		return xc;
	}

	public static Calendar toCalendar(XMLGregorianCalendar xc)
			throws DatatypeConfigurationException {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(xc.toGregorianCalendar().getTimeInMillis());
		return c;
	}

}
