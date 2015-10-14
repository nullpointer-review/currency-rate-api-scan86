package ru.creditnet.test.sourses;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by scan on 09.10.15.
 */
public class TestBrokenSourse implements Source {

    private UriComponentsBuilder uriBuilder;

    public TestBrokenSourse() {
        uriBuilder = UriComponentsBuilder.fromUriString("http://127.0.0.1");
    }

    public String getUrlString(String param, String value) {
        return uriBuilder.toUriString().toString();
    }

}
