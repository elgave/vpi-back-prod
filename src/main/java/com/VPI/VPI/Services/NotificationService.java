package com.VPI.VPI.Services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class NotificationService {
    public void sendNotifiction(String token, Integer tiempoE) throws IOException {
        final String apiKey = "AAAAGZ7wIFQ:APA91bFmtwHN9SXJ8p_s5Z3237VaZj9aQ1hcem0fzVK4VYM5DQnQKUdKl3YfsGP7GmwYpEAIxOnaHtyXEhNm2pGkrt49j3gJ5Tuu5RQLDpRN3XBukzzYoQa0YFZpfvzsyzbz0_KqhyFn";
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);

        conn.setDoOutput(true);

        String input = "{\"to\":" + "\""+token+"\"," + "\"notification\" : {\"title\" : \"¡Ya confirmamos tu pedido!\", \"body\":\"Su pedido ha sido confirmado y el restaurante lo está preparando. El tiempo estimado de entrega es " + tiempoE.toString()+ " minutos\"}}";

        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + input);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

// print result
        System.out.println(response.toString());
    }

    public void sendNotifictionRechazoPedido(String token, String motivo) throws IOException {
        final String apiKey = "AAAAGZ7wIFQ:APA91bFmtwHN9SXJ8p_s5Z3237VaZj9aQ1hcem0fzVK4VYM5DQnQKUdKl3YfsGP7GmwYpEAIxOnaHtyXEhNm2pGkrt49j3gJ5Tuu5RQLDpRN3XBukzzYoQa0YFZpfvzsyzbz0_KqhyFn";
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);

        conn.setDoOutput(true);

        String input = "{\"to\":" + "\""+token+"\"," + "\"notification\" : {\"title\" : \"Pedido Rechazado\", \"body\":\"Su pedido ha sido rechazado por el siguiente motivo: " + motivo+ "\"}}";

        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + input);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

// print result
        System.out.println(response.toString());
    }

    public void sendNotifictionRechazoReclamo(String token, String motivo) throws IOException {
        final String apiKey = "AAAAGZ7wIFQ:APA91bFmtwHN9SXJ8p_s5Z3237VaZj9aQ1hcem0fzVK4VYM5DQnQKUdKl3YfsGP7GmwYpEAIxOnaHtyXEhNm2pGkrt49j3gJ5Tuu5RQLDpRN3XBukzzYoQa0YFZpfvzsyzbz0_KqhyFn";
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);

        conn.setDoOutput(true);

        String input = "{\"to\":" + "\""+token+"\"," + "\"notification\" : {\"title\" : \"Reclamo Rechazado\", \"body\":\"Su reclamo al pedido ha sido rechazado por el siguiente motivo: " + motivo+ "\"}}";

        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + input);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

// print result
        System.out.println(response.toString());
    }

    public void sendNotifictionAceptarReclamo(String token, String estado) throws IOException {
        final String apiKey = "AAAAGZ7wIFQ:APA91bFmtwHN9SXJ8p_s5Z3237VaZj9aQ1hcem0fzVK4VYM5DQnQKUdKl3YfsGP7GmwYpEAIxOnaHtyXEhNm2pGkrt49j3gJ5Tuu5RQLDpRN3XBukzzYoQa0YFZpfvzsyzbz0_KqhyFn";
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);

        conn.setDoOutput(true);

        String input = "{\"to\":" + "\""+token+"\"," + "\"notification\" : {\"title\" : \"Reclamo procesado\", \"body\":\"Hemos procesado su reclamo. " + estado+ "\"}}";

        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + input);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

// print result
        System.out.println(response.toString());
    }
}
