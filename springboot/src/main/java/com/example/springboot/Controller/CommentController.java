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
        try {
            List<Comment> comments = commentService.getAllComments();
            System.out.println(comments);
            return ResponseEntity.ok(comments.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting all comments");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> insertNewComment(@RequestParam String content, @RequestParam Long newsID, @RequestParam Long userID) {
        try {
            Comment comment = commentService.insertNewComment(content, newsID, userID);
            return ResponseEntity.status(HttpStatus.CREATED).body("Comment added successfully. \nID: " + comment.getCommentid());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding comment: " + e.getMessage());
        }
    }

    @DeleteMapping("/{cid}")
    public ResponseEntity<String> deleteCommentByID(@PathVariable Long cid) {
        try {
            commentService.deleteCommentByID(cid);
            return ResponseEntity.ok("Comment deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting comment: " + e.getMessage());
        }
    }
}
