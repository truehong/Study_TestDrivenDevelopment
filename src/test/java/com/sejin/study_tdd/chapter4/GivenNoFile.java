package com.sejin.study_tdd.chapter4;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;

public class GivenNoFile {

    @Test
    @Disabled
    void noDataFile_then_Exception(){
//        givenNoFile("badpath.txt");
//        File data = new File("badpath.txt");
//        Assertions.assertThrows(IllegalArgumentException.class, ()-> MathUtils.su)
    }

    private void givenNoFile(String path) {
        File file = new File(path);
        if(file.exists()) {
            boolean deleted = file.delete();
            if(!deleted)
                throw new RuntimeException("fail givenNoFile" + path);
        }
    }

}
