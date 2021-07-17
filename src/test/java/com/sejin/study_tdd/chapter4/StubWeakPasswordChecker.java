package com.sejin.study_tdd.chapter4;

public class StubWeakPasswordChecker implements WeakPasswordChecker{
    private boolean weak;
    @Override
    public void setWeak(boolean weak) {
        this.weak = weak;
    }
    @Override
    public boolean checkPasswordWeak(String pw) {
        return weak;
    }
}
