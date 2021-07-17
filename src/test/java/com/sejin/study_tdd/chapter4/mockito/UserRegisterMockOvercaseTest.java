package com.sejin.study_tdd.chapter4.mockito;

import com.sejin.study_tdd.chapter4.WeakPasswordChecker;
import com.sejin.study_tdd.chapter4.emailSpy.EmailNotifier;
import com.sejin.study_tdd.chapter4.fakeRepository.MemoryUserRepository;
import com.sejin.study_tdd.chapter4.fakeRepository.User;
import com.sejin.study_tdd.chapter4.fakeRepository.UserRegister;
import com.sejin.study_tdd.chapter4.fakeRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class UserRegisterMockOvercaseTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private UserRepository  mockRepository = Mockito.mock(UserRepository.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setup(){
        userRegister = new UserRegister(mockPasswordChecker,
                mockRepository,
                mockEmailNotifier);
    }

    @Test
    void noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        then(mockRepository).should().save(captor.capture());

        User savedUser = captor.getValue();
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @Test
    void 같은_ID가_없으면_가입(){
        userRegister = new UserRegister(mockPasswordChecker,
                fakeRepository,
                mockEmailNotifier);
        userRegister.register("id", "pw", "email");

        User savedUser = fakeRepository.findById("id");
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @Test
    void 의미없음_그냥쳐보고싶었음() throws IOException {
        Assertions.assertThrows(NoSuchFileException.class, () ->{
            Path path = Paths.get("test");
            List<PayInfo> payInfos = Files.lines(path)
                    .map(line ->{
                        String[] data = line.split(",");
                        return new PayInfo(
                                data[0],data[1],Integer.parseInt(data[2])
                        );
                    }).collect(Collectors.toList());
        });
    }

    @AllArgsConstructor
    public static class PayInfo {
        String a1;
        String a2;
        int a3;
    }
}

