package com.example.services;

import java.util.List;

import com.example.entities.Autor;

public interface AutorService {

    public Autor findById(int id);
    public List<Autor> findAll();

    public Autor save(Autor autor);

    List<Autor> findAutoresByLibrosId(Integer libroId);
    public boolean existById(Integer autorId);
    public List<Autor> findByNombreContaining(String nombre);
    public void deleteById(int id);

}
