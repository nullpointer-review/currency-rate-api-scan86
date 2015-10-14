package ru.creditnet.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.creditnet.test.response.RateResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by scan on 13.10.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/integration-test.xml"})
@WebAppConfiguration
public class IntegrationTest {

    private final static Logger logger = LoggerFactory.getLogger(IntegrationTest.class);

    @Autowired
    private ApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        CurrencyEndpoint endpoint = ctx.getBean(CurrencyEndpoint.class);

        mockMvc = MockMvcBuilders.standaloneSetup(endpoint)
                .setViewResolvers(viewResolver)
                .build();
    }

    @org.junit.Test
    public void testGet() throws UnsupportedEncodingException {
        String path = "/test";

        MockHttpServletResponse response = get(path);

        Assert.assertEquals("{\"error\":\"test\"}", response.getContentAsString());
        Assert.assertEquals(200, response.getStatus());
    }

    @org.junit.Test
    public void notFoundGet() throws UnsupportedEncodingException {
        String path = "/helloWorld";

        MockHttpServletResponse response = get(path);

        Assert.assertEquals("", response.getContentAsString());
        Assert.assertEquals(404, response.getStatus());
    }

    @Test
    public void testFindByCode() throws UnsupportedEncodingException {
        String usd = "/api/rate/USD";

        MockHttpServletResponse response = get(usd);

        logger.info(response.getContentAsString());

        RateResponse rr = this.parseResponse(response);

        Assert.assertEquals("USD", rr.getCode());
        Assert.assertEquals("30.9436", rr.getRate().toString());

    }

    @Test
    public void testFindByUnknwonCode() throws UnsupportedEncodingException {
        String path = "/api/rate/XXX";

        MockHttpServletResponse response = get(path);
        RateResponse rr = this.parseResponse(response);

        Assert.assertEquals("Code XXX not found in cbr reply", rr.getError());
        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void testUnknownDate() throws UnsupportedEncodingException {
        String path = "/api/rate/USD/XXX";

        MockHttpServletResponse response = get(path);
        RateResponse rr = this.parseResponse(response);

        Assert.assertEquals("Can't parse date : XXX", rr.getError());
        Assert.assertEquals(404, response.getStatus());
    }

    @Test
    public void testUnknownCodeValidDate() throws UnsupportedEncodingException {
        String path = "/api/rate/XXX/2015-01-01";

        MockHttpServletResponse response = get(path);
        RateResponse rr = this.parseResponse(response);


        Assert.assertEquals("Code XXX not found in cbr reply", rr.getError());
        Assert.assertEquals(200, response.getStatus());

    }


    private MockHttpServletResponse get(String path) {
        logger.info("Do get : {}", path);

        MockHttpServletResponse response = null;
        try {
            response = mockMvc.perform(MockMvcRequestBuilders.get(path)).andReturn().getResponse();
        } catch (Exception e) {
            logger.error("Get Failed : {} : {}", path, e);
        }

        return response;
    }

    private RateResponse parseResponse(MockHttpServletResponse response) {
        ObjectMapper om = new ObjectMapper();
        RateResponse rr = null;
        try {
            rr = om.readValue(response.getContentAsString(), RateResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rr;
    }


}
