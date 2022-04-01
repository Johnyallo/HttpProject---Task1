package NasaProject;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InvalidObjectException;

public class Nasa {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)//max time connecting for client
                        .setSocketTimeout(30000)//max time waiting of getting data
                        .setRedirectsEnabled(false) //possibility to follow redirect in answer
                        .build())
                .build();
        HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=w4ivyhIeGeSnJgjfxPmRF87D2kwNqxncbgwUzDJk");
        CloseableHttpResponse response = httpClient.execute(request);
    }
}
