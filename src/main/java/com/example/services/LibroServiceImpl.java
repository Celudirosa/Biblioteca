package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.AutorDao;
import com.example.dao.LibroDao;
import com.example.entities.Libro;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroDao libroDao;
    private final AutorDao autorDao;

    @Override
    public List<Libro> findById(int idAutor) {
        return libroDao.findByAutores(autorDao.findById(idAutor));
    }

    @Override
    public List<Libro> findLibrosByAutorId(int idAutor) {
        return libroDao.findLibrosByAutoresId(idAutor);
    }

    @Override
    public List<Libro> findAll() {
        return libroDao.findAll();
    }

    @Override
    public Libro save(Libro libro) {
        return libroDao.save(libro);
    }

}