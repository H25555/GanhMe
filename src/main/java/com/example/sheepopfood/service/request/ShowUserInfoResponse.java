package com.example.sheepopfood.service.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowUserInfoResponse {
    private String id;
    private String username;
    private String email;
}
