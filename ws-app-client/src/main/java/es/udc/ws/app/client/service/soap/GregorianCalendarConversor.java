package es.udc.ws.app.client.service.soap;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class GregorianCalendarConversor {

	public static XMLGregorianCalendar toXMLGregorianCalendar(Calendar c) {
		if (c == null) {
			return null;
		} else {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(c.getTimeInMillis());
			XMLGregorianCalendar xc = null;
			try {
				xc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			} catch (DatatypeConfigurationException e) {
				return null;
			}
			return xc;
		}
	}

	public static Calendar toCalendar(XMLGregorianCalendar xc) {
		if (xc == null) {
			return null;
		} else {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(xc.toGregorianCalendar().getTimeInMillis());
			return c;
		}
	}

}
