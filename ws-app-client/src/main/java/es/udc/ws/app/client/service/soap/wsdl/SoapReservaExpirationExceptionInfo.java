package es.udc.ws.app.client.service.soap.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for soapReservaExpirationExceptionInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="soapReservaExpirationExceptionInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataFin" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ofertaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "soapReservaExpirationExceptionInfo", propOrder = { "dataFin",
		"ofertaId" })
public class SoapReservaExpirationExceptionInfo {

	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar dataFin;
	protected Long ofertaId;

	/**
	 * Gets the value of the dataFin property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getDataFin() {
		return dataFin;
	}

	/**
	 * Sets the value of the dataFin property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setDataFin(XMLGregorianCalendar value) {
		this.dataFin = value;
	}

	/**
	 * Gets the value of the ofertaId property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getOfertaId() {
		return ofertaId;
	}

	/**
	 * Sets the value of the ofertaId property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setOfertaId(Long value) {
		this.ofertaId = value;
	}

}
