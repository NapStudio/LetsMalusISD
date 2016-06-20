package es.udc.ws.app.soapservice;

import java.util.Calendar;

public class SoapReservaExpirationExceptionInfo {

    private Long reservaId;
    private Calendar fechaExpiracion;

    public SoapReservaExpirationExceptionInfo() {
    }

    public SoapReservaExpirationExceptionInfo(Long reservaId, 
                                           Calendar fechaExpiracion) {
        this.reservaId = reservaId;
        this.fechaExpiracion = fechaExpiracion;
    }

    public Long getOfertaId() {
        return reservaId;
    }

    public Calendar getDataFin() {
        return fechaExpiracion;
    }

    public void setDataFin(Calendar fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public void setOfertaId(Long reservaId) {
        this.reservaId = reservaId;
    }    
    
}

