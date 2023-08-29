package com.example.sheepopfood.service;

import com.example.sheepopfood.model.User;
import com.example.sheepopfood.repository.UserRepository;
import com.example.sheepopfood.service.User.request.UserSaveRequest;
import com.example.sheepopfood.service.User.response.UserListResponse;
import com.example.sheepopfood.service.request.ShowUserInfoResponse;
import com.example.sheepopfood.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void UserCreate(UserSaveRequest request) {
        User newUser = AppUtils.mapper.map(request, User.class);
        userRepository.save(newUser);
    }

    public Optional<User> findByUsernameIgnoreCase(String username) {
        return userRepository.findByEmailIgnoreCase(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();

    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.getByEmail(username);
    }

//    public Page<UserListResponse> finAllpaging(Pageable pageable) {
//        return userRepository.findAll(pageable).map(user -> AppUtils.mapper.map(user, UserListResponse.class));
//    }

    public ShowUserInfoResponse showInfoUserClient() {
        User user = getUser();
        if (user != null) {
            return AppUtils.mapper.map(getUser(), ShowUserInfoResponse.class);
        }
        return null;
    }


}