package com.anytimemeals.bff.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@SuperBuilder
public class SignUpResponse implements Serializable {
    private final String message;
    private final int status;
}
