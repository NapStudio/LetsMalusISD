chcp 65001&
@echo comando1>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-addOffer 'Cena  para  dos' 'Cena con menú degustación para dos personas en el restaurante UDC' '01/08/2016 00:00' '31/12/2016 23:59' 80 40">>outputComandos.log&
@echo comando2>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-addOffer 'Casa  rural' 'Habitación doble en la casa rural UDC' '01/12/2016 23:59' '31/12/2016 23:59' 100 70">>outputComandos.log&
@echo comando3>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-addOffer 'Fisioterapia' 'Bono de dos sesiones de fisioterapia' '<now  +  2  minutos>' '30/06/2016 23:59' 60 30">>outputComandos.log&
@echo comando4>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-updateOffer 2 'Casa rural' 'Habitación triple en la casa rural UDC' '01/12/2016 23:59' '31/12/2016 23:59' 150 100">>outputComandos.log&
@echo comando5>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffer 2">>outputComandos.log&
@echo comando6>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffer 3423">>outputComandos.log&
@echo comando7>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 1 'usr1@udc.es' 1234567890123456">>outputComandos.log&
@echo comando8>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 2 'usr1@udc.es' 1234567890123456">>outputComandos.log&
@echo comando9>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 1 'usr2@udc.es' 1111222233334444">>outputComandos.log&
@echo comando10>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 2 'usr2@udc.es' 1111222233334444">>outputComandos.log&
@echo comando11>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 2 'usr2@udc.es' 1111222233334444">>outputComandos.log&
@echo comando12>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-updateOffer 2 'Casa rural' 'Habitación triple en la casa rural UDC' '01/06/2016 23:59' '30/10/2016 23:59' 50 100">>outputComandos.log&
@echo comando13>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffer 2">>outputComandos.log&
@echo comando14>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-claimReservation 2">>outputComandos.log&
@echo comando15>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-invalidateOffer 2">>outputComandos.log&
@echo comando16>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-invalidateOffer 2">>outputComandos.log&
@echo comando17>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-claimReservation 4">>outputComandos.log&
@echo comando18>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOfferReservations 2">>outputComandos.log&
@echo comando19>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findUserReservations 'usr2@udc.es' válida">>outputComandos.log&
@echo comando20>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findUserReservations 'usr2@udc.es' todas">>outputComandos.log&
@echo comando21>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findUserOffers 'usr1@udc.es'">>outputComandos.log&
@echo comando22>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffers 'DoS de'">>outputComandos.log&
@echo comando23>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffers 'udc'">>outputComandos.log&
@echo comando24>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 3 'usr2@udc.es' 1111222233334444">>outputComandos.log&
@echo comando25>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-removeOffer 1">>outputComandos.log&
@echo comando26>>outputComandos.log&
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-removeOffer 3">>outputComandos.log&
findstr /v "INFO WARNING" outputComandos.log>outputComandosWO.log