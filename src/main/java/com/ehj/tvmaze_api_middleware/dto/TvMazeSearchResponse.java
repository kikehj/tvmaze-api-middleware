package com.ehj.tvmaze_api_middleware.dto;

import java.util.List;

public record TvMazeSearchResponse( Double score, TvMazeShow show) {

    public record TvMazeShow( Long id, String name, TvMazeNetwork network, TvMazeWebChannel webChannel, String summary, List<String> genres ) {
    }

    public record TvMazeNetwork( String name ) {
    }

    public record TvMazeWebChannel( String name ) {
    }
}