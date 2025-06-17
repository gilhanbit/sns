package com.sns.like.service;

import com.sns.like.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeBO {

    private final LikeMapper likeMapper;

    public void toggle(int postId, int userId){
        // select으로 먼저 확인 (like 개수)
        if(likeMapper.selectLikeCountByUserIdPostId(postId, userId) > 0){
            // like 있을 경우 -> 삭제
            likeMapper.delLike(postId, userId);
        } else {
            // like 없을 경우 -> 추가
            likeMapper.addLike(postId, userId);
        }
    }

    // i: postId
    // o: like 수
    public int getCountLike(int postId){
        return likeMapper.selectCountLike(postId);
    }

    // i: postId, userId
    // o: boolean
    public boolean isFilledLike(int postId, Integer userId){
        // 1) login x -> false
        if(userId==null){
            return false;
        }

        // 2) login & like x -> false
        // 3) login & like o -> true
        return likeMapper.selectLikeCountByUserIdPostId(postId, userId) > 0;
    }

    public void deleteLikeByPostId(int postId){
        likeMapper.deleteLikeByPostId(postId);
    }
}
