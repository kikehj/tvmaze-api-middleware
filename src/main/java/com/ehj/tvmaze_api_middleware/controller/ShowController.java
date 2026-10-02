package com.ehj.tvmaze_api_middleware.controller;

import com.ehj.tvmaze_api_middleware.dto.ShowSearchResponse;
import com.ehj.tvmaze_api_middleware.service.ShowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ehj.tvmaze_api_middleware.dto.TvMazeSearchResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }
    
    // A- Endpoint search
    @GetMapping("/search")
    public List<ShowSearchResponse> searchShows( @RequestParam("search_query") String searchQuery ) {

        return showService.searchShows(searchQuery);
    }
    
    @GetMapping("/{showId}")
    public TvMazeSearchResponse.TvMazeShow getShowById( @PathVariable Long showId ) {

        return showService.getShowById(showId);
    }
    
    
}