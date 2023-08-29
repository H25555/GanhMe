package com.example.sheepopfood.service;

import com.example.sheepopfood.model.User;
import com.example.sheepopfood.model.enums.EUserRole;
import com.example.sheepopfood.repository.UserRepository;
import com.example.sheepopfood.service.User.request.RegisterRequest;
import com.example.sheepopfood.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request){
        var user = AppUtils.mapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(EUserRole.ROLE_USER);
        userRepository.save(user);
    }

    public boolean checkUsernameOrEmail(RegisterRequest request, BindingResult result){
        boolean check = false;
        if(userRepository.existsByEmailIgnoreCase(request.getEmail())){
            result.reject("username", null,
                    "đã có một tài khoảng đăng ký trùng với tên người dùng .");
            check = true;
        }

        return check;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("tài khoảng không tồn tại") );
        var role = new ArrayList<SimpleGrantedAuthority>();
        role.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), role);
    }

}
