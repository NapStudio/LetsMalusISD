
package es.udc.ws.app.client.service.soap.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ofertaDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ofertaDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comisionOferta" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="descripcionOferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoOferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaLimiteOferta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaLimiteReserva" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="nombreOferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ofertaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="precioDescontadoOferta" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="precioRealOferta" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ofertaDto", propOrder = {
    "comisionOferta",
    "descripcionOferta",
    "estadoOferta",
    "fechaLimiteOferta",
    "fechaLimiteReserva",
    "nombreOferta",
    "ofertaId",
    "precioDescontadoOferta",
    "precioRealOferta"
})
public class OfertaDto {

    protected float comisionOferta;
    protected String descripcionOferta;
    protected String estadoOferta;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaLimiteOferta;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaLimiteReserva;
    protected String nombreOferta;
    protected Long ofertaId;
    protected float precioDescontadoOferta;
    protected float precioRealOferta;

    /**
     * Gets the value of the comisionOferta property.
     * 
     */
    public float getComisionOferta() {
        return comisionOferta;
    }

    /**
     * Sets the value of the comisionOferta property.
     * 
     */
    public void setComisionOferta(float value) {
        this.comisionOferta = value;
    }

    /**
     * Gets the value of the descripcionOferta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionOferta() {
        return descripcionOferta;
    }

    /**
     * Sets the value of the descripcionOferta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionOferta(String value) {
        this.descripcionOferta = value;
    }

    /**
     * Gets the value of the estadoOferta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoOferta() {
        return estadoOferta;
    }

    /**
     * Sets the value of the estadoOferta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoOferta(String value) {
        this.estadoOferta = value;
    }

    /**
     * Gets the value of the fechaLimiteOferta property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaLimiteOferta() {
        return fechaLimiteOferta;
    }

    /**
     * Sets the value of the fechaLimiteOferta property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaLimiteOferta(XMLGregorianCalendar value) {
        this.fechaLimiteOferta = value;
    }

    /**
     * Gets the value of the fechaLimiteReserva property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaLimiteReserva() {
        return fechaLimiteReserva;
    }

    /**
     * Sets the value of the fechaLimiteReserva property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaLimiteReserva(XMLGregorianCalendar value) {
        this.fechaLimiteReserva = value;
    }

    /**
     * Gets the value of the nombreOferta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreOferta() {
        return nombreOferta;
    }

    /**
     * Sets the value of the nombreOferta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreOferta(String value) {
        this.nombreOferta = value;
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
     * Gets the value of the precioDescontadoOferta property.
     * 
     */
    public float getPrecioDescontadoOferta() {
        return precioDescontadoOferta;
    }

    /**
     * Sets the value of the precioDescontadoOferta property.
     * 
     */
    public void setPrecioDescontadoOferta(float value) {
        this.precioDescontadoOferta = value;
    }

    /**
     * Gets the value of the precioRealOferta property.
     * 
     */
    public float getPrecioRealOferta() {
        return precioRealOferta;
    }

    /**
     * Sets the value of the precioRealOferta property.
     * 
     */
    public void setPrecioRealOferta(float value) {
        this.precioRealOferta = value;
    }

}
