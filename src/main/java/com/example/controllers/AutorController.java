package com.example.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Autor;
import com.example.services.AutorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @PostMapping("/autors")
    public ResponseEntity<Autor> createAutor(@RequestBody Autor autor) {
    Autor a = autorService.save(autor);
    return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

}
