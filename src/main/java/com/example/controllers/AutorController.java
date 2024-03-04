package com.example.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Autor;
import com.example.entities.Libro;
import com.example.exception.ResourceNotFoundException;
import com.example.services.AutorService;
import com.example.services.LibroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;
    private final LibroService libroService;

    // metodo para crear un autor
    @PostMapping("/add")
    public ResponseEntity<Autor> createAutor(@RequestBody Autor autor) {
    Autor a = autorService.save(autor);
    return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    // metodo para sacar los autores por el id del libro
    @GetMapping("/{libroId}/autores")
    public ResponseEntity<List<Autor>> getAllAutoresByLibroId(
        @PathVariable(value = "libroId") Integer libroId) {

        if (!libroService.existById(libroId)) {
            throw new ResourceNotFoundException("Not found libro with id = " + libroId);
        }

        List<Autor> autores = autorService.findAutoresByLibrosId(libroId);
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }

    // te saca los autores por orden alfabetico
    @GetMapping("/all")
    public ResponseEntity<List<Autor>> getAllAutores(
        @RequestParam(required = false) String title) {
        List<Autor> autores = new ArrayList<Autor>();

        if (title == null)
            autorService.findAll().forEach(autores::add);
        else
            autorService.findByNombreContaining(title).forEach(autores::add);

        if (autores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        autores.sort(Comparator.comparing(Autor::getNombre));
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }

    // recuperar un autor por su id
    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAutorById(@PathVariable(value = "id") Integer id) {
        if (!autorService.existById(id)) {
            throw new ResourceNotFoundException("Not found autor with id = " + id);
        }
        Autor autor = autorService.findById(id);
        return new ResponseEntity<>(autor, HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Autor> updateAutor(
        @PathVariable("id") int id,
        @RequestBody Autor autorRequest) {

        if (!autorService.existById(id)) {
            throw new ResourceNotFoundException("Not found autor with id = " + id);
        }
        
        Autor autorUpdate = autorService.findById(id);
        autorUpdate.setNombre(autorRequest.getNombre());

        return new ResponseEntity<>(autorService.save(autorUpdate), HttpStatus.OK);
    }

}
