package com.example.springboot.Service;

import com.example.springboot.Entity.Comment;
import com.example.springboot.Mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> getAllComments() {
        return commentMapper.selectList(null);
    }

    public void insertNewComment(String content, Long newsID, Long userID) {
        commentMapper.insert(new Comment(content, newsID, userID));
    }

    public void deleteCommentByID(Long cid){
        commentMapper.deleteById(cid);
    }
}
