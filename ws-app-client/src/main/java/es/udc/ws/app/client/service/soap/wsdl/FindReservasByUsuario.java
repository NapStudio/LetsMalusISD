
package es.udc.ws.app.client.service.soap.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for findReservasByUsuario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findReservasByUsuario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="emailUsuarioReserva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoReserva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findReservasByUsuario", propOrder = {
    "emailUsuarioReserva",
    "estadoReserva"
})
public class FindReservasByUsuario {

    protected String emailUsuarioReserva;
    protected String estadoReserva;

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

}
