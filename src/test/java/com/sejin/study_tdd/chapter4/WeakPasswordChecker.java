package com.sejin.study_tdd.chapter4;

public interface WeakPasswordChecker {
    void setWeak(boolean weak);
    boolean checkPasswordWeak(String pw);
}
