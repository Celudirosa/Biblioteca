package com.example.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    // TODO: 1 POST
    // metodo para crear un autor
    @PostMapping("/{id}/autores")
    public ResponseEntity<Autor> createAutor(@RequestBody Autor autor) {
        Autor a = autorService.save(autor);
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    // TODO: 2 GET no salen los autores de cada libro
        // metodo para sacar los autores por el id del libro
        @GetMapping("/{libroId}/autores")
        public ResponseEntity<Libro> getAllAutoresByLibroId(
            @PathVariable(value = "libroId") Integer libroId) {
    
                Libro libro = libroService.findFirstById(libroId);
                if(libro == null) {
                    throw new ResourceNotFoundException("Not found Libro with Id = " + libroId);
                } else {
    
                    return new ResponseEntity<>(libro, HttpStatus.OK);
                }
        }



    // no saca los autores
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable(value = "id") Integer id) {
        if (!libroService.existById(id)) {
            throw new ResourceNotFoundException("Not found libro with id = " + id);
        }
        Libro libro = libroService.findFirstById(id);
        return new ResponseEntity<>(libro, HttpStatus.OK);

    }

    // Método para eliminar un libro por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLibroById(@PathVariable(value = "id") Integer id) {
        if (!libroService.existById(id)) {
            throw new ResourceNotFoundException("Not found libro with id = " + id);
        }

        libroService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Método para eliminar un libro correspondiente a un autor por su ID
    @DeleteMapping("/{libroId}/autores/{autorId}")
    public ResponseEntity<Map<String, Object>> deleteLibroByAutorIdAndLibroId(
            @PathVariable(value = "autorId") Integer autorId,
            @PathVariable(value = "libroId") Integer libroId) {

        Map<String, Object> responseAsMap = new HashMap<>();
        ResponseEntity<Map<String, Object>> responseEntity = null;

        try {
            Autor autor = autorService.findById(autorId);
            if (autor == null) {
                throw new ResourceNotFoundException("Not found autor with id = " + autorId);
            }

            Libro existLibro = libroService.findFirstById(libroId);
            if (existLibro == null) {
                throw new ResourceNotFoundException("Not found libro with id = " + libroId);
            } else if (!libroId.equals(existLibro.getId())) {
                String errorMessage = "The libro id doesn't match";
                responseAsMap.put("errorMessage", errorMessage);

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if (!autor.getLibros().contains(existLibro)) {
                String errorMessage = "The libro does not belong to the specified autor";
                responseAsMap.put("errorMessage", errorMessage);

                return new ResponseEntity<>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

            autor.deleteLibroById(libroId);
            autorService.save(autor);
            String succesMessage = "The libro has been deleted succesfully";
            responseAsMap.put("succesMessage", succesMessage);
            responseEntity = new ResponseEntity<>(responseAsMap, HttpStatus.OK);

        } catch (DataAccessException e) {
            String error = "Error deleting the libro: " + e.getMostSpecificCause();
                responseAsMap.put("Error", error);
                responseEntity = new ResponseEntity<>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
