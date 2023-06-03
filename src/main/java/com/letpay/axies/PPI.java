package com.letpay.axies;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PPI {

    private Long referenceId;
    private String customerName;
    private LocalDateTime localDateTime;
    public String phoneNumber;
    private String email;
    @JsonProperty("amt")
    private Long AMT;

//    PPI=LP110S|anjali|anjalim541@gmail.com|9773865014|100.55

    @Override
    public String toString() {
        return referenceId +"|"+customerName + "|" + localDateTime + "|" +phoneNumber+ "|" + email + "|" + AMT;
    }
}
