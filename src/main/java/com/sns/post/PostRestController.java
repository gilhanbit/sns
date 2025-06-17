package com.sns.post;

import com.sns.post.service.PostBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostRestController {

    private final PostBO postBO;

    @PostMapping("/create")
    public Map<String, Object> create(
            @RequestParam("content") String content,
            @RequestParam(value="file", required=false)MultipartFile file,
            HttpSession session
    ){

        Integer userId = (Integer) session.getAttribute("userId");
        String userLoginId = (String)session.getAttribute("userLoginId");

        Map<String, Object> result = new HashMap<>();
        if (userId == null) {
            result.put("code", 403); // 비로그인 상태 (프론트에서 로그인 시에만 글쓰기가 보이도록 구현했는데 필수 코드인가? -> 혹시 모르니 검증)
            result.put("error_message", "로그인을 해주세요.");
            return result;
        }

        boolean check = postBO.createPost(userId, userLoginId, content, file);

        if(check){
            result.put("code", 200);
            result.put("result", "success");
        } else {
            result.put("code", 500);
            result.put("error_message", "fail");
        }

        return result;
    }

    @DeleteMapping("/delete")
    public Map<String, Object> delete(
            @RequestParam("postId") int postId,
            HttpSession session
    ){

        Integer userId = (Integer) session.getAttribute("userId");

        Map<String, Object> result = new HashMap<>();
        if(userId == null) {
            result.put("code", 403);
            result.put("error_message", "로그인을 해주세요.");
            return result;
        }

        postBO.deleteCardByPostId(postId);

        return result;
    }
}
