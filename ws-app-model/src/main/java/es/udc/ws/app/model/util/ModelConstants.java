package es.udc.ws.app.model.util;

public final class ModelConstants {
	
    public static final String OFERTA_DATA_SOURCE = "ws-app-ds";
    public static final int RESERVA_EXPIRATION_DAYS = 2;
    public static final int OFERTA_EXPIRATION_DAYS = 2;
    public static final String BASE_URL = "http://ws-app.udc.es/reserva/stream/";
    public static final short MAX_RUNTIME = 1000;
    public static final float MAX_PRICE = 1000;

    private ModelConstants() {
    }
}
