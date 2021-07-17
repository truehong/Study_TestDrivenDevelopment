package com.sejin.study_tdd.chapter4.fakeRepository;

import com.sejin.study_tdd.chapter4.WeakPasswordChecker;
import com.sejin.study_tdd.chapter4.WeakPasswordException;
import com.sejin.study_tdd.chapter4.emailSpy.EmailNotifier;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserRegister {
    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;
    private EmailNotifier emailNotifier;

    public void register(String id, String pw, String email) {
        if(passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
        User user = userRepository.findById(id);
        if(user != null){
            throw new DupleIdException();
        }
        userRepository.save(new User(id, pw, email));
        emailNotifier.sendRegisterEmail(email);
    }
}
