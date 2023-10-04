package org.niit.userauthenticationapp.feignclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupData {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
}
