package com.perpule.plutusintegrationdemo.doTransaction;

import com.perpule.plutusintegrationdemo.Header;

import java.util.List;

public class Response {

    private Header header;
    private com.perpule.plutusintegrationdemo.Response response;
    private DetailResponse detailResponse;


    public Response() {
    }

    public Response(Header header, com.perpule.plutusintegrationdemo.Response response, DetailResponse detailResponse) {
        this.header = header;
        this.response = response;
        this.detailResponse = detailResponse;

    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public com.perpule.plutusintegrationdemo.Response getResponse() {
        return response;
    }

    public void setResponse(com.perpule.plutusintegrationdemo.Response response) {
        this.response = response;
    }

    public DetailResponse getDetailResponse() {
        return detailResponse;
    }

    public void setDetailResponse(DetailResponse detailResponse) {
        this.detailResponse = detailResponse;
    }


}
