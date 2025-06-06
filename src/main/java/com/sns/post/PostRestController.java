package com.sns.post;

import com.sns.post.service.PostBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostRestController {

    private final PostBO postBO;

    @PostMapping("/write")
    public Map<String, Object> write(
            @RequestParam("content") String content,
            @RequestParam(value="file", required=false)MultipartFile file,
            HttpSession session
            ){

        int userId = (int)session.getAttribute("userId");
        String userLoginId = (String)session.getAttribute("userLoginId");

        Map<String, Object> result = new HashMap<>();
//        if (userId == null) {
//            result.put("code", 403); // 비로그인 상태 (프론트에서 로그인 시에만 글쓰기가 보이도록 구현했는데 필수 코드인가?)
//            result.put("error_message", "로그인을 해주세요.");
//            return result;
//        }

        boolean check = postBO.writePost(userId, userLoginId, content, file);

        if(check){
            result.put("code", 200);
            result.put("result", "success");
        } else {
            result.put("code", 500);
            result.put("error_message", "fail");
        }
        return result;
    }
}
