package com.example.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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

    // TODO: Esto es necesario?
    // Getter y Setter para el conjunto de libros
    public Set<Libro> getLibros() {
        return libros;
    }

    public void setProductos(Set<Libro> libros) {
        this.libros = libros;
    }

    // Método para eliminar un libro por su ID
    public void deleteLibroById(Integer libroId) {
        Libro libro = this.libros.stream()
            .filter(p -> p.getId() == libroId)
            .findFirst()
            .orElse(null);
        if (libro != null) {
            this.libros.remove(libro); // Eliminas el libro del conjunto de libros del autor
            libro.getAutores().remove(this); // Eliminas el autor del conjunto de autores del libro
        }
    }

    // Método para agregar un libro al autor
    public void addLibro(Libro libro) {
        libros.add(libro); // Agregas el libro al conjunto de libros del autor
        libro.getAutores().add(this); // Agregas el autor al conjunto de autores del libro
    }

}
