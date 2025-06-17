package com.sns.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

    public int selectLikeCountByUserIdPostId(
            @Param("postId") int postId,
            @Param("userId") int userId);

    public void delLike(
            @Param("postId") int postId,
            @Param("userId") int userId);

    public void addLike(
            @Param("postId") int postId,
            @Param("userId") int userId);

    public int selectCountLike(int postId);

    public void deleteLikeByPostId(int postId);
}
