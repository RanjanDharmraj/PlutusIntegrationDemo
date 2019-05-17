package com.perpule.plutusintegrationdemo.doTransaction;

import java.util.List;

public class Response {
    private List<Payments> payments;
    private List<AdditionalInfo> additionalInfo;

    public Response(List<Payments> payments, List<AdditionalInfo> additionalInfo) {
        this.payments = payments;
        this.additionalInfo = additionalInfo;
    }

    public List<Payments> getPayments() {
        return payments;
    }

    public void setPayments(List<Payments> payments) {
        this.payments = payments;
    }

    public List<AdditionalInfo> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(List<AdditionalInfo> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
