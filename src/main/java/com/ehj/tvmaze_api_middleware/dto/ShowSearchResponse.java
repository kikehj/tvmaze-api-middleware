package com.ehj.tvmaze_api_middleware.dto;

import java.util.List;

public record ShowSearchResponse( Long id, String name, String channel, String summary, List<String> genres ) {}