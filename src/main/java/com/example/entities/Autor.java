package com.example.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "autores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name cannot be empty")
    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    }, mappedBy = "autores")
    @JsonIgnore
    @Builder.Default
    private Set<Libro> libros = new HashSet<>();

    // Método para eliminar un libro por su ID
    public void deleteLibroById(Integer libroId) {
        libros.removeIf(libro -> libro.getId() == libroId); // Utiliza removeIf para eliminar el libro del conjunto de libros del autor
        libros.forEach(libro -> libro.getAutores().remove(this)); // Itera sobre los libros restantes y elimina el autor del conjunto de autores de cada libro
    }

    // Método para agregar un libro al autor
    public void addLibro(Libro libro) {
        libros.add(libro); // Agregas el libro al conjunto de libros del autor
        libro.getAutores().add(this); // Agregas el autor al conjunto de autores del libro
    }

}
