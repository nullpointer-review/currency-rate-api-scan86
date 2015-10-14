package ru.creditnet.test.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scan on 01.10.15.
 */
@XmlRootElement(name = "ValCurs")
public class ValCurs {

    @XmlAttribute
    String name;

    @XmlAttribute(name = "Date")
    String date;

    @XmlElement(name = "Valute")
    private List<Valute> valutes = new ArrayList<Valute>();

    public List<Valute> getValutes() {
        return valutes;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        int len = valutes.size();
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append(String.format("%s %s %d ", name, date, len));
        for ( Valute v : getValutes() ) {
            sb.append(v.toString());
        }
        sb.append(" ]");
        return sb.toString();
    }

}
