package com.example.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Autor;
import com.example.services.AutorService;
import com.example.services.LibroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;
    private final LibroService libroService;

    @PostMapping("/autores")
    public ResponseEntity<Autor> createAutor(@RequestBody Autor autor) {
    Autor a = autorService.save(autor);
    return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    @GetMapping("/autores/{libroId}/autores")
    public ResponseEntity<List<Autor>> getAllAutoresByLibroId(
        @PathVariable(value = "libroId") Integer libroId) {

        if (!libroService.existById(libroId)) {
            throw new ResourceNotFoundException("Not found libro with id = " + libroId);
        }
        List<Autor> autores = autorService.findAutoresByLibrosId(libroId);
        return new ResponseEntity<>(autores, HttpStatus.OK);
    } 

}
