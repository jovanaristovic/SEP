package com.ftn.paypal.dto;

public class ResponseDto {

    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public ResponseDto(){}

    public ResponseDto(String href) {
        this.href = href;
    }
}
