package com.modelsecurity.cinema_module.service.impl;

import com.modelsecurity.cinema_module.entity.Movie;
import com.modelsecurity.cinema_module.repository.MovieRepository;
import com.modelsecurity.cinema_module.service.interfaces.IMovieService;
import com.modelsecurity.security_module.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements IMovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        super(movieRepository);
        this.movieRepository = movieRepository;
    }
}