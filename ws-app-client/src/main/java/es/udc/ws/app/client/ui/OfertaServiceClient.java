package es.udc.ws.app.client.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import es.udc.ws.app.client.service.ClientOfertaService;
import es.udc.ws.app.client.service.ClientOfertaServiceFactory;
import es.udc.ws.app.dto.OfertaDto;
import es.udc.ws.app.dto.ReservaDto;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class OfertaServiceClient {
	public static void main(String[] args) {

		if (args.length == 0) {
			printUsageAndExit();
		}
		ClientOfertaService clientOfertaService = ClientOfertaServiceFactory
				.getService();
		if ("-a".equalsIgnoreCase(args[0])
				|| "-addOffer".equalsIgnoreCase(args[0])) {
			System.out.println("Creating Oferta...");
			validateArgs(args, 8, new int[] { 6, 8 });
			System.out.println("Args checked...");

			// [add] OfertaServiceClient -a <nombre> <descripcion>
			// <fechaLimiteOferta> <fechaLimiteReserva>
			// <priceReal> <priceDescontado> <comision>
			// estado al crear es siempre algoo 

			// public OfertaDto(Long ofertaId, String nombreOferta,
			// String descripcionOferta, String estadoOferta,
			// float precioRealOferta, float precioDescontadoOferta,
			// float comisionOferta, Calendar fechaLimiteOferta,
			// Calendar fechaLimiteReserva)
			try {

				Calendar fechaLimiteOferta = new GregorianCalendar();
				Calendar fechaLimiteReserva = new GregorianCalendar();

				System.out.print("fecha a parsear : " + args[3].toString());
				if (args[3].startsWith("<")) {
					String[] minutos = args[3].split(" ");
					int minsToAdd = Integer.valueOf(minutos[4]);
					fechaLimiteOferta.add(Calendar.MINUTE, minsToAdd);
					System.out.print("fecha parseada ,; " + fechaLimiteOferta);
				} else if (!args[3].equals(null) || !args[3].isEmpty()) {
					System.out.print("fecha a parsear : " + args[3].toString());
					SimpleDateFormat dataini = new SimpleDateFormat(
							"dd/MM/yyyy HH:mm");
					Date dini = dataini.parse(args[3]);
					System.out.print("fecha parseada ,; " + dini);
					fechaLimiteOferta.setTime(dini);
				}

				if (args[4].startsWith("<")) {
					String[] minutos = args[4].split(" ");
					int minsToAdd = Integer.valueOf(minutos[4]);
					fechaLimiteReserva.add(Calendar.MINUTE, minsToAdd);
					System.out.print("fecha parseada ,; " + fechaLimiteReserva);
				} else if (!args[4].equals(null) || !args[4].isEmpty()) {
					System.out.print("fecha a parsear : " + args[4].toString());
					SimpleDateFormat datafin = new SimpleDateFormat(
							"dd/MM/yyyy HH:mm");
					Date dfin = datafin.parse(args[4]);
					System.out.print("fecha parseada ,; " + dfin);
					fechaLimiteReserva.setTime(dfin);
				}
				float comision = 0;
				System.out.println(args.length);
				if (args.length > 7) {
					comision = Float.valueOf(args[7]);
				}

				Long ofertaId = clientOfertaService.addOferta(new OfertaDto(
						null, args[1], args[2], "válida", Float
								.valueOf(args[5]), Float.valueOf(args[6]),
						comision, fechaLimiteOferta, fechaLimiteReserva,0));

				System.out.println("Oferta " + ofertaId
						+ " created sucessfully");

			} catch (NumberFormatException | InputValidationException ex) {
				ex.printStackTrace(System.err);
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-r".equalsIgnoreCase(args[0])|| "-removeOffer".equalsIgnoreCase(args[0])) {
			validateArgs(args, 2, new int[] { 1 });

			// [remove] OfertaServiceClient -r <ofertaId>

			try {
				clientOfertaService.removeOferta(Long.parseLong(args[1]));

				System.out.println("Oferta with id " + args[1]
						+ " removed sucessfully");

			} catch (NumberFormatException | InstanceNotFoundException ex) {
				ex.printStackTrace(System.err);
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-u".equalsIgnoreCase(args[0])
				|| "-updateOffer".equalsIgnoreCase(args[0])) {
			validateArgs(args, 8, new int[] { 1, 3, 5 });

			// -updateOffer <offerId2> 'Casa rural'
			// 'Habitación triple en la casa rural UDC' '01/12/2016 23:59'
			// '31/12/2016 23:59' 150 100 [20]"

			// [update] OfertaServiceClient -u <offerId> <nombre> <descripcion>
			// <fechaLimiteOferta> <fechaLimiteReserva>
			// <priceReal> <priceDescontado> <comision>

			try {

				Calendar fechaLimiteOferta = new GregorianCalendar();
				Calendar fechaLimiteReserva = new GregorianCalendar();
				String argsFechaLimOferta = args[4];
				String argsFechaLimReserva = args[5];

				System.out.print("fecha a parsear : "
						+ argsFechaLimOferta.toString());
				if (argsFechaLimOferta.startsWith("<")) {
					String[] minutos = argsFechaLimOferta.split(" ");
					int minsToAdd = Integer.valueOf(minutos[4]);
					fechaLimiteOferta.add(Calendar.MINUTE, minsToAdd);
					System.out.print("fecha parseada ,; " + fechaLimiteOferta);
				} else if (!argsFechaLimOferta.equals(null)
						|| !argsFechaLimOferta.isEmpty()) {
					System.out.print("fecha a parsear : "
							+ argsFechaLimOferta.toString());
					SimpleDateFormat dataini = new SimpleDateFormat(
							"dd/MM/yyyy HH:mm");
					Date dini = dataini.parse(argsFechaLimOferta);
					System.out.print("fecha parseada ,; " + dini);
					fechaLimiteOferta.setTime(dini);
				}

				if (argsFechaLimReserva.startsWith("<")) {
					String[] minutos = argsFechaLimReserva.split(" ");
					int minsToAdd = Integer.valueOf(minutos[4]);
					fechaLimiteReserva.add(Calendar.MINUTE, minsToAdd);
					System.out.print("fecha parseada ,; " + fechaLimiteReserva);
				} else if (!argsFechaLimReserva.equals(null)
						|| !argsFechaLimReserva.isEmpty()) {
					System.out.print("fecha a parsear : "
							+ argsFechaLimReserva.toString());
					SimpleDateFormat datafin = new SimpleDateFormat(
							"dd/MM/yyyy HH:mm");
					Date dfin = datafin.parse(argsFechaLimReserva);
					System.out.print("fecha parseada ,; " + dfin);
					fechaLimiteReserva.setTime(dfin);
				}
				float comision = 0;
				System.out.println(args.length);
				if (args.length > 8) {
					comision = Float.valueOf(args[8]);
				}
				OfertaDto ofertaDto=new OfertaDto();
				ofertaDto.setOfertaId(Long.valueOf(args[1]));
				ofertaDto.setNombreOferta(args[2]);
				ofertaDto.setDescripcionOferta(args[3]);
				ofertaDto.setPrecioRealOferta(Float.valueOf(args[6]));
				ofertaDto.setPrecioDescontadoOferta(Float.valueOf(args[7]));
				ofertaDto.setComisionOferta(comision);
				ofertaDto.setFechaLimiteOferta(fechaLimiteOferta);
				ofertaDto.setFechaLimiteReserva(fechaLimiteReserva);
				ofertaDto.setEstadoOferta("nocambiar");
				
				clientOfertaService.updateOferta(ofertaDto);

				System.out
						.println("Oferta " + args[1] + " updated sucessfully");

			} catch (NumberFormatException | InputValidationException
					| InstanceNotFoundException ex) {
				ex.printStackTrace(System.err);
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-fUR".equalsIgnoreCase(args[0])
				|| "-findUserReservations".equalsIgnoreCase(args[0])) {
			validateArgs(args, 3, new int[] {});

			// [find] OfertaServiceClient -findUserReservations <emailUsuario>
			// <estado>

			try {
				String estado = null;
				if (args.length > 2) {
					estado = args[2];
				}
				List<ReservaDto> reservas = clientOfertaService
						.findReservasByUsuario(args[1], estado);

				System.out.println("emailUsuario " + args[1] + " estado: "
						+ estado);
				System.out.println("Found " + reservas.size()
						+ " reservas(s) with user '" + args[1] + "'");
				if (args.length > 2) {
					System.out.println(" with state: " + estado);
				}
				for (ReservaDto reserva : reservas) {
					System.out.println("Id: " + reserva.getReservaId()
							+ " State: " + reserva.getEstadoReserva()
							+ " Email: " + reserva.getEmailUsuarioReserva()
							+ " Credit card numbr: "
							+ reserva.getTarjetaCreditoReserva()
							+ " from the offer with id: "
							+ reserva.getOfertaId());
				}
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-fUO".equalsIgnoreCase(args[0])
				|| "-findUserOffers".equalsIgnoreCase(args[0])) {
			validateArgs(args, 3, new int[] {});

			// [find] OfertaServiceClient -findUserOffers <emailUsuario>

			try {
				List<ReservaDto> reservas = clientOfertaService
						.findReservasByUsuario(args[1], null);
				List<OfertaDto> ofertas = new ArrayList<>();
				for (ReservaDto reserva : reservas) {
					ofertas.add(clientOfertaService.findOferta(reserva
							.getOfertaId()));
				}

				System.out.println("Found " + ofertas.size()
						+ " oferta(s) with user '" + args[1] + "'");
				if (ofertas.size() == reservas.size()) {
					for (int i = 0; i < ofertas.size(); i++) {
						OfertaDto oferta = ofertas.get(i);
						System.out.println("Id: " + oferta.getOfertaId()
								+ "\n Title: " + oferta.getNombreOferta()
								+ "\n Estado: " + oferta.getEstadoOferta()
								+ "\n Description: "
								+ oferta.getDescripcionOferta()
								+ "\n Regular price: "
								+ oferta.getPrecioRealOferta()
								+ "\n Discounted price: "
								+ oferta.getPrecioDescontadoOferta()
								+ "\n Deadline offer: "
								+ oferta.getFechaLimiteOferta().getTime()
								+ "\n Deadline reservation: "
								+ oferta.getFechaLimiteReserva().getTime()
								+ "\n Likes: "
								+ oferta.getFacebookLikes()
								+ "\n   and reservation number: "
								+ reservas.get(i).getReservaId()+"\n");

					}
				}
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-f".equalsIgnoreCase(args[0])
				|| "-findOffer".equalsIgnoreCase(args[0])) {
			validateArgs(args, 2, new int[] {});

			// [find] OfertaServiceClient -f <offerId>

			try {
				OfertaDto oferta = clientOfertaService.findOferta(Long
						.valueOf(args[1]));
				System.out.println("Found oferta with: ");
				System.out.println(" Id: " + oferta.getOfertaId()
						+ "\n Title: " + oferta.getNombreOferta()
						+ "\n Estado: " + oferta.getEstadoOferta()
						+ "\n Description: " + oferta.getDescripcionOferta()
						+ "\n Regular price: " + oferta.getPrecioRealOferta()
						+ "\n Discounted price: "
						+ oferta.getPrecioDescontadoOferta()
						+ "\n Deadline offer: " + oferta.getFechaLimiteOferta().getTime()
						+ "\n Likes: "
						+ oferta.getFacebookLikes()
						+ "\n Deadline reservation: "
						+ oferta.getFechaLimiteReserva().getTime());
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		}else if ("-fs".equalsIgnoreCase(args[0])
				|| "-findOffers".equalsIgnoreCase(args[0])) {
//			validateArgs(args, 2, new int[] {});

			// [find] OfertaServiceClient -f <offerId>

			try {
				String estado=null;
				String keyword=null;
				Calendar fecha = new GregorianCalendar();
				fecha=null;
				String addToPrint="";
				if(args.length>3){
					estado=args[2];
					keyword=args[1];
					fecha = new GregorianCalendar();
					System.out.print("fecha a parsear : " + args[3].toString());
					SimpleDateFormat dataini = new SimpleDateFormat(
							"dd/MM/yyyy HH:mm");
					Date dini = dataini.parse(args[3]);
					System.out.print("fecha parseada ,; " + dini);
					fecha.setTime(dini);
					addToPrint=" with  keywords: '" + keyword + "' and state: " + estado + " and date: " + fecha.getTime();
				}else if(args.length>2){
					System.out.println(2);
					estado=args[2];	
					keyword=args[1];
					addToPrint=" with  keywords: '" + keyword + "' and state: " + estado ;
				}else if(args.length>1){
					keyword=args[1];	
					addToPrint=" with keywords: " + keyword;
				}
				
				List<OfertaDto> ofertas=clientOfertaService.findOfertas(keyword, estado, fecha);
				
				
				System.out.println("Found " + ofertas.size()
						+ " oferta(s)"
						+ addToPrint);
				for (OfertaDto oferta:ofertas) {
					System.out.println(" Id: " + oferta.getOfertaId()
							+ "\n  Title: " + oferta.getNombreOferta()
							+ "\n  Estado: " + oferta.getEstadoOferta()
							+ "\n  Description: " + oferta.getDescripcionOferta()
							+ "\n  Regular price: " + oferta.getPrecioRealOferta()
							+ "\n  Discounted price: "
							+ oferta.getPrecioDescontadoOferta()
							+ "\n Deadline offer: " + oferta.getFechaLimiteOferta().getTime()
							+ "\n Likes: "
							+ oferta.getFacebookLikes()
							+ "\n Deadline reservation: "
							+ oferta.getFechaLimiteReserva().getTime());
				}
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-fOR".equalsIgnoreCase(args[0])
				|| "-findOfferReservations".equalsIgnoreCase(args[0])) {
			validateArgs(args, 2, new int[] {});

			// [find] OfertaServiceClient -findOfferReservations <offerId>

			try {
				List<ReservaDto> reservas = clientOfertaService
						.findReservasByOferta(Long.valueOf(args[1]));
				System.out.println("Found " + reservas.size()
						+ " reserva(s) belonging to  the offer '" + args[1]
						+ "'" + " with: ");
				for (ReservaDto reserva : reservas) {
					System.out.println("Id: " + reserva.getReservaId()
							+ " User: " + reserva.getEmailUsuarioReserva()
							+ " Estado: " + reserva.getEstadoReserva()
							+ " Credit card number: "
							+ reserva.getTarjetaCreditoReserva());
				}
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-re".equalsIgnoreCase(args[0])
				|| "-reserveOffer".equalsIgnoreCase(args[0])) {
			validateArgs(args, 4, new int[] { 1 });

			// [reserve] OfertaServiceClient -re <movieId> <userId>
			// <creditCardNumber>

			Long reservaId;
			try {
				reservaId = clientOfertaService.reservarOferta(
						Long.parseLong(args[1]), args[2], args[3]);

				System.out.println("Oferta " + args[1]
						+ " purchased sucessfully with reservation number "
						+ reservaId);

			} catch (NumberFormatException | InstanceNotFoundException
					| InputValidationException ex) {
				ex.printStackTrace(System.err);
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

			// } else if ("-g".equalsIgnoreCase(args[0])) {
			// validateArgs(args, 2, new int[] { 1 });
			//
			// // [get] OfertaServiceClient -g <reervaId>
			//
			// try {
			// String movieURL = clientOfertaService.getMovieUrl(Long
			// .parseLong(args[1]));
			//
			// System.out.println("The URL for the reerva " + args[1] + " is "
			// + movieURL);
			// } catch (NumberFormatException | InstanceNotFoundException
			// | reervaExpirationException ex) {
			// ex.printStackTrace(System.err);
			// } catch (Exception ex) {
			// ex.printStackTrace(System.err);
			// }
		} else if ("-c".equalsIgnoreCase(args[0])
				|| "-claimReservation".equalsIgnoreCase(args[0])) {
			validateArgs(args, 3, new int[] { 1 });

			// [reserve] OfertaServiceClient -claimReservation <reservationId>
			// [<userId>]

			Long reservaId;
			try {
				reservaId = clientOfertaService.reclamarOferta(Long
						.parseLong(args[1]));

				System.out.println("The reservation number is " + reservaId
						+ "\n Thanks for your purchase ");

			} catch (NumberFormatException | InstanceNotFoundException ex) {
				ex.printStackTrace(System.err);
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}
		} else if ("-in".equalsIgnoreCase(args[0])
				|| "-invalidateOffer".equalsIgnoreCase(args[0])) {
			validateArgs(args, 3, new int[] { 1 });

			// [reserve] OfertaServiceClient -invalidateOffer <offerId>

			try {
				clientOfertaService.invalidarOferta(Long.parseLong(args[1]));

				System.out.println("The offer with number is " + args[1]
						+ " has been invalidated");

			} catch (NumberFormatException | InstanceNotFoundException ex) {
				ex.printStackTrace(System.err);
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}
		}else{
			printUsageAndExit();
		}

	}

	public static void validateArgs(String[] args, int expectedArgs,
			int[] numericArguments) {
		if (expectedArgs < args.length) {
			System.out.println("problem args length!! \n" + expectedArgs + " "
					+ args.length);
			printUsageAndExit();
		}
		// for (int i = 0; i < numericArguments.length; i++) {
		// int position = numericArguments[i];
		// try {
		// Double.parseDouble(args[position]);
		// } catch (NumberFormatException n) {
		// System.out.println("exception NumberFormatException");
		// printUsageAndExit();
		// } catch(ArrayIndexOutOfBoundsException e){
		//
		// }
		// }
	}

	public static void printUsageAndExit() {
		printUsage();
		System.exit(-1);
	}

	public static void printUsage() {
		System.err
				.println("Usage:\n"
						+ "    [add] OfertaServiceClient -a <nombre> <descripcion> <estado> <priceReal> <priceDescontado> <comision> "
						+ "<fechaLimiteOferta> <fechaLimiteReserva>\n"
						+ "    [remove] OfertaServiceClient -r <ofertaId>\n"
						+ "    [update] OfertaServiceClient -u <nombre> <descripcion> <estado> <priceReal> <priceDescontado> <comision> "
						+ "<fechaLimiteOferta> <fechaLimiteReserva>\n"
						+ "    [find] OfertaServiceClient -f <keywords> <estado> <fecha>\n"
						+ "    [reserve] OfertaServiceClient -re <movieId> <userId> <creditCardNumber>\n"
				// + "    [get]    MovieServiceClient -g <reervaId>\n"
				);
	}
}
