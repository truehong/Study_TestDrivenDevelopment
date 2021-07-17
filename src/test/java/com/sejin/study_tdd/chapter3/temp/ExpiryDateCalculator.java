package com.sejin.study_tdd.chapter3.temp;

import java.time.LocalDate;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount() / 10_000;
        LocalDate candidateEXP = payData.getBillingDate().plusMonths(1);
        if(payData.getFirstBilingDate() != null) {
            if (payData.getFirstBilingDate().getDayOfMonth() != candidateEXP.getDayOfMonth()) {
                return candidateEXP.withDayOfMonth(payData.getFirstBilingDate().getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
