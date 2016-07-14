package es.udc.ws.app.model.facebook;

import java.io.IOException;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;

import es.udc.ws.app.model.oferta.Oferta;

public class FacebookService {

	String BASE_URL_FACEBOOK = "https://graph.facebook.com/v2.6";
	String NAME_PAGE = "ofertas.isd";
	String TOKEN = "EAAOpf24fsfEBAAC2KhIJIGmlTynE04BIwiJXhTpAv0dz6nFguOeLoZCT26khA5po8SGeiedGdZCInMpDCBQfse7ckDx6H9XnZBrVAdya7bR2tb2Dv12ZASYOUM7diQ222BooVfcMdhfEgq3miXxbLogCFEuZAyIy8rW76GDn6MqgwWw8SlSIC";

	public String publicarOferta(Oferta oferta) throws ClientProtocolException,
			IOException {
		System.out.println("facebook entrando en publicar..");
		String message = "Oferta!! " + oferta.getNombreOferta().toString()
				+ "    Descripcion :"
				+ oferta.getDescripcionOferta().toString()
				+ "  Reservala hasta : "
				+ oferta.getFechaLimiteOferta().getTime() + "    al precio de "
				+ oferta.getPrecioDescontadoOferta();
		message = message.replaceAll("  ", " ");
		message = message.replaceAll(" ", "%20");
		message = message.replaceAll("·", "-");
		System.out.println(message);
		try {
			System.out.println("facebook entrando en try..");
			HttpResponse response = Request
					.Post(BASE_URL_FACEBOOK + "/" + NAME_PAGE
							+ "/feed?message='" + message + "'&access_token="
							+ TOKEN).execute().returnResponse();
			System.out.println(response.getEntity());
			String jsonString = EntityUtils.toString(response.getEntity());
			JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonString);
			System.out.println("json" + json.toString());
			String id="";
			try {
				id = json.getString("id");
			} catch (JSONException e) {
				System.out.println("JSON exception");
			}
			return id;
		} catch (ClientProtocolException e) {
			System.out.println("Errorfacebook 1" + e);
		} catch (IOException e) {
			System.out.println("Errorfacebook 2" + e);
		}
		return message;

	}

	// "https://graph.facebook.com/v2.6/1646294162355960_1646311522354224?access_token=EAAOpf24fsfEBAB42qoL30f2xyeeBQvO0PEj0p5UhUaxu1figincb27u8N84SrN0ngzesrGprCKmzyKP76J0mvhmsMrja35ohyV3IuTzc5VZADDL9ZAYQpl07fO96iwtaCCATH5Xyti9jwJU5y6cNaYr962UdBZCjZCk6AYYZBnGzLQ7RSiCJV"

	public String actualizarOferta(Oferta oferta)
			throws ClientProtocolException, IOException {
		System.out.println(oferta.toStringSin());
		borrarOferta(oferta.getFacebookId());
		return publicarOferta(oferta);
	}

	public void borrarOferta(String ofertaId) throws ClientProtocolException,
			IOException {

		System.out.println("idFb: " + ofertaId);
		// "https://graph.facebook.com/v2.6/1646294162355960_1646311522354224?access_token=EAAOpf24fsfEBAAC2KhIJIGmlTynE04BIwiJXhTpAv0dz6nFguOeLoZCT26khA5po8SGeiedGdZCInMpDCBQfse7ckDx6H9XnZBrVAdya7bR2tb2Dv12ZASYOUM7diQ222BooVfcMdhfEgq3miXxbLogCFEuZAyIy8rW76GDn6MqgwWw8SlSIC"
		try {
			HttpResponse response = Request
					.Delete(BASE_URL_FACEBOOK + "/" + ofertaId
							+ "?access_token=" + TOKEN).execute()
					.returnResponse();
			System.out.println("borrando..."
					+ EntityUtils.toString(response.getEntity()));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		}

	}

	public int getOfertaLikes(String ofertaId) throws ClientProtocolException,
			IOException {
		// https://graph.facebook.com/v2.6/1646294162355960_1646329665685743/likes?summary=1&access_token=EAAOpf24fsfEBAAC2KhIJIGmlTynE04BIwiJXhTpAv0dz6nFguOeLoZCT26khA5po8SGeiedGdZCInMpDCBQfse7ckDx6H9XnZBrVAdya7bR2tb2Dv12ZASYOUM7diQ222BooVfcMdhfEgq3miXxbLogCFEuZAyIy8rW76GDn6MqgwWw8SlSIC

		HttpResponse response;
		try {
			response = Request
					.Get(BASE_URL_FACEBOOK + "/" + ofertaId
							+ "/likes?summary=1" + "&access_token=" + TOKEN)
					.execute().returnResponse();

			String jsonString = EntityUtils.toString(response.getEntity());
			JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonString);
			System.out.println("json" + json.toString());
			JSONObject likesJSON = json.getJSONObject("summary");
			System.out.println(likesJSON);
			int likes = 0;
			try {
				likes = likesJSON.getInt("total_count");
			} catch (JSONException e) {
				System.out.println("JSON exception");
			}

			return likes;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		}

	}
}
