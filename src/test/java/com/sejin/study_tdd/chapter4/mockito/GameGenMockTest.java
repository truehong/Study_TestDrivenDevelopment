package com.sejin.study_tdd.chapter4.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

public class GameGenMockTest {
    @Test
    @DisplayName("BDDMockito.given()을 이용한 스텁 설정")
    void mockTest() {
        /*
         * -BDDMockito.given()
         * 모의객체의 스텁을 구성한다.
         * 모의 객체의 메서트가 특정 값을 리턴하도록 설정
         * */
        GameNumGen genMock = mock(GameNumGen.class); // 모의 객체 생성
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String num = genMock.generate(GameLevel.EASY);
        assertEquals("123", num);
    }

    @Test
    @DisplayName("특정 타입의 입셉션을 발생 하도록 스텁 설정")
    void mockThrowTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(null)).willThrow(IllegalArgumentException.class);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> genMock.generate(null));
    }

    @Test
    @DisplayName("BDDMockito.willThrow()메서드는 발생할 익셉션이나 익셉션 객체를 인자로 받는다")
    void voidMethodWillThrowTest() {
        List<String> mockList = mock(List.class);
        willThrow(UnsupportedOperationException.class)
                .given(mockList) // given, 인자로 전달받은 모의객체 자신을 리턴한다.
                .clear(); // 입셉션을 바랭시킬 메서드를 호출 한다.
        assertThrows(UnsupportedOperationException.class,
                ()-> mockList.clear());
    }





}
