package com.sejin.study_tdd.chapter2.passwordStrengh;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if(s == null || s.isEmpty()) return PasswordStrength.INVALID;
        int meetCount = getMetCriteriaCounts(s);
        if(meetCount <= 1) return PasswordStrength.WEAK;
        if(meetCount == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingUppercaseCriteria(String s) {
        for(char ch: s.toCharArray()){
            if(Character.isUpperCase(ch)){
                return  true;
            }
        }
        return false;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        for(char ch : s.toCharArray()) {
            if(ch >= '0' && ch <= '9'){
                return true;
            }
        }
        return false;
    }

    private int getMetCriteriaCounts(String s) {
        int meetCount = 0;
        if(s.length() >=8) meetCount++;
        if(meetsContainingNumberCriteria(s)) meetCount++;
        if(meetsContainingUppercaseCriteria(s)) meetCount++;
        return meetCount;
    }
}
