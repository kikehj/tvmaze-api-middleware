package com.ehj.tvmaze_api_middleware.client;

import com.ehj.tvmaze_api_middleware.dto.TvMazeSearchResponse;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class TvMazeClient {

    private final RestClient restClient;

    public TvMazeClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<TvMazeSearchResponse> searchShows(String query) {
        return restClient
                .get()
                .uri( uriBuilder -> uriBuilder.path("/search/shows").queryParam("q", query).build() )
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}