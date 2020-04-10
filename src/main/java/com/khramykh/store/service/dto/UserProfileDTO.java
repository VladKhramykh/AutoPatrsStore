package com.khramykh.store.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO implements Serializable {

    private Long id;
    @Size(min = 1, max = 50)
    private String firstName;
    @Size(max = 50)
    private String lastName;
    @Size(max = 20)
    private String telNumber;
    private String password;
    @Size(max = 255)
    private String address;
    @Email
    private String email;
    private String activationCode;

}
