package com.calmpet.app_calmpet.repository;

import com.calmpet.app_calmpet.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public boolean existsByUsernameOrEmail(String username, String email);

    public boolean existsById(int id);
}
