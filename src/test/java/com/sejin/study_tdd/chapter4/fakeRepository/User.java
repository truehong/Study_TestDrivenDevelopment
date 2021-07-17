package com.sejin.study_tdd.chapter4.fakeRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private String id;
    private String password;
    private String email;
}
