package com.app.satpoint.controllers;

import com.app.satpoint.models.Comment;
import com.app.satpoint.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController() {
    }

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/noradId/{noradId}")
    public ResponseEntity<List<Comment>> getCommentsForSatellite(@PathVariable("noradId") int noradId){
        List<Comment> commentList = commentService.getCommentsForSatellite(noradId);
        if(commentList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(commentList);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<Comment>> getCommentsByUsername(@PathVariable("username") String username){
        List<Comment> commentList = commentService.getCommentsByUsername(username);
        if(commentList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(commentList);
    }

    @PostMapping()
    public ResponseEntity<List<Comment>> addComment(@RequestBody Comment comment){
        List<Comment> commentList = commentService.addComment(comment);
        if(commentList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().body(commentList);
    }
}
