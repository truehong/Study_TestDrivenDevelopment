package com.sejin.study_tdd.chapter3;

import com.sejin.study_tdd.chapter3.temp.ExpiryDateCalculator;
import com.sejin.study_tdd.chapter3.temp.PayData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationOverTimeImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExpiryDateCalculatorTest {
    @Test
    @DisplayName("1_쉬운것부터_테스트")
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2021, 7, 1))
                        .payAmount(10_000).build(),
                LocalDate.of(2021, 8, 1));
        assertExpiryDate(
                PayData.builder()
                .billingDate(LocalDate.of(2021, 9, 1))
                .payAmount(10_000).build(),
                LocalDate.of(2021, 10, 1));
    }

    @Test
    @DisplayName("2_예외상황_처리")
    void 납부일과_한달_뒤_일자가_같지_않음(){
        assertExpiryDate(
                PayData.builder()
                .billingDate(LocalDate.of(2021,1,31))
                .payAmount(10_000)
                .build(),
                LocalDate.of(2021,2,28)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2021,5,31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2021,6,30)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2021,1,31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2021,2,28)
        );
    }

    @Test
    @DisplayName("2_예외상황_계속_진행")
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        PayData payData = PayData.builder()
                .firstBilingDate(LocalDate.of(2019,1,31))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2019,3,31));

        PayData payData3 = PayData.builder()
                .firstBilingDate(LocalDate.of(2019,5,31))
                .billingDate(LocalDate.of(2019,6,30))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData3, LocalDate.of(2019,7,31));
    }


    @Test
    @DisplayName("다음_테스트_선택_쉬운_테스트")
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        PayData payData = PayData.builder().
                 billingDate(LocalDate.of(2019,3,1))
                .payAmount(20_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2019,5,1));
        PayData payData2 = PayData.builder().
                billingDate(LocalDate.of(2019,3,1))
                .payAmount(30_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2019,6,1));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(payData);
        Assertions.assertEquals(expectedExpiryDate, expiryDate);
    }
}
