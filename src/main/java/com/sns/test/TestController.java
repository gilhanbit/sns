package com.sns.test;

import com.sns.post.mapper.PostMapper;
import com.sns.post.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private PostMapper postMapper;

    @ResponseBody
    @GetMapping("/sns-test")
    public String snsTest(){
        return "hello world!";
    }

    @ResponseBody
    @GetMapping("/sns-test2")
    public List<Post> snsTest2(){
        return postMapper.selectListTest();
    }

    @GetMapping("/sns-test3")
    public String snsTest3(){
        return "test/ex01";
    }

    @ResponseBody
    @GetMapping("/sns-test4")
    public Map<String, Object> snsTest4(){
        Map<String, Object> map = new HashMap<>();
        map.put("a",5000);
        map.put("b",5000);
        map.put("c",5000);
        return map;
    }
}
