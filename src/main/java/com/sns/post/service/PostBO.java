package com.sns.post.service;

import com.sns.common.FileManagerService;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostBO {

    private final PostRepository postRepository;
    private final FileManagerService fileManager;

    public List<PostEntity> getPostList(){
        return postRepository.findAllByOrderByIdDesc();
    }

    public boolean writePost(int userId, String userLoginId, String content, MultipartFile file){

        // content 비필수 -> null 경우, NPE 발생?

        String imagePath = fileManager.uploadFile(file, userLoginId);

        PostEntity post = postRepository.save(PostEntity.builder()
                .userId(userId)
                .content(content)
                .imagePath(imagePath)
                .build());

        return post != null;
    }
}
