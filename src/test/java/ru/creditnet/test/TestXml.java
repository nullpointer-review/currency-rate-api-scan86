package ru.creditnet.test;

import ru.creditnet.test.xml.ValCurs;
import org.junit.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;


/**
 * Created by scan on 05.10.15.
 */
public class TestXml {

    private final static Logger logger = LoggerFactory.getLogger(TestXml.class);

    private static final String SAMPLE_XML = "/cbr.ru.response.xml";

    @Test
    public void check_xml() {

        ValCurs valCurs = null;

        URL url = (new TestXml()).getClass().getResource(SAMPLE_XML);
        logger.info("Test xml file : {}", url.getPath());

        try {
            Unmarshaller unmarshaller = JAXBContext.newInstance(ValCurs.class).createUnmarshaller();
            valCurs = (ValCurs) unmarshaller.unmarshal(new File(url.getFile()));
        } catch (JAXBException ex) {
            logger.error("{}", ex);
        }

        Assert.assertNotNull(valCurs);
        Assert.assertNotNull(valCurs.getValutes());
        Assert.assertNotNull(valCurs.getValutes().get(0).getNominal());
        Assert.assertEquals(17, valCurs.getValutes().size(), 0);

        logger.info(valCurs.toString());

    }

}
