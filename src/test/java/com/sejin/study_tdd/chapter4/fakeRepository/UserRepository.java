package com.sejin.study_tdd.chapter4.fakeRepository;

public interface UserRepository {
    void save(User user);
    User findById(String id);
}
