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

    @Override
    public void likeCommentByID(Long cid) {
        Comment comment = commentMapper.selectById(cid);
        if (comment != null){
            comment.likeOnce();
            commentMapper.updateById(comment);
        } else {
            throw new IllegalStateException("Comment not found with id: " + cid);
        }
    }

    @Override
    public void dislikeCommentByID(Long cid) {
        Comment comment = commentMapper.selectById(cid);
        if (comment != null){
            comment.dislikeOnce();
            commentMapper.updateById(comment);
        } else {
            throw new IllegalStateException("Comment not found with id: " + cid);
        }
    }

    @Override
    public Comment replyToComment(Long cid, String content, Long newsID, Long userID) {
        Comment comment = new Comment(content, newsID, userID);
        comment.setReferenceCommentID(cid);
        commentMapper.insert(comment);
        return comment;
    }
}
