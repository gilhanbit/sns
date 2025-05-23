package com.sns.post.mapper;

import com.sns.post.mapper.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface PostMapper {

    public List<Post> selectListTest();
}
