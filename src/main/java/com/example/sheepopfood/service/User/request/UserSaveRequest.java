package com.example.sheepopfood.service.User.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSaveRequest {

    private String username;

    private String password;

    private String email;

    private String role;

}
