package com.sns.comment.service;

import com.sns.comment.domain.Comment;
import com.sns.comment.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentBO {
    private final CommentMapper commentMapper;

    public int addComment(int userId, int postId, String content){
        return commentMapper.insertComment(userId, postId, content);
    }

    public List<Comment> getCommentList(){
        return commentMapper.selectCommentList();
    }

}
