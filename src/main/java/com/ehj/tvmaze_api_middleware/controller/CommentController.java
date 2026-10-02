package com.ehj.tvmaze_api_middleware.controller;

import com.ehj.tvmaze_api_middleware.dto.CommentRequest;
import com.ehj.tvmaze_api_middleware.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<String> saveComment(
            @RequestBody CommentRequest request) {

        commentService.saveComment(request);

        return ResponseEntity.ok("Comentario guardado correctamente");
    }
}
