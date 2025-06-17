package com.sns.comment.service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.entity.UserEntity;
import com.sns.user.service.UserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentBO {
    private final CommentMapper commentMapper;
    private final UserBO userBO;

    public int addComment(int userId, int postId, String content){
        return commentMapper.insertComment(userId, postId, content);
    }

    public List<CommentDTO> generateCommentListByPostId(int postId){
        List<CommentDTO> commentDTOList = new ArrayList<>();

        List<Comment> commentList = commentMapper.selectCommentList(postId);

        for(Comment comment : commentList){
            CommentDTO commentDTO = new CommentDTO();

            commentDTO.setComment(comment);

            commentDTO.setUser(userBO.getUserEntityById(comment.getUserId()));

            commentDTOList.add(commentDTO);
        }

        return commentDTOList;
    }

    public void deleteCommentById(int commentId) {
        commentMapper.deleteCommentById(commentId);
    }

    public void deleteCommentByPostId(int postId) {
        commentMapper.deleteCommentByPostId(postId);
    }
}
