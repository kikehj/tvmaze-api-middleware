package com.ehj.tvmaze_api_middleware.service;

import com.ehj.tvmaze_api_middleware.client.TvMazeClient;
import com.ehj.tvmaze_api_middleware.dto.ShowSearchResponse;
import com.ehj.tvmaze_api_middleware.dto.TvMazeSearchResponse;
import com.ehj.tvmaze_api_middleware.model.Show;
import com.ehj.tvmaze_api_middleware.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    private final TvMazeClient tvMazeClient;
    private final ShowRepository showRepository;

    public ShowService( TvMazeClient tvMazeClient, ShowRepository showRepository) {

        this.tvMazeClient = tvMazeClient;
        this.showRepository = showRepository;
    }

    public List<ShowSearchResponse> searchShows( String query ) {

        List<TvMazeSearchResponse> tvMazeShows = tvMazeClient.searchShows( query );

        return tvMazeShows.stream().map(this::toSearchResponse).toList();
    }

    private ShowSearchResponse toSearchResponse( TvMazeSearchResponse tvMazeResponse ) {

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
    
    public TvMazeSearchResponse.TvMazeShow getShowById(Long showId) {

        Show show = showRepository.findById(showId).orElse(null);

        if (show != null) {

            return toTvMazeShow(show);
        }

        TvMazeSearchResponse.TvMazeShow tvMazeShow = tvMazeClient.getShowById(showId);

        Show showToSave = toShow(tvMazeShow);

        showRepository.save(showToSave);

        return tvMazeShow;
    }

    private Show toShow( TvMazeSearchResponse.TvMazeShow show ) {

        Show mongoShow = new Show();

        mongoShow.setId(show.id());
        mongoShow.setName(show.name());
        mongoShow.setNetwork(show.network());
        mongoShow.setWebChannel(show.webChannel());
        mongoShow.setSummary(show.summary());
        mongoShow.setGenres(show.genres());

        return mongoShow;
    }

    private TvMazeSearchResponse.TvMazeShow toTvMazeShow( Show show ) {

        return new TvMazeSearchResponse.TvMazeShow( show.getId(), show.getName(), show.getNetwork(), show.getWebChannel(), show.getSummary(), show.getGenres() );
    }
}