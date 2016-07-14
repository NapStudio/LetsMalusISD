chcp 65001 2>&1 & pause &
@echo comando1 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-addOffer 'Cena  para  dos' 'Cena con menú degustación para dos personas en el restaurante UDC' '01/08/2016 00:00' '31/12/2016 23:59' 80 40" & pause &
@echo comando2 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-addOffer 'Casa  rural' 'Habitación doble en la casa rural UDC' '01/12/2016 23:59' '31/12/2016 23:59' 100 70" & pause &
@echo comando3 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-addOffer 'Fisioterapia' 'Bono de dos sesiones de fisioterapia' '<now  +  2  minutos>' '30/06/2016 23:59' 60 30" & pause &
@echo comando4 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-updateOffer 2 'Casa rural' 'Habitación triple en la casa rural UDC' '01/12/2016 23:59' '31/12/2016 23:59' 150 100" & pause &
@echo comando5 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffer 2" & pause &
@echo comando6 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffer 3423" & pause &
@echo comando7 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 1 'usr1@udc.es' 1234567890123456" & pause &
@echo comando8 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 2 'usr1@udc.es' 1234567890123456" & pause &
@echo comando9 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 1 'usr2@udc.es' 1111222233334444" & pause &
@echo comando10 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 2 'usr2@udc.es' 1111222233334444" & pause &
@echo comando11 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 2 'usr2@udc.es' 1111222233334444" & pause &
@echo comando12 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-updateOffer 2 'Casa rural' 'Habitación triple en la casa rural UDC' '01/06/2016 23:59' '30/10/2016 23:59' 50 100" & pause &
@echo comando13 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffer 2" & pause &
@echo comando14 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-claimReservation 2" & pause &
@echo comando15 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-invalidateOffer 2" & pause &
@echo comando16 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-invalidateOffer 2" & pause &
@echo comando17 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-claimReservation 4" & pause &
@echo comando18 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOfferReservations 2" & pause &
@echo comando19 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findUserReservations 'usr2@udc.es' válida" & pause &
@echo comando20 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findUserReservations 'usr2@udc.es' todas" & pause &
@echo comando21 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findUserOffers 'usr1@udc.es'" & pause &
@echo comando22 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffers 'DoS de'" & pause &
@echo comando23 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffers 'udc'" & pause &
@echo comando24 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 3 'usr2@udc.es' 1111222233334444" & pause &
@echo comando25 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-removeOffer 1" & pause &
@echo comando26 & pause &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-removeOffer 3" & pause &
findstr /v "INFO WARNING" outputComandos.log>outputComandosWO.log