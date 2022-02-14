package com.app.satpoint.repos;

import com.app.satpoint.models.Comment;
import com.app.satpoint.models.Satellite;
import com.app.satpoint.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentDAO  extends JpaRepository<Comment, Long> {

    Optional<List<Comment>> findCommentsBySatellite(Satellite satellite);
    Optional<List<Comment>> findCommentsByUser(User user);

}
