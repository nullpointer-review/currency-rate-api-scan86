package ru.creditnet.test;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.creditnet.test.sourses.Source;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by scan on 13.10.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans-test.xml"})
public class Test2 {

    private final static Logger logger = LoggerFactory.getLogger(Test2.class);
    @Autowired
    private ApplicationContext ctx;

    @org.junit.Test
    public void testCbrSource() {
        Source src = (Source) ctx.getBean("cbrCurrencySource");
        Assert.assertEquals("http://www.cbr.ru/scripts/XML_daily.asp?key=val", src.getUrlString("key", "val"));
    }

    @org.junit.Test
    public void testInternalDateFormat() {
        SimpleDateFormat internalDate = (SimpleDateFormat) ctx.getBean("internalDate");

        Long time = 1444755505966L; // 13/10/2015
        Assert.assertEquals("13/10/2015", internalDate.format(new Date(time)));
    }

    @org.junit.Test
    public void testExternalDateFormat() {
        SimpleDateFormat internalDate = (SimpleDateFormat) ctx.getBean("externalDate");

        String date = "2015-10-13";
        Date dt = null;
        try {
            dt = internalDate.parse(date);
        } catch (ParseException e) {
            logger.info("Failed parse {} : {}", date, e);
        }
        Assert.assertEquals(1444694400000L, dt.getTime()); //
    }

}
