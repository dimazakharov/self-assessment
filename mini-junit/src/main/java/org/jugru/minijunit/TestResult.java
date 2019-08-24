package org.jugru.minijunit;


import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
public class TestResult {
    private int passed = 0;
    private int failed = 0;
    private List<TestDetail> details = new ArrayList<>();

    void incrementPassed(){
        passed++;
    }

    void incrementFailed(){
        failed++;
    }

    void addDetails(TestDetail testDetail){
        details.add(testDetail);
    }
}
