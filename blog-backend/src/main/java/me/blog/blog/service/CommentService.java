package me.blog.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.blog.blog.common.Exception.BadRequestException;
import me.blog.blog.model.domain.Comment;
import me.blog.blog.model.domain.Post;
import me.blog.blog.model.domain.User;
import me.blog.blog.model.dto.CommentDto;
import me.blog.blog.model.dto.PostDto;
import me.blog.blog.repository.CommentRepository;
import me.blog.blog.repository.PostRepository;
import me.blog.blog.security.SecurityUtil;
import me.blog.blog.security.service.CustomUserDetails;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Hilal
 */
@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public Optional<Comment> findForId(Long id) {
        return commentRepository.findById(id);
    }

    public Optional<Post> findPostForId(Long id) {
        return postRepository.findById(id);
    }

    public Optional<List<Comment>> findCommentsByPostId(Long id) {
        return commentRepository.findByPostId(id);
    }

    public CommentDto registerComment(CommentDto commentDto, CustomUserDetails customUserDetails) {
        Optional<Post> postForId = this.findPostForId(commentDto.getPostId());
        if (postForId.isPresent()) {
            Comment newComment = new Comment();
            newComment.setBody(commentDto.getBody());
            newComment.setPost(postForId.get());
            newComment.setUser(new User(customUserDetails.getId(), customUserDetails.getName()));
            return new CommentDto(commentRepository.saveAndFlush(newComment));
        } else {
            throw new BadRequestException("Not exist post.");
        }
    }

    public Optional<CommentDto> editPost(CommentDto editCommentDto) {
        return this.findForId(editCommentDto.getId())
                .map(comment -> {
                    comment.setBody(editCommentDto.getBody());
                    return comment;
                })
                .map(CommentDto::new);
    }

    public void deletePost(Long id) {
        commentRepository.findById(id).ifPresent(comment -> {
            commentRepository.delete(comment);
        });
    }
}
