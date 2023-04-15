package com.letpay.axies;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentRequest {
    @JsonProperty("CID")
    //company_id
    private Integer CID;
    @JsonProperty("RID")
    //request_id

    private String RID;
    @JsonProperty("CRN")
    //uniqueCustomerReferenceNumber
    private Long CRN;
    @JsonProperty("AMT")
    //amount
    private Long AMT;
    @JsonProperty("VER")
    //version
    private Double VER;
    @JsonProperty("TYP")
    //type
    private String TYP;
    @JsonProperty("CNY")
    //currency
    private String CNY;
    @JsonProperty("RTU")
    //returnUrl
    private String RTU;
    @JsonProperty("PPI")
    //prepopulateValues
    private PPI PPI;
    @JsonProperty("RE1")
    //reserveField
    private String RE1;

    @Override
    public String toString() {
        return
                "CID=" + CID +
                "&RID=" + RID +
                "&CRN=" + CRN +
                "&AMT=" + AMT +
                "&VER=" + VER +
                "&TYP=" + TYP +
                "&CNY=" + CNY +
                "&RTU=" + RTU +
                "&PPI=" + PPI +
                "&RE1=" + RE1 ;
    }
}
