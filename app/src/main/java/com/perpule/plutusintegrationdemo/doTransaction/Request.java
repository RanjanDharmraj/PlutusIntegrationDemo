package com.perpule.plutusintegrationdemo.doTransaction;

import com.perpule.plutusintegrationdemo.Header;

import java.util.List;

public class Request {

    private Header header;
    private DetailRequest detailRequest;

    public Request() {
    }

    public Request(Header header, DetailRequest detailRequest) {
        this.header = header;
        this.detailRequest = detailRequest;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public DetailRequest getDetailRequest() {
        return detailRequest;
    }

    public void setDetailRequest(DetailRequest detailRequest) {
        this.detailRequest = detailRequest;
    }
}


