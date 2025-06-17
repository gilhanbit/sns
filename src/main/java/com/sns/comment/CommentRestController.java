package com.sns.comment;

import com.sns.comment.service.CommentBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentRestController {
    private final CommentBO commentBO;

    @PostMapping("/create")
    public Map<String, Object> create(
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

    @DeleteMapping("/delete")
    public Map<String, Object> delete(
            @RequestParam("commentId") int commentId,
            HttpSession session
    ) {
        // 로그인 여부 확인
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer)session.getAttribute("userId");
        if (userId == null) {
            result.put("code", 403);
            result.put("error_message", "로그인이 되지 않은 사용자 입니다.");
            return result;
        }

        // 삭제
        commentBO.deleteCommentById(commentId);

        // 응답값
        result.put("code", 200);
        result.put("result", "성공");
        return result;
    }
}
