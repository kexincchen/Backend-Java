package com.example.springboot.Controller;

import com.example.springboot.Entity.Comment;
import com.example.springboot.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<String> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        System.out.println(comments);
        return ResponseEntity.ok(comments.toString());
    }

    @PostMapping("/add")
    public ResponseEntity<String> insertNewComment(@RequestParam String content, @RequestParam Long newsID, @RequestParam Long userID) {
        Comment comment = commentService.insertNewComment(content, newsID, userID);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment added successfully. \nID: " + comment.getCommentid());
    }

    @DeleteMapping("/{cid}")
    public ResponseEntity<String> deleteCommentByID(@PathVariable Long cid) {
        commentService.deleteCommentByID(cid);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
