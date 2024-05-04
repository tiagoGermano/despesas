package com.gtsoftware.despesas;

import lombok.Getter;

@Getter
public class ResponseError {
    private final String message;

    public ResponseError(String message) {
        this.message = message;
    }
}
