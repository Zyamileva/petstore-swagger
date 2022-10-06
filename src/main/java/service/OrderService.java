package service;

import com.google.gson.Gson;
import entity.order.Order;
import exceptions.ResponseException;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.net.URI;

public class OrderService {
    private final Gson GSON = new Gson();

    public Order addOrder(URI uri, Order order) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            String orderJson = GSON.toJson(order);
            StringEntity stringEntity = new StringEntity(orderJson);
            httpPost.setEntity(stringEntity);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String execute = httpClient.execute(httpPost, responseHandler);
            return GSON.fromJson(execute, Order.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Order findOrderById(URI uri) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(uri);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String str = httpClient.execute(httpGet, responseHandler);
            return GSON.fromJson(str, Order.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteById(URI uri) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();) {
            HttpDelete httpDelete = new HttpDelete(uri);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            return "status: " + httpClient.execute(httpDelete, responseHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpClientResponseHandler<String> getStringHttpClientResponseHandler() {
        return response -> {
            int status = response.getCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ResponseException("Unexpected response status: " + status);
            }
        };
    }
}