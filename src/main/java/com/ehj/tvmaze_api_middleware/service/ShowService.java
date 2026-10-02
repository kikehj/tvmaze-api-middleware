package com.ehj.tvmaze_api_middleware.service;

import com.ehj.tvmaze_api_middleware.client.TvMazeClient;
import com.ehj.tvmaze_api_middleware.dto.CommentResponse;
import com.ehj.tvmaze_api_middleware.dto.ShowSearchResponse;
import com.ehj.tvmaze_api_middleware.dto.TvMazeSearchResponse;
import com.ehj.tvmaze_api_middleware.model.Show;
import com.ehj.tvmaze_api_middleware.repository.CommentRepository;
import com.ehj.tvmaze_api_middleware.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import com.ehj.tvmaze_api_middleware.dto.ShowResponse;

@Service
public class ShowService {

    private final TvMazeClient tvMazeClient;
    private final ShowRepository showRepository;
    private final CommentRepository commentRepository;

    public ShowService( TvMazeClient tvMazeClient, ShowRepository showRepository, CommentRepository commentRepository) {

        this.tvMazeClient = tvMazeClient;
        this.showRepository = showRepository;
        this.commentRepository = commentRepository;
    }

    public List<ShowSearchResponse> searchShows( String query ) {

        List<TvMazeSearchResponse> tvMazeShows = tvMazeClient.searchShows( query );

        return tvMazeShows.stream().map(this::toSearchResponse).toList();
    }

    private ShowSearchResponse toSearchResponse( TvMazeSearchResponse tvMazeResponse ) {

        TvMazeSearchResponse.TvMazeShow show = tvMazeResponse.show();
        
        List<CommentResponse> comments =
                commentRepository.findByShowId(show.id())
                        .stream()
                        .map(comment -> new CommentResponse(
                                comment.getComment(),
                                comment.getRating()
                        ))
                        .toList();

        return new ShowSearchResponse( show.id(), show.name(), getChannel(show), show.summary(), show.genres(), comments );
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
    
    public ShowResponse getShowById(Long showId) {

        Show show = showRepository.findById(showId).orElse(null);

        if (show == null) {

            TvMazeSearchResponse.TvMazeShow tvMazeShow =
                    tvMazeClient.getShowById(showId);

            show = toShow(tvMazeShow);

            showRepository.save(show);
        }

        List<CommentResponse> comments =
                commentRepository.findByShowId(showId)
                        .stream()
                        .map(comment -> new CommentResponse(
                                comment.getComment(),
                                comment.getRating()
                        ))
                        .toList();

        return new ShowResponse(
                show.getId(),
                show.getName(),
                show.getNetwork(),
                show.getWebChannel(),
                show.getSummary(),
                show.getGenres(),
                comments
        );
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

}