package ru.creditnet.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import ru.creditnet.test.sourses.Source;
import ru.creditnet.test.xml.ValCurs;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by scan on 01.10.15.
 */
@Component
public class CurrencyProvider {

    private final static Logger logger = LoggerFactory.getLogger(CurrencyProvider.class);

    @Autowired
    private Source currencySource;

    @Autowired
    private SimpleDateFormat internalDate;


    @Resource(name = "cbr")
    private RestOperations restOperations;

    public ValCurs provide(Date date) {

        logger.info("Providing currency for {} date ", internalDate.format(date));

        String requestUrl;

        synchronized (this) {
            String dt = internalDate.format(date);
            requestUrl = currencySource.getUrlString("date_req", dt);
        }
        logger.info("Request url {}", requestUrl);

        ResponseEntity<ValCurs> responseEntity = null;
        try {
            responseEntity = restOperations.getForEntity(requestUrl, ValCurs.class);
        } catch (Exception ex) {
            logger.error("Can't fetch url {} : {}", requestUrl, ex.toString());
            return null;
        }


        return responseEntity.getBody();

    }

    public void setRestOperations(RestOperations restOperations) {
        this.restOperations = restOperations;
    }


}
