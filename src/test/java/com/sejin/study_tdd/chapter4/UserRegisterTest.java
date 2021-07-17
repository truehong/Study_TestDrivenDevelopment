package com.sejin.study_tdd.chapter4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();

    /**
     * 스텁(Stub) : 구현을 단순한것으로 대체, 테스트에 맞게 단순히 원하는 동작 수행
     * */
    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker);
    }
    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() {
        stubWeakPasswordChecker.setWeak(true); // 암호가 약하다고 응답하도록 설정
        Assertions.assertThrows(WeakPasswordException.class,()->
            userRegister.register("id", "pw", "email"));
    }
}
