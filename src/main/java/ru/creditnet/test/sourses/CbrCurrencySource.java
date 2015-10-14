package ru.creditnet.test.sourses;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;
import java.util.Map;

/**
 * Created by scan on 09.10.15.
 */
@Component
public class CbrCurrencySource implements Source {

    private UriComponentsBuilder uriBuilder;

    public String getUrlString(String param, String value) {
        return uriBuilder.replaceQueryParam(param, value).toUriString().toString();
    }


    public void setUriBuilder(UriComponentsBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
    }

}
