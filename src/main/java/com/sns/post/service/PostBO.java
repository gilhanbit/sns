package com.sns.post.service;

import com.sns.comment.service.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.service.LikeBO;
import com.sns.post.domain.Post;
import com.sns.post.entity.PostEntity;
import com.sns.post.mapper.PostMapper;
import com.sns.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostBO {
    //
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final FileManagerService fileManager;
    private final CommentBO commentBO;
    private final LikeBO likeBO;

    public List<PostEntity> getPostList(){
        return postRepository.findAllByOrderByIdDesc();
    }

    public boolean createPost(int userId, String userLoginId, String content, MultipartFile file){

        // content 비필수 -> null 경우, NPE 발생?

        String imagePath = fileManager.uploadFile(file, userLoginId);

        PostEntity post = postRepository.save(PostEntity.builder()
                .userId(userId)
                .content(content)
                .imagePath(imagePath)
                .build());

        return post != null;
    }

    @Transactional
    public void deleteCardByPostId(int postId){
        Post post = postMapper.selectByPostId(postId);

        fileManager.deleteFile(post.getImagePath());

        postMapper.deleteCardByPostId(postId);
        commentBO.deleteCommentByPostId(postId);
        likeBO.deleteLikeByPostId(postId);
    }
}
