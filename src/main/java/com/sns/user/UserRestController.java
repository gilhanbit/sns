package com.sns.user;

import com.sns.user.domain.User;
import com.sns.user.service.UserBO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/sns")
@RestController
public class UserRestController {

    private final UserBO userBO;

    /**
     * 회원가입 API
     * @param loginId
     * @param password
     * @param name
     * @param email
     * @return
     */
    @PostMapping("/sign-up")
    public Map<String, Object> signUp(
            @RequestParam("loginId") String loginId,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("email") String email){

        boolean isResult = userBO.addUser(loginId, password, name, email);

        Map<String, Object> result = new HashMap<>();
        if(isResult){
            result.put("code",200);
            result.put("result","success");
        } else {
            result.put("code", 500);
            result.put("error_message", "회원가입에 실패했습니다.");
        }

        return result;
    }

    /**
     * 중복확인 API
     * @param loginId
     * @return
     */
    @GetMapping("/id-duplicate-id")
    public Map<String, Object> isDuplicateId(
        @RequestParam("loginId") String loginId){

        boolean isDuplicate = userBO.isDuplicateId(loginId);

        Map<String, Object> result = new HashMap<>();
        result.put("code",200);
        result.put("is_duplicate_id",isDuplicate);
        result.put("error_message", "존재하지 않는 사용자입니다.");
        return result;
    }

    /**
     * 로그인 API
     * @param loginId
     * @param password
     * @param request
     * @return
     */
    @PostMapping("/sign-in")
    public Map<String, Object> signIn(
            @RequestParam("loginId") String loginId,
            @RequestParam("password") String password,
            HttpServletRequest request){

        User user =  userBO.getUser(loginId, password);

        Map<String, Object> result = new HashMap<>();
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("userLoginId", user.getLoginId());

            result.put("code", 200);
            result.put("result", "success");
        } else {
            result.put("code", 401);
            result.put("error_message", "존재하지 않는 사용자입니다.");
        }

        return result;
    }
}
