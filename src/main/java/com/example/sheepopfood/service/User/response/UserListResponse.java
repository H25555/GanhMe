package com.example.sheepopfood.service.User.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserListResponse {
    private long id;
    private String email;
    private String password;
}

