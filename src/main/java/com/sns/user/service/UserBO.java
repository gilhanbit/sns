package com.sns.user.service;

import com.sns.common.SHA256;
import com.sns.user.domain.User;
import com.sns.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserBO {

    private final UserMapper userMapper;

    public boolean addUser(String loginId, String password, String name, String email) {
        String encryptedPassword;
        try {
            encryptedPassword = SHA256.encrypt(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userMapper.insertUser(loginId, encryptedPassword, name, email);
    }

    public User getUser(String loginId, String password){
        String encryptedPassword;
        try {
            encryptedPassword = SHA256.encrypt(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userMapper.selectUser(loginId, encryptedPassword);
    }

    public boolean isDuplicateId(String loginId){
        return userMapper.findUser(loginId) != null;
    }

}
