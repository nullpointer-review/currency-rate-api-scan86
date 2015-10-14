package ru.creditnet.test;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.creditnet.test.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by scan on 01.10.15.
 */
@RestController
@Component
public class CurrencyEndpoint {

    private final static Logger logger = LoggerFactory.getLogger(CurrencyEndpoint.class);

    @Autowired
    private RateProvider rateProvider;

    @RequestMapping(path = "/api/rate/{code}", method = RequestMethod.GET)
    public ResponseEntity<RateResponse> getByCode(
            @PathVariable String code) {

        if (rateProvider == null) {
            logger.info("rateProvider null");
        }

        Date dt = new Date();
        RateResponse rateResponse = rateProvider.provideByCode(code);
        return new ResponseEntity<RateResponse>(rateResponse, rateResponse.getHttpStatus());

    }

    @RequestMapping(path = "/api/rate/{code}/{date}", method = RequestMethod.GET)
    public ResponseEntity<RateResponse> getByCodeAndDate(
            @PathVariable String code,
            @PathVariable String date) {

        RateResponse rateResponse = rateProvider.provideByCodeAndDate(code, date);
        return new ResponseEntity<RateResponse>(rateResponse, rateResponse.getHttpStatus());

    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ResponseEntity<RateResponse> getByCodeAndDate() {

	    logger.info("Procesing test request...");
        RateResponse rateResponse = new RateResponse("test");
	    rateResponse.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<RateResponse>(rateResponse, rateResponse.getHttpStatus());
    }


}
