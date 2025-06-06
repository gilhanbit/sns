package com.sns.comment.mapper;

import com.sns.comment.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    public int insertComment(
            @Param("userId") int userId,
            @Param("postId") int postId,
            @Param("content") String content);

    public List<Comment> selectCommentList();

}
