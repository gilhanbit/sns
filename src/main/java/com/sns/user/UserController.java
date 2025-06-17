package com.sns.user;

import com.sns.user.service.UserBO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/sns")
@Controller
public class UserController {

    @Autowired
    private UserBO userBO;

    /**
     * 회원가입 화면
     * @return
     */
    @GetMapping("/sign-up")
    public String signUp(){
        return "user/signUp";
    }

    /**
     * 로그인 화면
     * @return
     */
    @GetMapping("/sign-in")
    public String signIn(){
        return "user/signIn";
    }

    /**
     * 로그아웃
     * @param session
     * @return
     */
    @GetMapping("/sign-out")
    public String signOut(HttpSession session) {
        session.removeAttribute("userId");
        session.removeAttribute("userName");
        session.removeAttribute("userLoginId");
        return "redirect:/timeline";
    }

}
