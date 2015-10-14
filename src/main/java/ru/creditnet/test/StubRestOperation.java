package ru.creditnet.test;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import ru.creditnet.test.xml.ValCurs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * Created by scan on 05.10.15.
 */
@Component
public class StubRestOperation implements RestOperations {


    public StubRestOperation() {
    }

    public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return null;
    }

    public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {
        return null;
    }

    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        ValCurs valCurs = null;

        URL example = (new StubRestOperation()).getClass().getResource("/cbr.ru.response.xml");

        try {
            Unmarshaller unmarshaller = JAXBContext.newInstance(ValCurs.class).createUnmarshaller();
            valCurs = (ValCurs) unmarshaller.unmarshal(new File(example.getFile()));
        } catch (JAXBException ex) {

        }

        return new ResponseEntity<T>((T) valCurs, HttpStatus.OK);
    }

    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType) throws RestClientException {
        return null;
    }

    public HttpHeaders headForHeaders(String url, Object... uriVariables) throws RestClientException {
        return null;
    }

    public HttpHeaders headForHeaders(String url, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    public HttpHeaders headForHeaders(URI url) throws RestClientException {
        return null;
    }

    public URI postForLocation(String url, Object request, Object... uriVariables) throws RestClientException {
        return null;
    }

    public URI postForLocation(String url, Object request, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    public URI postForLocation(URI url, Object request) throws RestClientException {
        return null;
    }

    public <T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return null;
    }

    public <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    public <T> T postForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
        return null;
    }

    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return null;
    }

    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    public <T> ResponseEntity<T> postForEntity(URI url, Object request, Class<T> responseType) throws RestClientException {
        return null;
    }

    public void put(String url, Object request, Object... uriVariables) throws RestClientException {

    }

    public void put(String url, Object request, Map<String, ?> uriVariables) throws RestClientException {

    }

    public void put(URI url, Object request) throws RestClientException {

    }

    public void delete(String url, Object... uriVariables) throws RestClientException {

    }

    public void delete(String url, Map<String, ?> uriVariables) throws RestClientException {

    }

    public void delete(URI url) throws RestClientException {

    }

    public Set<HttpMethod> optionsForAllow(String url, Object... uriVariables) throws RestClientException {
        return null;
    }

    public Set<HttpMethod> optionsForAllow(String url, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    public Set<HttpMethod> optionsForAllow(URI url) throws RestClientException {
        return null;
    }

    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return null;
    }

    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType) throws RestClientException {
        return null;
    }

    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Object... uriVariables) throws RestClientException {
        return null;
    }

    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType) throws RestClientException {
        return null;
    }

    public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, Class<T> responseType) throws RestClientException {
        return null;
    }

    public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, ParameterizedTypeReference<T> responseType) throws RestClientException {
        return null;
    }

    public <T> T execute(String url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Object... uriVariables) throws RestClientException {
        return null;
    }

    public <T> T execute(String url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Map<String, ?> uriVariables) throws RestClientException {
        return null;
    }

    public <T> T execute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        return null;
    }
}
