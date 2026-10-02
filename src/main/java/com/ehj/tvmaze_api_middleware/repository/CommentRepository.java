package com.ehj.tvmaze_api_middleware.repository;

import com.ehj.tvmaze_api_middleware.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByShowId(Long showId);
}
