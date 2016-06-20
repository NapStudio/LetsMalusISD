package es.udc.ws.app.client.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import es.udc.ws.app.client.service.ClientOfertaService;
import es.udc.ws.app.client.service.ClientOfertaServiceFactory;
import es.udc.ws.app.dto.OfertaDto;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class OfertaServiceClient {
	public static void main(String[] args) {

		if (args.length == 0) {
			printUsageAndExit();
		}
		ClientOfertaService clientOfertaService = ClientOfertaServiceFactory
				.getService();
		if ("-a".equalsIgnoreCase(args[0])) {
			validateArgs(args, 5, new int[] { 2, 4 });

			// [add] OfertaServiceClient -a <nombre> <descripcion> <estado>
			// <priceReal> <priceDescontado> <comision>
			// <fechaLimiteOferta> <fechaLimiteReserva>

			// public OfertaDto(Long ofertaId, String nombreOferta,
			// String descripcionOferta, String estadoOferta,
			// float precioRealOferta, float precioDescontadoOferta,
			// float comisionOferta, Calendar fechaLimiteOferta,
			// Calendar fechaLimiteReserva)
			try {
				SimpleDateFormat dataini = new SimpleDateFormat(
						"dd/MM/yyyy HH:mm");
				Date dini = dataini.parse(args[2]);
				Calendar fechaLimiteOferta = new GregorianCalendar();
				fechaLimiteOferta.setTime(dini);

				SimpleDateFormat datafin = new SimpleDateFormat(
						"dd/MM/yyyy HH:mm");
				Date dfin = datafin.parse(args[3]);
				Calendar fechaLimiteReserva = new GregorianCalendar();
				fechaLimiteReserva.setTime(dfin);

				Long ofertaId = clientOfertaService.addOferta(new OfertaDto(
						null, args[1], args[2], args[3],
						Float.valueOf(args[4]), Float.valueOf(args[5]), Float
								.valueOf(args[6]), fechaLimiteOferta,
						fechaLimiteReserva));

				System.out.println("Oferta " + ofertaId
						+ " created sucessfully");

			} catch (NumberFormatException | InputValidationException ex) {
				ex.printStackTrace(System.err);
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-r".equalsIgnoreCase(args[0])) {
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

		} else if ("-u".equalsIgnoreCase(args[0])) {
			validateArgs(args, 6, new int[] { 1, 3, 5 });

			// [update] OfertaServiceClient -u <nombre> <descripcion> <estado>
			// <priceReal> <priceDescontado> <comision>
			// <fechaLimiteOferta> <fechaLimiteReserva>

			try {
				//TODO null si no quieres actualizar
				
				SimpleDateFormat dataini = new SimpleDateFormat(
						"dd/MM/yyyy HH:mm");
				Date dini = dataini.parse(args[2]);
				Calendar fechaLimiteOferta = new GregorianCalendar();
				fechaLimiteOferta.setTime(dini);

				SimpleDateFormat datafin = new SimpleDateFormat(
						"dd/MM/yyyy HH:mm");
				Date dfin = datafin.parse(args[3]);
				Calendar fechaLimiteReserva = new GregorianCalendar();
				fechaLimiteReserva.setTime(dfin);

				clientOfertaService.updateOferta(new OfertaDto(null, args[1],
						args[2], args[3], Float.valueOf(args[4]), Float
								.valueOf(args[5]), Float.valueOf(args[6]),
						fechaLimiteOferta, fechaLimiteReserva));

				System.out
						.println("Oferta " + args[1] + " updated sucessfully");

			} catch (NumberFormatException | InputValidationException
					| InstanceNotFoundException ex) {
				ex.printStackTrace(System.err);
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-f".equalsIgnoreCase(args[0])) {
			validateArgs(args, 2, new int[] {});

			// [find] OfertaServiceClient -f <keywords> <estado> <fecha>

			try {
				SimpleDateFormat dataini = new SimpleDateFormat(
						"dd/MM/yyyy HH:mm");
				Date dini = dataini.parse(args[3]);
				Calendar fechaBusqueda = new GregorianCalendar();
				fechaBusqueda.setTime(dini);
				
				
				List<OfertaDto> ofertas = clientOfertaService
						.findOfertas(args[1], args[2], fechaBusqueda);
				System.out.println("Found " + ofertas.size()
						+ " oferta(s) with keywords '" + args[1] + "'"
						+ " with state: "+ args[2]
						+ "and date: " + args[3]);
				for (int i = 0; i < ofertas.size(); i++) {
					OfertaDto ofertaDto = ofertas.get(i);
					System.out.println("Id: " + ofertaDto.getOfertaId()
							+ " Title: " + ofertaDto.getNombreOferta() + " Estado: "
							+ ofertaDto.getEstadoOferta() + " Description: "
							+ ofertaDto.getDescripcionOferta() + " Price descontado: "
							+ ofertaDto.getPrecioDescontadoOferta());
				}
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

		} else if ("-re".equalsIgnoreCase(args[0])) {
			validateArgs(args, 4, new int[] { 1 });

			// [reserve] OfertaServiceClient -re <movieId> <userId> <creditCardNumber>

			Long reservaId;
			try {
				reservaId = clientOfertaService.reservarOferta(Long.parseLong(args[1]),
						args[2], args[3]);

				System.out.println("Oferta " + args[1]
						+ " purchased sucessfully with reservation number " + reservaId);

			} catch (NumberFormatException | InstanceNotFoundException
					| InputValidationException ex) {
				ex.printStackTrace(System.err);
			} catch (Exception ex) {
				ex.printStackTrace(System.err);
			}

//		} else if ("-g".equalsIgnoreCase(args[0])) {
//			validateArgs(args, 2, new int[] { 1 });
//
//			// [get] OfertaServiceClient -g <saleId>
//
//			try {
//				String movieURL = clientOfertaService.getMovieUrl(Long
//						.parseLong(args[1]));
//
//				System.out.println("The URL for the sale " + args[1] + " is "
//						+ movieURL);
//			} catch (NumberFormatException | InstanceNotFoundException
//					| SaleExpirationException ex) {
//				ex.printStackTrace(System.err);
//			} catch (Exception ex) {
//				ex.printStackTrace(System.err);
//			}
		}

	}

	public static void validateArgs(String[] args, int expectedArgs,
			int[] numericArguments) {
		if (expectedArgs != args.length) {
			printUsageAndExit();
		}
		for (int i = 0; i < numericArguments.length; i++) {
			int position = numericArguments[i];
			try {
				Double.parseDouble(args[position]);
			} catch (NumberFormatException n) {
				printUsageAndExit();
			}
		}
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
//						+ "    [get]    MovieServiceClient -g <saleId>\n"
						);
	}
}
