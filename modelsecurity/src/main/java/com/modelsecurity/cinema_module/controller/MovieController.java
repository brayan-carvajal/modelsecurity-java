package com.modelsecurity.cinema_module.controller;

import com.modelsecurity.cinema_module.dto.MovieDto;
import com.modelsecurity.cinema_module.entity.Movie;
import com.modelsecurity.cinema_module.mapper.MovieMapper;
import com.modelsecurity.cinema_module.service.interfaces.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final IMovieService movieService;

    // Obtener todas las películas
    @GetMapping
    public ResponseEntity<List<MovieDto>> getAll() {
        List<MovieDto> list = movieService.findAll()
                .stream()
                .map(MovieMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Obtener una película por ID
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getById(@PathVariable Integer id) {
        return movieService.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toDto(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear película
    @PostMapping
    public ResponseEntity<MovieDto> create(@Valid @RequestBody MovieDto dto) {
        Movie entity = MovieMapper.toEntity(dto);
        Movie saved = movieService.save(entity);
        return ResponseEntity.ok(MovieMapper.toDto(saved));
    }

    // Actualizar película
    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable Integer id, @Valid @RequestBody MovieDto dto) {
        return movieService.findById(id)
                .map(existing -> {
                    existing.setTitle(dto.getTitle());
                    existing.setDescription(dto.getDescription());
                    existing.setDuration(dto.getDuration());
                    existing.setReleaseDate(dto.getReleaseDate());
                    existing.setGenre(dto.getGenre());
                    existing.setIsDeleted(dto.getIsDeleted());

                    Movie updated = movieService.update(id, existing);
                    return ResponseEntity.ok(MovieMapper.toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar película
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            movieService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}