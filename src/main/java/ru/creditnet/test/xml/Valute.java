package ru.creditnet.test.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

/**
 * Created by scan on 05.10.15.
 */
@XmlRootElement(name = "Valute")
public class Valute {

    @XmlAttribute(name = "ID")
    String id;

    @XmlElement(name = "NumCode")
    String numCode;

    @XmlElement(name = "CharCode")
    String charCode;

    @XmlElement(name = "Nominal")
    Long nominal;

    @XmlElement(name = "name")
    String name;

    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(ValueAdapter.class)
    BigDecimal value;

    public String toString() {

        String str = String.format("[%s %s %s %s %s %s ]",
                id, numCode, charCode, String.valueOf(nominal), name, String.valueOf(value));

        return str;

//
//        StringBuilder sb = new StringBuilder();
//        sb.append("[ ");
//        sb.append(id + " " + numCode + " " + charCode + " ");
//        sb.append(nominal + " " + name + " " + value.toString());
//        sb.append("]");
//        return sb.toString();
    }

    public Long getNominal() {
        return nominal;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCharCode() {
        return charCode;
    }

}
