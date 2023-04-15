package com.letpay.axies;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PaymentResponse {
    @BsonId
    @JsonProperty("bnr")
    private String BRN;
    @JsonProperty("stc")
    private Integer STC;
    @JsonProperty("rmk")
    private String RMK;
    @JsonProperty("trn")
    private String TRN;
    @JsonProperty("tet")
    private LocalDateTime TET;
    @JsonProperty("pmd")
    private String PMD;
    @JsonProperty("rid")
    private  String RID;
    @JsonProperty("ver")
    private Double VER;
    @JsonProperty("cid")
    private Integer CID;
    @JsonProperty("typ")
    private String TYP;
    @JsonProperty("crn")
    private Long CRN;
    @JsonProperty("cny")
    private String CNY;
    @JsonProperty("amt")
    private Long AMT;

    public PaymentResponse() {
    }
}
