package com.sns.user.mapper;

import com.sns.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {

    public boolean insertUser(
            @Param("loginId") String loginId,
            @Param("password") String encryptedPassword,
            @Param("name") String name,
            @Param("email") String email);

    public User selectUser(
            @Param("loginId") String loginId,
            @Param("password") String encryptedPassword);

    public User findUser(String loginId);
}
