package com.anytimemeals.bff.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@SuperBuilder
public class ErrorResponse implements Serializable {
    private final String error;
    private final int status;
    private final String message;
}
