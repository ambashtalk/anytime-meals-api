package com.anytimemeals.bff.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignupRequestBody implements Serializable {
    private String username;
    private String email;
    private String password;
}
