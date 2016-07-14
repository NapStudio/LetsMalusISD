chcp 65001 2>&1 &
@echo comando1 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-addOffer 'Cena  para  dos' 'Cena con menú degustación para dos personas en el restaurante UDC' '01/08/2016 00:00' '31/12/2016 23:59' 80 40" 1>>outputComandos.log 2>&1 &
@echo comando2 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-addOffer 'Casa  rural' 'Habitación doble en la casa rural UDC' '01/12/2016 23:59' '31/12/2016 23:59' 100 70" 1>>outputComandos.log 2>&1 &
@echo comando3 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-addOffer 'Fisioterapia' 'Bono de dos sesiones de fisioterapia' '<now  +  2  minutos>' '30/06/2016 23:59' 60 30" 1>>outputComandos.log 2>&1 &
@echo comando4 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-updateOffer 2 'Casa rural' 'Habitación triple en la casa rural UDC' '01/12/2016 23:59' '31/12/2016 23:59' 150 100" 1>>outputComandos.log 2>&1 &
@echo comando5 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffer 2" 1>>outputComandos.log 2>&1 &
@echo comando6 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffer 3423" 1>>outputComandos.log 2>&1 &
@echo comando7 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 1 'usr1@udc.es' 1234567890123456" 1>>outputComandos.log 2>&1 &
@echo comando8 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 2 'usr1@udc.es' 1234567890123456" 1>>outputComandos.log 2>&1 &
@echo comando9 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 1 'usr2@udc.es' 1111222233334444" 1>>outputComandos.log 2>&1 &
@echo comando10 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 2 'usr2@udc.es' 1111222233334444" 1>>outputComandos.log 2>&1 &
@echo comando11 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 2 'usr2@udc.es' 1111222233334444" 1>>outputComandos.log 2>&1 &
@echo comando12 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-updateOffer 2 'Casa rural' 'Habitación triple en la casa rural UDC' '01/06/2016 23:59' '30/10/2016 23:59' 50 100" 1>>outputComandos.log 2>&1 &
@echo comando13 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffer 2" 1>>outputComandos.log 2>&1 &
@echo comando14 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-claimReservation 2" 1>>outputComandos.log 2>&1 &
@echo comando15 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-invalidateOffer 2" 1>>outputComandos.log 2>&1 &
@echo comando16 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-invalidateOffer 2" 1>>outputComandos.log 2>&1 &
@echo comando17 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-claimReservation 4" 1>>outputComandos.log 2>&1 &
@echo comando18 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOfferReservations 2" 1>>outputComandos.log 2>&1 &
@echo comando19 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findUserReservations 'usr2@udc.es' válida" 1>>outputComandos.log 2>&1 &
@echo comando20 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findUserReservations 'usr2@udc.es' todas" 1>>outputComandos.log 2>&1 &
@echo comando21 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findUserOffers 'usr1@udc.es'" 1>>outputComandos.log 2>&1 &
@echo comando22 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffers 'DoS de'" 1>>outputComandos.log 2>&1 &
@echo comando23 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-findOffers 'udc'" 1>>outputComandos.log 2>&1 &
@echo comando24 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-reserveOffer 3 'usr2@udc.es' 1111222233334444" 1>>outputComandos.log 2>&1 &
@echo comando25 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-removeOffer 1" 1>>outputComandos.log 2>&1 &
@echo comando26 1>>outputComandos.log 2>&1 &
call mvn exec:java -Dexec.mainClass="es.udc.ws.app.client.ui.OfertaServiceClient" -Dexec.args="-removeOffer 3" 1>>outputComandos.log 2>&1 &
findstr /v "INFO WARNING" outputComandos.log>outputComandosWO.log