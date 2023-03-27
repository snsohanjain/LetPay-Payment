package com.letpay.axies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PPI {

    private String referenceId;
    private String customerName;
    private LocalDate localDate;
    public String phoneNumber;
    private String email;
    private Long AMT;

//    PPI=LP110S|anjali|anjalim541@gmail.com|9773865014|100.55

    public static Map<String, Object> toMap(PPI ppi) {
        Map<String, Object> map = new HashMap<>();
        map.put("customerName", ppi.getCustomerName());
        map.put("phoneNumber", ppi.getPhoneNumber());
        map.put("email", ppi.getEmail());
        map.put("AMT", ppi.getAMT());
        return map;
    }


    @Override
    public String toString() {
        return referenceId +"|"+customerName + "|" + localDate + "|" +phoneNumber+ "|" + email + "|" + AMT;
    }
}
