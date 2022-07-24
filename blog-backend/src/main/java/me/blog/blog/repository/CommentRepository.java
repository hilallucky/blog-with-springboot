package me.blog.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.blog.blog.model.domain.Comment;

import java.util.List;
import java.util.Optional;

/**
 * @author Hilal
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    public Optional<List<Comment>> findByPostId(Long postId);

}
