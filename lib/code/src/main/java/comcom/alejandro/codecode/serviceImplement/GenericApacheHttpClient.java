

package com.alejandro.code.serviceImplement;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
public class GenericApacheHttpClient {

    private static final String USER_AGENT = "Mozilla/5.0";
    private String GET_URL = "";
    private String POST_URL = "";
/*
 * Por ahora a esta clase hay que codificar el return especifco para cada metodo
 * esto es una clase de ayuda y no de funcionabilidad como tal
 * */


    public <T> void send(String envio, String url, T t) {
        if (envio.equals("GET")) {
            try {
                GET_URL = url;
                this.sendGET();
            } catch (IOException e) {
                 e.printStackTrace();
            }
        } else if (envio.equals("POST")) {
            try {
                    POST_URL = url;
                    this.sendPOST(t);
              } catch (IOException e) {
                e.printStackTrace();
               }
            }
        }



    private void sendGET() throws IOException {
        String inputLine;
        HttpGet httpGet = new HttpGet(GET_URL);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        httpGet.addHeader("User-Agent", USER_AGENT); //header
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
    // cambiar por un logger
    System.out.println("GET Response Status:: " + httpResponse.getStatusLine().getStatusCode());
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {  response.append(inputLine);  }
        reader.close();
    // print result
    System.out.println("Respuesta del get: " + response.toString()); // cambiar por un logger
        httpClient.close();
    }





    private <T> void sendPOST(T t) throws IOException {
        Gson gson = new Gson();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(POST_URL);
        httpPost.addHeader("User-Agent", USER_AGENT); //header
        httpPost.addHeader("Content-type", "application/json"); //header
        StringEntity postingString = new StringEntity(gson.toJson(t));//gson.tojson() converts your pojo to json
        httpPost.setEntity(postingString);
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
    // cambiar por un logger
    System.out.println("POST Response Status:: " + httpResponse.getStatusLine().getStatusCode());
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
        response.append(inputLine);
      }
        reader.close();
   // print result
   System.out.println("respuesta Post:" + response.toString()); // cambiar por un logger
        httpClient.close();
    }



}
