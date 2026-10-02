package com.ehj.tvmaze_api_middleware.repository;

import com.ehj.tvmaze_api_middleware.model.Show;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowRepository extends MongoRepository<Show, Long> {
}
