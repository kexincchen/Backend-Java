package com.example.springboot.Service;

import com.example.springboot.Entity.Comment;
import com.example.springboot.Mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();

    Comment insertNewComment(String content, Long newsID, Long userID);

    void deleteCommentByID(Long cid);

    void likeCommentByID(Long cid);

    void dislikeCommentByID(Long cid);

    Comment replyToComment(Long cid, String content, Long newsID, Long userID);
}
