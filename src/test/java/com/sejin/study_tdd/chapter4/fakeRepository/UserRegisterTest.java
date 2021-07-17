package com.sejin.study_tdd.chapter4.fakeRepository;

import com.sejin.study_tdd.chapter4.StubWeakPasswordChecker;
import com.sejin.study_tdd.chapter4.emailSpy.SpyEmailNotifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, fakeRepository,spyEmailNotifier);
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    void dupleExists() {
        // 이미 같은 ID 존재하는 상황 만들기
        fakeRepository.save(new User("id", "pw1", "email@email.com"));

        Assertions.assertThrows(DupleIdException.class, ()-> {
                   userRegister.register("id", "pw2", "email");
        });
    }

    @DisplayName("같은 ID가 있으면 성공함")
    @Test
    void noDupId_RegisterSuccess(){
        userRegister.register("id", "pw", "email");

        User savedUser = fakeRepository.findById("id"); //가입 결과 확인
        Assertions.assertEquals("id", savedUser.getId());
        Assertions.assertEquals("email", savedUser.getEmail());
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        final String email = "email@email.com";
        userRegister.register("id,", "pw", email);
        Assertions.assertTrue(spyEmailNotifier.isCalled());
        Assertions.assertEquals(email, spyEmailNotifier.getEmail());
    }

}
