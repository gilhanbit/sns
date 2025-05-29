package com.sns.timeline;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sns")
@Controller
public class TimelineController {

    private final PostBO postBO;

    @GetMapping("/timeline")
    public String timeline(HttpSession session){
        List<PostEntity> postList = postBO
        return "timeline/timeline";
    }
}
