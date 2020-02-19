package com.baizhi.dto;

import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

@Configuration
public class DateDTO implements Serializable {
    private List<Integer> date;

    public List<Integer> getDate() {
        return date;
    }

    public void setDate(List<Integer> date) {
        this.date = date;
    }
}
