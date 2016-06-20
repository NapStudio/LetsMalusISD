
package es.udc.ws.app.client.service.soap.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addOferta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addOferta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ofertaDto" type="{http://soap.ws.udc.es/}ofertaDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addOferta", propOrder = {
    "ofertaDto"
})
public class AddOferta {

    protected OfertaDto ofertaDto;

    /**
     * Gets the value of the ofertaDto property.
     * 
     * @return
     *     possible object is
     *     {@link OfertaDto }
     *     
     */
    public OfertaDto getOfertaDto() {
        return ofertaDto;
    }

    /**
     * Sets the value of the ofertaDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfertaDto }
     *     
     */
    public void setOfertaDto(OfertaDto value) {
        this.ofertaDto = value;
    }

}
