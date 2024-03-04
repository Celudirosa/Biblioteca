package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Autor;

@Repository
public interface AutorDao extends JpaRepository<Autor, Integer> {

    // Método para encontrar un autor por su ID
    Optional<Autor> findById(int id);

    // Método para encontrar un autor por su ID (otra versión)
    Autor findAutorById(int id);

    // Método para encontrar autores por el ID de un libro
    List<Autor> findByLibrosId(int libroId);

    // TODO: Este metodo es necesario?
    List<Autor> findByNombreContaining(String nombre);

}
