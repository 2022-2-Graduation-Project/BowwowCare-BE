package com.bowwowcare.sm.advice.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> extends Result {

    private T data;
}
