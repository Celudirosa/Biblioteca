package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.AutorDao;
import com.example.dao.LibroDao;
import com.example.entities.Autor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorServiceImpl implements AutorService {

    private final AutorDao autorDao;
    private final LibroDao libroDao;

    @Override
    public Autor findById(int id) {
        return autorDao.findById(id);
    }

    @Override
    public List<Autor> findAll() {
        return autorDao.findAll();
    }

    @Override
    public Autor save(Autor autor) {
        return autorDao.save(autor);
    }

}