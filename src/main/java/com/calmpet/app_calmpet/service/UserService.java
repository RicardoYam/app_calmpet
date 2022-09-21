package com.calmpet.app_calmpet.service;

import com.calmpet.app_calmpet.entity.UserEntity;
import com.calmpet.app_calmpet.model.User;
import com.calmpet.app_calmpet.repository.UserRepository;
import com.calmpet.app_calmpet.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Result getAllUsers(UserEntity userEntity) {
        try {
            List<UserEntity> users = userRepository.findAll();
            List<User> customUsers = new ArrayList<>();
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            users.stream().forEach(e -> {
                User temp = new User();
                BeanUtils.copyProperties(e, temp);
                customUsers.add(temp);
            });
            for (User customUser : customUsers) {
                if (customUser.getUsername().equals(user.getUsername()) || customUser.getEmail().equals(user.getEmail())) {
                    if (customUser.getPassword().equals(user.getPassword())) {
                        return Result.success(customUser);
                    }
                    return Result.fail(404, "Password Incorrect");
                }
            }
            return Result.fail(404, "Please Sign Up");
        } catch (Exception e) {
            throw e;
        }
    }

    public Result addUser(UserEntity userEntity) {
        try {
            // 前端要校验
            if (!userRepository.existsByUsernameOrEmail(userEntity.getUsername(), userEntity.getEmail())) {
                userRepository.save(userEntity);
                return Result.success(null);
            } else {
                return Result.fail(404, "User already exists in database");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Result removeUser(UserEntity userEntity) {
        try {
            // 从token remove
            if (userRepository.existsByUsernameOrEmail(userEntity.getUsername(), userEntity.getEmail())) {
                // 没id
                userRepository.delete(userEntity);
                return Result.success(null);
            } else {
                return Result.fail(404, "User dose not exists in database");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Result updateUser(UserEntity userEntity) {
        try {
            // 用token
            if (userRepository.existsById(userEntity.getId())) {
                userRepository.save(userEntity);
                return getAllUsers(userEntity);
            } else {
                return Result.fail(404, "User dose not exists in database");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
