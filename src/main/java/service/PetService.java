package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.pet.Pet;
import entity.pet.Status;
import entity.response.ApiResponse;
import exceptions.ResponseException;
import org.apache.commons.codec.binary.Base64;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PetService {
    private final Gson GSON = new Gson();

    public List<Pet> findByStatus(URI uri, Status status) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(uri);
            URI newUri = new URIBuilder(httpGet.getUri())
                    .addParameter("status", status.getName())
                    .build();
            httpGet.setUri(newUri);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String str = EntityUtils.toString(response.getEntity(), "UTF-8");
            Type type = new TypeToken<List<Pet>>() {
            }.getType();
            Object o = GSON.fromJson(str, type);
            return GSON.fromJson(str, type);
        } catch (IOException | ParseException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet findById(URI uri) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(uri);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String str = httpClient.execute(httpGet, responseHandler);
            return GSON.fromJson(str, Pet.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ApiResponse uploadImage(URI uri, String filePath, String fileName) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(uri);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            File file = new File(filePath, fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = fileInputStream.readAllBytes();
            fileInputStream.close();
            multipartEntityBuilder.addTextBody("additionalMetadata", "jpg");
            multipartEntityBuilder.addBinaryBody("file", bytes, ContentType.DEFAULT_BINARY, fileName);
            HttpEntity multipart = multipartEntityBuilder.build();
            httpPost.setEntity(multipart);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String str = httpClient.execute(httpPost, responseHandler);
            return GSON.fromJson(str, ApiResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ApiResponse deleteById(URI uri) {
        String api_key = "special-key";
        try (CloseableHttpClient httpClient = HttpClients.createDefault();) {
            HttpDelete httpDelete = new HttpDelete(uri);
            byte[] encodedAuth = Base64.encodeBase64(api_key.getBytes(StandardCharsets.UTF_8));
            String authHeader = "Basic " + new String(encodedAuth);
            httpDelete.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String str = httpClient.execute(httpDelete, responseHandler);
            return GSON.fromJson(str, ApiResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet addPet(URI uri, Pet pet) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            String petJson = GSON.toJson(pet);
            StringEntity stringEntity = new StringEntity(petJson);
            httpPost.setEntity(stringEntity);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String execute = httpClient.execute(httpPost, responseHandler);
            return GSON.fromJson(execute, Pet.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet updatePet(URI uri, Pet pet) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(uri);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-type", "application/json");
            String petJson = GSON.toJson(pet);
            StringEntity stringEntity = new StringEntity(petJson);
            httpPut.setEntity(stringEntity);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String execute = httpClient.execute(httpPut, responseHandler);
            return GSON.fromJson(execute, Pet.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ApiResponse updatePetWithFormData(URI uri, String namePet, Status statusEnum) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            URI newUri = new URIBuilder(httpPost.getUri())
                    .addParameter("name", namePet)
                    .addParameter("status", statusEnum.getName())
                    .build();
            httpPost.setUri(newUri);
            HttpClientResponseHandler<String> responseHandler = getStringHttpClientResponseHandler();
            String execute = httpClient.execute(httpPost, responseHandler);
            return GSON.fromJson(execute, ApiResponse.class);
        } catch (IOException | URISyntaxException e) {
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
