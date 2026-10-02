package com.ehj.tvmaze_api_middleware.service;

import com.ehj.tvmaze_api_middleware.client.TvMazeClient;
import com.ehj.tvmaze_api_middleware.dto.ShowSearchResponse;
import com.ehj.tvmaze_api_middleware.dto.TvMazeSearchResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    private final TvMazeClient tvMazeClient;

    public ShowService( TvMazeClient tvMazeClient ) {
        this.tvMazeClient = tvMazeClient;
    }

    public List<ShowSearchResponse> searchShows( String query ) {

        List<TvMazeSearchResponse> tvMazeShows = tvMazeClient.searchShows( query );

        return tvMazeShows.stream().map(this::toSearchResponse).toList();
    }
    
    public TvMazeSearchResponse.TvMazeShow getShowById(Long showId) {
        return tvMazeClient.getShowById(showId);
    }

    private ShowSearchResponse toSearchResponse( TvMazeSearchResponse tvMazeResponse) {

        TvMazeSearchResponse.TvMazeShow show = tvMazeResponse.show();

        return new ShowSearchResponse( show.id(), show.name(), getChannel(show), show.summary(), show.genres() );
    }

    private String getChannel( TvMazeSearchResponse.TvMazeShow show ) {

        if ( show.network() != null ) {
            return show.network().name();
        }

        if ( show.webChannel() != null ) {
            return show.webChannel().name();
        }

        return null;
    }
}