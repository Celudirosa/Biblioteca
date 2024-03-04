package com.example.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Libro;
import com.example.exception.ResourceNotFoundException;
import com.example.services.AutorService;
import com.example.services.LibroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/libros")
@RequiredArgsConstructor
public class LibroController {

    private final AutorService autorService;
    private final LibroService libroService;

    // // metodo que te saaca todos los libros con paginacion
    // @GetMapping
    // public ResponseEntity<Page<Libro>> getAllLibros(
    //     @RequestParam(name = "page", required = false) Integer page,
    //     @RequestParam(name = "size", required = false) Integer size,
    //     @RequestParam(name = "sortBy", required = false) Integer sortBy) throws IOException {

    //     Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    //     Page<Libro> libros = libroService.findAll(pageable);

    //     return new ResponseEntity<>(libros, HttpStatus.OK);
    // }

    // metodo que te saca todos los libros por el id del autor
    @GetMapping("/{autorId}/libros")
    public ResponseEntity<List<Libro>> getAllLibrosByAutorId(
        @PathVariable(value = "autorId") Integer autorId) {

        if (!autorService.existById(autorId)) {
            throw new ResourceNotFoundException("Not found autor with id = " + autorId);
        }

        List<Libro> libros = libroService.findLibrosByAutorId(autorId);
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

}
