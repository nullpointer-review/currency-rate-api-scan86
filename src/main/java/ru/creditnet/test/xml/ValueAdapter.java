package ru.creditnet.test.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by user on 05.10.2015.
 */
public class ValueAdapter extends XmlAdapter<String, BigDecimal> {

    private NumberFormat format = NumberFormat.getInstance(Locale.getDefault());

    @Override
    public BigDecimal unmarshal(String v) throws Exception {

        BigDecimal value = new BigDecimal(v.replace(",", "."));
        return value;

    }

    @Override
    public String marshal(BigDecimal v) throws Exception {
        return null;
    }

}
