package com.example.sheepopfood.repository;

import com.example.sheepopfood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
;import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);


    Optional<User> findById(Long id);

    User getByEmail(String email);
    List<User> findAll();

}

