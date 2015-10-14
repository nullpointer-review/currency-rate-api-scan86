package ru.creditnet.test.response;


import com.fasterxml.jackson.annotation.*;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by scan on 02.10.15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect
public class RateResponse {

    @JsonProperty
    private String code;

    @JsonProperty
    private BigDecimal rate;

    @JsonProperty
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "MSK")
    private Date date;

    @JsonProperty
    private String error;

    @JsonIgnore
    private HttpStatus httpStatus;

    public RateResponse() {
    }

    public RateResponse(String error) {
        this.error = error;
    }

    public RateResponse(String code, BigDecimal rate, Date date) {
        this.code = code;
        this.rate = rate;
        this.date = date;
    }

    public boolean hasErrors() {
        return (error == null) ? true : false;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public String getCode() {
        return code;
    }

    public String getError() {
        return error;
    }
}
