package ru.creditnet.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.creditnet.test.CurrencyEndpoint;
import ru.creditnet.test.CurrencyProvider;
import ru.creditnet.test.response.RateResponse;
import ru.creditnet.test.xml.ValCurs;
import ru.creditnet.test.xml.ValueAdapter;
import ru.creditnet.test.xml.Valute;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by user on 08.10.2015.
 */
@Component
public class RateProvider {

    private final static Logger logger = LoggerFactory.getLogger(RateProvider.class);

    @Autowired
    private CurrencyProvider currencyProvider;

    @Autowired
    private SimpleDateFormat externalDate;

    public RateResponse provideByCode(String code) {

        logger.debug("Finding by code {}", code);
        Date dt = new Date();
        ValCurs valCurs = currencyProvider.provide(dt);

        if (valCurs == null) {
            logger.error("Returned null valCurs");
            RateResponse err = new RateResponse();
            err.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE);
            return err;
        }

        logger.debug(valCurs.getValutes().toString());
        return this.constructRateByCode(valCurs, code, dt);
    }

    public RateResponse provideByCodeAndDate(String code, String date) {

        logger.debug("Finding by code and date {} {}", code, date);
        Date dt = null;
        synchronized (this) {
            try {
                dt = externalDate.parse(date);
            } catch (ParseException ex) {
                String msg = "Can't parse date pathVariable";
                logger.error(msg + " : {}", date);
                RateResponse err = new RateResponse(msg);
                err.setHttpStatus(HttpStatus.NOT_FOUND);
                return err;
            }
        }

        ValCurs valCurs = currencyProvider.provide(dt);

        if (valCurs == null) {
            logger.error("Returned null valCurs");
            RateResponse err = new RateResponse();
            err.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE);
            return err;
        }

        return this.constructRateByCode(valCurs, code, dt);
    }

    private RateResponse constructRateByCode(ValCurs valCurs, String code, Date date) {

        for (Valute valute : valCurs.getValutes()) {

            if (valute.getCharCode().equals(code)) {
                BigDecimal nominal = new BigDecimal(valute.getNominal());
                BigDecimal rate = valute.getValue().divide(nominal);
                RateResponse response = new RateResponse(code, rate, date);
                response.setHttpStatus(HttpStatus.OK);
                return response;
            }

        }
        String msg = new String("Code " + code + " not found in cbr reply");
        RateResponse response = new RateResponse(msg);
        response.setHttpStatus(HttpStatus.OK);
        return response;
    }

    private RateResponse construct503Response() {
        RateResponse err = new RateResponse();
        err.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE);
        return err;
    }


}
