package com.ehj.tvmaze_api_middleware.dto;

public record CommentRequest( Long showId, String comment, Integer rating ) { }
