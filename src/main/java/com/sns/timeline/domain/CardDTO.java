package com.sns.timeline.domain;

import com.sns.comment.domain.CommentDTO;
import com.sns.like.service.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;
import lombok.Data;
import lombok.ToString;

import java.util.List;

// 글 1개, 댓글 n개, 좋아요 n개, 글쓴이 정보
@ToString
@Data
public class CardDTO {

    private PostEntity post;
    private UserEntity user;
    private List<CommentDTO> commentList;
    private int likeCount;
    private boolean filledLike;
}
