package com.sejin.study_tdd.chapter3.temp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class PayData {
    private LocalDate firstBilingDate;
    private LocalDate billingDate;
    private int payAmount;
}
