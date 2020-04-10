package com.khramykh.store.service.dto;

import com.khramykh.store.domain.orgs.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Default;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO implements Serializable {
    private Long id;
    @Size(min = 1, max = 50)
    private String firstName;
    @Size(max = 50)
    private String lastName;
    @Email
    private String email;
    private String telNumber;
    @Size(max = 255)
    private String address;
    @Size(min = 3, max = 22)
    private String password;


}
