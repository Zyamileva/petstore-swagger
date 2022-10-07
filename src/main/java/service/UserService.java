package service;

import com.google.gson.Gson;
import entity.response.ApiResponse;
import entity.user.User;
import exceptions.ResponseException;
import org.apache.commons.codec.binary.Base64;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.CredentialsProvider;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final Gson GSON = new Gson();

    public ApiResponse createWithList(URI uri, List<User> users) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            String userJson = GSON.toJson(users);
            StringEntity stringEntity = new StringEntity(userJson);
            httpPost.setEntity(stringEntity);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String execute = httpClient.execute(httpPost, responseHandler);
            return GSON.fromJson(execute, ApiResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ApiResponse createWithArray(URI uri, ArrayList<User> users) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            String userJson = GSON.toJson(users);
            StringEntity stringEntity = new StringEntity(userJson);
            httpPost.setEntity(stringEntity);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String execute = httpClient.execute(httpPost, responseHandler);
            return GSON.fromJson(execute, ApiResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User findUserByUserName(URI uri) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(uri);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String str = httpClient.execute(httpGet, responseHandler);
            return GSON.fromJson(str, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ApiResponse addUser(URI uri, User user) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            String userJson = GSON.toJson(user);
            StringEntity stringEntity = new StringEntity(userJson);
            httpPost.setEntity(stringEntity);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String execute = httpClient.execute(httpPost, responseHandler);
            return GSON.fromJson(execute, ApiResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ApiResponse updateUserByUserName(URI uri, User updateUser) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(uri);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-type", "application/json");
            String updateUserJson = GSON.toJson(updateUser);
            StringEntity stringEntity = new StringEntity(updateUserJson);
            httpPut.setEntity(stringEntity);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String execute = httpClient.execute(httpPut, responseHandler);
            return GSON.fromJson(execute, ApiResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ApiResponse deleteByName(URI uri) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();) {
            HttpDelete httpDelete = new HttpDelete(uri);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String str = httpClient.execute(httpDelete, responseHandler);
            return GSON.fromJson(str, ApiResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void loginUser(URI uri, String login, String password) throws IOException {
      //  CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(uri);

        BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        char[] encodedPath = (password.toCharArray());
basicCredentialsProvider.setCredentials(new AuthScope("petstore.swagger.io",80),new UsernamePasswordCredentials(login,
        encodedPath));
        CloseableHttpClient build = HttpClients.custom().setDefaultCredentialsProvider(basicCredentialsProvider).build();
        CloseableHttpResponse execute = build.execute(httpGet);

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