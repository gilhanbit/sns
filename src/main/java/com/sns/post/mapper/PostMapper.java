package com.sns.post.mapper;

import com.sns.post.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    public List<Post> selectListTest();
}
