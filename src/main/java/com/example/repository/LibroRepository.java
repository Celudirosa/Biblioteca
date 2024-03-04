package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

}
