package com.example.services;

import java.util.List;

import com.example.entities.Autor;

public interface AutorService {

    public Autor findById(int id);
    public List<Autor> findAll();

}
