package com.ehj.tvmaze_api_middleware.model;

import com.ehj.tvmaze_api_middleware.dto.TvMazeSearchResponse;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "shows")
public class Show {

    @Id
    private Long id;

    private String name;

    private TvMazeSearchResponse.TvMazeNetwork network;

    private TvMazeSearchResponse.TvMazeWebChannel webChannel;

    private String summary;

    private List<String> genres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TvMazeSearchResponse.TvMazeNetwork getNetwork() {
        return network;
    }

    public void setNetwork(TvMazeSearchResponse.TvMazeNetwork network) {
        this.network = network;
    }

    public TvMazeSearchResponse.TvMazeWebChannel getWebChannel() {
        return webChannel;
    }

    public void setWebChannel(TvMazeSearchResponse.TvMazeWebChannel webChannel) {
        this.webChannel = webChannel;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}