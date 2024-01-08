package com.example.springboot.ServiceImpl;

import com.example.springboot.Entity.Comment;
import com.example.springboot.Mapper.CommentMapper;
import com.example.springboot.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> getAllComments() {
        return commentMapper.selectList(null);
    }

    public Comment insertNewComment(String content, Long newsID, Long userID) {
        Comment comment = new Comment(content, newsID, userID);
        commentMapper.insert(comment);
        return comment;
    }

    public void deleteCommentByID(Long cid){
        commentMapper.deleteById(cid);
    }
}
