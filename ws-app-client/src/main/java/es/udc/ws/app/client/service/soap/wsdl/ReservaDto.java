
package es.udc.ws.app.client.service.soap.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for reservaDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reservaDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="emailUsuarioReserva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoReserva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ofertaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="reservaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tarjetaCreditoReserva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservaDto", propOrder = {
    "emailUsuarioReserva",
    "estadoReserva",
    "ofertaId",
    "reservaId",
    "tarjetaCreditoReserva"
})
public class ReservaDto {

    protected String emailUsuarioReserva;
    protected String estadoReserva;
    protected Long ofertaId;
    protected Long reservaId;
    protected String tarjetaCreditoReserva;

    /**
     * Gets the value of the emailUsuarioReserva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailUsuarioReserva() {
        return emailUsuarioReserva;
    }

    /**
     * Sets the value of the emailUsuarioReserva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailUsuarioReserva(String value) {
        this.emailUsuarioReserva = value;
    }

    /**
     * Gets the value of the estadoReserva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoReserva() {
        return estadoReserva;
    }

    /**
     * Sets the value of the estadoReserva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoReserva(String value) {
        this.estadoReserva = value;
    }

    /**
     * Gets the value of the ofertaId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOfertaId() {
        return ofertaId;
    }

    /**
     * Sets the value of the ofertaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOfertaId(Long value) {
        this.ofertaId = value;
    }

    /**
     * Gets the value of the reservaId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getReservaId() {
        return reservaId;
    }

    /**
     * Sets the value of the reservaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setReservaId(Long value) {
        this.reservaId = value;
    }

    /**
     * Gets the value of the tarjetaCreditoReserva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarjetaCreditoReserva() {
        return tarjetaCreditoReserva;
    }

    /**
     * Sets the value of the tarjetaCreditoReserva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarjetaCreditoReserva(String value) {
        this.tarjetaCreditoReserva = value;
    }

}
