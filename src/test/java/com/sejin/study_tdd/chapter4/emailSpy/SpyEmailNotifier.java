package com.sejin.study_tdd.chapter4.emailSpy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class SpyEmailNotifier implements EmailNotifier{
    private boolean called;
    private String email;

    @Override
    public void sendRegisterEmail(String email) {
        this.called = true;
        this.email = email;
    }
}
