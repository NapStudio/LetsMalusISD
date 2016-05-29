package es.udc.ws.app.model.oferta;

import es.udc.ws.util.configuration.ConfigurationParametersManager;

public class OfertaDAOFactory {
	
    private final static String CLASS_NAME_PARAMETER = "OfertaDAOFactory.className";
    private static OfertaDAO dao = null;

    private OfertaDAOFactory() {
    }

    @SuppressWarnings("rawtypes")
    private static OfertaDAO getInstance() {
        try {
            String daoClassName = ConfigurationParametersManager
                    .getParameter(CLASS_NAME_PARAMETER);
            Class daoClass = Class.forName(daoClassName);
            return (OfertaDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public synchronized static OfertaDAO getDao() {

        if (dao == null) {
            dao = getInstance();
        }
        return dao;

    }

}
