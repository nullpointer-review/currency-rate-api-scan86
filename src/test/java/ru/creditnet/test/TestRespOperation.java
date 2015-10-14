package ru.creditnet.test;

import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestOperations;
import ru.creditnet.test.xml.ValCurs;

import javax.annotation.Resource;

/**
 * Created by scan on 13.10.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans-test.xml"})
public class TestRespOperation {

    @Autowired
    private ApplicationContext ctx;

    private final static Logger logger = LoggerFactory.getLogger(TestRespOperation.class);

    @org.junit.Test
    public void testStub() {
        String url = "http://null";

        RestOperations restOperations  = (RestOperations) ctx.getBean("cbrStub");

        ResponseEntity<ValCurs> responseEntity = restOperations.getForEntity(url, ValCurs.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getBody());

        ValCurs valCurs = responseEntity.getBody();

        Assert.assertNotNull(valCurs);
        Assert.assertNotNull(valCurs.getValutes());
        Assert.assertNotNull(valCurs.getValutes().get(0).getNominal());
        Assert.assertEquals(17, valCurs.getValutes().size(), 0);

    }

    @Test
    public void testReal() {

        String url = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=02/03/2002";

        RestOperations restOperations  = (RestOperations) ctx.getBean("cbr");

        ResponseEntity<ValCurs> responseEntity = restOperations.getForEntity(url, ValCurs.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getBody());

        ValCurs valCurs = responseEntity.getBody();

        Assert.assertNotNull(valCurs);
        Assert.assertNotNull(valCurs.getValutes());
        Assert.assertNotNull(valCurs.getValutes().get(0).getNominal());
        Assert.assertEquals(17, valCurs.getValutes().size(), 0);

    }



}
