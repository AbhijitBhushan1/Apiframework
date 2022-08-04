package Utils;

import Enviornment.BaseUrl;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class BaseApi {
    public static Response sendGet(String url, Map<String, String> header) throws IOException {
        String inputline = "";
        Response responseBody=new Response();
        StringBuffer body=new StringBuffer();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String, String> map : header.entrySet())
            httpGet.addHeader(map.getKey(), map.getValue());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        if (response.getEntity() != null) {
            while ((inputline = bufferedReader.readLine()) != null) {
                body.append(inputline);
            }
            bufferedReader.close();
        }
        responseBody.setStatusCode(response.getStatusLine().getStatusCode());
        responseBody.setStatusLine(response.getStatusLine().toString());
        responseBody.setResponseBody(body.toString());
        return responseBody;
    }
}
