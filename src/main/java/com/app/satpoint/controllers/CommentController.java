package com.app.satpoint.controllers;

import com.app.satpoint.models.Comment;
import com.app.satpoint.models.CommentDTO;
import com.app.satpoint.services.CommentService;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
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


    //uses comment class to create new comment
    @PostMapping()
    public ResponseEntity<List<Comment>> addComment(@RequestBody Comment comment) {
        List<Comment> commentList = commentService.addComment(comment);
        if (commentList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().body(commentList);
    }

    //uses commentDTO to create a new comment
    @PostMapping("/new")
    public ResponseEntity<List<Comment>> addCommentByNoradId(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        int userId = 0;
        try {
            userId = Integer.parseInt((String) request.getSession(false).getAttribute("userId"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

        List<Comment> commentList = commentService.addCommentByNoradId(commentDTO.getNoradId(),
                userId, commentDTO.getMessage(), commentDTO.getDate());
        if(commentList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().body(commentList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable("id") int commentId, HttpServletRequest request){
        int userId = 0;
        try {
            userId = Integer.parseInt((String) request.getSession(false).getAttribute("userId"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

        if(commentService.deleteComment(commentId, userId)){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
}
