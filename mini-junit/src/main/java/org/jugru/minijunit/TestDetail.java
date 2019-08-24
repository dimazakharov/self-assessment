package org.jugru.minijunit;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class TestDetail {
    private String className;
    private String methodName;
    private boolean succeed;
    private String message;

}
