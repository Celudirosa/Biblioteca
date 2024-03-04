package com.example.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
