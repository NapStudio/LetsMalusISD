package es.udc.ws.app.model.reserva;

import es.udc.ws.util.configuration.ConfigurationParametersManager;

public class ReservaDAOFactory {
    private final static String CLASS_NAME_PARAMETER = "ReservaDAOFactory.className";
    private static ReservaDAO dao = null;

    private ReservaDAOFactory() {
    }

    @SuppressWarnings("rawtypes")
    private static ReservaDAO getInstance() {
        try {
            String daoClassName = ConfigurationParametersManager
                    .getParameter(CLASS_NAME_PARAMETER);
            Class daoClass = Class.forName(daoClassName);
            return (ReservaDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public synchronized static ReservaDAO getDao() {

        if (dao == null) {
            dao = getInstance();
        }
        return dao;

    }
}
