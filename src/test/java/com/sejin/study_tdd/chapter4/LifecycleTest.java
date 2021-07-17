package com.sejin.study_tdd.chapter4;

import org.assertj.core.api.filter.FilterOperator;
import org.junit.jupiter.api.*;

public class LifecycleTest {

    public LifecycleTest() {
        System.out.println("new LifecycleTest");
    }

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
    }

    @Test
    @Disabled
    void a() {
        System.out.println("A");
    }

    @Test
    @Disabled
    void c() {
        System.out.println("C");
    }

    @Test
    @DisplayName("DISPLAY_TEST")
    void b() {
        System.out.println("B");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }
}
