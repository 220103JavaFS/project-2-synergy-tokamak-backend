package com.app.satpoint.services;

import com.app.satpoint.models.Comment;
import com.app.satpoint.models.Satellite;
import com.app.satpoint.models.User;
import com.app.satpoint.repos.CommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentDAO commentDAO;
    private SatelliteService satService;
    private UserService userService;


    public CommentService() {
    }

    @Autowired
    public CommentService(CommentDAO commentDAO, SatelliteService satService, UserService userService) {
        this.commentDAO = commentDAO;
        this.satService = satService;
        this.userService = userService;
    }

    public List<Comment> getCommentsForSatellite(int noradId){
        Satellite s = satService.getSatelliteByNoradId(noradId);
        if(s.getSatName() == null){
            return new ArrayList<>();
        }
        Optional<List<Comment>> commentListOptional = commentDAO.findCommentsBySatellite(s);
        if(commentListOptional.isPresent()){
            List<Comment> commentList = commentListOptional.get();
            commentList.forEach(comment -> {
                comment.setSatNoradId(comment.getSatellite().getNoradId());
            });
            return commentList;
        }
        else{
            return new ArrayList<>();
        }

    }


    public List<Comment> getCommentsByUsername(String username){
        User user = userService.getUserByUsername(username);
        if(user.getUsername() == null){
            return new ArrayList<>();
        }

        Optional<List<Comment>> commentListOptional = commentDAO.findCommentsByUser(user);
        if(commentListOptional.isPresent()){
            List<Comment> commentList = commentListOptional.get();
            commentList.forEach(comment -> {
                comment.setSatNoradId(comment.getSatellite().getNoradId());
            });
            return commentList;
        }
        else{
            return new ArrayList<>();
        }
    }


    public List<Comment> addComment(Comment comment){
        try{
            User user = userService.getUserByUsername(comment.getUser().getUsername());
            if(user.getUsername() == null){
                System.out.println("bad username");
                return new ArrayList<>();
            }
            Satellite satellite = satService.getSatelliteByNoradId(comment.getSatellite().getNoradId());
            if(satellite.getSatName() == null){
                System.out.println("satellite MIA");
                return new ArrayList<>();
            }
            comment.setCommentId(0);
            comment.setUser(user);
            comment.setSatellite(satellite);
            commentDAO.save(comment);
            return getCommentsForSatellite(satellite.getNoradId());

        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Comment> addCommentByNoradId(int noradId, int userId, String commentMessage, String date) {
        try{

            User user = userService.getUserByUserId(userId);
            if(user.getUsername() == null){
                return new ArrayList<>();
            }
            Satellite satellite = satService.getSatelliteByNoradId(noradId);
            if(satellite.getSatName() == null){
                return new ArrayList<>();
            }
            Comment comment = new Comment();
            comment.setCommentId(0);
            comment.setUser(user);
            comment.setSatellite(satellite);
            comment.setComment(commentMessage);
            comment.setDate(date);
            commentDAO.save(comment);
            return getCommentsForSatellite(satellite.getNoradId());

        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean deleteComment(int commentId, int userId){
        try{
            Optional<Comment> commentOptional = commentDAO.findById((long)commentId);
            if(commentOptional.isPresent() && userId == commentOptional.get().getUser().getId()){
               commentDAO.delete(commentOptional.get());
               return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
