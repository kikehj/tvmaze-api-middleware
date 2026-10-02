package com.ehj.tvmaze_api_middleware.service;

import com.ehj.tvmaze_api_middleware.dto.CommentRequest;
import com.ehj.tvmaze_api_middleware.model.Comment;
import com.ehj.tvmaze_api_middleware.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void saveComment(CommentRequest request) {

        if (request.rating() < 0 || request.rating() > 5) {
            throw new IllegalArgumentException("El rango debería de ser entre 0 y 5");
        }

        Comment comment = new Comment();

        comment.setShowId(request.showId());
        comment.setComment(request.comment());
        comment.setRating(request.rating());

        commentRepository.save(comment);
    }
}