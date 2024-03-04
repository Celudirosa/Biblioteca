package com.example.utilities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.entities.Autor;
import com.example.entities.Libro;
import com.example.services.AutorService;
import com.example.services.LibroService;

@Configuration
public class LoadSampleData {

    @Bean
    public CommandLineRunner saveSampleData(LibroService libroService, AutorService autorService) {

        return data -> {

            // autores
            autorService.save(Autor.builder()
            .nombre("Isabel")
            .build());

            autorService.save(Autor.builder()
            .nombre("Andrea")
            .build());

            // libros
            Libro libro1 = libroService.save(Libro.builder()
                .titulo("El arte de hacerse bocadillos de lomo")
                .date(LocalDate.of(2023, 8, 10))
                .build());

            Libro libro2 = libroService.save(Libro.builder()
                .titulo("Conviertete en Sailor Moon en 3 sencillos pasos")
                .date(LocalDate.of(2023, 9, 10))
                .build());

        };
    }

}
