package com.baizhi.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Error implements Serializable {
    private String errno;
    private String errorMsg;

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
