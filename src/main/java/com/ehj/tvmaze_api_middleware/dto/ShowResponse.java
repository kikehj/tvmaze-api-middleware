package com.ehj.tvmaze_api_middleware.dto;

import java.util.List;

public record ShowResponse( Long id, String name, TvMazeSearchResponse.TvMazeNetwork network, TvMazeSearchResponse.TvMazeWebChannel webChannel, String summary, List<String> genres, List<CommentResponse> comments ) {}
