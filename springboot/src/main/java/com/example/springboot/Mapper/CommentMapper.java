package com.example.springboot.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.Entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
