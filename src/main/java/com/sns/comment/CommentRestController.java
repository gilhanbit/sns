package com.sns.comment;

import com.sns.comment.service.CommentBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentRestController {
    private final CommentBO commentBO;

    @PostMapping("/write")
    public Map<String, Object> write(
            @RequestParam("postId") int postId,
            @RequestParam("content") String content,
            HttpSession session
    ){
        int userId = (int)session.getAttribute("userId");

        int count = commentBO.addComment(userId, postId, content);

        Map<String, Object> result = new HashMap<>();
        if(count > 0){
            result.put("code",200);
        } else {
            result.put("error_message","댓글 작성에 실패했습니다.");
        }

        return result;
    }
}
