package HttpProject;

import com.fasterxml.jackson.core.type.TypeReference;
import jdk.jfr.ContentType;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class HttpProject {
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException{
        CloseableHttpClient httpClient = HttpClientBuilder.create() //we create class which wii be describe client
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
        HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
        request.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = httpClient.execute(request);//response will be work with GET POST PATCH etc.
        List<TransformationToJson> result = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<TransformationToJson>>() {});

        result.stream()
                .filter(value -> value.getUpvotes() != null && Integer.parseInt(value.getUpvotes()) > 0)
                .forEach(System.out::println);



    }
}
