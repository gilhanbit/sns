package com.sns.timeline.service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.comment.entity.CommentEntity;
import com.sns.comment.service.CommentBO;
import com.sns.like.service.LikeBO;
import com.sns.post.domain.Post;
import com.sns.post.entity.PostEntity;
import com.sns.post.service.PostBO;
import com.sns.timeline.domain.CardDTO;
import com.sns.user.entity.UserEntity;
import com.sns.user.service.UserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TimelineBO {

    private final PostBO postBO;
    private final UserBO userBO;
    private final CommentBO commentBO;
    private final LikeBO likeBO;

    public List<CardDTO> generateCardList(Integer userId) {
        List<CardDTO> cardList = new ArrayList<>();

        // 글 목록 List<PostEntity>
        List<PostEntity> postList = postBO.getPostList();

        // 글 반복문 PostEntity, UserEntity 생성 -> CardDTO에 담기 -> put cardList (잊어버리기 쉬우니 주의!!!)
        for(int i = 0; i < postList.size(); i++){
            // 하나의 card
            CardDTO card = new CardDTO();

            // post
            card.setPost(postList.get(i));

            // user (작성자)
            UserEntity user = userBO.getUserEntityById(postList.get(i).getUserId());
            card.setUser(user);

            // 댓글 n개
            List<CommentDTO> commentList = commentBO.generateCommentListByPostId(postList.get(i).getId());
            card.setCommentList(commentList);

            // 좋아요 개수
            card.setLikeCount(likeBO.getCountLike(postList.get(i).getId()));

            // 좋아요 눌렀는지 여부
            card.setFilledLike(likeBO.isFilledLike(postList.get(i).getId(), userId));

            // 마지막 카드 리스트에 담기!!!
            cardList.add(card);
        }

        return cardList;
    }
}
