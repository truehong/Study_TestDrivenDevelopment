package com.sejin.study_tdd.chapter4.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AnyMatcherTest {

    @Test
    @DisplayName("스텁에 설정한 값과 다른 값을 불러올때에는 null이 발생한다")
    void mockThrowDifferentTypeTest() {
        GameNumGen genMock = mock(GameNumGen.class); // 모의 객체 생성
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String num = genMock.generate(GameLevel.NORMAL);
        assertNull(num);
    }

    @Test
    void anyMatcherTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(any())).willReturn("456");

        String num = genMock.generate(GameLevel.EASY);
        Assertions.assertEquals("456", num);

        String num2 = genMock.generate(GameLevel.EASY);
        Assertions.assertEquals("456", num2);
    }

    @Test
    void mixAnyAndEq() {
        List<String> mockList = mock(List.class);
        given(mockList.set(anyInt(), eq("123"))).willReturn("456");
        String old= mockList.set(5,"123");
        assertEquals("456", old);
    }
}
